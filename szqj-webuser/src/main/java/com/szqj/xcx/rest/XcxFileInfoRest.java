package com.szqj.xcx.rest;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.szqj.redis.RedisService;
import com.szqj.util.ConstantUtils;
import com.szqj.util.RestJson;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.domain.AccountRepository;
import com.szqj.weborg.domain.FileInfo;
import com.szqj.weborg.domain.FileInfoRepository;

@RestController
@RequestMapping("/xcx/")
@EnableAutoConfiguration


public class XcxFileInfoRest {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private FileInfoRepository fileInfoRepository;
	
	@Autowired
	private  RedisService redisService;
	 
	
	@Value("${web.upload-path}")
	private String uploadPath;
	
	@RequestMapping(value = "/accidentinfo/uploadImg.xcx"  )
	@ResponseBody
	public RestJson uploadImg(@RequestParam("file") MultipartFile file,String openIdMd5,String accidentPicId){
		String openid = redisService.getOpenId(openIdMd5);
		Account account = accountRepository.findByOpenid(openid).get(0);
		String dirName ="content/accidentinfo/img/";
		String fileName = file.getOriginalFilename();
		//获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + suffixName;
        File dest = new File(uploadPath +dirName+File.separator+ fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            FileInfo f=new FileInfo();
    		f.setCreateDate(new Date());
    		f.setCreateUserId(account.getAccountId());
    		f.setDelFlag(ConstantUtils.NEW_FLAG);
    		f.setFilePath(uploadPath +dirName+File.separator+ fileName);
    		f.setFileWebPath(dirName+"/"+fileName);
    		f.setFileName(file.getOriginalFilename());
    		f.setFileType(suffixName);
    		f.setBussinessId(accidentPicId);
    		fileInfoRepository.save(f);
            return RestJson.createSucces(f);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return RestJson.createError();
		
		
	}
	
	
	@RequestMapping(value = "/before/applayproject/uploadImg.xcx"  )
	@ResponseBody
	public RestJson uploadBeforeImg(@RequestParam("file") MultipartFile file,String openIdMd5,String picId){
		String openid = redisService.getOpenId(openIdMd5);
		Account account = accountRepository.findByOpenid(openid).get(0);
		String dirName ="content/applayproject/img/";
		String fileName = file.getOriginalFilename();
		//获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + suffixName;
        File dest = new File(uploadPath +dirName+File.separator+ fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            FileInfo f=new FileInfo();
    		f.setCreateDate(new Date());
    		f.setCreateUserId(account.getAccountId());
    		f.setDelFlag(ConstantUtils.NEW_FLAG);
    		f.setFilePath(uploadPath +dirName+File.separator+ fileName);
    		f.setFileWebPath(dirName+"/"+fileName);
    		f.setFileName(file.getOriginalFilename());
    		f.setFileType(suffixName);
    		f.setBussinessId(picId);
    		fileInfoRepository.save(f);
            return RestJson.createSucces(f);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return RestJson.createError();
		
		
	}
	
	
	@RequestMapping(value = "/accidentinfo/uploadVideo.xcx"  )
	@ResponseBody
	public RestJson uploadVideo(@RequestParam("file") MultipartFile file,String openIdMd5,String accidentVideoId){
		String openid = redisService.getOpenId(openIdMd5);
		Account account = accountRepository.findByOpenid(openid).get(0);
		String dirName ="content/accidentinfo/video/";
		String fileName = file.getOriginalFilename();
		//获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + suffixName;
        File dest = new File(uploadPath +dirName+File.separator+ fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            FileInfo f=new FileInfo();
    		f.setCreateDate(new Date());
    		f.setCreateUserId(account.getAccountId());
    		f.setDelFlag(ConstantUtils.NEW_FLAG);
    		f.setFilePath(uploadPath +dirName+File.separator+ fileName);
    		f.setFileWebPath(dirName+"/"+fileName);
    		f.setFileName(file.getOriginalFilename());
    		f.setFileType(suffixName);
    		f.setBussinessId(accidentVideoId);
    		fileInfoRepository.save(f);
            return RestJson.createSucces(f);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return RestJson.createError();
		
		
	}
	
	
	
	@RequestMapping(value = "/accidentinfo/deleteFile.xcx"  )
	@ResponseBody
	public RestJson deleteFile(String fileInfoId,String openIdMd5){
		String openid = redisService.getOpenId(openIdMd5);
		FileInfo f=fileInfoRepository.findById(fileInfoId).get();
		Account account = accountRepository.findByOpenid(openid).get(0);
	    f.setDelDate(new Date());
	    f.setDelUserId(account.getAccountId());
	    f.setDelFlag(ConstantUtils.DEL_FLAG);
	    fileInfoRepository.save(f);
	    List<FileInfo> list = fileInfoRepository.findByBussinessId(f.getBussinessId());
	    return RestJson.createSucces(list);
	}
	
	@RequestMapping(value = "/accidentinfo/getFileList.xcx"  )
	@ResponseBody
	public RestJson getFileList(String bussinessId){
		List<FileInfo> list = fileInfoRepository.findByBussinessId(bussinessId);
	    return RestJson.createSucces(list);
	}
}
