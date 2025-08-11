package com.barbellnation.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="customer")
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Customer extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "package_id", nullable=false)
	private Package packageId;
	
	@OneToMany(mappedBy = "customer", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<NewCustomerPayment> payments = new ArrayList<>();
	
	public void addPayment(NewCustomerPayment cp) {
		this.payments.add(cp);
		cp.setCustomer(this);
	}

    // This method was added to resolve the compiler error in your service.
    public void setPackage(Package aPackage) {
        this.packageId = aPackage;
    }
}
