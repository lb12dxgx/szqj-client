package com.szqj.mail.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szqj.mail.domain.RechargeRecord;
import com.szqj.mail.domain.RechargeRecordRepository;
import com.szqj.xcx.util.PayModel;
import com.szqj.xcx.util.PayUtil;
import com.szqj.xcx.util.WxPay;

@Service
@Transactional
public class PayService {

	@Autowired
	private  RechargeRecordRepository rechargeRecordRepository;
	
	public Map<String,String> wxPay(RechargeRecord rechargeRecord) {
		rechargeRecordRepository.save(rechargeRecord);
		String rechargeRecordId = rechargeRecord.getRechargeRecordId();
		String tradeNo=rechargeRecordId.replaceAll("-", "");
		rechargeRecord.setTradeNo(tradeNo);
		rechargeRecordRepository.save(rechargeRecord);
		PayModel paymentPo=new PayModel();
		paymentPo.setBody(rechargeRecord.getBusinessContent());
		paymentPo.setOpenid(rechargeRecord.getOpenid());
		paymentPo.setOut_trade_no(tradeNo);
		paymentPo.setTotal_fee(rechargeRecord.getMoney()+"");
		
		Map<String, String> map = new HashMap<String,String>();
		try {
			WxPay wxPay=new WxPay();
			map = wxPay.goPay(paymentPo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	
	public String wxNotity(String notityXml) {
		String resXml = "";
        Map<String,String> map = PayUtil.doXMLParse(notityXml);
        String returnCode = (String) map.get("return_code");
        if("SUCCESS".equals(returnCode)){
            //验证签名是否正确
            Map<String, String> validParams = PayUtil.paraFilter(map);  //回调验签时需要去除sign和空值参数
            String validStr = PayUtil.createLinkString(validParams);//把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
            String sign = PayUtil.sign(validStr, WxPay.PAYKEY, "utf-8").toUpperCase();//拼装生成服务器端验证的签名
            //根据微信官网的介绍，此处不仅对回调的参数进行验签，还需要对返回的金额与系统订单的金额进行比对等
            if(sign.equals(map.get("sign"))){
            	String tradeNo=map.get("out_trade_no");	
            	String finshMoney=map.get("total_fee");
            	String transactionId=map.get("transaction_id");
            	String finshDate=map.get("time_end");
            	RechargeRecord rechargeRecord = rechargeRecordRepository.findByTradeNo(tradeNo);
            	
            	rechargeRecord.setState(1);
            	rechargeRecord.setFinshDate(finshDate);
            	rechargeRecord.setTransactionId(transactionId);
            	rechargeRecord.setFinshMoney(Integer.parseInt(finshMoney));
            	
            	rechargeRecordRepository.save(rechargeRecord);
            	
                /**此处添加自己的业务逻辑代码start**/
 
 
                /**此处添加自己的业务逻辑代码end**/
                //通知微信服务器已经支付成功
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
            }
        }else{
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }
		return resXml;
	}

}
