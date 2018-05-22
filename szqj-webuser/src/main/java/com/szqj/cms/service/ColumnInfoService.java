package com.szqj.cms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szqj.cms.domain.ColumnInfo;
import com.szqj.cms.domain.ColumnInfoRepository;
import com.szqj.cms.rest.vo.ColumnInfoNode;

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
public class ColumnInfoService {
	
	@Autowired
	private ColumnInfoRepository columnInfoRepository;
	
	public List<ColumnInfoNode> getColumnTree(){
		List<ColumnInfoNode> l=new ArrayList<ColumnInfoNode>();
		
		ColumnInfo root = columnInfoRepository.getRoot();
		if(root==null){
			root=new ColumnInfo();
			root.setParentId("-1");
			root.setColumnName("菜单管理");
			columnInfoRepository.save(root);
		}
		
		List<ColumnInfo> ColumnInfos = columnInfoRepository.getTreeNoes();
		Map<String,ColumnInfoNode> map=new HashMap<String,ColumnInfoNode>();
		ColumnInfoNode rootNode=change(root);
		map.put(root.getColumnId(), rootNode);
		
		for(ColumnInfo m:ColumnInfos){
			map.put(m.getColumnId(),change(m));
		}
		
		for(ColumnInfo m:ColumnInfos){
			String pid = m.getParentId();
			ColumnInfoNode n = map.get(m.getColumnId());
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
	
	
	public List<ColumnInfoNode> getTreeByRoleId(String[] columnIdList){
		Iterable<String> ids=java.util.Arrays.asList(columnIdList);
		List<ColumnInfo> exitList = columnInfoRepository.findAllById(ids);
		ColumnInfo root = columnInfoRepository.getRoot();
		List<ColumnInfo> ColumnInfos = columnInfoRepository.getTreeNoes();
		Map<String,ColumnInfoNode> map=new HashMap<String,ColumnInfoNode>();
		ColumnInfoNode rootNode=change(root);
		map.put(root.getColumnId(), rootNode);
		
		for(ColumnInfo m:ColumnInfos){
			map.put(m.getColumnId(),change(m));
		}
		
		for(ColumnInfo m:exitList){
			String pid = m.getParentId();
			ColumnInfoNode parent = map.get(pid);
			if(parent.getColumnId().equals(root.getColumnId())) {
				List<ColumnInfoNode> l = rootNode.getChildren();
				l.contains(parent)
			}
			parent.addNode(change(m));
			addParent(parent,map);
		}
	}
	
	

	private void addParent(ColumnInfoNode node, Map<String, ColumnInfoNode> map) {
		if(node.getParentId()!='-1') {
			
		}else {S
			return ;
		}
		
	}


	private ColumnInfoNode change(ColumnInfo columnInfo){
		ColumnInfoNode m=new ColumnInfoNode();
		m.setColumnId(columnInfo.getColumnId());
		m.setColumnName(columnInfo.getColumnName());
		m.setParentId(columnInfo.getParentId());
		m.setColumnCode(columnInfo.getColumnCode());
		m.setColumnJson(columnInfo.getColumnJson());
		m.setLogin(columnInfo.getLogin());
		m.setState(columnInfo.getState());
		return m;
	}
	


	
	
	

}
