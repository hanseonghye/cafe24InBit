package exception;

public class MyException extends Exception {
	private static final long serialVersionUID = 1L;
	public MyException() {
		super("myexception occurs..");
	}
	
	public MyException(String msg) {
		super(msg);
	}
}
