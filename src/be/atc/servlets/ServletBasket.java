package be.atc.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import be.atc.connection.EMF;
import be.atc.entities.Book;
import be.atc.services.BookService;

public class ServletBasket extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ServletUser. class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idBookToAddInTheBasket = Utilities.getInstance().convertStringRequestParameterToInt(request.getParameter("idBook"));
		EntityManager em = EMF.getEM();
		BookService bookservice = new BookService(em);
		
		Book book = bookservice.findBookById(idBookToAddInTheBasket);
		HttpSession session = request.getSession();
		addBookToBasket(book, session);
		
		log.debug(session.getAttribute("basket").toString());
		response.sendRedirect("/VenteLivresBDD/book");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("post");
	}
	
	/* ******************** */
	
	private ArrayList<Book> getBasket(HttpSession session){
		@SuppressWarnings("unchecked")
		ArrayList<Book> basket = (ArrayList<Book>) session.getAttribute("basket");
		if(basket == null)
			basket = new ArrayList<Book>();
		return basket;
	}
	
	private void addBookToBasket(Book book, HttpSession session){
		ArrayList<Book> basket = getBasket(session);
		basket.add(book);
		computeTotalBasket(basket, session);
		session.setAttribute("basket", basket);
	}
	
	private void computeTotalBasket(ArrayList<Book> basket, HttpSession session){
		double total = 0;
		
		for(Book book : basket)
			total += book.getPrice();
		
		session.setAttribute("basketTotal", total);
	}
}