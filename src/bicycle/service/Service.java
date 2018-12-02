package bicycle.service;

import java.io.IOException;

import exception.BicycleNoException;
import exception.IdOverlapException;
import exception.KoreanNameException;

public interface Service {
	void rentBicycle() throws BicycleNoException, IOException;
	void returnBicycle() throws IOException;
	void run() throws IdOverlapException, KoreanNameException, BicycleNoException, IOException;
	// 조회(회원 : 남은 기간 조회, 비회원 : 이용 시간 조회, 관리자 : 비회원과 회원의 이용시간 및 남은 기간 조회)
	void inquiry();
}
