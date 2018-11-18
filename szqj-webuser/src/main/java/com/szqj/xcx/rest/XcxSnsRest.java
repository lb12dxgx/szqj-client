package com.szqj.xcx.rest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.mail.domain.GiftRepository;
import com.szqj.redis.RedisService;
import com.szqj.reg.service.RegService;
import com.szqj.service.domain.AccidentInfo;
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
	private GiftRepository giftRepository;
	
	@Autowired
	private RegService regService;
	
	@Autowired
	private RedisService redisService;
	
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
		Person person = regService.getPersonByOpenid(openid);
		problem.setCreateDate(new Date());
		problem.setOpenid(openid);
		problem.setPersonId(person.getPersonId());
		problem.setPersonName(person.getPersonName());
		problem.setEnterpriseName(person.getEnterpriseName());
		problem.setPersonPosition(person.getPersonPosition());
		problem.setViewNum(1);
		
		String giftId=problem.getGiftId();
		Integer price = giftRepository.findById(giftId).get().getPrice();
		Integer giftNum = problem.getGiftNum();
		problem.setMoney(price*giftNum);
		problemRepository.save(problem);
		return RestJson.createSucces(problem);
	}
	
	@RequestMapping(value = "/getProblem.xcx"  )
	public RestJson getProblem(String problemId){
		Problem problem = problemRepository.findById(problemId).get();
		setEndTime(problem);
		setShareCode(problem);
		return RestJson.createSucces(problem);
	}
	
	@RequestMapping(value = "/getProblemByShareCode.xcx"  )
	public RestJson getProblemByShareCode(String shareCode){
		Problem problem =new Problem();
		String openIdAndproblemId=redisService.getOpenIdAndProblemId(shareCode);
		if(StringUtils.isNotBlank(openIdAndproblemId)) {
			String str[]=StringUtils.split(openIdAndproblemId, "|");
			String preOpenId=str[0];
			String problemId=str[1];
			problem = problemRepository.findById(problemId).get();
			problem.setPreOpenId(preOpenId);
			setEndTime(problem);
			setShareCode(problem);
		}
		return RestJson.createSucces(problem); 
	}


	private void setShareCode(Problem problem) {
		String shareCode=redisService.putOpenIdAndProblemId(problem.getOpenid(), problem.getProblemId());
		problem.setShareCode(shareCode);
	}


	private void setEndTime(Problem problem) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(problem.getCreateDate());
		cal.add(Calendar.DATE,problem.getDayNum());
		
		long date  = cal.getTime().getTime()-new Date().getTime();
		if(date>0) {
			int day = (int)date / (1000 * 60 * 60 * 24);  
		    long hour = (date / (1000 * 60 * 60) - day * 24);  
		    long min = ((date / (60 * 1000)) - day * 24 * 60 - hour * 60);
		    long s = (date/1000 - day*24*60*60 - hour*60*60 - min*60);
		    String endTime=""+day+"天"+hour+"小时"+min+"分"+s+"秒"; 	
		    problem.setEndTime("距结束时间: "+endTime);
		}else {
			 problem.setEndTime("已结束!");
		}
	    
        
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
		String openIdAndproblemId=redisService.getOpenIdAndProblemId(answer.getShareCode());
		if(StringUtils.isNotBlank(openIdAndproblemId)) {
			String str[]=StringUtils.split(openIdAndproblemId, "|");
			String preOpenId=str[0];
			String problemId=str[1];
			Problem problem = problemRepository.findById(problemId).get();
			Person prePerson = regService.getPersonByOpenid(preOpenId);
			Person person = regService.getPersonByOpenid(openid);
			answer.setCreateDate(new Date());
			answer.setOpenid(openid);
			answer.setPersonId(person.getPersonId());
			answer.setPreOpenid(preOpenId);
			answer.setProblem(problem);
			answer.setPrePersonId(prePerson.getPersonId());
			
		}
		answerRepository.save(answer);
		return RestJson.createSucces(answer);
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
