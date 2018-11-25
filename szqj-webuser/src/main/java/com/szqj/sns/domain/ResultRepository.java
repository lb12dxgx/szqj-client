package com.szqj.sns.domain;

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
public interface ResultRepository extends PagingAndSortingRepository<Result, String> {

	@Query("select m from Result m  where m.problem.problemId=?1 order by m.createDate ")
	List<Result> findByProblemId(String problemId);
	
	@Query("select m from Result m  where m.problem.problemId=?1 and m.type=1 order by m.createDate ")
	List<Result>  findOneByProblemId(String problemId);
	
	@Query("select m from Result m  where m.answerId=?1 and m.type=2 order by m.createDate ")
	Result  findTwoByAnswerId(String answerId);

	@Query("select m from Result m  where m.answerId=?1 and m.type=3 order by m.createDate ")
	Result  findThreeByAnswerId(String answerId);
	

}
