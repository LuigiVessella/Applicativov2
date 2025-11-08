package project.CustomException;

public class NoMainAddressException extends Exception{
	public NoMainAddressException(String errorMessage) {
        super(errorMessage);
    }
}