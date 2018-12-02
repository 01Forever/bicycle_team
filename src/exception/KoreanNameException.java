package exception;

public class KoreanNameException extends Exception {
	String name;

	public KoreanNameException(String msg, String name) {
		super(msg);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return super.getMessage() + "한글로 입력해주세요.";
	}

}

