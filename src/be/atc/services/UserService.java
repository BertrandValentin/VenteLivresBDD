package be.atc.services;

import java.util.List;

import javax.persistence.EntityManager;

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
}