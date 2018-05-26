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
	
	
	public List<ColumnInfoNode> getTreeByPrivage(String[] columnIdList){
		List<ColumnInfoNode> l=new ArrayList<ColumnInfoNode>();
		
		Iterable<String> ids=java.util.Arrays.asList(columnIdList);
		List<ColumnInfo> exitList = columnInfoRepository.findAllById(ids);
		ColumnInfo root = columnInfoRepository.getRoot();
		List<ColumnInfo> ColumnInfos = columnInfoRepository.getTreeNoes();
		Map<String,ColumnInfoNode> map=new HashMap<String,ColumnInfoNode>();
		ColumnInfoNode rootNode=change(root);

		for(ColumnInfo m:ColumnInfos){
			ColumnInfoNode node=change(m);
			map.put(m.getColumnId(),node);
			
		}
		
		Map<String,ColumnInfoNode> pmap=new HashMap<String,ColumnInfoNode>();
		
		for(ColumnInfo m:exitList){
			String pid = m.getParentId();
			
			if(!pid.equals(rootNode.getColumnId())&&!pid.equals("-1")){
				
				ColumnInfoNode node=change(m);
				ColumnInfoNode pnode=pmap.get(pid);
				if(pnode==null){
					pnode = map.get(pid);
					rootNode.addNode(pnode);
					pmap.put(pnode.getColumnId(), pnode);
				}
				pnode.addNode(node);
			}
			
		}
		
		l.add(rootNode);
		return l;
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
