package com.szqj.xcx.rest;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.mail.domain.RechargeRecord;
import com.szqj.mail.service.PayService;
import com.szqj.reg.service.RegService;
import com.szqj.service.domain.Person;
import com.szqj.util.RestJson;


@RestController
@RequestMapping("/xcx/login/pay/")
@EnableAutoConfiguration
public class XcxPayRest {
	
	@Autowired
	private PayService payService;
	
	@Autowired
	private RegService regService;
	

	
	
	
	@RequestMapping(value = "wxPay.xcx"  )
	public RestJson wxPay(RechargeRecord rechargeRecord,@ModelAttribute("openid") String openid){
		Person person = regService.getPersonByOpenid(openid);
		rechargeRecord.setOpenid(openid);
		rechargeRecord.setPersonId(person.getPersonId());
		rechargeRecord.setPersonName(person.getPersonName());
		rechargeRecord.setCreateDate(new Date());
	
		
		Map<String, String> map = payService.wxPay(rechargeRecord);
		return RestJson.createSucces(map);
		
	}
	
	
	
	/**
     * @Description:微信支付回调接口
     * @return
     * @throws Exception
     */
    @RequestMapping(value="wxNotify.xcx")
    @ResponseBody
    public void wxNotify(HttpServletRequest request,HttpServletResponse response) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine()) != null){
            sb.append(line);
        }
        br.close();
        //sb为微信返回的xml
        String notityXml = sb.toString();
        String resXml = payService.wxNotity(notityXml);
        System.out.println(resXml);
        System.out.println("微信支付回调数据结束");
 
 
        BufferedOutputStream out = new BufferedOutputStream(
                response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();

    }





}
