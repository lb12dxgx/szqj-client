package com.szqj.weborg.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

;

/**
 * 
* @ClassName: FileInfoRepository 
* @Description:附件访问服务
* @author zhanggy  
* @date 2015年12月18日 下午9:12:48 
*
*/
public interface FileInfoRepository extends PagingAndSortingRepository<FileInfo, String> {

	@Query("select m from FileInfo m where m.bussinessId =?1 and  m.delFlag!=1")
	public List<FileInfo> findByBussinessId(String bussinessId);
}
