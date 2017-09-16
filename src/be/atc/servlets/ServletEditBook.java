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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title").isEmpty() ? "" : request.getParameter("title");
		int idAuthor = Utilities.getInstance().convertStringRequestParameterToInt(request.getParameter("author"));
		int idCategory = Utilities.getInstance().convertStringRequestParameterToInt(request.getParameter("category"));
		int idEditor = Utilities.getInstance().convertStringRequestParameterToInt(request.getParameter("editor"));
		double price = Utilities.getInstance().convertStringRequestParameterToInt(request.getParameter("price"));
		boolean isActive = request.getParameter("isActive") == null ? false : true;
		int idBookToUpdate = Utilities.getInstance().convertStringRequestParameterToInt(request.getParameter("idBookToUpdate"));
		
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
	
	private void prepareData(HttpServletRequest request) {
		EntityManager em = EMF.getEM();
		int idBookToUpdate = request.getParameter("idBook") == null  ? -1 : Integer.parseInt(request.getParameter("idBook"));
		
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
	
	private void createBook(String title, double price, boolean isActive, Author author, Category category,
			Editor editor, BookService bookService, Book book) throws BookServiceException {
		log.debug("* new *");
		bookService.updateBook(book, title, author, category, editor, price, isActive);
		bookService.createBook(book);
	}

	private void updateBook(String title, double price, boolean isActive, int idBookToUpdate, Author author,
			Category category, Editor editor, BookService bookService, Book book) throws BookServiceException {
		log.debug("* update * : " + idBookToUpdate);
		book.setIdBook(idBookToUpdate);
		book = bookService.findBook(book);
		bookService.updateBook(book, title, author, category, editor, price, isActive);
	}
}