package com.szqj.xcx.rest;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.reg.service.RegService;
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
@RequestMapping("/xcx/login/sns/")
@EnableAutoConfiguration
public class XcxSnsRest {
	

	@Autowired
	private ProblemRepository problemRepository;
	
	
	@Autowired
	private AnswerRepository answerRepository;
	
	
	@Autowired
	private ResultRepository resultRepository;
	

	
	@Autowired
	private  SnsService  snsService;
	
	@Value("${web.upload-path}")
	private String uploadPath;

	
	
	
	@RequestMapping(value = "/listProblem.xcx"  )
	public RestJson listProblem(@ModelAttribute("openid") String openid,Integer pageNum){
		PageRequest pageable=Tools.getPage(pageNum,5);
		Page<Problem> page = problemRepository.listProblem(openid,pageable);
		return RestJson.createSucces(page);
	}
	
	@RequestMapping(value = "/otherProblem.xcx"  )
	public RestJson otherProblem(@ModelAttribute("openid") String openid,Integer pageNum){
		PageRequest pageable=Tools.getPage(pageNum,5);
		Page<Problem> page = problemRepository.otherProblem(openid,pageable);
		return RestJson.createSucces(page);
	}
	
	
	
	
	@RequestMapping(value = "/myProblem.xcx"  )
	public RestJson myProblem(@ModelAttribute("openid") String openid,Integer pageNum){
		PageRequest pageable=Tools.getPage(pageNum,5);
		Page<Problem> page = problemRepository.findPageByOpenid(openid,pageable);
		return RestJson.createSucces(page);
	}
	
	
	@RequestMapping(value = "/addProblem.xcx"  )
	public RestJson addProblem( Map<String,String> map){
		map.put("picId", UUID.randomUUID().toString());
		return RestJson.createSucces(map);
	}
	
	@RequestMapping(value = "/saveProblem.xcx"  )
	public RestJson saveProblem(@ModelAttribute("openid") String openid,Problem problem){
		Problem retproblem = snsService.saveProblem(openid,problem);
		return RestJson.createSucces(retproblem);
	}
	
	@RequestMapping(value = "/getProblem.xcx"  )
	public RestJson getProblem(String problemId){
		Problem problem = snsService.getProblemByProblemId(problemId);
		return RestJson.createSucces(problem);
	}
	
	@RequestMapping(value = "/getProblemByShareCode.xcx"  )
	public RestJson getProblemByShareCode(String shareCode,@ModelAttribute("openid") String openid){
		Problem problem = snsService.getProblemByShareCode(shareCode,openid);
		return RestJson.createSucces(problem); 
	}


	@RequestMapping(value = "/createShareImg.xcx"  )
	public RestJson createShareImg(String problemId,@ModelAttribute("openid")String openid,String sharePath,String shareCode){
		String webImgPath = snsService.createShareImg(problemId, openid, sharePath, shareCode,uploadPath);
		return RestJson.createSucces(webImgPath);
	}


	
	

	@RequestMapping(value = "/getAnswerByProblemId.xcx"  )
	public RestJson getAnswerByProblemId(String problemId){
		List<Answer> list = answerRepository.findByProblemId(problemId);
		return RestJson.createSucces(list);
	}
	
	
	@RequestMapping(value = "/getAnswerByProblemIdAndOpenId.xcx"  )
	public RestJson getAnswerByProblemIdAndOpenId(@ModelAttribute("openid") String openid,String problemId){
		Answer  answer = answerRepository.findByProblemIdAndOpenId(problemId,openid);
		return RestJson.createSucces(answer);
	}
	
	@RequestMapping(value = "/saveAnswer.xcx"  )
	public RestJson saveAnswer(@ModelAttribute("openid") String openid,Answer answer){
		Answer retanswer = snsService.saveAnswer(openid,answer);
		return RestJson.createSucces(retanswer);
	}
	
	
	@RequestMapping(value = "/getResultByProblemId.xcx"  )
	public RestJson getResultByProblemId(String problemId){
		List<Result> list = resultRepository.findOneByProblemId(problemId);
		for(Result oneResult:list) {
			Result twoResult = resultRepository.findTwoByAnswerId(oneResult.getAnswerId());
			Result threeResult = resultRepository.findThreeByAnswerId(oneResult.getAnswerId());
			oneResult.setTwoResult(twoResult);
			oneResult.setThreeResult(threeResult);
		}
		return RestJson.createSucces(list);
	}
	
	@RequestMapping(value = "/saveResult.xcx"  )
	public RestJson saveResult(String problemId,String[] answerIdList){
		snsService.saveResult(problemId, answerIdList);
		return RestJson.createSucces();
	}
	
	

}
