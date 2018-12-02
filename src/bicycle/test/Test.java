package bicycle.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import bicycle.service.Service;
import bicycle.service.ServiceImpl;
import bicycle.service.login.LoginService;
import bicycle.service.login.LoginServiceImpl;
import bicycle.vo.Manager;
import bicycle.vo.Member;
import bicycle.vo.Reservation;
import bicycle.vo.User;
import exception.BicycleNoException;
import exception.IdOverlapException;
import exception.KoreanNameException;

public class Test {
	public static User user = null;
	public static List<User> users = new ArrayList<>();
	public static Scanner scanner = new Scanner(System.in);
	public static boolean flag = true;
	public static List<Reservation> res = new ArrayList<>(); // 예약 내용 저장에 사용
	
	static {
		users.add(new Member("a", "a", "홍길동", new Date()));
		users.add(new Member("b", "b", "1길동", new Date()));
		users.add(new Manager("c", "c", "2길동", new Date()));
		users.add(new Member("d", "d", "3길동", new Date()));
		users.add(new Member("e", "e", "4길동", new Date()));
	}

	public static void main(String[] args) {
		LoginService loginService = new LoginServiceImpl();
		Service service = new ServiceImpl();
		boolean mainFlag = true;
		while (mainFlag) {
			try {
				System.out.println("1. 관리자 \t 2. 회원 \t 3. 비회원 \t 0. 종료");
				String input = scanner.nextLine();
				int inputNo = Integer.parseInt(input);
				String id = null;
				String pw = null;
				flag = true;
				if (inputNo == 1 || inputNo == 2) {
					System.out.println("id :: ");
					id = scanner.nextLine();
					System.out.println("pw :: ");
					pw = scanner.nextLine();
				}
				switch (inputNo) {
				case 1:
				case 2:
					if (loginService.login(id, pw)) {
						service.run();
					} else {
						System.out.println("유효하지 않는 회원입니다.");
					}
					break;

				case 3:
					service.run();
					break;
				
				case 0:
					System.out.println("이용해주셔서 감사합니다.");
					mainFlag = false;
					break;
					
				default:
					throw new NumberFormatException();
					
				} // switch end
			} catch (CloneNotSupportedException | NumberFormatException e) {
				System.out.println("잘못된 번호를 입력하였습니다.");
				System.out.println();
			} catch (IdOverlapException e) {
				System.out.println(e.getMessage());
			} catch (BicycleNoException e) {
				System.out.println(e.getMessage());
			} catch (KoreanNameException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
			}
		}
	}
}