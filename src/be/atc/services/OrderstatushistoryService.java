package be.atc.services;

import java.util.List;

import javax.persistence.EntityManager;

import be.atc.entities.Orderstatushistory;;

public class OrderstatushistoryService {
	protected EntityManager em;

	OrderstatushistoryService(EntityManager em){
		this.em = em;
	}
	
	public Orderstatushistory createOrderStatusHistory(Orderstatushistory orderStatusHistory){
		Orderstatushistory orderStatusHistoryTarget = new Orderstatushistory();
		
		return modifyOrderStatusHistory(orderStatusHistoryTarget, orderStatusHistory);
	}
	
	public void removeOrderStatus(Orderstatushistory orderStatusHistory){
		if (em.find(Orderstatushistory.class, orderStatusHistory.getIdOrderStatusHistory()) != null)
			em.remove(orderStatusHistory);
	}
	
	public Orderstatushistory modifyOrderStatusHistory(Orderstatushistory orderStatusHistoryTarget, Orderstatushistory orderStatusHistoryNew){
		orderStatusHistoryTarget = em.find(Orderstatushistory.class, orderStatusHistoryNew.getIdOrderStatusHistory());
		
		return orderStatusHistoryTarget;
	}
	
	public Orderstatushistory findOrderline(Orderstatushistory orderStatusHistory){
		return em.find(Orderstatushistory.class, orderStatusHistory.getIdOrderStatusHistory());
	}
	
	public List<Orderstatushistory> findAllLocalities(){
		javax.persistence.TypedQuery<Orderstatushistory> query = (javax.persistence.TypedQuery<Orderstatushistory>) em.createQuery("SELECT osh FROM Orderstatushistory osh", Orderstatushistory.class);
		return query.getResultList();
	}
}
