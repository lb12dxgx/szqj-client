package com.szqj.weborg.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


/**
 * 
 * @author lb12
 *
 */
@Repository
public interface OrgRepository extends JpaRepository<Org, String> {
	
	
	
}
