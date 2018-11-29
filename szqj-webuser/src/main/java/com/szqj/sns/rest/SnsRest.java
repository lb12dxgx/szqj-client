package com.szqj.sns.rest;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.mail.domain.RefundRecord;
import com.szqj.sns.domain.Answer;
import com.szqj.sns.domain.AnswerRepository;
import com.szqj.sns.domain.Problem;
import com.szqj.sns.domain.ProblemRepository;
import com.szqj.sns.domain.Result;
import com.szqj.sns.domain.ResultRepository;
import com.szqj.sns.service.SnsService;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;

@RestController
@RequestMapping("/system/sns/")
@EnableAutoConfiguration
public class SnsRest {
	
	@Autowired
	private ProblemRepository problemRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private ResultRepository resultRepository;
	
	@Autowired
	private SnsService snsService;
	
	
	/**
	 *  正在处理的问题列表
	 * @param title
	 * @param pageNum
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/problem/list.do"  )
	public RestJson list(String title, Integer pageNum, Integer size){
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		Page<Problem> page=null;
		if(StringUtils.isNotBlank(title)){
			page=problemRepository.findHandleByTitle(title,pageable);
		}else{
			page=problemRepository.findHandleAll(pageable);
		}
		
		return RestJson.createSucces(page);
	}
	
	
	/**
	 *  正在处理的问题列表
	 * @param title
	 * @param pageNum
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/problem/get.do"  )
	public RestJson getProblem(String problemId){
		Problem problem = problemRepository.findById(problemId).get();
		
		return RestJson.createSucces(problem);
	}
	
	/**
	 * 已经超时的项目列表
	 * @param title
	 * @param pageNum
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/problem/overlist.do"  )
	public RestJson overlist(String title, Integer pageNum, Integer size){
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		Page<Problem> page=null;
		if(StringUtils.isNotBlank(title)){
			page=problemRepository.findOverByTitle(title,pageable);
		}else{
			page=problemRepository.findOverAll(pageable);
		}
		
		return RestJson.createSucces(page);
	}
	
	
	/**
	 * 已经超时的项目列表
	 * @param title
	 * @param pageNum
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/problem/overrefundlist.do"  )
	public RestJson overRefundlist(String title, Integer pageNum, Integer size){
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		Page<Problem> page=null;
		if(StringUtils.isNotBlank(title)){
			page=problemRepository.findOverAndRefundByTitle(title,pageable);
		}else{
			page=problemRepository.findOverAndRefundAll(pageable);
		}
		
		return RestJson.createSucces(page);
	}
	
	/**
	 * 已经结束的项目列表
	 * @param title
	 * @param pageNum
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/problem/endlist.do"  )
	public RestJson endlist(String title, Integer pageNum, Integer size){
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		Page<Problem> page=null;
		if(StringUtils.isNotBlank(title)){
			page=problemRepository.findEndByTitle(title,pageable);
		}else{
			page=problemRepository.findEndAll(pageable);
		}
		
		return RestJson.createSucces(page);
	}
	
	
	@RequestMapping(value = "/problem/refund.do"  )
	public RestJson refund(String problemId){
		RefundRecord refundRecord=snsService.refund(problemId);
		return RestJson.createSucces(refundRecord);
	}
	
	
	/**
	 * 
	 * @param problemId
	 * @return
	 */
	@RequestMapping(value = "/answer/list.do"  )
	public RestJson answerlist(String problemId){
		List<Answer> list = answerRepository.findByProblemId(problemId);
		return RestJson.createSucces(list);
	}
	
	
	/**
	 * 
	 * @param problemId
	 * @return
	 */
	@RequestMapping(value = "/result/list.do"  )
	public RestJson resultlist(String problemId){
		List<Result> list = resultRepository.findByProblemId(problemId);
		return RestJson.createSucces(list);
	}
	
	
	


}
