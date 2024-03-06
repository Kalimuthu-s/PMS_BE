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

@Entity
@Table(name = "menu_transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuTransaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "menu_trans_id")
	private int menuTransactionId;
	
	@Column(name = "role_id")
	private int roleId;
	
	@Column(name = "menu_id")
	private int menuId;
	
	@Column(name = "status")
	private String status;

}
