import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;

public class Test {
	
	
	public static void main(String[] args) throws ParseException, FileNotFoundException, IOException {
		String startDate="2007-01-01";
		String endDate="2007-12-31";
		 long d = new SimpleDateFormat("yyyy-MM-dd").parse(startDate).getTime();
		System.out.println(d/1000);
		
		
		  PdfDocument pdfDoc = new PdfDocument(new PdfReader("e://811申请单.pdf"), new PdfWriter("e://form1.pdf"));
          PdfAcroForm pdfAcroForm = PdfAcroForm.getAcroForm(pdfDoc, true);
          PdfFont font = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H", false);
          pdfAcroForm.getField("city").setFont(font).setValue("北京");
          pdfAcroForm.getField("applyCode").setValue("010-20170102-119");
          pdfAcroForm.getField("orgName").setFont(font).setValue("惺惺惜惺惺想撒大声地撒大声地市政一公司");
        
//          pdfAcroForm.getField("hashValue").setValue(copyRightsVo.getHashValue());
          pdfAcroForm.flattenFields();
          pdfDoc.close();
          
          
          
        
		
	}

}
