package be.atc.servlets;


import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import be.atc.connection.EMF;
import be.atc.entities.Book;
import be.atc.entities.User;
import be.atc.services.BookService;

import java.io.IOException;

public class ServletDeleteBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ServletUser. class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		if(! user.getRole().getRoleName().equals("admin")){
			response.sendRedirect("/VenteLivresBDD/book");
			return ;
		}

		log.debug("servlet delete book");
		EntityManager em = EMF.getEM();
		int idBookToDelete = Utilities.getInstance().convertStringRequestParameterToInt(request.getParameter("idBook"));
		BookService bookService = new BookService(em);
		Book book = bookService.findBookById(idBookToDelete);
		
		request.setAttribute("book", book);
		this.getServletContext().getRequestDispatcher("/VIEW/deleteConfirm.jsp").forward(request, response);
		em.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(!session.getAttribute("role").toString().equals("admin")){
			response.sendRedirect("/VenteLivresBDD/book");
			return ;
		}
		
		int idBookToDelete = Utilities.getInstance().convertStringRequestParameterToInt(request.getParameter("idBook"));
		EntityManager em = EMF.getEM();
		BookService bookService = new BookService(em);
		Book book = bookService.findBookById(idBookToDelete);
		
		if(book != null) {
			em.getTransaction().begin();
			bookService.remove(book);
			em.getTransaction().commit();
			em.close();
		}
		response.sendRedirect("/VenteLivresBDD/book");
	}
}