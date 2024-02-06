package com.hyundai.pms.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.hyundai.pms.entity.UserMaster;
import com.hyundai.pms.repository.UserRepository;
@Component
public class UserInfoUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository ur;

	
	 @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 System.err.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + username);
	        Optional<UserMaster> userInfo = ur.findByUsername(username);
	        return userInfo.map(UserInfoUserDetails::new)
	                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

	    }

}
