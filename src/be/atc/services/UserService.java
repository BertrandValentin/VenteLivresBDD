package be.atc.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;

import be.atc.entities.Locality;
import be.atc.entities.User;

public class UserService {
	protected EntityManager em;
	final String QUERY_SELECT_USER = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password";
	
	public UserService(EntityManager em){
		this.em = em;
	}
	
	public User createUser(User user){
		em.persist(user);
		
		return user;
	}
	
	public void removeUser(User user){
		if (em.find(User.class, user.getIdUser()) != null)
			em.remove(user);
	}
	
	public User modifyUser(User userTarget, User userNew){
		userTarget = em.find(User.class, userNew.getIdUser());
		
		return userTarget;
	}
	
	public User findUser(User user){
		return em.find(User.class, user.getIdUser());
	}
	
	public List<User> findAllUsers(){
		javax.persistence.TypedQuery<User> query = (javax.persistence.TypedQuery<User>) em.createQuery("SELECT u FROM User u", User.class);
		return query.getResultList();
	}
	
	public User getUser(String email, String password) throws UserServiceException{
		if (email.isEmpty() || password.isEmpty())
			throw new UserServiceException("Email or password cannot be empty");
		
		List<User> userList = em.createQuery(QUERY_SELECT_USER, User.class)
				.setParameter("email", email)
				.setParameter("password", password)
				.getResultList();
		
		if (userList.size() == 0)
			throw new UserServiceException("Wrong email or password");
		else if (userList.size() == 1)
			return userList.get(0);
		//TODO verify how to avoid with the database to do not duplicate entry
		else if (userList.size() > 1)
			throw new UserServiceException("Duplicated user");
		return null;
	}

	public User findUserById(int id) {
		User userToFind = new User();
		userToFind.setIdUser(id);
		return findUser(userToFind);
	}
	
	public User updateUser(User user, String firstName, String lastName, Date birthDate, String email, String phone,
			String street, int number, String box, Locality locality, String country, boolean isActive) throws UserServiceException{
		
		updateNormalUser(user, firstName, lastName, birthDate, email, phone, street, number, box, locality, country);
		
		user.setIsActive(isActive);
	
		return user;
	}

	public void updateNormalUser(User user, String firstName, String lastName, Date birthDate, String email, String phone,
			String street, int number, String box, Locality locality, String country) throws UserServiceException {
		
		validateUser(firstName, lastName, birthDate, email, phone, street, number, box, locality, country);
		
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setBirthday(birthDate);
		user.setEmail(email);
		user.setPersonalPhone(phone);
		user.setStreet(street);
		user.setNumber(number);
		user.setBox(box);
		user.setLocality(locality);
		user.setCountry(country);
	}
	
	public void validateUser(String firstname, String lastname, Date birthDate, String email, String phone,
			String street, int number, String box, Locality locality, String country) throws UserServiceException
	{
		HashMap<String, String> errors = new HashMap<String, String>();
		if(firstname.length() < 2 || firstname.length() > 255)
			errors.put("firstnameError", "Wrong size for the firstname");
		if(lastname.length() < 2 || lastname.length() > 255)
			errors.put("lastError", "Wrong size for the lastname");
		if(birthDate == null)
			errors.put("dateError", "Date cannot be empty");
		if(email.length() < 5 || email.length() > 50)
			errors.put("mailError", "Wrong size for the email");
		if(phone.length() < 9 || phone.length() > 50)
			errors.put("phoneError", "Wrong size for the phone number");
		if(street.length() < 2 || street.length() > 50)
			errors.put("streetError", "Wrong size for the street");
		if(number < 1 || number > 1000)
			errors.put("numberError", "Wrong value for the number");
		if(box.length() < 1 || box.length() > 10000)
			errors.put("boxError", "Wrong value for the box");
		if(locality == null)
			errors.put("localityError", "The locality cannot be empty");
		if(country.length() < 3 || country.length() > 50)
			errors.put("countryError", "Wrong size for the country");
		
		if (errors.size() > 0)
			throw new UserServiceException(errors.toString());
	}
}