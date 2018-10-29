package com.szqj.mail.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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




}
