package com.szqj.service.domain;

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
public interface AccidentInfoRepository extends PagingAndSortingRepository<AccidentInfo, String> {


	@Query("select m from AccidentInfo m where m.accountId=?1  order by m.creatDate desc")
	Page<AccidentInfo> findPageByAccountId(String accountId,Pageable pageable);
	


	@Query("select m from AccidentInfo m  order by m.creatDate desc")
	Page<AccidentInfo> findAllPage(Pageable pageable);


	@Query("select m from AccidentInfo m where m.accidentName like %?1% and m.state=0 order by m.creatDate desc")
	Page<AccidentInfo> findUnPageByAccidentName(String accidentName, Pageable pageable);


	@Query("select m from AccidentInfo m where m.state=0 order by m.creatDate desc")
	Page<AccidentInfo> findUnPage(Pageable pageable);


	@Query("select m from AccidentInfo m where m.accidentName like %?1% and m.state=1 order by m.creatDate desc")
	Page<AccidentInfo> findPassPageByAccidentName(String accidentName, Pageable pageable);


	@Query("select m from AccidentInfo m where m.state=1 order by m.creatDate desc")
	Page<AccidentInfo> findPassPage(PageRequest pageable);


	@Query("select m from AccidentInfo m where m.accidentName like %?1% and m.state=2 order by m.creatDate desc")
	Page<AccidentInfo> findNoPassPageByAccidentName(String accidentName, Pageable pageable);


	@Query("select m from AccidentInfo m where  m.state=2 order by m.creatDate desc")
	Page<AccidentInfo> findNoPassPage(Pageable pageable);
	
}
