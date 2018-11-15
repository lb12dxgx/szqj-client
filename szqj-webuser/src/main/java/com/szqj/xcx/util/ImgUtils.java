package com.szqj.xcx.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;



public class ImgUtils {
	
	private BufferedImage image;
	private  Integer width=750;
	private  Integer height;
	
	
	
	private void createImage(String fileLocation) {
        BufferedOutputStream bos = null;
        if(image != null){
            try {
            	ImageIO.write(image, "jpg", new File(fileLocation));
               
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                if(bos!=null){//关闭输出流
                    try {
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
	
	private void drawBgImg(String imgPath,Integer height){
		
	    image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics mainPic = image.getGraphics();
	    BufferedImage bimg = null;
	    try {
	           bimg = javax.imageio.ImageIO.read(new java.io.File(imgPath));
	        } catch (Exception e) {}
	     
	        if(bimg!=null){
	            mainPic.drawImage(bimg, 0, 0, width, height, null);
	            mainPic.dispose();
	        }
	        this.height=height;
		
	}
	
	
	private void drawMainContent(){
		Graphics2D userNamePic = image.createGraphics();
		userNamePic.setColor(Color.WHITE);
		userNamePic.fillRect(20, 60, width-40, height-100);
		
	}
	
	
	private void  drawUserName(String userName,Integer height,Integer FontSize){
        Graphics2D userNamePic = image.createGraphics();
		userNamePic.setColor(Color.BLACK);
	    Font titleFont = new Font("宋体", Font.BOLD, FontSize);
	    userNamePic.setFont(titleFont);
	    userNamePic.drawString(userName, width/3, height);
	 
	}
	
	
	private void  drawCompany(String postName,String companyName,Integer height,Integer FontSize){
        Graphics2D userNamePic = image.createGraphics();
		userNamePic.setColor(Color.BLACK);
	    Font titleFont = new Font("宋体", Font.BOLD, FontSize);
	    userNamePic.setFont(titleFont);
	    userNamePic.drawString(postName+" | "+companyName, width/3, height);
	}
	
	

	private void  drawMoney(String money,Integer height,Integer FontSize){
		
		 Graphics2D moneyPic = image.createGraphics();
	     moneyPic.setColor(Color.RED);
	     Font titleFont = new Font("宋体", Font.BOLD, FontSize+5);
	     moneyPic.setFont(titleFont);
	     moneyPic.drawString("价值 100元", width/3+40, height);	
		
        Graphics2D rectPic = image.createGraphics();
        rectPic.setColor(Color.RED);
        rectPic.drawRect(width/4, height+20, 400, 60);
        
         Graphics2D khPic = image.createGraphics();
         khPic.setColor(Color.RED);
	     Font khFont = new Font("宋体", Font.BOLD, FontSize);
	     khPic.setFont(khFont);
	     khPic.drawString("接力传递  与好友共享奖励", width/4+40, height+60);	
        
   }
	
	
	private void  drawProblem(String title,String content,Integer height,Integer FontSize){
		 Graphics2D titlePic = image.createGraphics();
		 titlePic.setColor(Color.BLACK);
	     Font titleFont = new Font("宋体", Font.BOLD, FontSize+5);
	     titlePic.setFont(titleFont);
	     if(title.length()>20){
	    	 title=title.substring(0, 20);
	     }
	     titlePic.drawString(title, 40, height);	
	     
	     if(content.length()>90){
	    	 content=content.substring(0,90); 
	     }
	     List<String> l = spliteByLength(content,25);
	     for(int i=0;i<l.size();i++){
		     Graphics2D contentPic = image.createGraphics();
		     contentPic.setColor(Color.BLACK);
		     Font contentFont = new Font("宋体", Font.PLAIN, FontSize);
		     titlePic.setFont(contentFont);
		     titlePic.drawString(l.get(i), 60, height+(i+1)*40);	
	     }
	}
	
	
	private void  drawShare(String name,Integer height,Integer FontSize){
		Graphics2D linePic = image.createGraphics();
		BasicStroke stokeLine   = new BasicStroke(   2.0f   ); 
		linePic.setStroke(stokeLine);
		linePic.setColor(Color.BLACK);
		linePic.drawLine(40, height, width-40, height);
		
		 Graphics2D namePic = image.createGraphics();
		 namePic.setColor(Color.BLACK);
	     Font nameFont = new Font("宋体", Font.BOLD, FontSize+5);
	     namePic.setFont(nameFont);
	     namePic.drawString(name,60, height+40);
	     
	     
	     Graphics2D khPic = image.createGraphics();
	     khPic.setColor(Color.BLACK);
	     Font khFont = new Font("宋体", Font.PLAIN, FontSize);
	     khPic.setFont(khFont);
	     khPic.drawString("帮朋友传播  大家来帮忙",60, height+80);
	}
	
	
	private void drawShareImg(String imgPath,Integer height){
		
		Graphics mainPic = image.getGraphics();
	    BufferedImage bimg = null;
	    try {
	           bimg = javax.imageio.ImageIO.read(new java.io.File(imgPath));
	        } catch (Exception e) {
	        	e.getMessage();
	        }
	     
	        if(bimg!=null){
	            mainPic.drawImage(bimg, 500, height+10, 140, 140, null);
	            mainPic.dispose();
	        }
	        
		
	}
	
	
	private  List<String> spliteByLength(String str,Integer length){
		List<String> list=new ArrayList<String>();
		int totalLength = str.length();
		
		if(totalLength<=length){
			list.add(str);
		}else{
			 double m = Math.ceil(totalLength/Double.parseDouble(length+""));
			 int l = (int)m;
			 for(int i=0;i<l;i++){
				  String s="";
				  if(i+1<l){
					  s=str.substring(i*length, (i+1)*length);
				  }else{
					  s=str.substring(i*length, totalLength-1);
				  }
				  System.out.println(s);
				  list.add(s);
			 }
		}
		return list;
	}
	
	
	public String createShareImg(String fileDir,XcxShareImgModel xcxShareImgModel){
		CodeUtils codeUtils=new CodeUtils();
		String access_token =codeUtils.getToken();
		
		drawBgImg(fileDir+File.separator+"sharetemplet"+File.separator+"bg.jpg", 987);
		drawMainContent();
		drawUserName(xcxShareImgModel.getCreateUserName(), 100,35);
		drawCompany(xcxShareImgModel.getPostName(),xcxShareImgModel.getCompanyName(), 140,25);
		drawMoney(xcxShareImgModel.getMoney(),180,25);
		drawProblem(xcxShareImgModel.getTitle(),xcxShareImgModel.getContent(),300,25);
		drawShare(xcxShareImgModel.getShareUserName(),500, 25);
		String codeFilePath=fileDir+File.separator+"shareImg"+File.separator+xcxShareImgModel.getShareCode()+"code.jpg";
		
		codeUtils.createBCode(access_token, xcxShareImgModel.getSharePath(), xcxShareImgModel.getShareCode(),codeFilePath);
		drawShareImg(codeFilePath,500);
		
		String imgFilePath=fileDir+File.separator+"shareImg"+File.separator+xcxShareImgModel.getShareCode()+".jpg";
		createImage(imgFilePath);
		
		return "/shareImg/"+xcxShareImgModel.getShareCode()+".jpg";
	}
	
	
	/*public static void main(String[] args) {
		String content="科技日报广东珠海11月6日消息，11月6日，世界最大军事技术展会之一，为期6天的2018珠海航展正式拉开序幕。云洲作为广东省军民融合企业优秀代表受邀参展，此次多款最新重磅无人艇产品参展，向外界展示云洲全球领先的技术实力及最新的军民融合成果。值得注意的是，展会上云洲首次公开展示了刚刚成功进行导弹飞行试验的察打一体导弹无人艇——“瞭望者Ⅱ”，这是中国第一艘导弹无人艇，也是全球第二个成功发射导弹的无人艇，填补了国内导弹无人艇这一技术空白，具有重要的战略意义，成为本届航展新亮点。";
		
		ImgUtils cg = new ImgUtils();
		 try {
        	cg.drawBgImg("E:\\test\\timg.jpg", 987);
        	cg.drawMainContent();
        	cg.drawUserName("张国勇", 100,35);
        	cg.drawCompany("经理","北京微昂科技有限公司", 140,25);
        	cg.drawMoney("1000",180,25);
        	cg.drawProblem("中国首艘导弹无人艇公开亮相 可精确打击海上目标",content,300,25);
        	cg.drawShare("张上",500, 25);
        	
        	
        	cg.createImage("E:\\test\\create.jpg");
        	
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		//cg.spliteByLength(content, 20);
		
    }*/
	

}
