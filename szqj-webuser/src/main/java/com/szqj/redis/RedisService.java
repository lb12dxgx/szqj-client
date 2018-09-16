package com.szqj.redis;

import java.util.concurrent.TimeUnit;

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

}
