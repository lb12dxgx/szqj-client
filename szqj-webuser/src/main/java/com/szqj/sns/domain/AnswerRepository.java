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
public interface AnswerRepository extends PagingAndSortingRepository<Answer, String> {

	@Query("select m from Answer m  where m.problem.problemId=?1 order by m.createDate ")
	List<Answer> findByProblemId(String problemId);


}
