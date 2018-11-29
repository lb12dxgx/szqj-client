package com.szqj.sns.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szqj.mail.domain.GiftRepository;
import com.szqj.mail.domain.RechargeRecord;
import com.szqj.mail.domain.RechargeRecordRepository;
import com.szqj.mail.domain.RefundRecord;
import com.szqj.mail.service.PayService;
import com.szqj.mail.service.ScoreRecordEventService;
import com.szqj.redis.RedisService;
import com.szqj.reg.service.RegService;
import com.szqj.service.domain.Person;
import com.szqj.sns.domain.Answer;
import com.szqj.sns.domain.AnswerRepository;
import com.szqj.sns.domain.Problem;
import com.szqj.sns.domain.ProblemRepository;
import com.szqj.sns.domain.ProblemViewRecord;
import com.szqj.sns.domain.ProblemViewRecordRepository;
import com.szqj.sns.domain.Result;
import com.szqj.sns.domain.ResultRepository;
import com.szqj.xcx.util.ImgUtils;
import com.szqj.xcx.util.XcxShareImgModel;

@Service
@Transactional
public class SnsService {

	@Autowired
	private ProblemRepository problemRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private ResultRepository resultRepository;


	@Autowired
	private GiftRepository giftRepository;

	@Autowired
	private ProblemViewRecordRepository problemViewRecordRepository;

	@Autowired
	private RegService regService;
	
	@Autowired
	private ScoreRecordEventService scoreRecordEventService;

	@Autowired
	private RedisService redisService;
	
	@Autowired
	private PayService payService;
	@Autowired
	private RechargeRecordRepository rechargeRecordRepository;
	

	public Problem getProblemByShareCode(String shareCode, String openid) {

		Problem problem = new Problem();
		String openIdAndproblemId = redisService.getOpenIdAndProblemId(shareCode);
		if (StringUtils.isNotBlank(openIdAndproblemId)) {
			String str[] = StringUtils.split(openIdAndproblemId, "|");
			String preOpenId = str[0];
			String problemId = str[1];

			problem = problemRepository.findById(problemId).get();

			problem.setPreOpenId(preOpenId);
			setEndTime(problem);
			setShareCode(problem,openid,shareCode);
			if (StringUtils.isNotBlank(openid)) {
				saveProblemViewRecord(problem, preOpenId, openid);
			}
		}
		return problem;

	}
	
	
	public Problem getProblemByProblemId(String problemId) {
		Problem problem = problemRepository.findById(problemId).get();
		setEndTime(problem);
		setShareCode(problem,problem.getOpenid(),"");
		return problem;
	}
	
	
	public Problem saveProblem(String openid,Problem problem){
		Person person = regService.getPersonByOpenid(openid);
		problem.setCreateDate(new Date());
		problem.setOpenid(openid);
		problem.setPersonId(person.getPersonId());
		problem.setPersonName(person.getPersonName());
		problem.setEnterpriseName(person.getEnterpriseName());
		problem.setPersonPosition(person.getPersonPosition());
		problem.setViewNum(1);
		problem.setState(0);
		
		String giftId=problem.getGiftId();
		Integer price = giftRepository.findById(giftId).get().getPrice();
		Integer giftNum = problem.getGiftNum();
		problem.setMoney(price*giftNum);
		problemRepository.save(problem);
		
		return problem;
	}
	
	
	public String createShareImg(String problemId, String openid, String sharePath, String shareCode,String  uploadPath) {
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
		return webImgPath;
	}
	
	
	public Answer saveAnswer(String openid,Answer answer){
		String openIdAndproblemId=redisService.getOpenIdAndProblemId(answer.getPreShareCode());
		if(StringUtils.isNotBlank(openIdAndproblemId)) {
			String str[]=StringUtils.split(openIdAndproblemId, "|");
			String preOpenId=str[0];
			String problemId=str[1];
			String prepreShareCode="";
			if(str.length>2) {
				prepreShareCode=str[2];
			}
			
			
			Problem problem = problemRepository.findById(problemId).get();
			Person prePerson = regService.getPersonByOpenid(preOpenId);
			Person person = regService.getPersonByOpenid(openid);
			answer.setCreateDate(new Date());
			answer.setOpenid(openid);
			answer.setPersonId(person.getPersonId());
			answer.setPreOpenid(preOpenId);
			answer.setProblem(problem);
			answer.setPrePersonId(prePerson.getPersonId());
			answer.setPersonName(person.getPersonName());
			answer.setEnterpriseName(person.getEnterpriseName());
			answer.setPersonPosition(person.getPersonPosition());
			
			if(!prepreShareCode.equals("")) {
				String preOpenIdAndproblemId=redisService.getOpenIdAndProblemId(prepreShareCode);
				String prestr[]=StringUtils.split(preOpenIdAndproblemId, "|");
				String prepreOpenId=str[0];
				Person preprePerson = regService.getPersonByOpenid(preOpenId);
				
				answer.setPrepreShareCode(prepreShareCode);
				answer.setPrepreOpenid(prepreOpenId);
				answer.setPrprePersonId(preprePerson.getPersonId());
				
			}
			
			
			answerRepository.save(answer);
			
			problem.setAnswerNum(problem.getAnswerNum()+1);
			problemRepository.save(problem);
			
		}
		return answer;
	}
	
	
	public void saveResult(String problemId,String[] answerIdList){
		Problem problem = problemRepository.findById(problemId).get();
		List<Result> resultList=new ArrayList<Result>();
		List<Result> towResultList=new ArrayList<Result>();
		List<Result> threeResultList=new ArrayList<Result>();
		for(String answerId:answerIdList) {
			Answer answer = answerRepository.findById(answerId).get();
			String openId = answer.getOpenid();
			Person person = regService.getPersonByOpenid(openId);
			if(!problem.getPersonId().equals(person.getPersonId())) {
				Result result=new Result();
				result.setAnswerId(answerId);
				result.setProblem(problem);
				result.setType(1);
				result.setOpenid(openId);
				result.setPersonId(person.getPersonId());
				result.setPersonName(person.getPersonName());
				result.setPersonPosition(person.getPersonPosition());
				result.setEnterpriseName(person.getEnterpriseName());
				result.setCreateDate(new Date());
				resultRepository.save(result);
				
				
				resultList.add(result);
				
				if(StringUtils.isNotBlank(answer.getPreShareCode())) {
					Result twoResult = saveTwoResult(answer,result,problem);
					towResultList.add(twoResult);
				}
				if(StringUtils.isNotBlank(answer.getPrepreShareCode())) {
					Result threeResult = saveThreeResult(answer,result,problem);
					threeResultList.add(threeResult);
				}
			}
			
		}
		
		updateScore(resultList,towResultList,threeResultList,problem.getMoney());
		
		problem.setState(1);
		problemRepository.save(problem);
	}
	
	
	public RefundRecord refund(String problemId) {
		RechargeRecord rechargeRecord = rechargeRecordRepository.findByBusinessContent(problemId);
		RefundRecord refundRecord=payService.refundWxPay(rechargeRecord.getTradeNo(), rechargeRecord.getMoney());
		return refundRecord;
	}
	
	
	public void updateRefundProblem(){
		List<Problem> list = problemRepository.findHandleList();
		for(Problem problem:list){
			refund(problem.getProblemId());
		}
	}
	
