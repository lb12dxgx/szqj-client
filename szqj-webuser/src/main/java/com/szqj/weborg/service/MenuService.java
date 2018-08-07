package com.szqj.weborg.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szqj.util.ConstantUtils;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.domain.AccountRepository;
import com.szqj.weborg.domain.Menu;
import com.szqj.weborg.domain.MenuRepository;
import com.szqj.weborg.rest.vo.MenuNode;

/**
 * 
 * @ClassName: AccountService
 * @Description: 账号登录类
 * @author zhanggy
 * 
 *
 */
@Service
@Transactional
public class MenuService {
	
	@Autowired
	private MenuRepository menuRepository;
	
	public List<MenuNode> getMenuTree(){
		List<MenuNode> l=new ArrayList<MenuNode>();
		
		Menu root = menuRepository.getRoot();
		if(root==null){
			root=new Menu();
			root.setParentMenuId("-1");
			root.setMenuName("菜单管理");
			menuRepository.save(root);
		}
		
		List<Menu> menus = menuRepository.getTreeNoes();
		Map<String,MenuNode> map=new HashMap<String,MenuNode>();
		MenuNode rootNode=change(root);
		map.put(root.getMenuId(), rootNode);
		
		for(Menu m:menus){
			map.put(m.getMenuId(),change(m));
		}
		
		for(Menu m:menus){
			String pid = m.getParentMenuId();
			MenuNode n = map.get(m.getMenuId());
			if(n==null){
				map.get(pid).addNode(change(m));
			}else{
				if(map.get(pid)==null){
					System.out.print(pid);
				}else{
				map.get(pid).addNode(n);
				}
			}
		}
		
		l.add(rootNode);
		return l;
	}
	
	private MenuNode change(Menu menu){
		MenuNode m=new MenuNode();
		m.setMenuId(menu.getMenuId());
		m.setMenuName(menu.getMenuName());
		m.setMenuUrl(menu.getMenuUrl());
		m.setParentMenuId(menu.getParentMenuId());
		m.setCode(menu.getCode()+"");
		return m;
	}
	


	
	
	

}
