package com.szqj.service.domain;

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
public interface MeetSignUpRepository extends PagingAndSortingRepository<MeetSignUp, String> {


	@Query("select m from MeetSignUp m where m.meetId = ?1  order by createDate")
	Page<MeetSignUp> findPageByMeetId(String meetId, Pageable pageable);
	
	@Query("select m from MeetSignUp m where  m.meetId = ?1 and m.userName like %?2%   order by createDate")
	Page<MeetSignUp> findPageByMeetIdAndUserName(String meetId,String userName,Pageable pageable); 
	
	@Query("select m from MeetSignUp m where m.meetId = ?1  order by empName ,createDate")
	List<MeetSignUp> findListByMeetId(String meetId);
	
	
	@Query("select m from MeetSignUp m where m.meetId = ?1 and m.telphone= ?2  order by empName ,createDate")
	List<MeetSignUp> findListByMeetIdAndTelphone(String meetId,String telphone);
	
	

	
	
}
