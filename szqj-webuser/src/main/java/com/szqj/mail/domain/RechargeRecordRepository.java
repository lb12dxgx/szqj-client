package com.szqj.mail.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;




/**
 * 
 * @author lb12
 *
 */
@Repository
public interface RechargeRecordRepository extends PagingAndSortingRepository<RechargeRecord, String> {

	@Query("select m from RechargeRecord m  where m.personName like %?1%  order by m.createDate ")
	Page<RechargeRecord> findByPersonName(String personName, Pageable pageable);
	
	
	@Query("select m from RechargeRecord m  where m.tradeNo =?1   ")
     RechargeRecord findByTradeNo(String tradeNo);

	




}
