package com.szqj.before.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



/**
 * 
 * @author lb12
 *
 */
public interface BeforeApplyRepository extends JpaRepository<BeforeApply, String> {
	
	@Query("select m from BeforeApply m where  m.applyOrgId=?1 and m.state=0")
	public Page<BeforeApply> findUnPageByApplyOrgId(String applyOrgId,Pageable pageable);
	
	@Query("select m from BeforeApply m where  m.applyOrgId=?1 and m.state=1")
	public Page<BeforeApply> findEndPageByApplyOrgId(String applyOrgId,Pageable pageable);
	
	@Query("select m from BeforeApply m where  m.applyOrgId=?1 and m.state=2")
	public Page<BeforeApply> findRepayPageByApplyOrgId(String applyOrgId,Pageable pageable);
	
	@Query("select m from BeforeApply m where  m.applyOrgId=?1 and m.state=3")
	public Page<BeforeApply> findRefusePageByApplyOrgId(String applyOrgId,Pageable pageable);
	
	@Query("select m from BeforeApply m where  m.applyOrgId=?1 and applyCode like %?2%  and m.state=2")
	public Page<BeforeApply> findRepayPageByOrgIdAndCode(String applyOrgId,String applyCode,Pageable pageable);
	
	@Query("select m from BeforeApply m where  m.applyOrgId=?1 and applyCode like %?2%  and m.state=3")
	public Page<BeforeApply> findRefusePageByOrgIdAndCode(String applyOrgId,String applyCode,Pageable pageable);
	
	@Query("select m from BeforeApply m where m.applyCode =?1 and m.companyId=?2   and m.state=3")
	public List<BeforeApply> getByCompanyIdAndCode(String applyCode,String companyId,Pageable pageable);
	
}
