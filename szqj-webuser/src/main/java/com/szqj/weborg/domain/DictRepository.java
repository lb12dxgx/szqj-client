package com.szqj.weborg.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * 
 * @author lb12
 *
 */
public interface DictRepository extends JpaRepository<Dict, String> {
	
	@Query("select m from Dict m where m.pdictId =-1")
	public Dict getRoot();
	
	
	
	@Query("select m from Dict m where m.pdictId =?1 order by orderNum")
	public List<Dict> findByParentId(String parentId);
	
	@Query("select m from Dict m where m.dictValue =?1 order by orderNum")
	public List<Dict> findByDictValue(String dictValue);
	
	
}
