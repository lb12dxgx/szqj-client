package com.szqj.service.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "service_product_show")
public class ProductShow {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String productShowtId; 
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private  Product  product;
	
	private String productPicId;//产品图片标识
	
	private String productUrl;//产品地址
	
	private Integer orderNum;//顺序
	
	private Date createDate;//创建时间
	
	@Transient
	private String productPicPath;//产品图片

	public String getProductShowtId() {
		return productShowtId;
	}

	public void setProductShowtId(String productShowtId) {
		this.productShowtId = productShowtId;
	}

	

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getProductPicId() {
		return productPicId;
	}

	public void setProductPicId(String productPicId) {
		this.productPicId = productPicId;
	}

	public String getProductUrl() {
		return productUrl;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getProductPicPath() {
		return productPicPath;
	}

	public void setProductPicPath(String productPicPath) {
		this.productPicPath = productPicPath;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	
	

}
