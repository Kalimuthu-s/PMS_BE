package com.hyundai.pms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.hyundai.pms.config.SequenceGenerator;

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
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "custom_seq")
//   	@GenericGenerator(
//   	        name = "custom_seq",
//   	        strategy = "com.hyundai.pms.config.SequenceGenerator", 
//   	        parameters = {
//   	            @Parameter(name = SequenceGenerator.INCREMENT_PARAM, value = "50"),
//   	            @Parameter(name = SequenceGenerator.VALUE_PREFIX_PARAMETER, value = "CUST"),
//   	            @Parameter(name = SequenceGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })

	   	@Id
	    @Column(name="cust_id")
	   	@GeneratedValue(strategy = GenerationType.IDENTITY)
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