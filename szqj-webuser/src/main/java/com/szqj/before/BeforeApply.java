package com.szqj.before;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "before_apply")
public class BeforeApply {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String beforeApplyId;//申请ID
	
	@Column
	private String applyOrgId;//申请所属管理机构id
	
	@Column
	private Date createDate;//申请开始时间
	
	@Column
	private String companyId;//申请企业id
	
	
	
	
	
	

}
