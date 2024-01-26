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
@Table(name = "skill_transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SkillTransactionMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "skill_trans_id")
	private int skillTransactionId;
	
	@Column(name = "emp_id")
	private int employeeId;
	
	@Column(name = "skill_id")
	private int skillId;
	
	@Column(name = "proficiency_level")
	private String proficiencyLevel;

}
