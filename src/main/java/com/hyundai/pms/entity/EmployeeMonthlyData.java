package com.hyundai.pms.entity;

import java.time.LocalDate;

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
@Table(name = "employee_monthly_data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeMonthlyData {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "emp_month_id")
    private Long empMonthId;
    
    @Column(name="emp_id")
    private Long employeeId;
    
    @Column(name="project_id")
    private Long ProjectId;

    @Column(name = "month")
    private LocalDate month;
    
    @Column(name = "contribution")
    private Double contribution;

    @Column(name = "year")
    private Integer year;

}
