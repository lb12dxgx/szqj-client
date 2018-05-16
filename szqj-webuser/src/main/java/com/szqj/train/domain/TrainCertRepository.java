package com.szqj.train.domain;

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
public interface TrainCertRepository extends PagingAndSortingRepository<TrainCert, String> {

	@Query("select m from TrainCert m  where m.certTypeName=?1 order by m.createDate ")
	Page<TrainCert> findPageByCertTypeName(String certTypeName, Pageable pageable);

	@Query("select m from TrainCert m  where m.trainPlanId=?1 order by m.createDate ")
	Page<TrainCert> findByTrainPlanId(String trainPlanId, Pageable pageable);
	
	@Query("select m from TrainCert m  where m.trainPlanId=?1 and  m.userName=?2 order by m.createDate ")
	Page<TrainCert> findByTrainPlanIdAndUserName(String trainPlanId, String userName,Pageable pageable);
	
	@Query("select m from TrainCert m  where m.userName=?1 and m.certCode=?2 order by m.createDate ")
	List<TrainCert> findByUserNameAndCertCode(String userName, String certCode);
	
}
