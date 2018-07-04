package com.szqj.weborg.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * 
 * @author lb12
 *
 */
@Repository
public interface RoleColumnRepository extends JpaRepository<RoleColumn, String> {
	
	public List<RoleColumn> findByRoleId(String roleId);

	@Modifying
	@Query("delete from RoleColumn where roleId=?1")
	public void deleteByRoleId(String roleId);
	
}
