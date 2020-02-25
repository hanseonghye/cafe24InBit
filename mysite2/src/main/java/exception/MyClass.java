package exception;

public class MyClass {
	public void dangerMethod() throws MyException {
		System.out.println("normal state1");
		boolean isDanger = true;
		if ( isDanger ) {
			//예외 상황 발생
			throw new MyException();
		}
		
		System.out.println("normal state1");
	}
	
}
