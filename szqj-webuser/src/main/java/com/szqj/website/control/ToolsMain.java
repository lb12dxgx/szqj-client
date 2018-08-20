package com.szqj.website.control;

import java.io.File;
import java.io.IOException;
import java.util.*;

import net.coobird.thumbnailator.Thumbnails;

public class ToolsMain {
	
	
	public void reSize(String filepath,int width,int height) throws IOException{
		 List<File> files = getFileList(filepath);
		 for(File f:files) {
				System.out.println(f.getName()); 
				String suffixName = f.getName().substring(f.getName().lastIndexOf(".")+1);
			     if(suffixName.equals("JPEG")||suffixName.equals("jpeg")||suffixName.equals("jpg")){
			    	 Thumbnails.of(f).outputFormat("JPEG").size(width, height).keepAspectRatio(false).toFile(f);
			     }
			     
			     if(suffixName.equals("PNG")||suffixName.equals("png")){
			    	 Thumbnails.of(f).outputFormat("PNG").size(width, height).keepAspectRatio(false).toFile(f);
			     }
			}
	}
	
	private List<File> getFileList(String filepath){
		List<File> files=new ArrayList<File>();
		File pfile = new File(filepath);
		addFile(pfile,files);
		return files;
	}

	private void addFile(File pfile, List<File> files) {
		File[] fArray = pfile.listFiles();
		for(File dir:fArray){
			if(dir.isDirectory()){
				addFile(dir,files);
			}else{
				files.add(dir);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		String filepath = "D:\\1";
		ToolsMain toolsMain=new ToolsMain();
		toolsMain.reSize(filepath, 182, 258);
       
	}

}
