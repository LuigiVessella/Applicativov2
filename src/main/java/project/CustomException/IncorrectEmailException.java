package project.CustomException;

public class IncorrectEmailException extends Exception {
	public IncorrectEmailException(String errorMessage) {
        super(errorMessage);
    }
}
