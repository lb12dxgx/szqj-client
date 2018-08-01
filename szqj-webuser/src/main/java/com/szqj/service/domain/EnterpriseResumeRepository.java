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
public interface EnterpriseResumeRepository extends PagingAndSortingRepository<EnterpriseResume, String> {


	/**
	 * 待处理
	 * @param enterpriseName
	 * @param pageable
	 * @return
	 */                                                
	@Query("select a from EnterpriseResume a  where a.enterprise.enterpriseId =?1 and a.state=0  order by a.createDate ")
	Page<EnterpriseResume> findPageaByUn(String enterpriseId, Pageable pageable);
	
	/**
	 * 待处理
	 * @param enterpriseName
	 * @param pageable
	 * @return
	 */                                                
	@Query("select a from EnterpriseResume a  where a.enterprise.enterpriseId =?1 and a.state=0 and jobInfo.jobName like %?2%  order by a.createDate ")
	Page<EnterpriseResume> findPageaByUnAndJobName(String enterpriseId, String jobName,Pageable pageable);
	
	
	
	/**
	 * 已处理
	 * @param enterpriseName
	 * @param pageable1
	 * @return
	 */
	@Query("select a from EnterpriseResume a where a.enterprise.enterpriseId =?1 and (a.state=1 or a.state=2)   order by a.createDate ")
	Page<EnterpriseResume> findPageaByFinsh(String enterpriseId, Pageable pageable);
	
	@Query("select a from EnterpriseResume a where a.jobInfo.jobInfoId =?1 and a.person.personId=?2   order by a.createDate ")
	List<EnterpriseResume> findByJobInfoAndPerson(String jobInfoId,String personId);
	
	
	
}
