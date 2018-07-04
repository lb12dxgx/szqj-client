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
public interface RoleMenuRepository extends JpaRepository<RoleMenu, String> {
	
	public List<RoleMenu> findByRoleId(String roleId);

	@Modifying
	@Query("delete from RoleMenu where roleId=?1")
	public void deleteByRoleId(String roleId);
	
}
