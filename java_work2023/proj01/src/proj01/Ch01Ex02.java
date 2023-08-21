package proj01;

public class Ch01Ex02 {

	public static void main(String[] args) {
		// 데이터 타입과 변수
		// 참조형 데이터 타입 : 객체 참조, 배열 참조, 인터페이스...
		// 기본 데이터 타입: 8개(boolean, 실수, 정수(문자형 포함))
		// 문자도 정수 타입으로 취급된다.
		// boolean - 논리 형(true, false) - 1bit
		// double - 실수형의 기본 타입  - 8byte
		// float - 실수형 - 4byte
		// 나머지 5개가 정후형 데이터 타입
		// 정수형 중에 1개는 문자형 : char
		// byte, short, int, long
		// int형이 4byte
		// int형의 반 short (2byte)
		// byte 형은 1byte
		// int형의 두배가 long형이고 표기는 1000L
		// 자바에서 변수를 선언 할때는 타입을 반드시 지정 해 주어야한다.
		// 타입 변수명 = 초기값;
		int age = 10;
		String name = "홍길동";
		double number;
		number = 1000;
		
		age = (int)number;
		System.out.println("Age = " + age);
		System.out.print("Name = ");
		System.out.println(name);
	}

}
