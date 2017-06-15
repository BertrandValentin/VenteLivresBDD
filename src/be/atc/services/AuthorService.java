package be.atc.services;

import java.util.List;

import javax.persistence.EntityManager;

import be.atc.entities.Author;

public class AuthorService {
	protected EntityManager em;

	AuthorService(EntityManager em){
		this.em = em;
	}
	
	public Author createAuthor(Author author){
		Author authorTarget = new Author();
		
		return modifyAuthor(authorTarget, author);
	}
	
	public void removeAuthor(Author author){
		if (em.find(Author.class, author.getIdAuthor()) != null)
			em.remove(author);
	}
	
	public Author modifyAuthor(Author authorTarget, Author authorNew){
		authorTarget = em.find(Author.class, authorNew.getIdAuthor());
		
		return authorTarget;
	}
	
	public Author findAuthor(Author author){
		return em.find(Author.class, author.getIdAuthor());
	}
	
	public List<Author> findAllAuthors(){
		javax.persistence.TypedQuery<Author> query = (javax.persistence.TypedQuery<Author>) em.createQuery("SELECT a FROM Author a", Author.class);
		return query.getResultList();
	}
}
