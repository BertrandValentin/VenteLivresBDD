package be.atc.services;

import java.util.List;

import javax.persistence.EntityManager;

import be.atc.entities.User;

public class UserService {
	protected EntityManager em;
	
	public UserService(EntityManager em){
		this.em = em;
	}
	
	public User createUser(User user){
		User userTarget = new User();
		
		return modifyUser(userTarget, user);
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
		javax.persistence.TypedQuery<User> query = (javax.persistence.TypedQuery<User>) em.createQuery("SELECT * FROM User;", User.class);
		return query.getResultList();
	}
}
