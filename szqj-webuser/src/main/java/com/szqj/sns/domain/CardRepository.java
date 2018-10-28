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
public interface CardRepository extends PagingAndSortingRepository<Card, String> {
	@Query("select m from Card m  where m.openid=?1 ")
	List<Card> findByOpenid(String openid);

	


}
