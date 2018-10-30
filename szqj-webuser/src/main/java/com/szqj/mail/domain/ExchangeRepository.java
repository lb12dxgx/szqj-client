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
public interface ExchangeRepository extends PagingAndSortingRepository<Exchange, String> {

	@Query("select m from Exchange m  where m.exchangeCode like %?1% and m.personName like %?2%  and m.state=?3 order by m.createDate ")
	Page<Exchange> findByExchangeCodeAndPersonName(String exchangeCode, String personName, int state, Pageable pageable);

	@Query("select m from Exchange m  where m.exchangeCode like %?1%   and m.state=?2 order by m.createDate ")
	Page<Exchange> findByExchangeCode(String exchangeCode, int state, Pageable pageable);

	@Query("select m from Exchange m  where  m.personName like %?1%  and m.state=?2 order by m.createDate ")
	Page<Exchange> findByPersonName(String personName, int state, Pageable pageable);
	
	
	@Query("select m from Exchange m  where m.state=1 order by m.createDate ")
	Page<Exchange> findFinsh( Pageable pageable);
	
	
	@Query("select m from Exchange m  where m.state=0 order by m.createDate ")
	Page<Exchange> findUnFinsh( Pageable pageable);

	@Query("select m from Exchange m  where m.yearnum=?1 and  m.monthnum=?2 and daynum=?3 order by m.createDate ")
	List<Exchange> findBYYearAndMonthAndDay(int year, int month, int date);




}
