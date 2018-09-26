package com.szqj.xcx.rest;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;

@RestController
@RequestMapping("/xcx/map/")
@EnableAutoConfiguration
public class XcxMapRest {
	
	@Autowired
	private RestTemplate httpsRestTemplate;
	
	@RequestMapping(value = "getAddress.xcx"  )
	public RestJson getAddress(String longitude,String latitude){
		String ww="https://apis.map.qq.com";
		String zb=latitude+","+longitude;
		String url="/ws/geocoder/v1/?location="+zb+"&get_poi=1&key=6T3BZ-OJOKP-SUCDI-VQLQ3-OP3S6-A2FY6";
		String str=ww+url;
		String sk="DAdRyv3vq70VfRZwhrzpWo3zb72KvJy";
		String sn=Tools.MD5(URLEncoder.encode(url + sk));
		ResponseEntity<JSONObject> forEntity = httpsRestTemplate.getForEntity(
				str+"&sn="+sn, JSONObject.class);
		 JSONObject json = forEntity.getBody();
		 String address = json.getJSONObject("result").getString("address");
		 JSONObject addressJson =json.getJSONObject("result").getJSONObject("formatted_addresses");
		 if(addressJson!=null){
			 String recommend = addressJson.getString("recommend");
			 if(StringUtils.isNotBlank(recommend)){
				 address=recommend;
			 }
		 }
		System.out.println(address);
		return RestJson.createSucces(address);
		
	}
	
	
	@RequestMapping(value = "getCity.xcx"  )
	public RestJson getCity(String longitude,String latitude){
		String ww="https://apis.map.qq.com";
		String zb=latitude+","+longitude;
		String url="/ws/geocoder/v1/?location="+zb+"&get_poi=1&key=6T3BZ-OJOKP-SUCDI-VQLQ3-OP3S6-A2FY6";
		String str=ww+url;
		String sk="DAdRyv3vq70VfRZwhrzpWo3zb72KvJy";
		String sn=Tools.MD5(URLEncoder.encode(url + sk));
		ResponseEntity<JSONObject> forEntity = httpsRestTemplate.getForEntity(
				str+"&sn="+sn, JSONObject.class);
		 JSONObject json = forEntity.getBody();
		
		 JSONObject addressJson =json.getJSONObject("result").getJSONObject("address_component");
		 String province = addressJson.getString("province");
		 String city = addressJson.getString("city");
		 String district = addressJson.getString("district");
		 Map map=new HashMap();
		 map.put("province", province);
		 map.put("city", city);
		 map.put("district", district);
		 return RestJson.createSucces(map);
		
	}

}
