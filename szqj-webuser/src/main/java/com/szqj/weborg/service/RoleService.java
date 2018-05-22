package com.szqj.weborg.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szqj.weborg.domain.Role;
import com.szqj.weborg.domain.RoleColumn;
import com.szqj.weborg.domain.RoleColumnRepository;
import com.szqj.weborg.domain.RoleMenu;
import com.szqj.weborg.domain.RoleMenuRepository;
import com.szqj.weborg.domain.RoleRepository;
import com.szqj.weborg.domain.RoleUser;
import com.szqj.weborg.domain.RoleUserRepository;

@Service
@Transactional
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RoleUserRepository roleUserRepository;
	
	@Autowired
	private RoleMenuRepository roleMenuRepository;
	
	@Autowired
	private RoleColumnRepository roleColumnRepository;
	
	
	public Page<Role> findByRoleName(String roleName, PageRequest pageable) {
		if(StringUtils.isBlank(roleName)){
			return roleRepository.findAll(pageable);
		}else{
			return roleRepository.findByRoleName(roleName,pageable);
		}
		
	}
	
	public void  saveRoleAccount( String roleId,String[] userList){
		roleUserRepository.deleteByRoleId(roleId);
		
		for(String userId:userList){
			RoleUser roleUser=new RoleUser();
			roleUser.setRoleId(roleId);
			roleUser.setUserId(userId);
			roleUserRepository.save(roleUser);
		}
		
	}
	
	public List<RoleMenu> getRoleMenu(String roleId) {
		List<RoleMenu> l = roleMenuRepository.findByRoleId(roleId);
		return l;
	}

	public void saveRoleMenu(String roleId, String[] menuList) {
		roleMenuRepository.deleteByRoleId(roleId);
		for(String menuId:menuList){
			RoleMenu roleMenu=new RoleMenu();
			roleMenu.setRoleId(roleId);
			roleMenu.setMenuId(menuId);
			roleMenuRepository.save(roleMenu);
		}
		
	}
	
	public List<RoleColumn> getRoleColumn(String roleId) {
		List<RoleColumn> l = roleColumnRepository.findByRoleId(roleId);
		return l;
	}

	public void saveRoleColumn(String roleId, String[] columnList) {
		roleMenuRepository.deleteByRoleId(roleId);
		for(String columnId:columnList){
			RoleColumn roleColum=new RoleColumn();
			roleColum.setRoleId(roleId);
			roleColum.setColumnId(columnId);
			roleColumnRepository.save(roleColum);
		}
		
	}

	

	

	

}
