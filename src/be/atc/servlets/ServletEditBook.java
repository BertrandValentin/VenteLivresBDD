package be.atc.servlets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import be.atc.connection.EMF;
import be.atc.entities.Author;
import be.atc.entities.Book;
import be.atc.entities.Category;
import be.atc.entities.Editor;
import be.atc.entities.User;
import be.atc.services.AuthorService;
import be.atc.services.BookService;
import be.atc.services.BookServiceException;
import be.atc.services.CategoryService;
import be.atc.services.EditorService;

public class ServletEditBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ServletUser. class);

	/**
	 * verify if the connected user is an administrator, if not, sends back to the book list page.
	 * send the user to an edition of book page.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		if(!user.getRole().getRoleName().equals("admin")){
			response.sendRedirect("/VenteLivresBDD/book");
			return ;
		}

		prepareData(request);
		
		this.getServletContext().getRequestDispatcher("/VIEW/editBook.jsp").forward(request, response);
	}

	/**
	 * recovers the values of the inputs in the jsp and update the values in the database
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title").isEmpty() ? "" : request.getParameter("title");
		int idAuthor = Utilities.getInstance().convertStringRequestParameterToInt(request.getParameter("author"));
		int idCategory = Utilities.getInstance().convertStringRequestParameterToInt(request.getParameter("category"));
		int idEditor = Utilities.getInstance().convertStringRequestParameterToInt(request.getParameter("editor"));
		double price = Utilities.getInstance().convertStringRequestParameterToDouble(request.getParameter("price"));
		boolean isActive = Utilities.getInstance().convertStringRequestParameterToBoolean(request.getParameter("isActive"));
		int idBookToUpdate = Utilities.getInstance().convertStringRequestParameterToInt(request.getParameter("idBook"));
		
		log.debug(title + " | " + idAuthor + " | " + idCategory + " | " + idEditor + " | " + price + " | " + isActive);
		
		EntityManager em = EMF.getEM();
		
		try{
			em.getTransaction().begin();
			
			Author author = new AuthorService(em).findAuthorById(idAuthor);
			Category category = new CategoryService(em).findCategoryById(idCategory);
			Editor editor = new EditorService(em).findEditorById(idEditor);
			
			BookService bookService = new BookService(em);
			Book book = new Book();
			
			if (idBookToUpdate != -1) {
				updateBook(title, price, isActive, idBookToUpdate, author, category, editor, bookService, book);
			}
			else {
				createBook(title, price, isActive, author, category, editor, bookService, book);
			}
			
			em.getTransaction().commit();
			response.sendRedirect("/VenteLivresBDD/book");
		}
		catch (BookServiceException e) {
			request.setAttribute("errors", e.getMessage());
			prepareData(request);
			this.getServletContext().getRequestDispatcher("/VIEW/editBook.jsp").forward(request, response);
		}
		finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
				log.debug("transaction rolled back");
			}
			em.close();
		}
	}

	/* ************************************* */
	
	/**
	 * create a service to get the author, category and the editor from their respective table.
	 * recover the other data from the book table to feed the inputs in the jsp.
	 * if the id is egal to -1, it means that there was an error while recovering the data. 
	 * @param request
	 */
	private void prepareData(HttpServletRequest request) {
		EntityManager em = EMF.getEM();
		int idBookToUpdate = Utilities.getInstance().convertStringRequestParameterToInt(request.getParameter("idBook"));
		
		AuthorService authorService = new AuthorService(em);
		CategoryService categoryService = new CategoryService(em);
		EditorService editorService = new EditorService(em);
		
		request.setAttribute("allAuthors", authorService.findAllAuthors());
		request.setAttribute("allCategories", categoryService.findAllCategories());
		request.setAttribute("allEditors", editorService.findAllEditors());
		
		if (idBookToUpdate != -1){
			BookService bookService = new BookService(em);
			Book bookToEdit = bookService.findBookById(idBookToUpdate);
			
			request.setAttribute("bookToEdit", bookToEdit);
		}
		
		em.close();
	}
	
	/**
	 * this function is used to create a new book with a jsp with empty inputs.
	 * @param title
	 * @param price
	 * @param isActive
	 * @param author
	 * @param category
	 * @param editor
	 * @param bookService
	 * @param book
	 * @throws BookServiceException
	 */
	private void createBook(String title, double price, boolean isActive, Author author, Category category,
			Editor editor, BookService bookService, Book book) throws BookServiceException {
		log.debug("* new *");
		bookService.updateBook(book, title, author, category, editor, price, isActive);
		bookService.createBook(book);
	}

	/**
	 * this function is used to update the data from a book with the values of the inputs in the jsp.
	 * @param title
	 * @param price
	 * @param isActive
	 * @param idBookToUpdate
	 * @param author
	 * @param category
	 * @param editor
	 * @param bookService
	 * @param book
	 * @throws BookServiceException
	 */
	private void updateBook(String title, double price, boolean isActive, int idBookToUpdate, Author author,
			Category category, Editor editor, BookService bookService, Book book) throws BookServiceException {
		log.debug("* update * : " + idBookToUpdate);
		book.setIdBook(idBookToUpdate);
		book = bookService.findBook(book);
		bookService.updateBook(book, title, author, category, editor, price, isActive);
	}
}