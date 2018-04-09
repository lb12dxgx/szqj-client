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
public interface RoleRepository extends JpaRepository<Role, String> {
	
	public Page<Role> findByRoleName(String roleName,Pageable pageable);
	
}
