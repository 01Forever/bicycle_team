package bicycle.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static bicycle.test.Test.*;
import static java.lang.Integer.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import bicycle.service.login.LoginServiceImpl;
import bicycle.util.DateString;
import bicycle.vo.Bicycle;
import bicycle.vo.Manager;
import bicycle.vo.Member;
import bicycle.vo.ResReturn;
import bicycle.vo.Reservation;
import bicycle.vo.User;
import exception.IdOverlapException;
import exception.KoreanNameException;

public class ServiceImpl implements Service {

	List<User> usersName = null; // 이용자 이름별 정렬에 사용
	List<Reservation> resDate = null; // 이용자 날짜별 정렬에 사용
	List<Bicycle> garage = new ArrayList<Bicycle>();
	LoginServiceImpl login = new LoginServiceImpl(); 
	List<ResReturn> resReturn = new ArrayList<ResReturn>(); // 반납 정보 저장
	
	{
		
		for (int i = 0; i < 15; i++) {
			garage.add(new Bicycle(i + 1));
		}
		
		res.add(new Reservation("a", "a", 1));
		res.add(new Reservation("b", "b", 2));
		res.add(new Reservation("c", "c", 3));
		
		for(int i=0; i<res.size(); i++) {
			for(int j=0; j<garage.size(); j++) {
				if(res.get(i).getBicycleNo() == garage.get(j).getNo()) {
					garage.get(j).setNo(0);
				}
			}
		}
	}
	
	@Override
	public void run() throws IOException, IdOverlapException, KoreanNameException { // 실행 화면
		// TODO Auto-generated method stub
		if(user instanceof Manager) {
			System.out.println("< 관리자 권한으로 접속하였습니다. >");
			while (flag) {
				System.out.println("[1. 회원 리스트 조회 \t 2. 회원 추가 \t 3. 회원 삭제 \t 4. 회원 정보 수정  \n "
					      +"5. 자전거 현황 조회 \t 6. 자전거 반납 \t 7. 자전거 갯수 추가 \t 8. 자전거 갯수 삭제 \t 9. 자전거 이용 현황 \t 10. 반납 내역 조회 \t 0. 종료]");

				int input = parseInt(scanner.nextLine());
				
				switch(input) {
				case 1:
					getUserList(); // 리스트 조회
					System.out.println();
					break;
				
				case 2:
					login.signup(); // 회원 추가
					System.out.println();
					break;
					
				case 3:
					login.userDelete(); // 회원 삭제
					System.out.println();
					break;
					
				case 4:
					login.userModify(); // 회원 정보 수정
					System.out.println();
					break;
				
				case 5:
					showGarage(); // 자전거 현황 조회
					System.out.println();
					break;
				case 6:
					returnBicycle();
					System.out.println();
					break;
					
				case 7:
					cycleAdd(); // 자전거 갯수 추가
					showGarage();
					System.out.println();
					break;
					
				case 8:
					cycleDelete(); // 자전거 갯수 삭제
					showGarage();
					System.out.println();
					break;
					
				case 9:
					getReservationList();
					System.out.println();
					break;
					
				case 10:
					returnList();
					System.out.println();
					break;
					
				case 0:
					login.logout(); // 로그아웃
					break;
					
				default:
					break;
					
				}
			}
		}else if(user instanceof Member) {
			System.out.println("< '"+user.getName()+"("+user.getId()+")'님 환영합니다. >");
			while (flag) {
				System.out.println("[1. 자전거 현황 조회 \t 2. 자전거 대여 \t 3. 자전거 반납 \t 4. 이용 현황 조회 \t 5. 회원 정보 수정 \t 6. 회원 삭제 \t 0. 종료]");

				int input = parseInt(scanner.nextLine());
				
				switch(input) {
				case 1:
					showGarage();
					System.out.println();
					break;
					
				case 2:
					boolean b = true;
					for (Reservation r : res) {
						if (r.getUserId().equals(user.getId())) {
							System.out.println("이미 대여했습니다.");
							System.out.println();
							b = false;
							break;
						}
					}
					if (b)
						rentBicycle(); // 자전거 대여
					System.out.println();
					break;
				
				case 3:
					returnBicycle(); // 자전거 반납
					System.out.println();
					break;
					
				case 4:
					inquiry(); // 이용 현황 조회
					System.out.println();
					break;
					
				case 5:
					login.userModify(); // 회원 정보 수정
					System.out.println();
					login.logout();
					break;
					
				case 6:
					login.userDelete(); // 회원 삭제
					System.out.println();
					login.logout();
					break;
					
				case 0:
					login.logout(); // 로그아웃
					break;
					
				default:
					break;
					
				} // switch 끝
			} // while 끝
			
		} else {
			
			while (flag) {
				System.out.println("[1. 자전거 현황 조회 \t 2. 자전거 대여 \t 3. 자전거 반납 \t 4. 이용 현황 조회 \t 5. 회원 가입 \t 0. 종료]");
				int input = parseInt(scanner.nextLine());
				switch(input) {
				case 1:
					showGarage();
					System.out.println();
					break;
					
				case 2:
					login.guestLogin();
					rentBicycle();
					System.out.println();
					login.logout();
					break;
				
				case 3:
					returnBicycle();
					System.out.println();
					login.logout();
					break;
					
				case 4:
					inquiry();
					System.out.println();
					break;
					
				case 5:
					login.signup();
					System.out.println();
					break;
					
				case 0:
					login.logout(); // 로그아웃
					break;
					
				default:
					break;
					
				} // switch 끝
				
			} // while 끝
			
		} // else 끝 
	} // run() 끝

