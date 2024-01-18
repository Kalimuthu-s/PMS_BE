package com.hyundai.pms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name ="experience_master" )
public class ExperienceMaster {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "exp_id")
    private Long experienceId;

    @Column(name = "exp_level")
    private String experienceLevel;

    @Column(name = "no_of_years")
    private String noOfYears;

	public Long getExperienceId() {
		return experienceId;
	}

	public void setExperienceId(Long experienceId) {
		this.experienceId = experienceId;
	}

	public String getExperienceLevel() {
		return experienceLevel;
	}

	public void setExperienceLevel(String experienceLevel) {
		this.experienceLevel = experienceLevel;
	}

	public String getNoOfYears() {
		return noOfYears;
	}

	public void setNoOfYears(String noOfYears) {
		this.noOfYears = noOfYears;
	}

}