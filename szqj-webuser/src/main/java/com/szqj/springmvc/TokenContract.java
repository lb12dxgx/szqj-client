package com.szqj.springmvc;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import org.springframework.web.context.request.RequestAttributes;

import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class TokenContract {
 
	private static final Logger logger = LoggerFactory.getLogger(TokenContract.class);
 
    @Before("within(@org.springframework.stereotype.Controller *) && @annotation(token)")
    public void testToken(final JoinPoint joinPoint, Token token){
        try {
            if (token != null) {
                //获取 joinPoint 的全部参数
            	 RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
            	 
            	 HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
            	 
            	  boolean needRemoveSession = token.remove();
                  if (needRemoveSession) {
                      if (isRepeatSubmit(request)) {
                          logger.error("表单重复提交");
                          throw new FormRepeatException("表单重复提交");
                      }
                      request.getSession(false).removeAttribute( "submittoken" );
                  };
            	 
                boolean needSaveSession = token.save();
                if (needSaveSession){
                    String uuid = UUID.randomUUID().toString();
                    request.getSession().setAttribute( "submittoken" , uuid);
                    logger.info("进入表单页面，Token值为："+uuid);
                }
 
              
            }
 
        } catch (FormRepeatException e){
            throw e;
        } catch (Exception e){
            logger.error("submittoken 发生异常 : "+e);
        }
    }
 
    private boolean isRepeatSubmit(HttpServletRequest request) throws FormRepeatException {
        String serverToken = (String) request.getSession( false ).getAttribute( "submittoken" );
        String clinetToken = request.getParameter( "submittoken" );
        logger.info("校验是否重复提交：表单页面submittoken值为："+clinetToken + ",Session中的submittoken值为:"+serverToken);
        if (serverToken == null ) {
            //throw new FormRepeatException("session 为空");
            return true;
        }
       
        if (clinetToken == null || clinetToken.equals("")) {
            //throw new FormRepeatException("请从正常页面进入！");
            return true;
        }
        if (!serverToken.equals(clinetToken)) {
            //throw new FormRepeatException("重复表单提交！");
            return true ;
        }
       
        return false ;
    }
}