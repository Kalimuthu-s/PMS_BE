package com.hyundai.pms.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.pms.config.UserInfoUserDetailsService;
import com.hyundai.pms.entity.AuthRequest;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.entity.TokenDTO;
import com.hyundai.pms.entity.UserDTO;
import com.hyundai.pms.entity.UserMaster;
import com.hyundai.pms.entity.UserResponse;
import com.hyundai.pms.repository.UserRepository;
import com.hyundai.pms.service.JwtService;

@CrossOrigin
@RestController
public class UserController {

//	private static final Logger logger = LogManager.getLogger(UserController.class);
	
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


	@PostMapping("/signin")
	public UserResponse signin(@RequestBody AuthRequest authRequest) {
		 
//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
//		if (authentication.isAuthenticated()) {
		UserDetails userDetails = info.loadUserByUsername(authRequest.getUsername());
		if(passwordEncoder.matches(authRequest.getPassword(), userDetails.getPassword())) {
			String token= jwtService.generateToken(userDetails);
			Map<String,Object> data=new HashMap<>();
			data.put("userDetails", userDetails);
			data.put("token", token);
			data.put("roleId", userDetails.getAuthorities().toArray()[0].toString());
			data.put("userId", authRequest.getUsername());
			Optional<UserMaster> userSave = ur.findIdByName(userDetails.getUsername());
			UserMaster user = userSave.get();
			userSave.get().setToken(token);
			ur.save(user);
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
	
	@PostMapping("/verifyEmail")
	public UserResponse verifyEmail(@RequestBody Map<String,Object> email) {
	    try {
	        Optional<UserMaster> userOptional = ur.verifyEmail(email.get("email").toString());
	        if (userOptional.isPresent()) {
	            UserMaster user = userOptional.get();
	            if (user.getEmail() != null && user.getEmail().equals(email.get("email").toString())) {
	                return new UserResponse(1, "Success", user, true);
	            } else {
	                return new UserResponse(2, "Email not verified", null, true);
	            }
	        } else {
	            return new UserResponse(2, "User not found", null, true);
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); 
	        return new UserResponse(2, "Failed", null, true);
	    }
	}

	
	@PostMapping("/changePassword")
	public UserResponse changePassword(@RequestBody UserDTO user) {
		Optional<UserMaster> userdata = ur.verifyEmail(user.getEmail());
		String passwordPattern = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{6,}$";
	    if (!user.getPassword().matches(passwordPattern)) {
	        return new UserResponse(3, "Password does not meet the criteria", null, false);
	    }
		UserMaster userMaster = userdata.get();
		userMaster.setPassword(passwordEncoder.encode(user.getPassword()));
		ur.save(userMaster);	
		return new UserResponse(1,"Password Changed",user,true);
	}
	
	@PostMapping("/user/logout")
	public Response logout(@RequestBody TokenDTO token) {
		String username = jwtService.extractUsername(token.getToken());
		Optional<UserMaster> userData = ur.findIdByName(username);
		UserMaster user = userData.get();
		user.setToken(null);
		ur.save(user);
		return new Response(1,"Success", user);
	}

}
