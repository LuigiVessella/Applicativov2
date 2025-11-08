package project.CustomException;

public class DuplicatedPhoneException extends Exception{
	public DuplicatedPhoneException(String errorMessage) {
        super(errorMessage);
    }
}