package com.szqj.service.domain;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.szqj.train.domain.TrainClass;




/**
 * 
 * @author lb12
 *
 */
@Repository
public interface ProductShowRepository extends PagingAndSortingRepository<ProductShow, String> {

	@Query("select m from ProductShow m   order by  orderNum")
	List<ProductShow> findList();
	
}
