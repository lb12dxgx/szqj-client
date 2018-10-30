package com.szqj.mail.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 礼物
 * @author libingbing
 *
 */

@Entity
@Table(name = "mail_gift")
public class Gift {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String giftId;//礼物id
	
	private String name;//礼物名称
	
	private Integer price;//积分价格
	
	private String spec;//规格
	
	private Integer post;//0:免邮费 1:收邮费
	
	private Integer num;//礼物库存数量
	
	private Date createDate;//创建时间
	
	private Integer state=0;// 0:上架 1:下架
	
	private String picUrl;//礼物大图片地址
	
	private String smailPicUrl;//礼物小图片地址
	
	
	private  String descUrl;//礼物介绍地址

	public String getGiftId() {
		return giftId;
	}

	public void setGiftId(String giftId) {
		this.giftId = giftId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getPost() {
		return post;
	}

	public void setPost(Integer post) {
		this.post = post;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getSmailPicUrl() {
		return smailPicUrl;
	}

	public void setSmailPicUrl(String smailPicUrl) {
		this.smailPicUrl = smailPicUrl;
	}

	public String getDescUrl() {
		return descUrl;
	}

	public void setDescUrl(String descUrl) {
		this.descUrl = descUrl;
	}
	
	

}
