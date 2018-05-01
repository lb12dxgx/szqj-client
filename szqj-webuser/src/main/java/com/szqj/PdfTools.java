package com.szqj;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.szqj.before.domain.BeforeApply;

public class PdfTools {
	
	
	public static void genPdf(File file,File pdfForm,Map<String, String> hashMap) {
		 try {
		 PdfReader pdfReader=new PdfReader(pdfForm); 
		 PdfWriter pdfWriter=new PdfWriter(file);
		 PdfDocument pdfDoc = new PdfDocument(pdfReader, pdfWriter);
         PdfAcroForm pdfAcroForm = PdfAcroForm.getAcroForm(pdfDoc, true);
         PdfFont font = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H", false);
	     for(String key:hashMap.keySet()) {
        	 String value=hashMap.get(key);
        	 pdfAcroForm.getField(key).setFont(font).setValue(value);
         }
         pdfAcroForm.flattenFields();
         pdfDoc.close();
		 } catch (IOException e) {
				e.printStackTrace();
		}
        
	}

}
