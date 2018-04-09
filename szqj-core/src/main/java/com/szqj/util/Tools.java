package com.szqj.util;




import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.ModelMap;

/**
 * 通用的工具类
* @ClassName: Tools 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhanggy  
* @date 2016年1月5日 上午11:23:48 
*
 */
public class Tools {
	
	
 /**
  * 设置分页参数	
  * @param modelMap
  * @param page
  */
 public static void putPage(ModelMap modelMap,Page page){
		    Integer paginationSize=page.getSize();
		    Integer current =  page.getNumber() + 1;  
			Integer begin = Math.max(1, current - paginationSize/2);  
			Integer end = Math.min(begin + (paginationSize - 1), page.getTotalPages()); 
			if(end==0){
				end=1;
			}
			Long totalCount = page.getTotalElements();  
			modelMap.put("page", page);
			modelMap.put("current", current);
			modelMap.put("begin", begin);
			modelMap.put("end", end);
			modelMap.put("totalCount", totalCount);
			modelMap.put("total", page.getTotalPages());  
 }
	
	/**
	 * 返回初始的分页对象
	* @Title: getPage 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param pageNum
	* @param @param size
	* @param @return    参数说明 
	* @return PageRequest    返回类型 
	* @throws
	 */
	public static PageRequest getPage(Integer pageNum,Integer size ){
		if(pageNum==null){
			pageNum=0;
		}
		if(size==null){
			size=10;
		}
		
		return new PageRequest(pageNum,size);
	}
	
	/**
	 * 拷贝不为空的属性到目标对象中，主要用在更新操作里
	* @Title: copyBeanForUpdate 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param source 新对象
	* @param @param target 老对象
	* @return void    返回类型 
	* @throws
	 */
	public static void copyBeanForUpdate(Object newObject,Object oldObject){
		Map<String, Object> pMap=new HashMap<String, Object>();
		Map<String, Object> mMap=new HashMap<String, Object>();
		try {
			pMap = PropertyUtils.describe(newObject);
			mMap = PropertyUtils.describe(oldObject);
			
			for(String key:mMap.keySet()){
				if(!"class".equals(key)){
					Object value=pMap.get(key);
					if(value!=null){
						PropertyUtils.setProperty(oldObject, key, value);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	* @Title: MD5 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param planText
	* @param @return    参数说明 
	* @return String    返回类型 
	* @throws
	 */
	public static String MD5(String planText){
		String md5Str="";
		byte[] bytes;
		try {
			MessageDigest MD5 = MessageDigest.getInstance("MD5");
			bytes = MD5.digest(planText.getBytes());
			StringBuffer sb = new StringBuffer();
			for(byte b : bytes){
			int bt = b&0xff;
			if(bt<16){
			sb.append(0);
			}
				sb.append(Integer.toHexString(bt));
			}
			md5Str = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return md5Str;
	}
	
	
	 public static void main(String[] args) {
		 
		 System.out.println(MD5("lb14"));
		 
		
	}

}
