package com.hyundai.pms.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.pms.config.UserInfoUserDetailsService;
import com.hyundai.pms.entity.AuthRequest;
import com.hyundai.pms.entity.UserMaster;
import com.hyundai.pms.entity.UserResponse;
import com.hyundai.pms.repository.UserRepository;
import com.hyundai.pms.service.JwtService;

@CrossOrigin
@RestController
public class UserController {

	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	@Autowired
	private UserRepository ur;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserInfoUserDetailsService info;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	@GetMapping
//	public String 



	@PostMapping("/signin")
	public UserResponse signin(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			UserDetails userDetails = info.loadUserByUsername(authentication.getName());
			String token= jwtService.generateToken(userDetails);
			Map<String,Object> data=new HashMap<>();
			data.put("userDetails", userDetails);
			data.put("token", token);
			return new UserResponse(1,"Login Success",data,true);
		} else {
			return new UserResponse(2,"Login Failed",null,false);
		}
	}

	@PostMapping("/signup")
	public UserResponse signup(@RequestBody UserMaster user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		ur.save(user);
		return new UserResponse(1,"Signup Success",user,true);
	}

}
