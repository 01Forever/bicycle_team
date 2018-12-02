package bicycle.vo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import bicycle.test.Test;
import bicycle.util.DateString;

public class ResReturn extends Reservation {
	Reservation res = new Reservation();
	private Date date;

	public ResReturn() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResReturn(String userId, String userPw, int bicycleNo, Date redate) {
		super(userId, userPw, bicycleNo);
		// TODO Auto-generated constructor stub
		this.date = redate;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		String retStr = "";
		String tot = "";
		long dateKeep = 0;
		long money = 0;
		User u = res.getUserlist(super.getUserId());
		if(u instanceof Member) {
			retStr = "[회원]";
			
		}else if(u instanceof Guest) {
			retStr = "[비회원]";
		}
		
		dateKeep = DateString.minute(u.getDate().getTime(), this.getDate().getTime());
		money = DateString.money(u.getDate().getTime(), this.getDate().getTime(), 60, 30, 1000);
		
		tot = " NAME :: "+ u.getName() +" || ID :: " +u.getId() +" || DATE :: "+ DateString.dateString(u.getDate()) + " || RETURN :: "+DateString.dateString(getDate())+" || Hours Of Use :: "+dateKeep+"분 || Pay :: "+money+"원 || Bicycle No. :: "+ super.getBicycleNo();
		return retStr+tot;
	}
	
}
