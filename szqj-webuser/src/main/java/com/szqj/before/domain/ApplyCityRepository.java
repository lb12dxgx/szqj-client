package com.szqj.before.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



/**
 * 
 * @author lb12
 *
 */
@Repository
public interface ApplyCityRepository extends JpaRepository<ApplyCity, String> {
	
	@Query("select m from ApplyCity m where  order by m.createDate")
	public Page<ApplyCity> findPage(Pageable pageable);
	
	
	
	
}
