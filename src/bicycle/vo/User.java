package bicycle.vo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import bicycle.util.DateString;

public class User implements Cloneable{
	private String id;
	private String pw;
	private String name;
	private Date date;

	public User() {	}
	
	public User(String id, String pw, String name, Date date) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	

	@Override
	public User clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (User)super.clone();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", pw=" + pw + ", name=" + name + ", date="
				+ DateString.dateString(getDate()) + (this instanceof Manager ? "(관리자)" : "") +"]";
	}
	
}
