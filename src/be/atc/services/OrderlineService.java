package be.atc.services;

import java.util.List;

import javax.persistence.EntityManager;

import be.atc.entities.Orderline;

public class OrderlineService {
	protected EntityManager em;

	OrderlineService(EntityManager em){
		this.em = em;
	}
	
	public Orderline createOrderLine(Orderline orderLine){
		Orderline orderLineTarget = new Orderline();
		
		return modifyOrderLine(orderLineTarget, orderLine);
	}
	
	public void removeOrderLine(Orderline orderLine){
		if (em.find(Orderline.class, orderLine.getIdOrderLine()) != null)
			em.remove(orderLine);
	}
	
	public Orderline modifyOrderLine(Orderline orderLineTarget, Orderline orderLineNew){
		orderLineTarget = em.find(Orderline.class, orderLineNew.getIdOrderLine());
		
		return orderLineTarget;
	}
	
	public Orderline findOrderline(Orderline orderLine){
		return em.find(Orderline.class, orderLine.getIdOrderLine());
	}
	
	public List<Orderline> findAllLocalities(){
		javax.persistence.TypedQuery<Orderline> query = (javax.persistence.TypedQuery<Orderline>) em.createQuery("SELECT ol FROM Orderline ol", Orderline.class);
		return query.getResultList();
	}
}