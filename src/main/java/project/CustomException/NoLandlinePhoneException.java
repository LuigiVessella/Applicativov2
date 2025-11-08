package project.CustomException;

public class NoLandlinePhoneException extends Exception{
	public NoLandlinePhoneException(String errorMessage) {
        super(errorMessage);
    }
}