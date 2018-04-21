package com.szqj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;





@SpringBootApplication
@EnableScheduling
//@EnableBinding(OrgMessage.class) 
public class WebUserApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(WebUserApplication.class, args);

	}

}
