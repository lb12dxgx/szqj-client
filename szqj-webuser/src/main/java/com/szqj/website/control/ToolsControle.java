package com.szqj.website.control;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.szqj.util.RestJson;

import net.coobird.thumbnailator.Thumbnails;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class ToolsControle {
	
	@Value("${web.upload-path}")
	private String uploadPath;

	@RequestMapping(value = "/tools/teacherPic.html"  )
	@ResponseBody
	public RestJson teacherPic() throws IOException{
		String filepath = uploadPath+"content"+File.separator+"teacher";
		File file = new File(filepath);  
		File[] list = file.listFiles();
		for(File f:list) {
			System.out.println(f.getName()); 
			String suffixName = f.getName().substring(f.getName().lastIndexOf("."));
		     if(suffixName=="JPEG"||suffixName=="jpeg"){
		    	 Thumbnails.of(f).outputFormat("JPEG").size(200, 200).keepAspectRatio(false).toFile(f);
		     }
		     
		     if(suffixName=="PNG"||suffixName=="png"){
		    	 Thumbnails.of(f).outputFormat("PNG").size(200, 200).keepAspectRatio(false).toFile(f);
		     }
		}
		
		return RestJson.createSucces();
	}
	
}
