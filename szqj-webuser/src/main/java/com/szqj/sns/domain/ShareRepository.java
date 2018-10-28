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
public interface ShareRepository extends PagingAndSortingRepository<Share, String> {
	
	@Query("select m from Share m  where m.problem.problemId=?1 order by m.createDate ")
	List<Share> findByProblemId(String problemId);

	@Query("select m.problem from Share m  where m.openid=?1 order by m.createDate ")
	Page<Problem> findByOpenid(String openid, Pageable pageable);

	

}
