package project.CustomException;

public class PhoneNotFoundException extends Exception{
	public PhoneNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}