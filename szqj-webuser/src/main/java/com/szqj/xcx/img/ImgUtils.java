package com.szqj.xcx.img;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImgUtils {
	
	private BufferedImage image;
	private  Integer width=750;
	private  Integer height;
	
	
	
	public void createImage(String fileLocation) {
        BufferedOutputStream bos = null;
        if(image != null){
            try {
                FileOutputStream fos = new FileOutputStream(fileLocation);
                bos = new BufferedOutputStream(fos);
                 
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
                encoder.encode(image);
                bos.close();
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
	
	private void DrawBgImg(String imgPath,Integer height){
		
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
	
	
	private void DrawMainContent(){
		Graphics2D userNamePic = image.createGraphics();
		userNamePic.setColor(Color.WHITE);
		userNamePic.fillRect(20, 60, width-40, height-100);
		
	}
	
	
	private void  DrawUserName(String userName,Integer height,Integer FontSize){
        Graphics2D userNamePic = image.createGraphics();
		userNamePic.setColor(Color.BLACK);
	    Font titleFont = new Font("宋体", Font.BOLD, FontSize);
	    userNamePic.setFont(titleFont);
	    userNamePic.drawString(userName, width/3, height);
	 
	}
	
	
	private void  DrawCompany(String postName,String companyName,Integer height,Integer FontSize){
        Graphics2D userNamePic = image.createGraphics();
		userNamePic.setColor(Color.BLACK);
	    Font titleFont = new Font("宋体", Font.BOLD, FontSize);
	    userNamePic.setFont(titleFont);
	    userNamePic.drawString(postName+" | "+companyName, width/3, height);
	}
	
	
	public static void main(String[] args) {
		ImgUtils cg = new ImgUtils();
        try {
        	cg.DrawBgImg("E:\\test\\timg.jpg", 987);
        	cg.DrawMainContent();
        	cg.DrawUserName("张国勇", 100,35);
        	cg.DrawCompany("经理","北京微昂科技有限公司", 140,25);
        	cg.createImage("E:\\test\\create.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	

}
