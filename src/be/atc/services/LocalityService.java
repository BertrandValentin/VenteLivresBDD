package be.atc.services;

import java.util.List;

import javax.persistence.EntityManager;

import be.atc.entities.Locality;

public class LocalityService {
	protected EntityManager em;

	LocalityService(EntityManager em){
		this.em = em;
	}
	
	public Locality createLocality(Locality locality){
		Locality localityTarget = new Locality();
		
		return modifyLocality(localityTarget, locality);
	}
	
	public void removeLocality(Locality locality){
		if (em.find(Locality.class, locality.getIdLocality()) != null)
			em.remove(locality);
	}
	
	public Locality modifyLocality(Locality localityTarget, Locality localityNew){
		localityTarget = em.find(Locality.class, localityNew.getIdLocality());
		
		return localityTarget;
	}
	
	public Locality findLocality(Locality locality){
		return em.find(Locality.class, locality.getIdLocality());
	}
	
	public List<Locality> findAllLocalities(){
		javax.persistence.TypedQuery<Locality> query = (javax.persistence.TypedQuery<Locality>) em.createQuery("SELECT * FROM Locality;", Locality.class);
		return query.getResultList();
	}
}