package be.atc.services;

import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import be.atc.entities.Author;
import be.atc.entities.Book;
import be.atc.entities.Category;
import be.atc.entities.Editor;
import be.atc.servlets.ServletUser;

public class BookService {
	private static final Logger log = Logger.getLogger(ServletUser. class);
	protected EntityManager em;

	public BookService(EntityManager em){
		this.em = em;
	}
	
	public Book createBook(Book book){
		em.persist(book);
		
		return book;
	}
	
	public void remove(Book book){
		log.debug("remove this book : " + book.getIdBook());
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
	
	public Book findBookById(int id){
		Book bookToFind = new Book();
		bookToFind.setIdBook(id);
		return findBook(bookToFind);
	}
	
	public List<Book> findAllBooks(){
		javax.persistence.TypedQuery<Book> query = (javax.persistence.TypedQuery<Book>) em.createQuery("SELECT b FROM Book b", Book.class);
		return query.getResultList();
	}
	
	public Book updateBook(Book book, String title, Author author, Category category, Editor editor, double price, boolean isActive) throws BookServiceException{
		validateBook(title, author, category, editor, price);
		
		book.setTitle(title);
		book.setAuthor(author);
		book.setCategory(category);
		book.setEditor(editor);
		book.setPrice(price);
		book.setIsActive(isActive);
		
		log.debug(book.getIdBook() + " | " + book.getTitle() + " | " + book.getAuthor().getFirstName() + " | " + 
				book.getCategory().getCategoryName() + " | " + book.getEditor().getEditorName() + " | " +
				book.getPrice() + " | " + book.getIsActive());
		
		return book;
	}
	
	public void validateBook(String title, Author author, Category category, Editor editor, double price) throws BookServiceException {
		HashMap<String, String> errors = new HashMap<String, String>();
		
		if (title.length() < 1 || title.length() > 255)
			errors.put("titleError", "Wrong size of title");//throw new BookServiceException("Wrong size of string");
		if (author == null)
			errors.put("authorError", "Author cannot be null");//throw new BookServiceException("Author cannot be null");
		if (category == null)
			errors.put("categoryError", "Category cannot be null");//throw new BookServiceException("Category cannot be null");
		if (editor == null)
			errors.put("editorError", "Editor cannot be null");//throw new BookServiceException("Editor cannot be null");
		if (price < 0.01)
			errors.put("priceError", "Wrong value for the price");//throw new BookServiceException("Wrong value for the price");
		
		if (errors.size() > 0)
			throw new BookServiceException(errors.toString());
	}
}