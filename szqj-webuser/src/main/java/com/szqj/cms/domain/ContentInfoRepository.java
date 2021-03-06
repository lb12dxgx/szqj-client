package com.szqj.cms.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * 
 * @author lb12
 *
 */
@Repository
public interface ContentInfoRepository extends JpaRepository<ContentInfo, String> {
	
	@Query("select m from ContentInfo m where m.columnId =?1  order by level, createDate desc")
	public Page<ContentInfo> findByColumnId(String columnId,Pageable pageable);
	
	@Query("select m from ContentInfo m where m.columnId =?1 and m.contentTitle like %?2% order by createDate desc")
	public Page<ContentInfo> findByColumnId(String columnId,String contentTitle, Pageable pageable);
	
	@Query("select m from ContentInfo m where m.columnId in (select columnId from  ColumnInfo where parentId=?1 ) and m.level =?2 order by createDate desc")
	public List<ContentInfo> findByParntIdAndLevel(String parentId,Integer level);
	
	
	@Query("select m from ContentInfo m where m.columnId =?1 and m.level =?2 order by createDate desc")
	public List<ContentInfo> findByColumnIdAndLevel(String columnId,Integer level);
	
	@Query("select m from ContentInfo m where m.columnId =?1  order by createDate desc")
	public List<ContentInfo> findListByColumnId(String columnId);
	
	
	@Query("select count(m.contentId) from ContentInfo m where m.columnId =?1 ")
	public Integer findNumByColumnId(String columnId);
}
