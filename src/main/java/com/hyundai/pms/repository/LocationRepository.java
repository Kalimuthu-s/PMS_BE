package com.hyundai.pms.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hyundai.pms.entity.LocationMaster;


@Repository
public interface LocationRepository extends JpaRepository<LocationMaster,Long>{
	
	@Query("SELECT l FROM LocationMaster l WHERE l.locationName like %:searchKey% OR l.locationId like %:searchKey% "
			+ "OR city like %:searchKey% OR state like %:searchKey% OR country like %:searchKey% "
			+ "OR postalCode like %:searchKey%")
	Page<List<LocationMaster>> findAllLocation(Pageable pageable,String searchKey);

}