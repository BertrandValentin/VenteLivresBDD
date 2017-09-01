package be.atc.javabeans;

public class AuthorBean {
	private String firstName=null;
	private String lastName=null;
	
	public AuthorBean() {}
	
	public String setFistName() {
		return firstName;
	}
	
	public void getFistName(String firstName) {
		this. firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this. lastName = lastName;
	}
}