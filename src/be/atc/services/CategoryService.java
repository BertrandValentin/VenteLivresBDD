package be.atc.services;

import java.util.List;

import javax.persistence.EntityManager;

import be.atc.entities.Category;

public class CategoryService {
	protected EntityManager em;

	public CategoryService(EntityManager em){
		this.em = em;
	}
	
	public Category createCategory(Category category){
		em.persist(category);
		
		return category;
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
	
	public Category findCategoryById(int id){
		Category categoryTmp = new Category();
		categoryTmp.setIdCategory(id);
		return findCategory(categoryTmp);
	}
	
	public List<Category> findAllCategories(){
		javax.persistence.TypedQuery<Category> query = (javax.persistence.TypedQuery<Category>) em.createQuery("SELECT c FROM Category c", Category.class);
		return query.getResultList();
	}
}