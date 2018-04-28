package com.szqj.before.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szqj.before.domain.ApplyOrg;
import com.szqj.before.domain.ApplyOrgRepository;
import com.szqj.before.domain.BeforeApply;
import com.szqj.before.domain.BeforeApplyRepository;
import com.szqj.util.ConstantUtils;

@Service
@Transactional
public class BeforeApplyService {

	@Autowired
	private BeforeApplyRepository beforeApplyRepository;
	
	@Autowired
	private ApplyOrgRepository applyOrgRepository;
	
	public void create(BeforeApply beforeApply) {
		String applyCode=genApplyCode(beforeApply.getApplyOrgId());
		beforeApply.setApplyCode(applyCode);
		beforeApply.setCreateDate(new Date());
		beforeApply.setState(ConstantUtils.APPLY_UN);
		beforeApplyRepository.save(beforeApply);
	}

	
	private String genApplyCode(String applyOrgId) {
		String applyCode="";
		ApplyOrg applyOrg = applyOrgRepository.findById(applyOrgId).get();
		String orgCode = applyOrg.getOrgCode();
		Date day=new Date();    
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd"); 
		String curDatestr=df.format(day);
		List<BeforeApply> l = beforeApplyRepository.findByApplyCode(orgCode+"-"+curDatestr+"-");
		if(l==null||l.size()==0){
			applyCode="001";
		}else{
			String code=l.get(0).getApplyCode();
			applyCode = getApplyCode(code.split("-")[2]);  
		}
		  
		return orgCode+"-"+curDatestr+"-"+applyCode;
	}

	private String getApplyCode(String code) {
		int codeValue = Integer.parseInt(code);
		codeValue=codeValue+1;
		String applyCode = String.format("%03d", codeValue);
		return applyCode;
	}
	
	
	public static void main(String[] args) { 
		BeforeApplyService b=new BeforeApplyService();
		System.out.println(b.getApplyCode("001"));
		
		System.out.println(b.getApplyCode("009"));
		
		System.out.println(b.getApplyCode("010"));
		
		System.out.println(b.getApplyCode("099"));
	}
	
	
}
