package com.szqj.service.domain;

import java.util.List;

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
   
	
	
}
