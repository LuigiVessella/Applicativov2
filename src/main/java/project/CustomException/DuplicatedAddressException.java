package project.CustomException;

public class DuplicatedAddressException extends Exception {
	public DuplicatedAddressException(String errorMessage) {
        super(errorMessage);
    }
}
