package be.atc.services;

import java.util.List;

import javax.persistence.EntityManager;

import be.atc.entities.Category;

public class CategoryService {
	protected EntityManager em;

	CategoryService(EntityManager em){
		this.em = em;
	}
	
	public Category createCategory(Category category){
		Category categoryTarget = new Category();
		
		return modifyCategory(categoryTarget, category);
	}
	
	public void removeCategory(Category category){
		if (em.find(Category.class, category.getIdCategory()) != null)
			em.remove(category);
	}
	
	public Category modifyCategory(Category categoryTarget, Category categoryNew){
		categoryTarget = em.find(Category.class, categoryNew.getIdCategory());
		
		return categoryTarget;
	}
	
	public Category findCategory(Category category){
		return em.find(Category.class, category.getIdCategory());
	}
	
	public List<Category> findAllCategories(){
		javax.persistence.TypedQuery<Category> query = (javax.persistence.TypedQuery<Category>) em.createQuery("SELECT * FROM Category;", Category.class);
		return query.getResultList();
	}
}