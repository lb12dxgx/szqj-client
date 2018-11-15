package com.szqj.xcx.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itextpdf.io.util.UrlUtil;







public class CodeUtils {

	private static String WX_B_CODE_URL = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=ACCESS_TOKEN"; //不限次数 scene长度为32个字符
	
	private static String WX_Token_URL = "https://api.weixin.qq.com/cgi-bin/token"; //不限次数 scene长度为32个字符
 
	private static String APPID="wx9a605545c03d6b9e";
	private static String SECRET="5df1055596a0f04e6a36059b59311d28";
	
	
	
	public   String getToken() {
        try {
 
        	Map<String,String> param = new HashMap<>();
        	param.put("grant_type", "client_credential");
        	param.put("appid", APPID);//改成自己的appid
        	param.put("secret", SECRET);
        	String rt = sendPost(WX_Token_URL, param);
            
            System.out.println("what is:"+rt);
            JSONObject json = JSONObject.parseObject(rt);
 
            if (json.getString("access_token") != null || json.getString("access_token") != "") {
                return json.getString("access_token");
            } else {
                return null;
            }
       } catch (Exception e) {
   
            e.printStackTrace();
            return null;
        }
	}
 

	
	/**
	 * B接口生成小程序码
	 * @param access_token
	 * @param page
	 * @param scene
	 */
	public String createBCode(String access_token,String page,String shareCode,String filePath ){
	    String url = WX_B_CODE_URL.replace("ACCESS_TOKEN", access_token);
	   
	    Map<String,Object> param = new HashMap<>();
	    param.put("page", page);
	    param.put("scene", shareCode);
	    param.put("width", "280");
	    param.put("auto_color", false);
	    Map<String,Object> line_color = new HashMap<>();
	    line_color.put("r", 0);
	    line_color.put("g", 0);
	    line_color.put("b", 0);
	    param.put("line_color", line_color);
	    System.out.println(JSON.toJSONString(param));
	    JSONObject json = JSONObject.parseObject(JSON.toJSONString(param));
	  
	    try {
	        String imageUrl = httpPostWithJSON2(url, json.toString(), filePath);
	        return imageUrl;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	
	public static String sendPost(String url, Map<String, ?> paramMap) { 
		   PrintWriter out = null; 
		   BufferedReader in = null; 
		   String result = ""; 
		     
		   String param = ""; 
		Iterator<String> it = paramMap.keySet().iterator(); 
		  
		while(it.hasNext()) { 
		  String key = it.next(); 
		  param += key + "=" + paramMap.get(key) + "&"; 
		} 
		  
		   try { 
		     URL realUrl = new URL(url); 
		     // 打开和URL之间的连接 
		     URLConnection conn = realUrl.openConnection(); 
		     // 设置通用的请求属性 
		     conn.setRequestProperty("accept", "*/*"); 
		     conn.setRequestProperty("connection", "Keep-Alive"); 
		     conn.setRequestProperty("Accept-Charset", "utf-8"); 
		     conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)"); 
		     // 发送POST请求必须设置如下两行 
		     conn.setDoOutput(true); 
		     conn.setDoInput(true); 
		     // 获取URLConnection对象对应的输出流 
		     out = new PrintWriter(conn.getOutputStream()); 
		     // 发送请求参数 
		     out.print(param); 
		     // flush输出流的缓冲 
		     out.flush(); 
		     // 定义BufferedReader输入流来读取URL的响应 
		     in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")); 
		     String line; 
		     while ((line = in.readLine()) != null) { 
		       result += line; 
		     } 
		   } catch (Exception e) { 
		    System.out.println(e); 
		   } 
		   //使用finally块来关闭输出流、输入流 
		   finally{ 
		     try{ 
		       if(out!=null){ 
		         out.close(); 
		       } 
		       if(in!=null){ 
		         in.close(); 
		       } 
		     } 
		     catch(IOException ex){ 
		       ex.printStackTrace(); 
		     } 
		   } 
		   return result; 
		 } 


	

	//返回图片地址
	public String httpPostWithJSON2(String url, String json,String imagePath)
	        throws Exception {
	    
	    DefaultHttpClient httpClient = new DefaultHttpClient();
	    HttpPost httpPost = new HttpPost(url);
	    httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");

	    StringEntity se = new StringEntity(json);
	    se.setContentType("application/json");
	    se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"UTF-8"));
	    httpPost.setEntity(se);
	    HttpResponse response = httpClient.execute(httpPost);
	    
	    if (response != null) {
	        HttpEntity resEntity = response.getEntity();
	      
	        if (resEntity != null) {
	            InputStream instreams = resEntity.getContent();
	            //上传至资源服务器生成url
	            OutputStream os = new FileOutputStream(new File(imagePath));
	           
	            byte[] bs = new byte[1024];//缓冲数组
	            int len = -1;
	            while ((len = instreams.read(bs)) != -1) {
	            	os.write(bs, 0, len);
	            	 os.flush();
	            }
	            os.close();
	            instreams.close();
	        }
	    }
	    httpPost.abort();
	    return imagePath;
	}
	
	
	public static void main(String[] args) {
		
		/*CodeUtils codeUtils=new CodeUtils();
		String access_token =codeUtils.getToken();
		codeUtils.createBCode(access_token,"pages/index/main","a=123");*/
		
		
		String s = UUID.randomUUID().toString();
		System.out.println(s);
		System.out.println(s.length());
		
		
	}
	
	
}
