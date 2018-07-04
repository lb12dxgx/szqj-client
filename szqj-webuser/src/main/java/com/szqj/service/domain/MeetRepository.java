package com.szqj.service.domain;

import java.util.List;

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
public interface MeetRepository extends PagingAndSortingRepository<Meet, String> {


	@Query("select m from Meet m where m.meetName like %?1%  order by createDate")
	Page<Meet> findPageByMeetName(String meetName, Pageable pageable);
	
	@Query("select m from Meet m  order by createDate")
	Page<Meet> findPage(Pageable pageable);
	
	
	@Query("select m from Meet m where m.isSign=1 order by createDate")
	List<Meet> findByIsSign();
}
