package be.atc.services;

import java.util.List;

import javax.persistence.EntityManager;

import be.atc.entities.Book;

public class BookService {
	protected EntityManager em;

	BookService(EntityManager em){
		this.em = em;
	}
	
	public Book createBook(Book book){
		Book bookTarget = new Book();
		
		return modifyBook(bookTarget, book);
	}
	
	public void removeAuthor(Book book){
		if (em.find(Book.class, book.getIdBook()) != null)
			em.remove(book);
	}
	
	public Book modifyBook(Book bookTarget, Book bookNew){
		bookTarget = em.find(Book.class, bookNew.getIdBook());
		
		return bookTarget;
	}
	
	public Book findBook(Book book){
		return em.find(Book.class, book.getIdBook());
	}
	
	public List<Book> findAllBooks(){
		javax.persistence.TypedQuery<Book> query = (javax.persistence.TypedQuery<Book>) em.createQuery("SELECT b FROM Book b", Book.class);
		return query.getResultList();
	}
}
