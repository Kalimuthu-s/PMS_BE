package com.hyundai.pms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="customer_master")
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
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
	    private String partner;

	    @Column(name="status")
	    private String status;

    }