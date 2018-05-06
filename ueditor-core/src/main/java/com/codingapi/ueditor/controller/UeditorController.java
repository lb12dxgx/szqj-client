package com.codingapi.ueditor.controller;

import com.baidu.ueditor.ActionEnter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;

/**
 * create by lorne on 2017/12/24
 */
@Controller
public class UeditorController {

    private Logger logger = LoggerFactory.getLogger(UeditorController.class);

    @Value("${web.upload-path}")
    private String rootPath;
    
    @Value("${web.server-url}")
    private String serverUrl;

    private String projectPath = null;

    @Autowired
    private Environment environment;

  /*  private final static String staticPath = "static/";*/
    private final static String staticPath = "";

    public UeditorController() {
       /* String path  = UeditorController.class.getClassLoader().getResource("config.json").getPath();
        logger.info("path->"+path);
        File file =  new File(path);
        if(file.getParentFile().isDirectory()) {
            rootPath = new File(path).getParent()+"/";
        }else{
            rootPath = new File(path).getParentFile().getParent()+"/";
            rootPath = rootPath.replace("file:","");
        }
    	*/
    	 logger.info("path->"+rootPath);
    }


    private String getProjectPath(){
        if(null==projectPath) {
            String val = environment.getProperty("server.context-path", "");
            if ("".equals(val)) {
                val = environment.getProperty("server.contextPath", "");
                if ("".equals(val)) {
                    projectPath = "";
                    return projectPath;
                }
            }
            projectPath = val.replace("/", "") + "/";
        } 
        return projectPath;
    }

    @RequestMapping("/exec")
    public void exec(HttpServletRequest request, HttpServletResponse response, PrintWriter out){
        response.setHeader("Content-Type" , "text/html");
        logger.info("rootPath->"+rootPath+",staticPath->"+staticPath+",projectPath->"+getProjectPath());
        out.write( new ActionEnter( request, rootPath,staticPath,getProjectPath(),serverUrl).exec());
    }

}
