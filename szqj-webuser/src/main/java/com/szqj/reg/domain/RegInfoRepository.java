package com.szqj.reg.domain;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.szqj.weborg.domain.Account;




/**
 * 
 * @author lb12
 *
 */
@Repository
public interface RegInfoRepository extends PagingAndSortingRepository<RegInfo, String> {
	
	
	public RegInfo findByTelphone(String telphone);

	public RegInfo findByUserCode(String userCode);

	public RegInfo findByUserName(String userName);
	
	
	
}
