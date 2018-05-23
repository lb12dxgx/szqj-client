package com.szqj.weborg.rest;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.util.RestJson;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Role;
import com.szqj.weborg.domain.RoleColumn;
import com.szqj.weborg.domain.RoleMenu;
import com.szqj.weborg.domain.RoleRepository;
import com.szqj.weborg.domain.RoleUser;
import com.szqj.weborg.domain.RoleUserRepository;
import com.szqj.weborg.service.RoleService;

@RestController
@RequestMapping("/system/role/")
@EnableAutoConfiguration
public class RoleRest {

	@Autowired
	private RoleService roleService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RoleUserRepository roleUserRepository;

	// 返回角色列表
	@RequestMapping(value = "list.do")

	public RestJson list(String roleName, Integer pageNum, Integer size) {
		PageRequest pageable = Tools.getPage(pageNum - 1, size);
		Page<Role> page = roleService.findByRoleName(roleName, pageable);

		return RestJson.createSucces(page);
	}

	// 保存账号信息
	@RequestMapping(value = "save.do")
	public RestJson save(Role role) {
		roleRepository.save(role);
		return RestJson.createSucces(role);
	}

	// 更新
	@RequestMapping(value = "update.do")
	public RestJson update(Role role) {
		roleRepository.save(role);
		return RestJson.createSucces(role);
	}

	// 删除
	@RequestMapping(value = "delete.do")
	public RestJson delete(String roleId) {
		roleRepository.deleteById(roleId);
		return RestJson.createSucces();
	}

	// 根据角色ID返回账户集合
	@RequestMapping(value = "accountByRole.do")
	public RestJson accountByRole(String roleId) {
		List<RoleUser> l = roleUserRepository.findByRoleId(roleId);
		return RestJson.createSucces(l);
	}

	// 根据角色返回账号
	@RequestMapping(value = "saveRoleAccount.do")
	public RestJson saveRoleAccount(String roleId, String[] userList, HttpServletRequest request) {
		roleService.saveRoleAccount(roleId, userList);
		return RestJson.createSucces();
	}

	// 根据角色返回账号
	@RequestMapping(value = "saveRoleMenu.do")
	public RestJson saveRoleMenu(String roleId, String[] menuList, HttpServletRequest request) {
		Map<String, String[]> map = request.getParameterMap();
		roleService.saveRoleMenu(roleId, menuList);
		return RestJson.createSucces();
	}

	// 根据角色返回账号
	@RequestMapping(value = "getRoleMenu.do")
	public RestJson getRoleMenu(String roleId) {
		List<RoleMenu> l = roleService.getRoleMenu(roleId);
		return RestJson.createSucces(l);
	}

	@RequestMapping(value = "saveRoleColumn.do")
	public RestJson saveRoleColumn(String roleId, String[] columnList, HttpServletRequest request) {
		Map<String, String[]> map = request.getParameterMap();
		roleService.saveRoleColumn(roleId, columnList);
		return RestJson.createSucces();
	}

	@RequestMapping(value = "getRoleColumn.do")
	public RestJson getRoleColumn(String roleId) {
		List<RoleColumn> l = roleService.getRoleColumn(roleId);
		return RestJson.createSucces(l);
	}
	
	@RequestMapping(value = "getColumnByUserId.do")
	public RestJson getColumnByUserId(String userId){
		List<RoleColumn> l = roleService.getColumnByUserId(userId);
		return RestJson.createSucces(l);
	}

}
