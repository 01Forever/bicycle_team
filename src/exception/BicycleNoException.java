package exception;

public class BicycleNoException extends Exception {
	int no;

	public BicycleNoException () {}

	public BicycleNoException(int no) {
		this.no = no;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return  "이미 대여중입니다" ;
	}
}
