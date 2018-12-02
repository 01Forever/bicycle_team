package bicycle.vo;

import java.util.Date;

import bicycle.util.DateString;

public class Member extends User {

	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Member(String id, String pw, String name, Date date) {
		super(id, pw, name, date);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {

		return "[회원]   NAME : " + getName() + "\t ID : "+ getId() +"\t PW : " + getPw() + "\t DATE : "+ DateString.dateString(getDate());
	}
	
}
