package com.szqj.reg.domain;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.szqj.weborg.domain.Account;




/**
 * 
 * @author lb12
 *
 */
public interface RegInfoRepository extends PagingAndSortingRepository<RegInfo, String> {
	
	
	public RegInfo findByTelphone(String telphone);
	
	
	
}
