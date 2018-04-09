package com.szqj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.web.ZuulController;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.scheduling.annotation.EnableScheduling;





@SpringBootApplication
@EnableScheduling
//@EnableBinding(OrgMessage.class) 
public class WebUserApplication {
	ZuulController a=null;

	public static void main(String[] args) {
		SpringApplication.run(WebUserApplication.class, args);

	}

}
