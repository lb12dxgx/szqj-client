package com.szqj.xcx.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.szqj.mail.domain.RefundRecord;

public class WxPay {
	
	public static String PAYURL="https://api.mch.weixin.qq.com/pay/unifiedorder";
	public static String SEARCH_URL="https://api.mch.weixin.qq.com/pay/orderquery";
	public static String REFUND_URL="https://api.mch.weixin.qq.com/secapi/pay/refund";
	public static String APPID="wx9a605545c03d6b9e";
	public static String MCHID="1518303601";
	public static String PAYKEY="f9044d88c9fa47978d39173230f02bad";
	public static String NOTIFY_URL="https://xcx.118-china.com/xcx/login/pay/wxNotify.xcx";
	
	
	
	public Map<String, String> refundByRefundRecord(RefundRecord refundRecord,String certPath ) throws Exception {
		
		String total_fee = String.valueOf(new BigDecimal(refundRecord.getPayMoney()).multiply(new BigDecimal(100)).intValue());
		String refund_fee = String.valueOf(new BigDecimal(refundRecord.getRefundMoney()).multiply(new BigDecimal(100)).intValue());
		String out_trade_no=refundRecord.getPayTradeNo();
		String out_refund_no=refundRecord.getRefundTradeNo();
		
		String nonce_str=PayUtil.radomStr(4);
		Map<String, String> packageParams = new HashMap<String, String>();
		packageParams.put("appid", APPID);
		packageParams.put("mch_id", MCHID);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("out_trade_no", out_trade_no);//商户订单号
		packageParams.put("out_refund_no", out_refund_no);//商户退款单号
		packageParams.put("total_fee",total_fee);//订单金额
		packageParams.put("refund_fee", refund_fee);//退款金额
		
		
		String prestr = PayUtil.createLinkString(packageParams); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串 
		
		//MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
		String mysign = PayUtil.sign(prestr, PAYKEY, "utf-8").toUpperCase();
		
		
		
		//拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
		String xml = "<xml>" + "<appid>" + APPID+ "</appid>" 
		        + "<mch_id>" + MCHID + "</mch_id>" 
		        + "<nonce_str>" + nonce_str + "</nonce_str>" 
		        + "<out_refund_no>" + out_refund_no + "</out_refund_no>" 
		        + "<out_trade_no>" + out_trade_no + "</out_trade_no>" 
		        + "<refund_fee>" + refund_fee + "</refund_fee>" 
		        + "<total_fee>" + total_fee + "</total_fee>" 
		        + "<sign>" + mysign + "</sign>"
		        + "</xml>";
		System.out.println("调试模式_统一退款接口 请求XML数据：" + xml);
		 
		//调用统一下单接口，并接受返回的结果
		String res = PayUtil.httpSSLRequest(REFUND_URL, "POST", xml, certPath, MCHID);
		
		System.out.println("调试模式_统一退款接口 返回XML数据：" + res);
		
		// 将解析结果存储在HashMap中   
     	Map<String,String> map = PayUtil.doXMLParse(res);
		
		String return_code = (String) map.get("return_code");//返回状态码
		
		Map<String, String> result = new HashMap<String, String>();//返回给小程序端需要的参数
		if("SUCCESS".equals(return_code)){  
			
			String result_code = (String) map.get("result_code");//返回的预付单信息  
			if(result_code.equals("SUCCESS")) {
				String finshMoney = (String) map.get("refund_fee");//返回的预付单信息  
				String refund_id=map.get("refund_id");
				
				result.put("finshMoney", finshMoney);
				result.put("refund_id", refund_id);
			}
		}
		
		return result;
		
		
	}
	
	
	
	
	

