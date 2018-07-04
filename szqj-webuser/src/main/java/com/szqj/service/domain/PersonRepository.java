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
public interface PersonRepository extends PagingAndSortingRepository<Person, String> {

	@Query("select m from Person m where m.accountId =?1 ")
	List<Person> findByAccountId(String accountId);  
   
}
