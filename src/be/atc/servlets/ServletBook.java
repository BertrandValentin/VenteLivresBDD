package be.atc.servlets;

import java.io.IOException;
//import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import be.atc.connection.EMF;
import be.atc.services.BookService;

/**
 * Servlet implementation class ServletBook
 */

public class ServletBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ServletBook. class);
 
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("trying to display all books");
		
		EntityManager em = EMF.getEM();
		
		try{
			em.getTransaction().begin();
			BookService serv = new BookService(em);
			em.getTransaction().commit();
			
			HttpSession session = request.getSession();
			if(session.getAttribute("role") != null){
				request.setAttribute("allBooks", serv.findAllBooks());
				this.getServletContext().getRequestDispatcher("/VIEW/books.jsp").forward(request, response);
			}
			else
				response.sendRedirect("/VenteLivresBDD/login");
		}
		finally {
			if (em.getTransaction().isActive()){
				em.getTransaction().rollback();
				log.debug("transaction rolled back");
			}
			em.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
}