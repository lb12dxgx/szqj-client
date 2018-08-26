package com.szqj.service.domain;

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
public interface AbilityInfoRepository extends PagingAndSortingRepository<AbilityInfo, String> {

	
	
	
	@Query("select m from AbilityInfo m where m.enterpriseId=?1 order by createDate desc")
	List<AbilityInfo> findByEnterpriseId(String enterpriseId);

	@Query("select m from AbilityInfo m where m.enterpriseName like %?1% and m.abilityInfoType=?2 and m.state=?3  order by createDate desc")
	Page<AbilityInfo> findAdminPageByeEnterpriseNameAndAbilityInfoType(String enterpriseName, String abilityInfoType,Integer state,
			Pageable pageable);

	@Query("select m from AbilityInfo m where m.enterpriseName like %?1%   and m.state=?2  order by createDate desc")
	Page<AbilityInfo> findAdminPageByeEnterpriseName(String enterpriseName,Integer state, Pageable pageable);

	@Query("select m from AbilityInfo m where  m.abilityInfoType=?1  and m.state=?2  order by createDate desc")
	Page<AbilityInfo> findAdminPageByAbilityInfoType(String abilityInfoType,Integer state, Pageable pageable);

	@Query("select m from AbilityInfo m  where m.state=?1  order by createDate desc")
	Page<AbilityInfo> findPage(Integer state,Pageable pageable);
	
}
