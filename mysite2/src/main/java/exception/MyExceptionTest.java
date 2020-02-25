package exception;

public class MyExceptionTest {

	public static void main(String[] args) {
		try {
			MyClass myClass = new MyClass();
			myClass.dangerMethod();
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
