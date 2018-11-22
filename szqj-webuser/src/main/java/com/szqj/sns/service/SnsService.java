package com.szqj.sns.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szqj.mail.domain.GiftRepository;
import com.szqj.redis.RedisService;
import com.szqj.reg.service.RegService;
import com.szqj.service.domain.Person;
import com.szqj.sns.domain.Answer;
import com.szqj.sns.domain.AnswerRepository;
import com.szqj.sns.domain.Problem;
import com.szqj.sns.domain.ProblemRepository;
import com.szqj.sns.domain.ProblemViewRecord;
import com.szqj.sns.domain.ProblemViewRecordRepository;
import com.szqj.sns.domain.ResultRepository;
import com.szqj.sns.domain.ShareRepository;

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
	private ShareRepository shareRepository;
	@Autowired
	private GiftRepository giftRepository;

	@Autowired
	private ProblemViewRecordRepository problemViewRecordRepository;

	@Autowired
	private RegService regService;

	@Autowired
	private RedisService redisService;

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
			setShareCode(problem,openid);
			if (StringUtils.isNotBlank(openid)) {
				saveProblemViewRecord(problem, preOpenId, openid);
			}
		}
		return problem;

	}
	
	
	public Problem getProblemByProblemId(String problemId) {
		Problem problem = problemRepository.findById(problemId).get();
		setEndTime(problem);
		setShareCode(problem,problem.getOpenid());
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
	
	
	public Answer saveAnswer(String openid,Answer answer){
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
			answerRepository.save(answer);
			
			problem.setAnswerNum(problem.getAnswerNum()+1);
			problemRepository.save(problem);
			
		}
		return answer;
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

	private void setShareCode(Problem problem,String openId) {
		String shareCode = redisService.putOpenIdAndProblemId(openId, problem.getProblemId());
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

}
