package com.szqj.mail.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szqj.mail.domain.RechargeRecord;
import com.szqj.mail.domain.RechargeRecordRepository;
import com.szqj.mail.domain.RefundRecord;
import com.szqj.mail.domain.RefundRecordRepository;
import com.szqj.sns.domain.Problem;
import com.szqj.sns.domain.ProblemRepository;
import com.szqj.xcx.util.PayModel;
import com.szqj.xcx.util.PayUtil;
import com.szqj.xcx.util.WxPay;

@Service
@Transactional
public class PayService {

	@Autowired
	private  RechargeRecordRepository rechargeRecordRepository;
	
	@Autowired
	private  RefundRecordRepository refundRecordRepository;
	@Value("${web.cert}")
	private String certPath;
	
	@Autowired
	private ProblemRepository problemRepository;
	
	  
	
	
	public RefundRecord refundWxPay(String payTradeNo,Double refundMoney) {
		RechargeRecord rechargeRecord = rechargeRecordRepository.findByTradeNo(payTradeNo);

		 RefundRecord  refundRecord=new  RefundRecord();
		 refundRecord.setCreateDate(new Date());
		 refundRecord.setPayMoney(rechargeRecord.getMoney());
		 refundRecord.setRefundMoney(refundMoney);
		 refundRecord.setRechargeRecordId(rechargeRecord.getRechargeRecordId());
		 refundRecord.setState(0);
		 refundRecord.setBusinessContent(rechargeRecord.getBusinessContent());
		 refundRecord.setBusinessType(rechargeRecord.getBusinessType());
		 
		 
		 refundRecordRepository.save(refundRecord);
		 
		 String rechargeRecordId = refundRecord.getRechargeRecordId();
		 String refundTradeNo=rechargeRecordId.replaceAll("-", "");
		 refundRecord.setRefundTradeNo(refundTradeNo);
		 refundRecord.setPayTradeNo(payTradeNo);
		 refundRecordRepository.save(refundRecord);
		 
		Map<String, String> map=new HashMap<String,String>();
		try {
			WxPay wxPay=new WxPay();
			map = wxPay.refundByRefundRecord(refundRecord,certPath);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			Problem problem = problemRepository.findById(refundRecord.getBusinessContent()).get();
			problem.setRefundDate(new Date());
			problem.setRefundState(3);
			problemRepository.save(problem);
			refundRecord.setState(3);
			refundRecordRepository.save(refundRecord);
			
		}
		 String result_code = map.get("result_code");
		 if("FAIL".equals(result_code)) {
			 Problem problem = problemRepository.findById(refundRecord.getBusinessContent()).get();
				problem.setRefundDate(new Date());
				problem.setRefundState(3);
				problem.setRefundDesc(map.get("err_code_des"));
				problemRepository.save(problem);
				refundRecord.setState(3);
				refundRecordRepository.save(refundRecord);
		 }else {
		
			String finshMoney = map.get("finshMoney");
			String refundId = map.get("refund_id");
			refundRecord.setFinshMoney(Integer.parseInt(finshMoney));
			refundRecord.setRefundId(refundId);
			refundRecordRepository.save(refundRecord);
			
			if(refundRecord.getBusinessType().equals("1")){
				Problem problem = problemRepository.findById(refundRecord.getBusinessContent()).get();
				problem.setRefundDate(new Date());
				problem.setRefundState(2);
				problemRepository.save(problem);
			}
		 }
		
		
		return refundRecord;
		
	}
	
	
	public void updateRefundWxPay(String payTradeNo,Double refundMoney,String desc) {
		List<RefundRecord> list = refundRecordRepository.findByState(0);
		
		Map<String, String> map=new HashMap<String,String>();
		for(RefundRecord refundRecord:list) {
			try {
				WxPay wxPay=new WxPay();
				map = wxPay.searchByRefundTradeNo(refundRecord.getRefundTradeNo());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			if("SUCCESS".equals(map.get("tradeState"))){
				refundRecord.setFinshDate(map.get("finshDate"));
				refundRecord.setFinshMoney(Integer.parseInt(map.get("finshMoney")));
				refundRecord.setState(1);
				refundRecordRepository.save(refundRecord);
			}
			
			if("REFUND".equals(map.get("tradeState"))){
				refundRecord.setFinshDate(map.get("finshDate"));
				refundRecord.setFinshMoney(Integer.parseInt(map.get("finshMoney")));
				refundRecord.setState(3);
				refundRecordRepository.save(refundRecord);
			}
		}
		
		
		
	}
	
	public void updateWxPay() {
		List<RechargeRecord> list = rechargeRecordRepository.findByState(0);
		Map<String, String> map=new HashMap<String,String>();
		for(RechargeRecord rechargeRecord:list) {
			try {
				WxPay wxPay=new WxPay();
				map = wxPay.searchByPayTradeNo(rechargeRecord.getTradeNo());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			if("SUCCESS".equals(map.get("tradeState"))){
				rechargeRecord.setFinshDate(map.get("finshDate"));
				rechargeRecord.setFinshMoney(Double.parseDouble(map.get("finshMoney")));
				rechargeRecord.setState(1);
				rechargeRecordRepository.save(rechargeRecord);
			}
			
			if("REFUND".equals(map.get("tradeState"))){
				rechargeRecord.setFinshDate(map.get("finshDate"));
				rechargeRecord.setFinshMoney(Double.parseDouble(map.get("finshMoney")));
				rechargeRecord.setState(2);
				rechargeRecordRepository.save(rechargeRecord);
			}
		}
	}
	
	public Map<String,String> wxPay(RechargeRecord rechargeRecord) {
		rechargeRecordRepository.save(rechargeRecord);
		String rechargeRecordId = rechargeRecord.getRechargeRecordId();
		String tradeNo=rechargeRecordId.replaceAll("-", "");
		rechargeRecord.setTradeNo(tradeNo);
		rechargeRecord.setState(0);
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
            	rechargeRecord.setFinshMoney(Double.parseDouble(finshMoney));
            	
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
