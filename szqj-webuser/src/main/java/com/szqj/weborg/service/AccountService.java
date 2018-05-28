package com.szqj.weborg.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szqj.util.ConstantUtils;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.domain.AccountRepository;
import com.szqj.weborg.domain.Menu;
import com.szqj.weborg.domain.MenuRepository;
import com.szqj.weborg.domain.RoleMenu;
import com.szqj.weborg.domain.RoleMenuRepository;
import com.szqj.weborg.domain.RoleUser;
import com.szqj.weborg.domain.RoleUserRepository;
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
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private RoleUserRepository roleUserRepository;
	@Autowired
	private RoleMenuRepository roleMenuRepository;

	public Account login(String accountName, String passsword) {
		Account account = new Account();
		List<Account> l = accountRepository.findByAccountName(accountName);
		if (l == null || l.size() == 0) {
			account.setLoginStr("用户名不存在"); 
		} else {
			if (StringUtils.isNotBlank(passsword)) {
				account = l.get(0);
				String md5Pass = Tools.MD5(passsword);
				if (!md5Pass.equals(account.getAccountPassword())) {
					account.setLoginStr("密码错误");
				}
			}else{
				account.setLoginStr("密码为空");
			}
		}

		account.setuPassword(passsword);

		return account;
	}
	
	
	public Page<Account> findPageByUserNameAndType(Integer accountType, String userName,Pageable pageable){
		if(StringUtils.isBlank(userName)){
			return accountRepository.findPageByAccountType(accountType, pageable);
		}
		return accountRepository.findPageByUserNameAndType(accountType, userName, pageable);
	}

	public boolean isExit(String accountName) {
		List<Account> l = accountRepository.findByAccountName(accountName);
		if (l == null || l.size() == 0) {
			return false;
		}

		return true;
	}

	public void save(Account account) {
		String pasw = account.getAccountPassword();
		String md5pasw = Tools.MD5(pasw);
		account.setAccountPassword(md5pasw);
		account.setState(ConstantUtils.ACCOUNT_STATE_START);
		accountRepository.save(account);
	}

	
	
	public Account getAccount(String accountId) {
		return accountRepository.findById(accountId).get();
	}

	

	public void update(Account account) {
		accountRepository.save(account);
	}

	public void delete(String accountId) {
		Account account=getAccount(accountId);
		account.setState(ConstantUtils.ACCOUNT_STATE_DEL);
		accountRepository.save(account);
	}
	
	public void stop(Account account) {
		account.setState(ConstantUtils.ACCOUNT_STATE_STOP);
		accountRepository.save(account);
	}

	public void reset(String accountId) {
		Account account=getAccount(accountId);
		account.setAccountPassword(ConstantUtils.ACCOUNT_DEFAULT_PASSWORD);
		save(account);
		
	}


	public void batchRemove(String ids) {
		String[] idAarray = StringUtils.split(ids,",");
		for(String id:idAarray){
			delete(id);
		}
		
	}
	
	public List<Account> findAllList(){
		return accountRepository.findAllList();
	}


	public List<MenuNode> getMenuListByToken(String accessToken) {
		Menu root = menuRepository.getRoot();
		List<MenuNode> l=new ArrayList<MenuNode>();
		
		List<Menu> modelList = menuRepository.getModelByParentId(root.getMenuId());
		LinkedHashMap<String,MenuNode> modelMap=new LinkedHashMap<String,MenuNode>();
		for(Menu model:modelList){
			modelMap.put(model.getMenuId(), change(model));
		}
		
		
		List<Menu> menulList = menuRepository.getAllMenus(root.getMenuId());
		
		Map<String,String> menuMap=new HashMap<String,String>();
		
		List<RoleUser> roleUsers = roleUserRepository.findByUserId(accessToken); 
		
		List<RoleMenu> roleMenus=new ArrayList<RoleMenu>();
		for(RoleUser roleUser:roleUsers){
			roleMenus.addAll(roleMenuRepository.findByRoleId(roleUser.getRoleId()));
		}
		
		if(roleMenus.size()!=0){
			for(RoleMenu rm:roleMenus){
				menuMap.put(rm.getMenuId(), rm.getMenuId());
			}
			
			Iterator<Menu> it = menulList.iterator();
			
			while(it.hasNext()){
				Menu m = it.next();
			    if(menuMap.get(m.getMenuId())!=null){
			    	MenuNode nemuNode = change(m);
			    	MenuNode modelNode = modelMap.get(nemuNode.getParentMenuId());
			    	modelNode.addNode(nemuNode);
			    	
			    }
			}
			
			Iterator<MenuNode> iterator = modelMap.values().iterator();
			
			while(iterator.hasNext()){
				MenuNode m = iterator.next();
				if(m.getChildren()!=null&&m.getChildren().size()>0){
					l.add(m);
				}
			}
			
			
		}
		
		return l;
		
	}


	private MenuNode change(Menu menu){
		MenuNode m=new MenuNode();
		m.setMenuId(menu.getMenuId());
		m.setMenuName(menu.getMenuName());
		m.setMenuUrl(menu.getMenuUrl());
		m.setParentMenuId(menu.getParentMenuId());
		return m;
	}
	
	

}
