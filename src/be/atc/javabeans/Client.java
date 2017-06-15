package be.atc.javabeans;

public class Client implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String identifiant=null;
	private String motdepasse=null;

	public Client() {}
	public String getIdentifiant() {
		return identifiant;
	}
	
	public void setIdentifiant(String identifiant) {
		this. identifiant = identifiant;
	}
	
	public String getMotdepasse() {
		return motdepasse;
	}
	
	public void setMotdepasse(String motdepasse) {
		this. motdepasse = motdepasse;
	}
}