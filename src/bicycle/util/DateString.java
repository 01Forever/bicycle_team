package bicycle.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateString {
	
	static SimpleDateFormat dateform = new SimpleDateFormat("yyyy/MM/dd a hh:mm:ss");
	
	public static String dateString(Date date) {
		System.out.println(date);
		return dateform.format(date);
	}

	public static long minute(long firTime, long lastTime) {
		long minute = 0;
		minute = (lastTime - firTime) / 1000 / 60;
		return minute;
	}

	public static long money(long firTime, long lastTime,int setTime, int time, long money) {
		long getMoney = 0;
		long min = minute(firTime,lastTime);
		if(min<=setTime) {
			getMoney += money;
		}else if(min>setTime) {
			if((min-setTime)%time == 0) {
				getMoney += money + money*((min-setTime)/time);
			}else {
				getMoney += money + money*((min-setTime)/time+1);
			}
		}
		return getMoney;
	}
}
