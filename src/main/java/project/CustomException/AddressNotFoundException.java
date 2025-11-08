package project.CustomException;

public class AddressNotFoundException extends Exception {
	public AddressNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
