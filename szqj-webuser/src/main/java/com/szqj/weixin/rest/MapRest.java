package com.szqj.weixin.rest;

import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.szqj.util.RestJson;
import com.szqj.util.Tools;

@RestController
@RequestMapping("/weixin/")
@EnableAutoConfiguration
public class MapRest {
	
	@Autowired
	private RestTemplate httpsRestTemplate;
	
	@RequestMapping(value = "getAddress.wei"  )
	public RestJson getAddress(String longitude,String latitude){
		String ww="https://apis.map.qq.com";
		
		String url="/ws/geocoder/v1/?location=39.74788,116.14294&get_poi=1&key=6T3BZ-OJOKP-SUCDI-VQLQ3-OP3S6-A2FY6";
		String str=ww+url;
		String sk="DAdRyv3vq70VfRZwhrzpWo3zb72KvJy";
		String sn=Tools.MD5(URLEncoder.encode(url + sk));
		ResponseEntity<String> forEntity = httpsRestTemplate.getForEntity(
				str+"&sn="+sn, String.class);
		String html=forEntity.getBody();
		System.out.println(html);
		return RestJson.createSucces(html);
		
	}

}
