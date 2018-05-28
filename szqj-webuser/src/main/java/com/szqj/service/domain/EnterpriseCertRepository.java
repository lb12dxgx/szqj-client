package com.szqj.service.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;




/**
 * 
 * @author lb12
 *
 */
public interface EnterpriseCertRepository extends PagingAndSortingRepository<EnterpriseCert, String> {

	@Query("select m from EnterpriseCert m  where m.enterpriseId=?1 order by m.createDate ")
	List<EnterpriseCert> findByEnterpriseId(String enterpriseId);
	
	
	@Query("select m from EnterpriseCert m  where m.enterpriseName=?1 and m.certCode=?2 order by m.createDate ")
	List<EnterpriseCert> findByEnterpriseNameAndCertCode(String enterpriseName, String certCode);
	
}
