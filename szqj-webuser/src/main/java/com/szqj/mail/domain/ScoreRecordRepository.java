package com.szqj.mail.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;




/**
 * 
 * @author lb12
 *
 */
@Repository
public interface ScoreRecordRepository extends PagingAndSortingRepository<ScoreRecord, String> {

	@Query("select m from ScoreRecord m  where m.personName like %?1%  order by m.createDate ")
	Page<ScoreRecord> findByPersonName(String personName, Pageable pageable);
	
	@Query("select sum(m.num) from ScoreRecord m  where m.openid = ?1 ")
	Integer findSumByOpenid(String openid);




}