	private void showGarage() { // 자전거 출력
		System.out.println("==>> 대여가능한 자전거 코드 목록");
		
		for(int i=0; i<garage.size();i++) {
			
			if(garage.get(i).getNo() != 0) {
				
				if(garage.get(i).getNo()<10) {
					System.out.print("[0"+ garage.get(i).getNo() +"]  ");
				}else {
					System.out.print("["+ garage.get(i).getNo() +"]  ");
				} // 10보다 작은 수 두자리 만드는 if문
				
			} else {
				System.out.print("[■] ");
			} // 대여한 자전거 표기 차이 if문
			
		}
		System.out.println();	
	} // showGarage() 끝

	@Override
	public void rentBicycle() throws IOException { // 자전거 대여
		// TODO Auto-generated method stub
		showGarage();

		System.out.println("\n대여할 자전거의 번호를 입력하세요");
		System.out.print(">> ");
		int input = parseInt(scanner.nextLine());

		if(garage.get(input-1).getNo() == 0) {
			System.out.println("이미 대여가 된 자전거입니다.");
		}else if(garage.get(input-1).getNo() != 0){
			System.out.println("'"+user.getName()+"'님께서 대여하실 자전거는 ["+input+"]번 입니다.");
			System.out.println("대여의 진행을 원하시면 y를, 아니시라면 n를 입력해주세요.");
			System.out.print(">> ");
			String check = scanner.nextLine();
			switch(check) {
			case "y":
				garage.get(input-1).setNo(0);
				res.add(new Reservation(user.getId(), user.getPw(), input));
				login.getUser(user.getId()).setDate(new Date());
				System.out.println("["+input+"]번 자전거를 대여하셨습니다.");
				break;

			case "n":
				System.out.println("대여를 취소합니다.");
				break;

			default:
				System.out.println("잘못된 값을 입력하셨습니다.");
				break;
			}
		}		
	} // rentBicycle() 끝

	@Override
	public void returnBicycle() throws IOException { // 자전거 반납 
		
		// TODO Auto-generated method stub
		if(user instanceof Member) {
			System.out.print("'"+user.getName()+"("+user.getId()+")'님");
			
			returnBicycleRe(user);
			
		} // member 이용자
		else{
			System.out.println("반납하실 이용자의 아이디를 입력하세요.");
			System.out.print(">> ");
			String id = scanner.nextLine();
			User keepUser = null;
			keepUser = login.getUser(id);
			
			
			System.out.print("'"+keepUser.getName()+"("+keepUser.getId()+")'님");
			
			returnBicycleRe(keepUser);
		}
	} // returnBicycle() 끝

