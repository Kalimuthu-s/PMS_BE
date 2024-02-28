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
@Table(name ="experience_master")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExperienceMaster {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "exp_id")
    private int experienceId;

    @Column(name = "exp_level")
    private String experienceLevel;

    @Column(name = "no_of_years")
    private String noOfYears;

	
}