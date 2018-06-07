package com.szqj.weborg.service;

import java.util.List;



public class ZtbPage {
	
	
	private String total;
	private String searchtype;
	private String size;
	private String page;
	private String message;
	private String status;
	private List<Ztb> li; 
	
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getSearchtype() {
		return searchtype;
	}
	public void setSearchtype(String searchtype) {
		this.searchtype = searchtype;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	public List<Ztb> getLi() {
		return li;
	}
	public void setLi(List<Ztb> li) {
		this.li = li;
	}


}
