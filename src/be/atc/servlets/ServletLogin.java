package be.atc.servlets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import be.atc.connection.EMF;
import be.atc.entities.User;
import be.atc.services.UserService;
import be.atc.services.UserServiceException;


public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ServletUser. class);

	/**
	 * loads the login jsp
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("trying to connect");
		this.getServletContext().getRequestDispatcher("/VIEW/login.jsp").forward(request, response);
	}

	/**
	 * verify if the data entered in the login page are correct, and add a User object in the session 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		EntityManager em = EMF.getEM();
		
		try{
			em.getTransaction().begin();
			UserService userService = new UserService(em);
			
			try {
				User user = userService.getUser(email, password);
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				log.debug(user.getEmail() + " - " + user.getRole().getRoleName());
				response.sendRedirect("/VenteLivresBDD/book");
			} catch (UserServiceException e) {
				request.setAttribute("error", e.getMessage());
				this.getServletContext().getRequestDispatcher("/VIEW/login.jsp").forward(request, response);
			}
		}
		finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
				log.debug("transaction rolled back");
			}
			em.close();
		}
	}
}