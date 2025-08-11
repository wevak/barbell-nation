package com.barbellnation.service;

import java.math.BigDecimal;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barbellnation.dao.CustomerDao;
import com.barbellnation.dao.NewCustomerPaymentDao;
import com.barbellnation.dao.PackageDao;
import com.barbellnation.dto.PaymentReqDTO;
import com.barbellnation.dto.PaymentResponseDTO;
import com.barbellnation.entities.Customer;
import com.barbellnation.entities.NewCustomerPayment;
import com.barbellnation.entities.Package;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.Utils;

@Service
public class CustomerPaymentServiceImpl implements CustomerPaymentService {

    @Autowired
    private PackageDao packageRepository;
    @Autowired
    private CustomerDao customerRepository;
    @Autowired
    private NewCustomerPaymentDao newCustomerPaymentRepository;

    private final RazorpayClient razorpayClient;
    private final String razorpayKeySecret;

    public CustomerPaymentServiceImpl(
            @Value("${razorpay.key.id}") String keyId,
            @Value("${razorpay.key.secret}") String keySecret) throws Exception {
        this.razorpayClient = new RazorpayClient(keyId, keySecret);
        this.razorpayKeySecret = keySecret;
    }

    @Override
    public Order createRazorpayOrder(Long packageId) throws Exception {
        Package plan = packageRepository.findById(packageId)
                .orElseThrow(() -> new RuntimeException("Package not found with ID: " + packageId));

        int amountInPaise = plan.getPrice().multiply(new BigDecimal(100)).intValue();

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amountInPaise);
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "receipt_order_" + System.currentTimeMillis());

        return razorpayClient.orders.create(orderRequest);
    }

    @Override
    @Transactional
    public PaymentResponseDTO registerCustomerAndCreatePayment(PaymentReqDTO reqDTO) throws Exception {
        // Step 1: Verify Razorpay Signature
        verifySignature(reqDTO);

        // Step 2: Find the package
        Package plan = packageRepository.findById(reqDTO.getPackageId())
                .orElseThrow(() -> new RuntimeException("Package not found"));

        // Step 3: Create and save the Customer entity
        Customer customer = new Customer();
        customer.setName(reqDTO.getName());
        customer.setEmail(reqDTO.getEmail());
        customer.setGender(reqDTO.getGender());
        customer.setPhone(reqDTO.getPhone());
        customer.setPackage(plan);
        Customer savedCustomer = customerRepository.save(customer);

        // Step 4: Create and save the NewCustomerPayment entity
        NewCustomerPayment payment = new NewCustomerPayment();
        payment.setCustomer(savedCustomer);
        payment.setAmount(plan.getPrice());
        payment.setPaymentStatus("SUCCESS");
        payment.setRazorpayOrderId(reqDTO.getRazorpay_order_id());
        payment.setRazorpayPaymentId(reqDTO.getRazorpay_payment_id());
        payment.setRazorpaySignature(reqDTO.getRazorpay_signature());
        NewCustomerPayment savedPayment = newCustomerPaymentRepository.save(payment);

        // Step 5: Map to DTO and return
        return new PaymentResponseDTO(
                savedPayment.getId(),
                savedPayment.getAmount(),
                savedPayment.getDateOfJoining(),
                savedPayment.getPaymentStatus(),
                savedCustomer.getName(),
                plan.getName()
        );
    }

    private void verifySignature(PaymentReqDTO reqDTO) throws Exception {
        JSONObject options = new JSONObject();
        options.put("razorpay_order_id", reqDTO.getRazorpay_order_id());
        options.put("razorpay_payment_id", reqDTO.getRazorpay_payment_id());
        options.put("razorpay_signature", reqDTO.getRazorpay_signature());

        boolean isValid = Utils.verifyPaymentSignature(options, this.razorpayKeySecret);

        if (!isValid) {
            throw new Exception("Razorpay signature verification failed.");
        }
    }
}
//package com.barbellnation.service;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import org.json.JSONObject;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import com.barbellnation.custom_exceptions.ResourceNotFoundException;
//import com.barbellnation.dao.CustomerDao;
//import com.barbellnation.dao.PaymentDao;
//import com.barbellnation.dto.PaymentReqDTO;
//import com.barbellnation.dto.PaymentRespDTO;
//import com.barbellnation.entities.Customer;
//import com.barbellnation.entities.CustomerPayment;
//import com.razorpay.Order;
//import com.razorpay.RazorpayClient;
//
//import jakarta.transaction.Transactional;
//
//@Service
//@Transactional
//public class CustomerPaymentServiceImpl implements CustomerPaymentService {
//
//    private final PaymentDao paymentDao;
//    private final CustomerDao customerDao;
//    private final ModelMapper modelMapper;
//
//    @Value("${razorpay.key.id}")
//    private String razorPayKey;
//
//    @Value("${razorpay.secret.key}")
//    private String razorPaySecret;
//
//    public CustomerPaymentServiceImpl(PaymentDao paymentDao, CustomerDao customerDao, ModelMapper modelMapper) {
//        this.paymentDao = paymentDao;
//        this.customerDao = customerDao;
//        this.modelMapper = modelMapper;
//    }
//
//    private RazorpayClient getRazorpayClient() throws Exception {
//        return new RazorpayClient(razorPayKey, razorPaySecret);
//    }
//    @Override
//    public List<PaymentRespDTO> getAllPayments() {
//        return paymentDao.findAll().stream()
//            .map(payment -> modelMapper.map(payment, PaymentRespDTO.class))
//            .collect(Collectors.toList());
//    } 
//    @Override
//    public CustomerPayment createCustomerPayment(PaymentReqDTO c) throws Exception {
//        Customer customer = customerDao.findById(c.getCid())
//            .orElseThrow(() -> new ResourceNotFoundException("Invalid customer id!"));
//
//        CustomerPayment cp = modelMapper.map(c, CustomerPayment.class);
//        customer.addPayment(cp);
//
//        JSONObject orderReq = new JSONObject();
//        orderReq.put("amount", cp.getAmount() * 100);
//        orderReq.put("currency", "INR");
//        orderReq.put("receipt", "rcpt_" + System.currentTimeMillis());
//
//        RazorpayClient client = getRazorpayClient();
//        Order razorPayOrder = client.orders.create(orderReq);
//
//        cp.setRazorpayPaymentId(razorPayOrder.get("id"));
//        cp.setPaymentStatus(razorPayOrder.get("status"));
//
//        paymentDao.save(cp);
//        return cp;
//    }
//
//    @Override
//    public CustomerPayment updatePayment(Map<String, String> responsePayLoad) {
//        String razorPayPaymentId = responsePayLoad.get("razorPay_order_id");
//
//        CustomerPayment p = paymentDao.findByRazorpayPaymentId(razorPayPaymentId)
//            .orElseThrow(() -> new ResourceNotFoundException("Payment not found for Razorpay ID: " + razorPayPaymentId));
//
//        p.setPaymentStatus("payment_completed");
//        return paymentDao.save(p);
//    }
//}