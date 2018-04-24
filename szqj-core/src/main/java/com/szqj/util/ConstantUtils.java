package com.szqj.util;

import java.util.HashMap;
import java.util.Map;

public class ConstantUtils {
	
	public static Integer DEL_FLAG=1;
	public static Integer NEW_FLAG=0;
	
	
	//系统类型cccdfffl;;;
	public static Integer SYSTEM_FRONT=0;
	public static Integer SYSTEM_BACK=1;
	
   public static Map systemTypeMap=new HashMap();
	
	static{
		systemTypeMap.put(SYSTEM_FRONT+"", "前台");
		systemTypeMap.put(SYSTEM_BACK+"", "后台");
		
		
	}
	
	
	//协会类型
		public static Integer COMPANY_OWN=0;
		public static Integer COMPANY_PART=1;
		public static Integer COMPANY_SAAS=2;
		
	    public static Map companyTypeMap=new HashMap();
		
		static{
			companyTypeMap.put(COMPANY_OWN+"", "自有");
			companyTypeMap.put(COMPANY_PART+"", "合作");
			companyTypeMap.put(COMPANY_SAAS+"", "租用");
		}
		
		//账号状态
		public static Integer ACCOUNT_STATE_START=0;
		public static Integer ACCOUNT_STATE_STOP=1;
		public static Integer ACCOUNT_STATE_DEL=2;
		
		public static Integer ACCOUNT_ADMIN=0;
		public static Integer ACCOUNT_BUSINESS=10;
		public static Integer ACCOUNT_COMPANY=20;
		public static Integer ACCOUNT_PERSON=30;
		
		//账号类型
		public static Integer ACCOUNT_PLAT_ADMIN=0;
		public static Integer ACCOUNT_COMP_ADMIN=10;
		public static Integer ACCOUNT_COMP_COM=11;
		public static Integer ACCOUNT_DEPA_COM=21;
		public static Integer ACCOUNT_CUST_ADMIN=30;
		public static Integer ACCOUNT_CUST_COM=31;
		
		
		
		
		 public static Map accountTypeMap=new HashMap();
		 
		 static{
			 accountTypeMap.put(ACCOUNT_PLAT_ADMIN+"", "云平台管理员");
			 accountTypeMap.put(ACCOUNT_COMP_ADMIN+"", "协会管理员");
			 accountTypeMap.put(ACCOUNT_COMP_COM+"", "协会业务人员");
			 accountTypeMap.put(ACCOUNT_DEPA_COM+"", "分协会业务人员");
			 accountTypeMap.put(ACCOUNT_CUST_ADMIN+"", "企业业务人员");
			 accountTypeMap.put(ACCOUNT_CUST_COM+"", "企业员工");
		}
		 
		 //默认密码
		 
		 public static String ACCOUNT_DEFAULT_PASSWORD="123456";
		 
		 //角色类型
		 public static Integer ROLE_TYPE_ADMIN=1;
		 public static Integer ROLE_TYPE_COM=0;
		 
		 public static Map roleTypeMap=new HashMap();
		 
		 static{
			 roleTypeMap.put(ROLE_TYPE_COM+"", "普通角色");
			 roleTypeMap.put(ROLE_TYPE_ADMIN+"", "管理角色");
			 
		}
		 
		 //注册账号类型
		 
		 public static Integer REG_PERSON=0;
		 public static Integer REG_COMPANY=1;
		 
		 public static Map regTypeMap=new HashMap();
		 
		 static{
			 regTypeMap.put(REG_PERSON+"", "个人用户");
			 regTypeMap.put(REG_COMPANY+"", "企业用户");
			 
		}
		 
}
