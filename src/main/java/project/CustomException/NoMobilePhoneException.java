package project.CustomException;

public class NoMobilePhoneException extends Exception{
	public NoMobilePhoneException(String errorMessage) {
        super(errorMessage);
    }
}