package chen.yiheng.estore.utils;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FactoryMain {
	public static String getDate(int i) {
		Date d = new Date();
		//System.out.println(d);

		Calendar calendar   =   new GregorianCalendar();
		calendar.setTime(d);
		calendar.add(calendar.DATE,-i);
		d = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String result = sdf.format(d);

		return result;
	}
	public static void main(String[] args) {
		System.out.println(getDate(100));
	}
	
}
