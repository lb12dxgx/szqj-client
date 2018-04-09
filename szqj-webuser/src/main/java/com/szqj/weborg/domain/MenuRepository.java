package com.szqj.weborg.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * 
 * @author lb12
 *
 */
public interface MenuRepository extends JpaRepository<Menu, String> {
	
	@Query("select m from Menu m where m.parentMenuId =-1")
	public Menu getRoot();
	
	
	@Query("select m from Menu m where m.parentMenuId !=-1 order by code")
	public List<Menu> getTreeNoes();
	
	
	
}
