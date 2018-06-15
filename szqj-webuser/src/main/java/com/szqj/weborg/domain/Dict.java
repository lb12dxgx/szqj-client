package com.szqj.weborg.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.szqj.weborg.rest.vo.MenuNode;

/**
 * 系统字典表
 * @author lb12
 *
 */
@Entity
@Table(name = "webuser_dict")
public class Dict { 
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String dictId;//字典表ID
	
	private  String dictName;//字典表名称
	
	private String dictValue;//字典表值e
	
	private Integer orderNum;//顺序
    
	private String pdictId;//父节点id
	
	@Transient
	private List<Dict> children=new ArrayList<Dict>(); 

	

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getDictValue() {
		return dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

	public String getPdictId() {
		return pdictId;
	}

	public void setPdictId(String pdictId) {
		this.pdictId = pdictId;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public List<Dict> getChildren() {
		return children;
	}

	public void setChildren(List<Dict> children) {
		this.children = children;
	}
	
	
}
