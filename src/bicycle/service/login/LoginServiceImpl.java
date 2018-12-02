package bicycle.service.login;

import static bicycle.test.Test.*;

import java.io.IOException;
import java.util.Date;

import bicycle.service.ServiceImpl;
import bicycle.vo.Guest;
import bicycle.vo.Manager;
import bicycle.vo.Member;
import bicycle.vo.Reservation;
import bicycle.vo.User;
//import project.bicycle.vo.MemberUser;

public class LoginServiceImpl implements LoginService {
	public User getUser(String id) { 
		User user = null;
		for(User u : users) { // 이용자 정보 호출
			if(u.getId().equals(id)) {
				user = u;
			}
		}
		return user;
	}
	
	public Reservation getRes(String id) { // 예약 리스트 
		for(Reservation r : res) {
			if(r.getUserId().equals(id)) {
				return r;
			}
		}
		return null;
	}
	
	@Override
	public boolean login(String id, String pw) throws CloneNotSupportedException { // 로그인 
		// TODO Auto-generated method stub
		for (User u : users) {
			if (u.getId().equals(id) && u.getPw().equals(pw)) {
				user = u.clone();
				return true;
			}
		}
		return false;
	}

	@Override
	public void signup() throws IOException { // 회원가입
		System.out.println("         < 회 원 가 입 >");
		
		System.out.println("가입하실 회원님의 이름를 입력하세요.");
		System.out.print(">> ");
		String name = scanner.nextLine();
		System.out.println("가입하실 회원님의 아이디를 입력하세요.");
		System.out.print(">> ");
		String id = scanner.nextLine();
		System.out.println("가입하실 회원님의 비밀번호를 입력하세요.");
		System.out.print(">> ");
		String pw = scanner.nextLine();
		
		System.out.println();
		System.out.println("NAME :: "+name+" || ID :: "+id+" || PW :: "+pw);
		System.out.println();
		
		System.out.println("가입을 진행하시겠습니까?(y,n)");
		System.out.print(">> ");
		String check = scanner.nextLine();
		System.out.println();
		
		switch (check) {
		case "y":
			users.add(new Member(id, pw, name, new Date()));
			System.out.println("회원가입을 축하드립니다. 초기 화면으로 이동합니다.");
			logout();
			break;
			
		case "n":
			System.out.println("회원가입이 취소되었습니다.");
			break;
			
		default:
			System.out.println("잘못된 값을 입력하셨습니다.");
			break;
		}
	}

	@Override
	public void logout() { // 로그아웃
		// TODO Auto-generated method stub
		user = null;
		flag = false;
	}

	@Override
	public void userDelete() throws IOException { // 회원 삭제
		// 예약이 되어있는 사람은 제거 추가하기
		
		if(user instanceof Manager) {
			System.out.println("         < 회 원 삭 제 >");
			System.out.println("삭제를 진행하실 회원님의 아이디를 입력하세요.");
			System.out.print(">> ");
			String id = scanner.nextLine();
			
			userDeleteRe(id);
		}
		/* Member 일 경우 */
		else if(user instanceof Member) {
			System.out.println("         < 회 원 삭 제 >");
			
			userDeleteRe(user.getId());
		}
	}
	
	public void userDeleteRe(String id) throws IOException { // 회원 삭제 중복문
		
		User keepUser = getUser(id);
		Reservation keepRes = getRes(keepUser.getId());
		
		if(keepRes != null) {
			System.out.println("대여하신 자전거를 반납 후 재시도 해주세요.");
		}else if(keepRes == null){
			System.out.println("'"+keepUser.getName()+"("+keepUser.getId()+")' 님의 계정을 삭제하겠습니까?(y,n)");
			System.out.print(">> ");
			String check = scanner.nextLine();
			
			switch (check) {
			case "y":
				System.out.println("'"+keepUser.getName()+"("+keepUser.getId()+")' 님의 비밀번호를 입력하세요.");
				System.out.print(">> ");
				String pw = scanner.nextLine();
				if(keepUser.getPw().equals(pw)) {
					users.remove(keepUser);
					System.out.println("회원 정보가 삭제되었습니다. 감사합니다.");
				}else {
					System.out.println("비밀번호가 올바르지 않습니다.");
				}
				break;
				
			case "n":
				System.out.println("회원 정보 삭제를 취소합니다.");
				break;

			default:
				System.out.println("잘못된 값을 입력하였습니다.");
				break;
			} // switch (check) end
		}
	}
	
