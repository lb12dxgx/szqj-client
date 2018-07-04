package com.szqj.cms.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




/**
 * 
 * @author lb12
 *
 */
@Repository
public interface OutKeyInfoRepository  extends JpaRepository<OutKeyInfo, String> {
	
	
	
}
