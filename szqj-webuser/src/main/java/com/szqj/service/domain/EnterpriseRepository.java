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
public interface EnterpriseRepository extends PagingAndSortingRepository<Enterprise, String> {


	@Query("select m from Enterprise m where m.enterpriseName like %?1%  order by level desc,orderNum,createDate")
	Page<Enterprise> findPageByEnterpriseName(String enterpriseName, Pageable pageable);
	
	@Query("select m from Enterprise m  order by level desc,orderNum,createDate")
	Page<Enterprise> findPage(Pageable pageable);
	
	@Query("select m from Enterprise m  where level=30 order by level desc,orderNum,createDate")
	Page<Enterprise> findVipPage(Pageable pageable);
	
	
	@Query("select m from Enterprise m  where level=30 order by level desc,orderNum,createDate")
	List<Enterprise> findVipList();
	
	List<Enterprise> findByAccountId(String accountId);
	
	@Query("select m from Enterprise m where m.accountId=?1 and m.telphone=?2 ")
	List<Enterprise> findByAccountIdAndTelePhone(String accountId, String telphone);
	
	
	@Query("select m from Enterprise m where  m.telphone=?1 ")
	List<Enterprise> findByTelePhone( String telphone);
	
	@Query("select m from Enterprise m where m.jobLevel='20'")
	Page<Enterprise> findPageByJobLevel(Pageable pageable);
	
	@Query("select m from Enterprise m where m.applyCityId=?1 and m.enterpriseType=?2 ")
	List<Enterprise> findByApplyCityIdAndEnterpriseType(String applyCityId, Integer enterpriseType);
	
	@Query("select m from Enterprise m where m.applyCityId=?1 and m.enterpriseType=?3 and m.enterpriseName=?2 ")
	List<Enterprise> findByApplyCityIdAndEnterpriseTypeAndName(String applyCityId, String enterpriseName,String enterpriseType);
	
	@Query("select m from Enterprise m where m.applyCityId=?1 and m.enterpriseType=?2 ")
	Page<Enterprise> findByApplyCityIdAndEnterpriseType(String applyCityId, Integer enterpriseType,Pageable pageable);
	
	@Query("select m from Enterprise m where m.applyCityId=?1 and m.enterpriseType=?3 and m.enterpriseName like %?2% ")
	Page<Enterprise> findByApplyCityIdAndEnterpriseTypeAndName(String applyCityId, String enterpriseName,Integer enterpriseType,Pageable pageable);
	
}