	public void returnBicycleRe(User u) throws IOException { // 자전거 반납 중복 문장
		Reservation keepRes =  login.getRes(u.getId());
		
		System.out.println("께서 반납하실 자전거 번호는 ["+keepRes.getBicycleNo()+"]번 입니다.");
		System.out.println("반납 진행을 원하시면 y를, 아니시라면 n를 입력해주세요.");
		System.out.print(">> ");
		String check = scanner.nextLine();
		
		switch(check) {
		case "y": {
			System.out.println("반납을 진행하실 이용자의 비밀번호를 입력하세요.");
			System.out.print(">> ");
			String pw = scanner.nextLine();
			if(keepRes.getUserPw().equals(pw)) {
				resReturn.add(new ResReturn(u.getId(), u.getPw(), keepRes.getBicycleNo(), new Date()));
				ResReturn resRe = resReturn.get(resReturn.size()-1);
				System.out.print("이용 시간은 "+DateString.minute(u.getDate().getTime(), resRe.getDate().getTime())+"분이며, ");
				System.out.println("이용 요금은 "+DateString.money(u.getDate().getTime(), resRe.getDate().getTime(), 60, 30, 1000)+"원 입니다.");
				garage.get(keepRes.getBicycleNo()-1).setNo(keepRes.getBicycleNo());
				res.remove(keepRes);
				System.out.println("반납이 완료되었습니다. 감사합니다.");
				break;
			}
		}	
			break;
			
		case "n":
			System.out.println("반납을 취소합니다.");
			break;
			
		default:
			System.out.println("잘못된 값을 입력하셨습니다.");
			break;
		}
	}
		
	public void getUserList() { // 유저 리스트
		
		usersName = new ArrayList<>(users);
		Comparator<User> c = new Comparator<User>() { // 이름순 나열

			@Override
			public int compare(User o1, User o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareTo(o2.getName());
			} // compare() end
			
		}; // Comporator end 
		
		usersName.sort(c);
		usersName.forEach(System.out::println);
		
	} // getUserList() end

	public void getReservationList() { // 대여 리스트
		
		resDate = new ArrayList<>(res);
		Comparator<Reservation> c = new Comparator<Reservation>() { // 시간순 나열
			
			@Override
			public int compare(Reservation o1, Reservation o2) {
				// TODO Auto-generated method stub
				
				return login.getUser(o1.getUserId()).getDate().compareTo(login.getUser(o2.getUserId()).getDate());
			}
		};
		resDate.sort(c);
		resDate.forEach(System.out::println);
	} // getReservationList() end 

	@Override
	public void inquiry() { // 이용 현황
		// TODO Auto-generated method stub
		if(user instanceof Member) {
			System.out.println("'"+user.getName()+"("+user.getId()+")'님의 이용 현황");
			
			iquiryRe(user.getId(), user.getPw());
		} // Member 이용현황 끝
		else {
			System.out.println("조회할 이용자의 아이디를 입력하세요.");
			System.out.print(">> ");
			String id = scanner.nextLine();
			System.out.println("' "+login.getUser(id).getName()+"("+id+")'님의 비밀번호를 입력하세요.");
			System.out.print(">> ");
			String pw = scanner.nextLine();
			
			iquiryRe(id, pw);
		} // else end
	} // iquiry() end
	
	public void iquiryRe(String id, String pw) { // iquiry 중복 문장
		Reservation keepRes = login.getRes(id);
		
		if(keepRes.getUserId().equals(id) && keepRes.getUserPw().equals(pw)) {
			System.out.println();
			System.out.println(keepRes);
		}else {
			System.out.println("기록이 존재하지 않습니다.");
		}
	}
	
	public void cycleDelete() throws IOException { // 자전거 갯수 삭제
		System.out.println("삭제할 자전거의 갯수를 입력하세요.");
		System.out.print(">> ");
		int deletNum = Integer.parseInt(scanner.nextLine());
		for(int i=0; i<deletNum; i++) {
			if(garage.get(garage.size()-1).getNo() == 0) {
				System.out.println();
				System.out.println("SYSTEM :: 현재 대여 중인 자전거가 존재합니다.");
				break;
			}else {
				garage.remove(garage.size()-1);
			}
		}
		System.out.println();
	} // cycleDelete() end
		
	public void cycleAdd() throws IOException { // 자전거 갯수 추가
		System.out.println("추가할 자전거 갯수를 입력하세요.");
		System.out.print(">> ");
		int addNum = Integer.parseInt(scanner.nextLine());
		for(int i=0; i<addNum; i++) {
			garage.add(new Bicycle(garage.size()+1));
		}
		
		System.out.println("추가가 완료되었습니다.");
		System.out.println();
	} // cycleAdd() end
	
	public void returnList() {
		resReturn.forEach(System.out::println);
	}
	
	
	
}
