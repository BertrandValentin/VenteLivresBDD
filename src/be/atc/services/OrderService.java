package be.atc.services;

import java.util.List;

import javax.persistence.EntityManager;

import be.atc.entities.Order;

public class OrderService {
	protected EntityManager em;

	OrderService(EntityManager em){
		this.em = em;
	}
	
	public Order createOrder(Order order){
		Order orderTarget = new Order();
		
		return modifyOrder(orderTarget, order);
	}
	
	public void removeOrder(Order order){
		if (em.find(Order.class, order.getIdOrder()) != null)
			em.remove(order);
	}
	
	public Order modifyOrder(Order orderTarget, Order orderNew){
		orderTarget = em.find(Order.class, orderNew.getIdOrder());
		
		return orderTarget;
	}
	
	public Order findOrder(Order order){
		return em.find(Order.class, order.getIdOrder());
	}
	
	public List<Order> findAllLocalities(){
		javax.persistence.TypedQuery<Order> query = (javax.persistence.TypedQuery<Order>) em.createQuery("SELECT o FROM Order o", Order.class);
		return query.getResultList();
	}
}