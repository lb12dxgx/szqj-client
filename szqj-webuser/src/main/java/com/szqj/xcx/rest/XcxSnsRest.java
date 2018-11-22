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
import com.szqj.service.domain.Person;
import com.szqj.sns.domain.Answer;
import com.szqj.sns.domain.AnswerRepository;
import com.szqj.sns.domain.Card;
import com.szqj.sns.domain.CardRepository;
import com.szqj.sns.domain.Problem;
import com.szqj.sns.domain.ProblemRepository;
import com.szqj.sns.domain.Result;
import com.szqj.sns.domain.ResultRepository;
import com.szqj.sns.domain.Share;
import com.szqj.sns.domain.ShareRepository;
import com.szqj.sns.service.SnsService;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;
import com.szqj.xcx.util.ImgUtils;
import com.szqj.xcx.util.XcxShareImgModel;


@RestController
@RequestMapping("/xcx/login/sns/")
@EnableAutoConfiguration
public class XcxSnsRest {
	
	@Autowired
	private CardRepository cardRepository;
	
	@Autowired
	private ProblemRepository problemRepository;
	
	
	@Autowired
	private AnswerRepository answerRepository;
	
	
	@Autowired
	private ResultRepository resultRepository;
	
	
	@Autowired
	private ShareRepository shareRepository;

	
	@Autowired
	private RegService regService;
	

	
	@Autowired
	private  SnsService  snsService;
	
	@Value("${web.upload-path}")
	private String uploadPath;

	
	
	
	/**
	 * 
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/isCard.xcx"  )
	public RestJson isCard(@ModelAttribute("openid") String openid){
		List<Card> cards = cardRepository.findByOpenid(openid);
		if(cards==null||cards.size()>0) {
			RestJson.createSucces(0);
		}
		return RestJson.createSucces(1);
	}
	
	
	@RequestMapping(value = "/saveCard.xcx"  )
	public RestJson saveCard(@ModelAttribute("openid") String openid,Card card){
		cardRepository.save(card);
		return RestJson.createSucces(card);
	}
	
	@RequestMapping(value = "/getCard.xcx"  )
	public RestJson getCard(String cardId){
		Card card=cardRepository.findById(cardId).get();
		return RestJson.createSucces(card);
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
		Problem problem = problemRepository.findById(problemId).get();
		Person sharePerson = regService.getPersonByOpenid(openid);
		Person createPerson =regService.getPersonByOpenid(problem.getOpenid());
		ImgUtils imgUtils=new ImgUtils();
		XcxShareImgModel xcxShareImgModel=new XcxShareImgModel();
		
		xcxShareImgModel.setCompanyName(createPerson.getEnterpriseName());
		xcxShareImgModel.setCreateUserName(createPerson.getPersonName());
		xcxShareImgModel.setMoney(problem.getMoney()+"");
		xcxShareImgModel.setShareCode(shareCode);
		xcxShareImgModel.setPostName(createPerson.getPersonPosition());
		xcxShareImgModel.setSharePath(sharePath);
		xcxShareImgModel.setTitle(problem.getTitle());
		xcxShareImgModel.setShareUserName(sharePerson.getPersonName());
		xcxShareImgModel.setContent(problem.getContent());
		String webImgPath=imgUtils.createShareImg(uploadPath, xcxShareImgModel);
		return RestJson.createSucces(webImgPath);
	}
	
	@RequestMapping(value = "/getShareByProblemId.xcx"  )
	public RestJson getShareByProblemId(String problemId){
		List<Share> list = shareRepository.findByProblemId(problemId);
		return RestJson.createSucces(list);
	}
	
	
	@RequestMapping(value = "/myShare.xcx"  )
	public RestJson myShare(@ModelAttribute("openid")String openid ,Integer pageNum){
		PageRequest pageable=Tools.getPage(pageNum,5);
		Page<Problem> page = shareRepository.findByOpenid(openid,pageable);
		return RestJson.createSucces(page);
	}
	
	
	@RequestMapping(value = "/saveShare.xcx"  )
	public RestJson saveShare(@ModelAttribute("openid") String openid,Share share){
		shareRepository.save(share);
		return RestJson.createSucces(share);
	}
	
	@RequestMapping(value = "/getAnswerByProblemId.xcx"  )
	public RestJson getAnswerByProblemId(String problemId){
		List<Answer> list = answerRepository.findByProblemId(problemId);
		return RestJson.createSucces(list);
	}
	
	
	@RequestMapping(value = "/saveAnswer.xcx"  )
	public RestJson saveAnswer(@ModelAttribute("openid") String openid,Answer answer){
		Answer retanswer = snsService.saveAnswer(openid,answer);
		return RestJson.createSucces(retanswer);
	}
	
	
	@RequestMapping(value = "/getResultByProblemId.xcx"  )
	public RestJson getResultByProblemId(String problemId){
		List<Result> list = resultRepository.findByProblemId(problemId);
		return RestJson.createSucces(list);
	}
	
	@RequestMapping(value = "/saveResult.xcx"  )
	public RestJson saveResult(@ModelAttribute("openid") String openid,Result result){
		resultRepository.save(result);
		return RestJson.createSucces(result);
	}
	
	

}
