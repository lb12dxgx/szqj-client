package com.szqj.service.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
   
	
	@Query("select m from Person m where m.accountId =?1 order by userCode ")
	List<Person> findByAccountassd(String accountId);  
	
	@Query("select m from Person m where m.accountId =?1 order by userCode ")
	List<Person> findByAccountass(String accountId);  
	
	
	@Query("select m from Person m where m.accountId =?1 order by userCode ")
	List<Person> findByAccountammm(String accountId);  
	

	@Query("select m from Person m where m.accountId =?1 order by userCode ")
	List<Person> findByAccountam(String accountId); 
	
	
	@Query("select m from Person m where m.accountId =?1 order by userCode ")
	List<Person> findByAccountcccv(String accountId); 
}
