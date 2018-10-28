package com.szqj.sns.domain;

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
public interface ProblemRepository extends PagingAndSortingRepository<Problem, String> {

	@Query("select m from Problem m  where m.openid=?1 order by m.createDate ")
	Page<Problem> findPageByOpenid(String openid, Pageable pageable);

	

}
