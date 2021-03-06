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
public interface BuyInfoRepository extends PagingAndSortingRepository<BuyInfo, String> {


	@Query("select m from BuyInfo m where m.buyInfoName like %?1% and m.enterpriseId=?2  order by m.createDate desc")
	Page<BuyInfo> findPageByBuyInfoName(String buyInfoName,String enterpriseId, Pageable pageable);
	
	@Query("select m from BuyInfo m  where m.enterpriseId=?1  order by m.createDate desc")
	Page<BuyInfo> findPageByEnterpriseId(String enterpriseId,Pageable pageable);

	@Query("select m from BuyInfo m  order by m.createDate desc")
	Page<BuyInfo> findPage(Pageable pageable);
	
	@Query("select m from BuyInfo m where m.empName like %?1% order by m.createDate desc")
	Page<BuyInfo> findPageByEmpName(String empName, Pageable pageable);
	
	@Query("select m from BuyInfo m where m.buyInfoName like %?1% order by m.createDate desc")
	Page<BuyInfo> findPageByBuyInfoName(String buyInfoName, Pageable pageable);
	
	@Query("select m from BuyInfo m where m.buyInfoTypeCode=?1 order by m.createDate desc")
	Page<BuyInfo> findPageByBuyInfoTypeCode(String buyInfoTypeCode, Pageable pageable);
	
	
	@Query("select m from BuyInfo m where m.buyInfoTypeCode=?1  and m.buyInfoName like %?2%  order by m.createDate desc")
	Page<BuyInfo> findPageByBuyInfoTypeCodeAndBuyInfoName(String buyInfoTypeCode,String buyInfoName,  Pageable pageable);
	
}
