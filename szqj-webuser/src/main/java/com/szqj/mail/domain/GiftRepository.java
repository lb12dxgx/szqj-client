package com.szqj.mail.domain;

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
public interface GiftRepository extends PagingAndSortingRepository<Gift, String> {
	
	@Query("select m from Gift m  where m.state=0 order by m.createDate ")
	List<Gift> findSellGift();




}
