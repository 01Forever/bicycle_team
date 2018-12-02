package bicycle.vo;

import bicycle.test.Test;
import bicycle.util.DateString;
import static bicycle.test.Test.users;

public class Reservation {
	private String userId;
	private String userPw;
	private int bicycleNo;
	
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reservation(String userId, String userPw,int bicycleNo) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.bicycleNo = bicycleNo;
	}
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getBicycleNo() {
		return bicycleNo;
	}
	public void setBicycleNo(int bicycleNo) {
		this.bicycleNo = bicycleNo;
	}
	
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	
	@Override
	public String toString() {
		String retStr = "";
		String tot = "";
		User u = getUserlist(userId);
		if(u instanceof Member) {
			retStr = "[회원]   ";
		}else if(u instanceof Guest) {
			retStr = "[비회원] ";
		}else {
			retStr = "[관리자] ";
		}
		tot = "NAME :: "+ u.getName() +" || ID :: " +u.getId() +" || DATE :: "+ DateString.dateString(u.getDate()) +" || Bicycle No. :: "+ bicycleNo;
		return retStr+tot;
	}
	
	public User getUserlist(String id) {
		User user = null;
		for(User u : users) { // 이용자 정보 호출
			if(u.getId().equals(id)) {
				user = u;
			}
		}
		return user;
	}
	
}
