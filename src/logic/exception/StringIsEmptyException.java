package logic.exception;

public class StringIsEmptyException extends Exception{

	private static final long serialVersionUID = -811228214149798510L;
	
	public StringIsEmptyException(String message) {
		super(message);
	}

}
