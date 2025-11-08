package project.CustomException;

public class DuplicatedAccountException extends Exception{
	public DuplicatedAccountException(String errorMessage) {
        super(errorMessage);
    }
}
