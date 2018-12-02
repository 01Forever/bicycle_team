package bicycle.service.login;

import java.io.IOException;

import exception.IdOverlapException;
import exception.KoreanNameException;

public interface LoginService {
	boolean login(String id, String pw) throws CloneNotSupportedException; // 로그인
	void signup() throws IdOverlapException, KoreanNameException, IOException; // 회원가입
	void logout(); // 로그아웃
	void userDelete() throws IOException; // 회원 삭제
	void userModify() throws IdOverlapException, IOException; // 회원 수정
}
