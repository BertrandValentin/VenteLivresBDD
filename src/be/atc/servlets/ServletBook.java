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
import be.atc.entities.User;
import be.atc.services.BookService;

public class ServletBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ServletBook. class);

	/**
	 * recover all the books from the database and update the jsp.
	 * if the user is not connected, put the user back to the login page. 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		log.debug("trying to display all books for " + user);
		
		EntityManager em = EMF.getEM();
		
		try{
			BookService serv = new BookService(em);
			
			if (user != null) {
				request.setAttribute("allBooks", serv.findAllBooks());
				this.getServletContext().getRequestDispatcher("/VIEW/books.jsp").forward(request, response);
			}
			else {
				response.sendRedirect("/VenteLivresBDD/login");
			}
		}
		finally {
			em.close();
		}
	}
}