package com.szqj.springmvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//通过使用@ControllerAdvice定义统一的异常处理类，而不是在每个Controller中逐个定义。
@ControllerAdvice
public class GlobalExceptionHandler {

 public static final String DEFAULT_ERROR_VIEW = "error";
 // @ExceptionHandler用来定义函数针对的异常类型，最后将Exception对象和请求URL映射到error.html中
 @ExceptionHandler(value = FormRepeatException.class)
 public ModelAndView defaultErrorHandler(HttpServletRequest request, FormRepeatException e) throws Exception {
     ModelAndView mav = new ModelAndView() ;
     mav.addObject("errorname", "统一异常处理页面") ;
     mav.addObject("exception",e.getMessage()) ;
     mav.addObject("url", request.getRequestURL()) ;
     mav.setViewName(DEFAULT_ERROR_VIEW) ;
     return mav ;
 }
}