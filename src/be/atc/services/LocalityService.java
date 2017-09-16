package be.atc.services;

import java.util.List;

import javax.persistence.EntityManager;

import be.atc.entities.Locality;

public class LocalityService {
	protected EntityManager em;

	public LocalityService(EntityManager em){
		this.em = em;
	}
	
	public Locality createLocality(Locality locality){
		em.persist(locality);
		
		return locality;
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
	
	public Locality findLocalityById(int id) {
		Locality localityToFind = new Locality();
		localityToFind.setIdLocality(id);
		return findLocality(localityToFind);
	}
	
	public List<Locality> findAllLocalities(){
		javax.persistence.TypedQuery<Locality> query = (javax.persistence.TypedQuery<Locality>) em.createQuery("SELECT l FROM Locality l", Locality.class);
		return query.getResultList();
	}
}