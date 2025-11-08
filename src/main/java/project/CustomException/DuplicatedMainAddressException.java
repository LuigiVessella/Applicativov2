package project.CustomException;

public class DuplicatedMainAddressException extends Exception{
	public DuplicatedMainAddressException(String errorMessage) {
        super(errorMessage);
    }
}