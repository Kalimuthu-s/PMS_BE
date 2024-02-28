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
	@Query(value = "SELECT * FROM location_master " +
            "WHERE location_name LIKE %:searchKey% " +
            "OR location_id LIKE %:searchKey% " +
            "OR city LIKE %:searchKey% " +
            "OR state LIKE %:searchKey% " +
            "OR country LIKE %:searchKey% " +
            "OR postal_code LIKE %:searchKey%",
            nativeQuery = true)
  Page<LocationMaster> findAllLocations(Pageable pageable,String searchKey);
	
//	@Query(value = "select * from location_master where location_name like %?1% or state like %?1% or city like %?1% or country like %?1% "
//			+ "or postal_code like %?1%",nativeQuery = true)
//	List<LocationMaster> locationFilters(String inputValue);

}