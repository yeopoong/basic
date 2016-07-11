package kyun.basic.exception;

public class MyException extends Exception {
	
	public String errorCode;
	
	public MyException() {
	}

	public MyException(String message) {
		super(message);
	}
	
	public MyException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
}
