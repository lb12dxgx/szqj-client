package com.szqj.before.rest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.service.domain.MyPersonRepository;
import com.szqj.service.domain.Person;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.domain.AccountRepository;

@RestController
@RequestMapping("/system/beforeperson")
@EnableAutoConfiguration
public class BeforePersonRest {

	@Autowired
	private MyPersonRepository personRepository;

	@Autowired
	private AccountRepository accountRepository;

	// 根据企业ID返回人员列表
	@RequestMapping(value = "listByCompanyId.do")
	public RestJson list(String personName, String companyId, Integer pageNum, Integer size) {
		PageRequest pageable = Tools.getPage(pageNum - 1, size);
		Page<Person> page = null;
		if (StringUtils.isBlank(personName)) {
			page = personRepository.findPageByCompanyId(companyId, pageable);
		} else {
			page = personRepository.findPageByCompanyIdAndPersonName(companyId, personName, pageable);
		}
		return RestJson.createSucces(page);
	}

	// 修改人员状态
	@RequestMapping(value = "changeState.do")
	public RestJson changeState(Integer state, String personId) {
		Person person = personRepository.findById(personId).get();
		person.setState(state);
		personRepository.save(person);

		String accountId = person.getAccountId();
		Account account = accountRepository.findById(accountId).get();
		accountRepository.save(account);

		return RestJson.createSucces(person);
	}

	// 修改人员所属企业
	@RequestMapping(value = "changeCompany.do")
	public RestJson changeCompany(String personId) {
		Person person = personRepository.findById(personId).get();
		person.setCompanyId("");
		personRepository.save(person);
		return RestJson.createSucces(person);
	}

}
