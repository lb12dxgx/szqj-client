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
public interface MyPersonRepository extends PagingAndSortingRepository<Person, String> {

	@Query("select m from Person m where m.accountId =?1 ")
	List<Person> findByAccountId(String accountId);  
	
	
	@Query("select m from Person m where m.accountId !=?1 and m.telePhone=?2 ")
	List<Person> findByAccountIdAndTelePhone(String accountId,String telePhone);

	@Query("select m from Person m where  m.telePhone=?1 ")
	List<Person> findByTelePhone(String telphone);  
	
	@Query("select m from Person m order by m.updateDate ")
	Page<Person> findPage( Pageable pageable);
	
	
	@Query("select m from Person m where m.level=20 order by m.updateDate desc,level desc")
	Page<Person> findTopPage(Pageable pageable);
	
	
	
	@Query("select m from Person m where m.jobName like %?1%   order by m.updateDate desc,level desc")
	Page<Person> findAdminPageByJobName(String jobName,Pageable pageable);
	
	
	@Query("select m from Person m where m.personName like %?1% order by m.updateDate desc,level desc")
	Page<Person> findAdminPageByPersonName(String personName, Pageable pageable);
   
	
	
}