	public Map<String, String> searchByRefundTradeNo(String refundTradeNo) {
		String nonce_str=PayUtil.radomStr(4);
		Map<String, String> packageParams = new HashMap<String, String>();
		packageParams.put("appid", APPID);
		packageParams.put("mch_id", MCHID);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("out_refund_no", refundTradeNo);//商户订单号
		String prestr = PayUtil.createLinkString(packageParams); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串 
		
		//MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
		String mysign = PayUtil.sign(prestr, PAYKEY, "utf-8").toUpperCase();
		
		//拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
		String xml = "<xml>" + "<appid>" + APPID+ "</appid>" 
		        + "<mch_id>" + MCHID + "</mch_id>" 
		        + "<nonce_str>" + nonce_str + "</nonce_str>" 
		        + "<out_trade_no>" + refundTradeNo + "</out_trade_no>" 
		        + "<sign>" + mysign + "</sign>"
		        + "</xml>";
		System.out.println("调试模式_统一查询接口 请求XML数据：" + xml);
		 
		//调用统一下单接口，并接受返回的结果
		String res = PayUtil.httpRequest(SEARCH_URL, "POST", xml);
		
		System.out.println("调试模式_统一查询接口 返回XML数据：" + res);
		
		// 将解析结果存储在HashMap中   
     	Map<String,String> map = PayUtil.doXMLParse(res);
		
		String return_code = (String) map.get("return_code");//返回状态码
		
		Map<String, String> result = new HashMap<String, String>();//返回给小程序端需要的参数
		if("SUCCESS".equals(return_code)){  
			
			String trade_state = (String) map.get("trade_state");//返回的预付单信息  
			if(trade_state.equals("SUCCESS")||"REFUND".equals(trade_state)) {
				String time_end = (String) map.get("time_end");//返回的预付单信息  
				String total_fee = (String) map.get("total_fee");//返回的预付单信息  
				String transactionId=map.get("transaction_id");
				result.put("tradeState'", trade_state);
				result.put("finshDate", time_end);
				result.put("finshMoney", total_fee);
				result.put("transactionId", transactionId);
			}
		}
		
		return result;
	}
	
	
	
	
	
	
	
	public Map<String, String> goPay(PayModel paymentPo) throws Exception {
		//商品名称
		//String body = "测试商品名称";
		//金额元=paymentPo.getTotal_fee()*100
		String total_fee = String.valueOf(new BigDecimal(paymentPo.getTotal_fee()).multiply(new BigDecimal(100)).intValue());
		//组装参数，用户生成统一下单接口的签名
		String nonce_str=PayUtil.radomStr(4);
		Map<String, String> packageParams = new HashMap<String, String>();
		packageParams.put("appid", APPID);
		packageParams.put("mch_id", MCHID);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", paymentPo.getBody());
		packageParams.put("out_trade_no", paymentPo.getOut_trade_no());//商户订单号
		packageParams.put("total_fee", total_fee);//支付金额，这边需要转成字符串类型，否则后面的签名会失败
		packageParams.put("notify_url", NOTIFY_URL);//支付成功后的回调地址
		packageParams.put("trade_type", "JSAPI");//支付方式
		packageParams.put("openid", paymentPo.getOpenid());
		   
		String prestr = PayUtil.createLinkString(packageParams); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串 
		
		//MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
		String mysign = PayUtil.sign(prestr, PAYKEY, "utf-8").toUpperCase();
		
		//拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
		String xml = "<xml>" + "<appid>" + APPID+ "</appid>" 
		        + "<body><![CDATA[" + paymentPo.getBody() + "]]></body>" 
		        + "<mch_id>" + MCHID + "</mch_id>" 
		        + "<nonce_str>" + nonce_str + "</nonce_str>" 
		        + "<notify_url>" +NOTIFY_URL + "</notify_url>" 
		        + "<openid>" + paymentPo.getOpenid() + "</openid>" 
		        + "<out_trade_no>" + paymentPo.getOut_trade_no() + "</out_trade_no>" 
		       /* + "<spbill_create_ip>" + paymentPo.getSpbill_create_ip() + "</spbill_create_ip>" */
		        + "<total_fee>" + total_fee + "</total_fee>"
		        + "<trade_type>" + "JSAPI" + "</trade_type>" 
		        + "<sign>" + mysign + "</sign>"
		        + "</xml>";
		
		System.out.println("调试模式_统一下单接口 请求XML数据：" + xml);
 
		//调用统一下单接口，并接受返回的结果
		String res = PayUtil.httpRequest(PAYURL, "POST", xml);
		
		System.out.println("调试模式_统一下单接口 返回XML数据：" + res);
		
		
		
		
		// 将解析结果存储在HashMap中   
     	Map map = PayUtil.doXMLParse(res);
		
		String return_code = (String) map.get("return_code");//返回状态码
		
		Map<String, String> result = new HashMap<String, String>();//返回给小程序端需要的参数
		String prepay_id = null;
		if("SUCCESS".equals(return_code)){  
			prepay_id = (String) map.get("prepay_id");//返回的预付单信息   
 
		    result.put("nonceStr", nonce_str);
		    result.put("package", "prepay_id=" + prepay_id);
		    Long timeStamp = System.currentTimeMillis() / 1000;   
		    result.put("timeStamp", timeStamp + "");//这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误
		    //拼接签名需要的参数
		    String stringSignTemp = "appId=" + APPID + "&nonceStr=" + nonce_str + "&package=prepay_id=" + prepay_id+ "&signType=MD5&timeStamp=" + timeStamp;   
		    //再次签名，这个签名用于小程序端调用wx.requesetPayment方法
		    String paySign = PayUtil.sign(stringSignTemp, PAYKEY, "utf-8").toUpperCase();
		    
		    result.put("paySign", paySign);
		}
		result.put("appid", APPID);
		return result;

	}
	
