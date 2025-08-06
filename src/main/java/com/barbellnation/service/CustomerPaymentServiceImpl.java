package com.barbellnation.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.barbellnation.custom_exceptions.ResourceNotFoundException;
import com.barbellnation.dao.CustomerDao;
import com.barbellnation.dao.PaymentDao;
import com.barbellnation.dto.PaymentReqDTO;
import com.barbellnation.dto.PaymentRespDTO;
import com.barbellnation.entities.Customer;
import com.barbellnation.entities.CustomerPayment;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerPaymentServiceImpl implements CustomerPaymentService {

    private final PaymentDao paymentDao;
    private final CustomerDao customerDao;
    private final ModelMapper modelMapper;

    @Value("${razorpay.key.id}")
    private String razorPayKey;

    @Value("${razorpay.secret.key}")
    private String razorPaySecret;

    public CustomerPaymentServiceImpl(PaymentDao paymentDao, CustomerDao customerDao, ModelMapper modelMapper) {
        this.paymentDao = paymentDao;
        this.customerDao = customerDao;
        this.modelMapper = modelMapper;
    }

    private RazorpayClient getRazorpayClient() throws Exception {
        return new RazorpayClient(razorPayKey, razorPaySecret);
    }
    @Override
    public List<PaymentRespDTO> getAllPayments() {
        return paymentDao.findAll().stream()
            .map(payment -> modelMapper.map(payment, PaymentRespDTO.class))
            .collect(Collectors.toList());
    } 
    @Override
    public CustomerPayment createCustomerPayment(PaymentReqDTO c) throws Exception {
        Customer customer = customerDao.findById(c.getCid())
            .orElseThrow(() -> new ResourceNotFoundException("Invalid customer id!"));

        CustomerPayment cp = modelMapper.map(c, CustomerPayment.class);
        customer.addPayment(cp);

        JSONObject orderReq = new JSONObject();
        orderReq.put("amount", cp.getAmount() * 100);
        orderReq.put("currency", "INR");
        orderReq.put("receipt", "rcpt_" + System.currentTimeMillis());

        RazorpayClient client = getRazorpayClient();
        Order razorPayOrder = client.orders.create(orderReq);

        cp.setRazorpayPaymentId(razorPayOrder.get("id"));
        cp.setPaymentStatus(razorPayOrder.get("status"));

        paymentDao.save(cp);
        return cp;
    }

    @Override
    public CustomerPayment updatePayment(Map<String, String> responsePayLoad) {
        String razorPayPaymentId = responsePayLoad.get("razorPay_order_id");

        CustomerPayment p = paymentDao.findByRazorpayPaymentId(razorPayPaymentId)
            .orElseThrow(() -> new ResourceNotFoundException("Payment not found for Razorpay ID: " + razorPayPaymentId));

        p.setPaymentStatus("payment_completed");
        return paymentDao.save(p);
    }
}