package project.CustomException;

public class DuplicatedContactException extends Exception {
	public DuplicatedContactException(String errorMessage) {
        super(errorMessage);
    }
}
