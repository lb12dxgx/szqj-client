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
public interface MeetSignUpRepository extends PagingAndSortingRepository<MeetSignUp, String> {


	@Query("select m from MeetSignUp m where m.meetId = ?1  order by isSign ")
	Page<MeetSignUp> findPageByMeetId(String meetId, Pageable pageable);
	
	@Query("select m from MeetSignUp m where  m.meetId = ?1 and m.userName like %?2%   order by  isSign")
	Page<MeetSignUp> findPageByMeetIdAndUserName(String meetId,String userName,Pageable pageable); 
	
	@Query("select m from MeetSignUp m where m.meetId = ?1  order by empName ,createDate")
	List<MeetSignUp> findListByMeetId(String meetId);
	
	
	@Query("select m from MeetSignUp m where m.meetId = ?1 and m.telphone= ?2  order by empName ,createDate")
	List<MeetSignUp> findListByMeetIdAndTelphone(String meetId,String telphone);
	
	
	@Query("select m from MeetSignUp m where m.meetId = ?1 and m.userName= ?2  order by empName ,createDate")
	List<MeetSignUp> findListByMeetIdAndUserName(String meetId,String userName);
	
	

	
	
}
