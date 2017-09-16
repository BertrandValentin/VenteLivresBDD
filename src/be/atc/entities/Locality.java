package be.atc.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the locality database table.
 * 
 */
@Entity
@Table(name="locality")
@NamedQuery(name="Locality.findAll", query="SELECT l FROM Locality l")
public class Locality implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idLocality;

	@Column(length=50)
	private String city;

	@Column(length=50)
	private String zipCode;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="locality")
	private List<User> users;

	public Locality() {
	}

	public int getIdLocality() {
		return this.idLocality;
	}

	public void setIdLocality(int idLocality) {
		this.idLocality = idLocality;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setLocality(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setLocality(null);

		return user;
	}
}