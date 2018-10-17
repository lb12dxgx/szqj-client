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
public interface CityAreaRepository extends JpaRepository<CityArea, String> {
	
	@Query("select m from CityArea m where  m.cityDistrict.cityDistrictId=?1 ")
	public List<CityArea> findByApplyCityIdAndEnterpriseId(String cityDistrictId);
	
}