	public void updateProblemOver(){
		List<Problem> list = problemRepository.findHandleList();
		for(Problem problem:list){
			setEndState(problem);
		}
	}
	

	private void updateScore(List<Result> resultList, List<Result> towResultList, List<Result> threeResultList,Integer score) {
		DecimalFormat df = new DecimalFormat("######0");
		
		if(towResultList.size()==0) {
			Integer oneScore=Integer.parseInt(df.format((score)/resultList.size()));
			addScore(resultList,oneScore);	
		}
		
		if(threeResultList.size()==0) {
			Integer oneScore=Integer.parseInt(df.format((score*0.8)/resultList.size()));
			addScore(resultList,oneScore);	
			Integer twoScore=Integer.parseInt(df.format((score*0.2)/resultList.size()));
			addScore(towResultList,twoScore);	
		}
		
		
		if(threeResultList.size()>0) {
			Integer oneScore=Integer.parseInt(df.format((score*0.8)/resultList.size()));
			Integer twoScore=Integer.parseInt(df.format((score*0.12)/resultList.size()));
			Integer threeScore=Integer.parseInt(df.format((score*0.08)/resultList.size()));
			addScore(resultList,oneScore);	
			addScore(towResultList,twoScore);	
			addScore(threeResultList,threeScore);	
		}
		
		
		
	}


	private void addScore(List<Result> resultList, Integer scoreNum) {
		for(Result result:resultList) {
			result.setScoreNum(scoreNum);
			resultRepository.save(result);
			scoreRecordEventService.sendByResult(result);
		}
		
	}


