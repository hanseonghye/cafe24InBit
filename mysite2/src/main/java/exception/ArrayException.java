package exception;

public class ArrayException {
	public static void main(String[] args) {
	// 이 상황은 try-catch를 거는게 아니다. 코드를 바꿔야함.
		int [] a = {10,20,30};
		for(int i=0;i<4;++i) {
			System.out.println(a[i]);
		}
	}
}
