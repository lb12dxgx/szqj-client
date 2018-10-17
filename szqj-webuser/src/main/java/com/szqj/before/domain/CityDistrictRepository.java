package com.szqj.before.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



/**
 * 
 * @author lb12
 *
 */
@Repository
public interface CityDistrictRepository extends JpaRepository<CityDistrict, String> {
	
	@Query("select m from CityDistrict m where  m.applyCity.applyCityId=?1 ")
	public List<CityDistrict> findByApplyCityIdAndEnterpriseId(String applyCityId);
	
}
