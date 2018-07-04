package com.szqj.cms.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;




/**
 * 
 * @author lb12
 *
 */
@Repository
@Transactional
public interface OutNewsInfoRepository extends JpaRepository< OutNewsInfo, String> {
	
	@Query("select m from OutNewsInfo m where m.url=?1")
	public List<OutNewsInfo> findByUrl(String url);
	
	@Query("select m from OutNewsInfo m where m.keyword=?1 and m.State=?2 order by level desc")
	public List<OutNewsInfo> findByKeywordAndState(String keyword,Integer State);
	
	@Query("select m from OutNewsInfo m where  m.State=0 order by level desc")
	public List<OutNewsInfo> findByState();
	 
	@Query("select m.keyword,count(*) as num  from OutNewsInfo m where  m.State=0 group by keyword order by num desc")
	public List<Object[]> findNumByKey();

	@Modifying
	@Query("update OutNewsInfo set State=3  where keyword=?1" )
	public void deleteByKeyWord(String keyWord);
	
}
