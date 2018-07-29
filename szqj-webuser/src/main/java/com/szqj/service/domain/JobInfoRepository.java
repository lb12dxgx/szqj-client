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
public interface JobInfoRepository extends PagingAndSortingRepository<JobInfo, String> {


	@Query("select m from JobInfo m where m.jobName like %?1% and m.enterpriseId=?2  order by m.startDate desc")
	Page<JobInfo> findPageByJobInfoName(String jobName,String enterpriseId, Pageable pageable);
	
	@Query("select m from JobInfo m  where m.enterpriseId=?1  order by m.startDate desc")
	Page<JobInfo> findPageByEnterpriseId(String enterpriseId,Pageable pageable);

	@Query("select m from JobInfo m where m.level=20 order by m.startDate desc")
	Page<JobInfo> findTopPage(Pageable pageable);
	
	
	@Query("select m from JobInfo m  order by m.startDate desc,level desc")
	Page<JobInfo> findPage(Pageable pageable);
	
	
	@Query("select m from JobInfo m where m.jobName like %?1%   order by m.startDate desc,level desc")
	Page<JobInfo> findAdminPageByJobInfoName(String jobName,Pageable pageable);
	
	
	@Query("select m from JobInfo m where m.enterpriseName like %?1% order by m.startDate desc,level desc")
	Page<JobInfo> findAdminPageByEnterpriseName(String enterpriseName, Pageable pageable);
	
}
