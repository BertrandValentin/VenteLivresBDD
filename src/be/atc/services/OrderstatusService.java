package be.atc.services;

import java.util.List;

import javax.persistence.EntityManager;

import be.atc.entities.Orderstatus;

public class OrderstatusService {
	protected EntityManager em;

	OrderstatusService(EntityManager em){
		this.em = em;
	}
	
	public Orderstatus createOrderstatus(Orderstatus orderStatus){
		Orderstatus orderStatusTarget = new Orderstatus();
		
		return modifyOrderStatus(orderStatusTarget, orderStatus);
	}
	
	public void removeOrderStatus(Orderstatus orderStatus){
		if (em.find(Orderstatus.class, orderStatus.getIdOrderStatus()) != null)
			em.remove(orderStatus);
	}
	
	public Orderstatus modifyOrderStatus(Orderstatus orderStatusTarget, Orderstatus orderStatusNew){
		orderStatusTarget = em.find(Orderstatus.class, orderStatusNew.getIdOrderStatus());
		
		return orderStatusTarget;
	}
	
	public Orderstatus findOrderStatus(Orderstatus orderStatus){
		return em.find(Orderstatus.class, orderStatus.getIdOrderStatus());
	}
	
	public List<Orderstatus> findAllLocalities(){
		javax.persistence.TypedQuery<Orderstatus> query = (javax.persistence.TypedQuery<Orderstatus>) em.createQuery("SELECT os FROM Orderstatus os", Orderstatus.class);
		return query.getResultList();
	}
}