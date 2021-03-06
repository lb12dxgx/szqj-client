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
public interface RefundRecordRepository extends PagingAndSortingRepository<RefundRecord, String> {

	@Query("select m from RefundRecord m  where m.state =?1   ")
	List<RefundRecord> findByState(int i);



	
}
