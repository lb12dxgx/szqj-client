package com.szqj.mail.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 退款订单
 * @author lb12
 *
 */
@Entity
@Table(name = "mail_refund_record")
public class RefundRecord {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String refundRecordId;//退款记录ID
	
	private String rechargeRecordId;//充值记录ID
	
	private String payTradeNo;//支付订单编号
	
	private Double payMoney;//支付金额(元)
	
	private Double refundMoney;//退款金额(元)
	
	private String refundTradeNo;//退款订单号32
	
	private Integer state;//0:未到账  1:已到账
	
	private Date createDate;//创建时间
	
	private String finshDate;//订单号完成时间
	
	private Integer finshMoney;//实际金额
	
	private String refundId;//微信交易id

	public String getRefundRecordId() {
		return refundRecordId;
	}

	public void setRefundRecordId(String refundRecordId) {
		this.refundRecordId = refundRecordId;
	}

	public String getRechargeRecordId() {
		return rechargeRecordId;
	}

	public void setRechargeRecordId(String rechargeRecordId) {
		this.rechargeRecordId = rechargeRecordId;
	}

	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
   

	public String getRefundTradeNo() {
		return refundTradeNo;
	}

	public void setRefundTradeNo(String refundTradeNo) {
		this.refundTradeNo = refundTradeNo;
	}

	public String getFinshDate() {
		return finshDate;
	}

	public void setFinshDate(String finshDate) {
		this.finshDate = finshDate;
	}

	public Integer getFinshMoney() {
		return finshMoney;
	}

	public void setFinshMoney(Integer finshMoney) {
		this.finshMoney = finshMoney;
	}

	

	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	public String getPayTradeNo() {
		return payTradeNo;
	}

	public void setPayTradeNo(String payTradeNo) {
		this.payTradeNo = payTradeNo;
	}

	public Double getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}

	public Double getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(Double refundMoney) {
		this.refundMoney = refundMoney;
	}
	
	
	
	

	
	
	
	
	
}
