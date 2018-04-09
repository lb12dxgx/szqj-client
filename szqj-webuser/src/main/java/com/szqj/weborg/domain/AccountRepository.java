package com.szqj.weborg.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;




/**
 * 
 * @author lb12
 *
 */
public interface AccountRepository extends PagingAndSortingRepository<Account, String> {
	
	
	@Query("select m from Account m where m.accountName =?1")
	public List<Account> findByAccountName(String accountName);
	
	@Query("select m from Account m where m.accountType=?1 and  m.userName like %?2% and state!=2 ")
	public Page<Account> findPageByUserNameAndType(Integer accountType, String userName,Pageable pageable);
	
	
	@Query("select m from Account m where m.accountType=?1 and state!=2")
	public Page<Account> findPageByAccountType(Integer accountType,Pageable pageable);
	
	@Query("select m from Account m where  state!=2")
	public List<Account> findAllList();
	
	
	
}
