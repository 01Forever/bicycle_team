package bicycle.vo;

import java.util.Date;

public class Manager extends User {

	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Manager(String id, String pw, String name, Date date) {
		super(id, pw, name, date);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "[관리자] NAME : " + getName() + "\t ID : " + getId()+ "\t PW : "+getPw();
	}
	
}
