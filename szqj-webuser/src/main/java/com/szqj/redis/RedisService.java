package com.szqj.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

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

}