	private Result saveTwoResult(Answer answer, Result result,Problem problem) {
		
		String shareCode=answer.getPreShareCode();
		String openIdAndproblemId=redisService.getOpenIdAndProblemId(shareCode);
		String str[]=StringUtils.split(openIdAndproblemId, "|");
		String preOpenId=str[0];
		Person person = regService.getPersonByOpenid(preOpenId);
		Result towResult=null;
		if(!problem.getPersonId().equals(person.getPersonId())) {
			towResult=new Result();
			towResult.setAnswerId(answer.getAnswerId());
			towResult.setProblem(result.getProblem());
			towResult.setType(2);
			towResult.setOpenid(preOpenId);
			towResult.setPersonId(person.getPersonId());
			towResult.setPersonName(person.getPersonName());
			towResult.setPersonPosition(person.getPersonPosition());
			towResult.setEnterpriseName(person.getEnterpriseName());
			towResult.setCreateDate(new Date());
			resultRepository.save(towResult);
		}
		return towResult;
		
	}
	
	
	private Result saveThreeResult(Answer answer, Result result,Problem problem) {
		String shareCode=answer.getPrepreShareCode();
		String openIdAndproblemId=redisService.getOpenIdAndProblemId(shareCode);
		String str[]=StringUtils.split(openIdAndproblemId, "|");
		String preOpenId=str[0];
		Person person = regService.getPersonByOpenid(preOpenId);
		Result threeResult=null;
		if(!problem.getPersonId().equals(person.getPersonId())) {
			threeResult=new Result();
			threeResult.setAnswerId(answer.getAnswerId());
			threeResult.setProblem(result.getProblem());
			threeResult.setType(3);
			threeResult.setOpenid(preOpenId);
			threeResult.setPersonId(person.getPersonId());
			threeResult.setPersonName(person.getPersonName());
			threeResult.setPersonPosition(person.getPersonPosition());
			threeResult.setEnterpriseName(person.getEnterpriseName());
			threeResult.setCreateDate(new Date());
			resultRepository.save(threeResult);
		}
		return threeResult;
		
	}


	private void saveProblemViewRecord(Problem problem, String preOpenId, String openid) {
		Person prePerson = regService.getPersonByOpenid(preOpenId);
		Person person = regService.getPersonByOpenid(openid);
		List<ProblemViewRecord> list = problemViewRecordRepository
				.findPageByProblemIdAndPersonId(problem.getProblemId(), prePerson.getPersonId());
		ProblemViewRecord problemViewRecord = new ProblemViewRecord();
		if (list == null || list.size() == 0) {
			problemViewRecord.setCreateDate(new Date());
			problemViewRecord.setOpenid(openid);
			problemViewRecord.setPersonId(person.getPersonId());
			problemViewRecord.setPersonName(person.getPersonName());
			problemViewRecord.setPrePersonId(preOpenId);
			problemViewRecord.setPrePersonName(prePerson.getPersonName());
			problemViewRecord.setProblemId(problem.getProblemId());
			problem.setViewNum(problem.getViewNum() + 1);
			problemRepository.save(problem);
		} else {
			problemViewRecord = list.get(0);
			problemViewRecord.setCreateDate(new Date());
			;
		}

		problemViewRecordRepository.save(problemViewRecord);

	
	}

	private void setShareCode(Problem problem,String openId,String preShareCode) {
		String shareCode = redisService.putOpenIdAndProblemId(openId, problem.getProblemId(),preShareCode);
		problem.setShareCode(shareCode);
	}

	private void setEndTime(Problem problem) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(problem.getCreateDate());
		cal.add(Calendar.DATE, problem.getDayNum());

		long date = cal.getTime().getTime() - new Date().getTime();
		if (date > 0) {
			int day = (int) date / (1000 * 60 * 60 * 24);
			long hour = (date / (1000 * 60 * 60) - day * 24);
			long min = ((date / (60 * 1000)) - day * 24 * 60 - hour * 60);
			long s = (date / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
			String endTime = "" + day + "天" + hour + "小时" + min + "分" + s + "秒";
			problem.setEndTime("距结束时间: " + endTime);
		} else {
			problem.setEndTime("已结束!");
		}

	}
	
	
	private void setEndState(Problem problem) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(problem.getCreateDate());
		int day = cal.get(Calendar.DATE);//获取日
		System.out.println("day1==="+day);
		cal.add(Calendar.DATE, problem.getDayNum());
	    day = cal.get(Calendar.DATE);//获取日
		System.out.println("day2==="+day);
		long date = cal.getTime().getTime() - new Date().getTime();
		if(date<=0){
			problem.setState(2);
			problem.setRefundState(0);
		}
		
	}


	

}
