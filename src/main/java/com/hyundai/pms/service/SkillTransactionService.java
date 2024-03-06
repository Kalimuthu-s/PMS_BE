package com.hyundai.pms.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.pms.entity.SkillTransactionMaster;
import com.hyundai.pms.repository.SkillTransactionRepository;

@Service
public class SkillTransactionService {
	
	@Autowired
	private SkillTransactionRepository str;
	
	public List<SkillTransactionMaster> getAllSkillTransaction(){
		return str.findAll();
	}
	
	public Optional<SkillTransactionMaster> getSkillById(int skillid) {
		return str.findById(skillid);
	}
	
	public SkillTransactionMaster addSkillTransaction(SkillTransactionMaster stm) {
		return str.save(stm);
	}
	
	public List<Map<String, Object>> getAllSkillByProficiency(String skill,String proficiency){
		return str.getAllSkillByProficiency(skill,proficiency);
	}
	
//	public String addMultiSkillTransation(int empId,List<Integer> skillIds) {
//		
//		for(int skillId:skillIds) {
//		SkillTransactionMaster skillTransactions=new SkillTransactionMaster();
//		skillTransactions.setEmployeeId(empId);
//		skillTransactions.setSkillId(skillId);
//		str.save(skillTransactions);
//		}
//		return "Success";
//
//	}
	
	public SkillTransactionMaster updateSkillTransaction(SkillTransactionMaster stm) {
		return str.save(stm);
	}
	
	public String deleteSkillTransaction(int skillid) {
		str.deleteById(skillid);
		return "Success";
	}

}
