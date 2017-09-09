package be.atc.services;

public class BookServiceException extends Exception {
	private static final long serialVersionUID = 1L;

	public BookServiceException() {}

	public BookServiceException(String message){
		super(message);
	}
}
