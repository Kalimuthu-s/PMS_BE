package com.hyundai.pms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.UserMaster;

@Repository
public interface UserRepository extends JpaRepository<UserMaster, Integer>{
	
	@Query(value = "select * from user_master where user_id=?1",nativeQuery=true)
	Optional<UserMaster> findByUsername(String username);
	
	@Query(value = "select * from user_master where email=?1", nativeQuery = true)
	Optional<UserMaster> verifyEmail(String email);

}