	@Override
	public void userModify() throws IOException { // 회원 수정
		// Manager
		if(user instanceof Manager) {
			System.out.println("         < 회 원 수 정 >");
			System.out.println("수정을 진행하실 회원님의 아이디를 입력하세요.");
			System.out.print(">> ");
			String id = scanner.nextLine();
			
			userModifyRe(id);
		}
		
		else if(user instanceof Member) {
			System.out.println("         < 회 원 수 정 >");
			String id = user.getId();
			
			userModifyRe(id);
		}
			
	}
	
	public void userModifyRe(String id) throws IOException { // 회원 수정 중복문

		User keepUser = getUser(id);

		if(keepUser.getId().equals(id)) {
			System.out.println("'"+keepUser.getName()+"("+keepUser.getId()+")' 님의 계정을 수정하겠습니까?(y,n)");
			System.out.print(">> ");
			String check = scanner.nextLine();
			switch(check) {
			case "y":
				System.out.println("수정할 항목을 선택하세요.");
				System.out.println("> 1. 아이디 \t 2. 비밀번호 \t 3. 이름");
				int input = Integer.parseInt(scanner.nextLine());
				switch(input) {
				case 1:
					repeat(keepUser, "아이디", input);
					break;

				case 2:
					repeat(keepUser, "비밀번호", input);
					break;

				case 3:
					repeat(keepUser, "이름", input);
					break;

				default:
					System.out.println("잘못된 값을 입력하였습니다.");
					break;
				} // switch(check) end
				break;

			case "n":
				System.out.println("회원 정보 수정을 취소합니다.");
				break;

			default:
				System.out.println("잘못된 값을 입력하였습니다.");
				break;

			} 
		} 
	}
	
	public void repeat(User u,String input, int type) throws IOException { // 회원 수정 2차 중복문
		
		String str ="";
		str += "현재 "+input+"는 (";
		
		switch(type) {
		case 1:
			str += u.getId();
			break;
			
		case 2:
			str += u.getPw();
			break;
			
		case 3:
			str += u.getName();
			break;
			
		}
		
		str += ")입니다.\n";
		System.out.println(str+"수정할 "+input+"를 입력하세요.");
		System.out.print(">> ");
		String newThing = scanner.nextLine();
		
		switch(type) {
		case 1:
			u.setId(newThing);
			if(getRes(u.getId()) != null) {
				getRes(u.getId()).setUserId(newThing);
			}
			break;
			
		case 2:
			u.setPw(newThing);
			if(getRes(u.getId()) != null) {
				getRes(u.getId()).setUserPw(newThing);
			}
			break;
			
		case 3:
			u.setName(newThing);
			break;
		}
		System.out.println();
		System.out.println("회원 정보 수정이 완료되었습니다.");
		System.out.println("다시 로그인 해주세요.");
	}

	public void guestLogin() throws IOException { // 비회원 로그인
		try {
			System.out.println("[비회원] 이용자의 이름을 입력하세요.");
			System.out.print(">> ");
			String name = scanner.nextLine();
			System.out.println("[비회원] "+name+"님께서 사용하실 비밀번호를 입력하세요.");
			System.out.print(">> ");
			String pw = scanner.nextLine();
			
			users.add(new Guest(null, pw, name, new Date()));
			user = users.get(users.size()-1).clone();
			System.out.println("[비회원] 이용자님의 아이디는 "+user.getId()+"입니다.");
			System.out.println();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

