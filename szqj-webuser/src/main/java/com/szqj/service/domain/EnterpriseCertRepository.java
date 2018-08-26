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
public interface EnterpriseCertRepository extends PagingAndSortingRepository<EnterpriseCert, String> {

	@Query("select m from EnterpriseCert m   order by m.createDate ")
	Page<EnterpriseCert> findPage( Pageable pageable);
	
	@Query("select m from EnterpriseCert m  where m.enterpriseName like %?1%  order by m.createDate ")
	Page<EnterpriseCert> findPageByEnterpriseName(String enterpriseName,  Pageable pageable);
	
	
	@Query("select m from EnterpriseCert m  where m.enterpriseName=?1 and m.certCode=?2 order by m.createDate ")
	List<EnterpriseCert> findByEnterpriseNameAndCertCode(String enterpriseName, String certCode);
	
}
