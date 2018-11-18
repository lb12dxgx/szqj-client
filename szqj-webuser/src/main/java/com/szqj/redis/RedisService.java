package com.szqj.redis;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.szqj.util.Tools;

@Service
public class RedisService {
	
	@Autowired 
	private StringRedisTemplate stringRedisTemplate;
	
	
	public void putCityPhoneAndSmsCode(String tel,String smsCode) {
		stringRedisTemplate.opsForValue().set("city"+tel,smsCode,3, TimeUnit.MINUTES);

	}
	
	
	public String  getSmsCodeByCityPhone(String tel) {
	    if(stringRedisTemplate.hasKey("city"+tel)) {
	    	return stringRedisTemplate.opsForValue().get("city"+tel);
	    }
	    
	    return "";
	}
	
	public String putOpenId(String openid) {
		String openIdMd5 = Tools.MD5(openid);
		stringRedisTemplate.opsForValue().set("openid"+openIdMd5,openid,30, TimeUnit.DAYS);
		return openIdMd5;
	}
	
	
	public String  getOpenId(String openIdMd5) {
	    if(stringRedisTemplate.hasKey("openid"+openIdMd5)) {
	    	return stringRedisTemplate.opsForValue().get("openid"+openIdMd5);
	    }
	    
	    return "";
	}

	
	public  String getOpenIdAndProblemId(String key){
		return stringRedisTemplate.opsForValue().get(key);
		
	}
	
	public synchronized String putOpenIdAndProblemId(String openId,String problemId){
		String key=getIdByDay();
		stringRedisTemplate.opsForValue().set(key,openId+"|"+problemId);
		return key;
	}
	
	private String getIdByDay(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		String yearStr = calendar.get(Calendar.YEAR)+"";//获取年份
        int month = calendar.get(Calendar.MONTH) + 1;//获取月份
        String monthStr = month < 10 ? "0" + month : month + "";
        int day = calendar.get(Calendar.DATE);//获取日
        String dayStr = day < 10 ? "0" + day : day + "";
		String key="idday"+yearStr+monthStr+ dayStr;
		String numStr = stringRedisTemplate.opsForValue().get(key);
		String value="1";
		if(StringUtils.isBlank(numStr)){
			stringRedisTemplate.opsForValue().set(key,value,2, TimeUnit.DAYS);
		}else{
			value=Integer.parseInt(numStr)+1+"";
			stringRedisTemplate.opsForValue().set(key,value,2, TimeUnit.DAYS);
		}
		
		  return key+value;
	}

}
