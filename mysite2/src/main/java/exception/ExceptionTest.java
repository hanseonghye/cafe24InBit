package exception;

public class ExceptionTest {
	public static void main(String[] args) {
		try {
			int a = 0;
			System.out.println("...");
			int k = 100/0;
		}catch(ArithmeticException e) {
			// 1. 로그 남기기
			System.out.println("err : "+e);
			
			// 2. 사과
			System.out.println("죄송합니다");
			
			//3. return 정상 종료
			return ;
			
			//1~3이 안되면 이거라도 해야함. 비워두면 안된다.
//			e.printStackTrace();
		}finally {
			// return 되더라도 실행된다.
			System.out.println("자원 종료");
		}
	}
}
