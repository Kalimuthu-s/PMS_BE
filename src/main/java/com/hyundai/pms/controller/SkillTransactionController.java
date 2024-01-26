package com.hyundai.pms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.pms.entity.Response;
import com.hyundai.pms.entity.SkillTransactionMaster;
import com.hyundai.pms.service.SkillTransactionService;

@RestController
@RequestMapping("/skillTransaction")
@CrossOrigin(value="http://localhost:4200/")
public class SkillTransactionController {
	
	@Autowired
	private SkillTransactionService sts;
	
	@GetMapping("/getAll")
	public Response getAllSkillTransaction() {
		List<SkillTransactionMaster> list= sts.getAllSkillTransaction();
		return new Response(1, "Success", list);
	}
	
	@GetMapping("/getSkillById/{skillid}")
	public Response getSkillById(@PathVariable int skillid) {
		Optional<SkillTransactionMaster> stm=sts.getSkillById(skillid);
		return new Response(1, "Success", stm);
	}
	
	@PostMapping("/addSkillTransaction")
	public Response addSkillTransaction(@RequestBody SkillTransactionMaster stm) {
		sts.addSkillTransaction(stm);
		return new Response(1, "Success", stm);
	}
	
//	@PostMapping("/addMultiSkillTransaction")
//	public Response addMultiSkillTransation(@RequestBody STdto dto) {
//		System.err.println("??????????????? " +dto.getEmployeeId());
//		System.err.println("??????????????? " +dto.getSkillId());
//		String result=sts.addMultiSkillTransation(dto.getEmployeeId(),dto.getSkillId());
//		return new Response(1, result, dto);
//	}
	
	@PutMapping("/updateSkillTransaction")
	public Response updateSkillTransaction(@RequestBody SkillTransactionMaster stm) {
		sts.updateSkillTransaction(stm);
		return new Response(1, "Success", stm);
	}
	
	@DeleteMapping("/deleteSkillTransaction/{skillid}")
	public Response deleteSkillTransaction(@PathVariable int skillid) {
		String dsk = sts.deleteSkillTransaction(skillid);
		return new Response(1, dsk, skillid);
	}
}
