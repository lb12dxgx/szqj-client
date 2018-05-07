package com.szqj.cms.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.szqj.reg.domain.RegInfo;


/**
 * 
 * @author lb12
 *
 */
public interface ColumnInfoRepository extends JpaRepository<ColumnInfo, String> {
	
	@Query("select m from ColumnInfo m where m.parentId =-1")
	public ColumnInfo getRoot();
	
	
	@Query("select m from ColumnInfo m where m.parentId !=-1 order by columnCode")
	public List<ColumnInfo> getTreeNoes();
	
	
	public ColumnInfo findByColumnCode(String columnCode);
	
	@Query("select m from ColumnInfo m where m.parentId =?1 order by columnCode")
	public List<ColumnInfo> findByParentId(String parentId);
	
}