	public Map<String, String> searchByPayTradeNo(String tradeNo ) throws Exception {
		
		String nonce_str=PayUtil.radomStr(4);
		Map<String, String> packageParams = new HashMap<String, String>();
		packageParams.put("appid", APPID);
		packageParams.put("mch_id", MCHID);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("out_trade_no", tradeNo);//商户订单号
		String prestr = PayUtil.createLinkString(packageParams); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串 
		
		//MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
		String mysign = PayUtil.sign(prestr, PAYKEY, "utf-8").toUpperCase();
		
		//拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
		String xml = "<xml>" + "<appid>" + APPID+ "</appid>" 
		        + "<mch_id>" + MCHID + "</mch_id>" 
		        + "<nonce_str>" + nonce_str + "</nonce_str>" 
		        + "<out_trade_no>" + tradeNo + "</out_trade_no>" 
		        + "<sign>" + mysign + "</sign>"
		        + "</xml>";
		System.out.println("调试模式_统一查询接口 请求XML数据：" + xml);
		 
		//调用统一下单接口，并接受返回的结果
		String res = PayUtil.httpRequest(SEARCH_URL, "POST", xml);
		
		System.out.println("调试模式_统一查询接口 返回XML数据：" + res);
		
		// 将解析结果存储在HashMap中   
     	Map<String,String> map = PayUtil.doXMLParse(res);
		
		String return_code = (String) map.get("return_code");//返回状态码
		
		Map<String, String> result = new HashMap<String, String>();//返回给小程序端需要的参数
		if("SUCCESS".equals(return_code)){  
			
			String trade_state = (String) map.get("trade_state");//返回的预付单信息  
			if(trade_state.equals("SUCCESS")||"REFUND".equals(trade_state)) {
				String time_end = (String) map.get("time_end");//返回的预付单信息  
				String total_fee = (String) map.get("total_fee");//返回的预付单信息  
				String transactionId=map.get("transaction_id");
				result.put("tradeState'", trade_state);
				result.put("finshDate", time_end);
				result.put("finshMoney", total_fee);
				result.put("transactionId", transactionId);
			}
		}
		
		return result;
		
		
	}
	
	
	public static void main(String[] args) throws Exception {
		WxPay pay=new WxPay();
		PayModel paymentPo=new PayModel();
		paymentPo.setBody("地下管线-培训费用");
		paymentPo.setOpenid("oopM65P0UPIeXQIJmMGlYrrQ4h8E");
		paymentPo.setOut_trade_no("12233");
		paymentPo.setTotal_fee("0.01");
	
		pay.goPay(paymentPo);
	}




	

}
