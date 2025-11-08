package project.CustomException;

public class ContactNotFoundException extends Exception {
	public ContactNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}

