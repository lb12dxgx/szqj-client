package com.szqj;

import java.util.Date;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
// @ImportResource(locations={"classpath:application-test.xml"})
// @EnableDiscoveryClient
// @EnableRedisHttpSession
public class ConfigClass {

	private CorsConfiguration buildConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOrigin("*"); // 1
		corsConfiguration.addAllowedHeader("*"); // 2
		corsConfiguration.addAllowedMethod("*"); // 3\
		return corsConfiguration;
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", buildConfig()); // 4
		return new CorsFilter(source);
	}

	/*
	 * @Bean public HttpSessionStrategy httpSessionStrategy() { return new
	 * HeaderHttpSessionStrategy(); }
	 */

	@Bean
	public Converter<String, Date> addNewConvert() {
		return new StringToDateConverter();
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(-1);
		return factory.createMultipartConfig();
	}

	@Bean
	public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
		return new RestTemplate(factory);
	}

	@Bean
	public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setReadTimeout(5000);// 单位为ms
		factory.setConnectTimeout(5000);// 单位为ms
		return factory;
	}

}
