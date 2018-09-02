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
public interface MyPersonRepository extends PagingAndSortingRepository<Person, String> {

	@Query("select m from Person m where m.accountId =?1 ")
	List<Person> findByAccountId(String accountId);  
	
	
	@Query("select m from Person m where m.accountId !=?1 and m.telePhone=?2 ")
	List<Person> findByAccountIdAndTelePhone(String accountId,String telePhone);

	@Query("select m from Person m where  m.telePhone=?1 ")
	List<Person> findByTelePhone(String telphone);
	
	@Query("select m from Person m where  m.userCode=?1 ")
	List<Person> findByUserCode(String userCode);
	@Query("select m from Person m where  m.openid=?1 ")
	List<Person> findByOpenid(String openid);
	
	@Query("select m from Person m order by m.updateDate ")
	Page<Person> findPage( Pageable pageable);
	
	
	@Query("select m from Person m where m.level=20 order by m.updateDate desc,level desc")
	List<Person> findTopList();
	
	
	
	
	
	@Query("select m from Person m where m.personName like %?1% order by m.updateDate desc,level desc")
	Page<Person> findAdminPageByPersonName(String personName, Pageable pageable);
	
	
	@Query("select m from Person m where m.jobName like %?1%   order by m.updateDate desc,level desc")
	Page<Person> findAdminPageByJobName(String jobName,Pageable pageable);
	
	
	@Query("select m from Person m where workState!=3  order by m.updateDate desc,level desc")
	Page<Person> findEmpPage(Pageable pageable);
	
	@Query("select m from Person m where m.jobName like %?1% and workState!=3  order by m.updateDate desc,level desc")
	Page<Person> findEmpPageByJobName(String jobName,Pageable pageable);
	
	
	@Query("select m from Person m where m.workCity like %?1% and workState!=3  order by m.updateDate desc,level desc")
	Page<Person> findEmpPageByWorkCity(String workCity,Pageable pageable);
	
	
	@Query("select m from Person m where m.workCity like %?1% and m.jobName like %?2% and workState!=3  order by m.updateDate desc,level desc")
	Page<Person> findEmpPageByWorkCityAndJobName(String workCity,String jobName,Pageable pageable);


	


	
	
   
	
	
}
