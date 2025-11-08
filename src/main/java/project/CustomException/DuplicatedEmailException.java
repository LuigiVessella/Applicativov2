package project.CustomException;

public class DuplicatedEmailException extends Exception{
	public DuplicatedEmailException(String errorMessage) {
        super(errorMessage);
    }
}
