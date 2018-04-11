import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Test {

	public static void main(String[] args) throws ParseException {
		String startDate="2007-01-01";
		String endDate="2007-12-31";
		 long d = new SimpleDateFormat("yyyy-MM-dd").parse(startDate).getTime();
		System.out.println(d/1000);
		
	}

}
