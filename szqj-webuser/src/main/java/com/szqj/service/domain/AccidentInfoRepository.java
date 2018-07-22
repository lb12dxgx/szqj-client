package com.szqj.service.domain;

import java.util.List;

import org.springframework.data.domain.Page;
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
public interface AccidentInfoRepository extends PagingAndSortingRepository<AccidentInfo, String> {


	@Query("select m from AccidentInfo m where m.accountId=?1  order by m.creatDate desc")
	Page<AccidentInfo> findPageByAccountId(String accountId,Pageable pageable);
	


	@Query("select m from AccidentInfo m  order by m.creatDate desc")
	Page<AccidentInfo> findAllPage(Pageable pageable);
	
}
