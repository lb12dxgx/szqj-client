package com.szqj;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.szqj.xcx.filter.OpenidFilter;

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
		RestTemplate restTemplate = new RestTemplate(factory);
	    restTemplate.getMessageConverters().add(new WxMappingJackson2HttpMessageConverter());
		return restTemplate;
	}
	
	public class WxMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
	    public WxMappingJackson2HttpMessageConverter(){
	        List<MediaType> mediaTypes = new ArrayList<>();
	        mediaTypes.add(MediaType.TEXT_PLAIN);
	        setSupportedMediaTypes(mediaTypes);
	    }
	}

	@Bean
	public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setReadTimeout(5000);// 单位为ms
		factory.setConnectTimeout(5000);// 单位为ms
		return factory;
	}
	
	
	/*@Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/system/*");
        return registrationBean;
    }*/
	
	
	@Bean
	public FilterRegistrationBean openFilterRegistrationBean(){
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		OpenidFilter openidFilter = new OpenidFilter();
		registrationBean.setFilter(openidFilter);
		List<String> urlPatterns = new ArrayList<String>();
	    urlPatterns.add("/xcx/login/*");
	    registrationBean.setUrlPatterns(urlPatterns);
	    return registrationBean;
	}

}
