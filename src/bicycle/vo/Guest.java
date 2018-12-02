package bicycle.vo;

import java.util.Date;
import java.util.Random;

import bicycle.util.DateString;

public class Guest extends User {

	public Guest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Guest(String id, String pw, String name, Date date) {
		super(getRandom(), pw, name, date);
		// TODO Auto-generated constructor stub
	}

	public static String getRandom(){ // 랜덤 번호 생성

        char[] charaters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9'};

        StringBuffer sb = new StringBuffer();

        Random rn = new Random();

        for( int i = 0 ; i < 5 ; i++ ){

            sb.append( charaters[ rn.nextInt( charaters.length ) ] );

        }

        return sb.toString();

    }
	
	@Override
	public String toString() {
		return "[비회원] NAME : " + getName() + "\t ID : " + getId()+ "\t PW : "+ getPw() + "\t DATE : " + DateString.dateString(getDate());
	}
	
}
