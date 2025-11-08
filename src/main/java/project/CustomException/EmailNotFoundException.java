package project.CustomException;

public class EmailNotFoundException extends Exception{
	public EmailNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
