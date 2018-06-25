package com.szqj.service.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;




/**
 * 
 * @author lb12
 *
 */
public interface EnterpriseRepository extends PagingAndSortingRepository<Enterprise, String> {


	@Query("select m from Enterprise m where m.enterpriseName like %?1%  order by level desc,orderNum,createDate")
	Page<Enterprise> findPageByEnterpriseName(String enterpriseName, Pageable pageable);
	
	@Query("select m from Enterprise m  order by level desc,orderNum,createDate")
	Page<Enterprise> findPage(Pageable pageable);
	
	@Query("select m from Enterprise m  where level=30 order by level desc,orderNum,createDate")
	Page<Enterprise> findVipPage(Pageable pageable);
	
	List<Enterprise> findByAccountId(String accountId);
	
}
