package be.atc.javabeans;

public class UserBean {
	private String email=null;
	private String password=null;
	
	public UserBean() {}
	
	public String getIdentifiant() {
		return email;
	}
	
	public void setIdentifiant(String email) {
		this. email = email;
	}
	
	public String getMotdepasse() {
		return password;
	}
	
	public void setMotdepasse(String password) {
		this. password = password;
	}
}
