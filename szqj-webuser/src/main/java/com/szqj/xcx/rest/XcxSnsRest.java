package com.szqj.xcx.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.mail.domain.GiftRepository;
import com.szqj.service.domain.AccidentInfo;
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
	
	@RequestMapping(value = "/saveProblem.xcx"  )
	public RestJson saveProblem(@ModelAttribute("openid") String openid,Problem problem){
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
		return RestJson.createSucces(problem);
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
