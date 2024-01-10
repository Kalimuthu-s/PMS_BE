package com.hyundai.pms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer_master")
public class CustomerMaster {

	   	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name="cust_id")
	    private Long customerId;

	    @Column(name="cust_name")
	    private String customerName;

	    @Column(name="cust_location")
	    private String customerLocation;

	    @Column(name="no_of_projects_completed")
	    private int noOfProjectsCompleted;

	    @Column(name="partner")
	    private boolean partner;

	    @Column(name="status")
	    private String status;

    
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getCustomerLocation() {
		return customerLocation;
	}
	public void setCustomerLocation(String customerLocation) {
		this.customerLocation = customerLocation;
	}
	
	
	
	public String getStatus() {
		return status;
	}
	
	public int getNoOfProjectsCompleted() {
		return noOfProjectsCompleted;
	}
	public void setNoOfProjectsCompleted(int noOfProjectsCompleted) {
		this.noOfProjectsCompleted = noOfProjectsCompleted;
	}
	public boolean isPartner() {
		return partner;
	}
	public void setPartner(boolean partner) {
		this.partner = partner;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "CustomerMaster [customerId=" + customerId + ", customerName=" + customerName + ", customerLocation="
				+ customerLocation + ", noOfProjectsCompleted=" + noOfProjectsCompleted + ", partner=" + partner
				+ ", status=" + status + "]";
	}
    }