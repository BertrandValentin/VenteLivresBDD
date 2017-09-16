package be.atc.servlets;

import java.io.IOException;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import be.atc.connection.EMF;
import be.atc.entities.Locality;
import be.atc.entities.User;
import be.atc.services.LocalityService;
import be.atc.services.UserService;
import be.atc.services.UserServiceException;

@WebServlet("/profile")
public class ServletProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ServletBook. class);

	public ServletProfile() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		prepareData(request);
		this.getServletContext().getRequestDispatcher("/VIEW/user_profile.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User userSession = (User)session.getAttribute("user");
		
		String firstname = request.getParameter("firstname").isEmpty() ? "" : request.getParameter("firstname");
		String lastname = request.getParameter("lastname").isEmpty() ? "" : request.getParameter("lastname");
		Date birthDate = request.getParameter("birthDate").isEmpty() ? null : Utilities.getInstance().stringToDate(request.getParameter("birthDate"));
		log.debug(birthDate);
		String email = request.getParameter("email").isEmpty() ? "" : request.getParameter("email");
		String phone = request.getParameter("phone").isEmpty() ? "" : request.getParameter("phone");
		String street = request.getParameter("street").isEmpty() ? "" : request.getParameter("street");
		int number = request.getParameter("number").isEmpty() ? null : Utilities.getInstance().convertStringRequestParameterToInt(request.getParameter("number"));
		String box = request.getParameter("box").isEmpty() ? "" : request.getParameter("box");
		int idLocality = request.getParameter("locality").isEmpty() ? null : Utilities.getInstance().convertStringRequestParameterToInt(request.getParameter("locality"));
		String country = request.getParameter("country").isEmpty() ? "" : request.getParameter("country");
		boolean isActive = request.getParameter("isActive").isEmpty() ? false : request.getParameter("isActive") != null;
		
		log.debug(firstname + " - " + lastname + " - " + birthDate + " - " + email + " - " + phone +
				 " - " + street + " - " + number + " - " + box + " - " + idLocality + " - " + country + " - " + isActive);
		
		EntityManager em = EMF.getEM();
		
		Locality locality = new LocalityService(em).findLocalityById(idLocality);
		log.debug(locality.getIdLocality() + " & idLocality=" + idLocality);
		
		try{
			em.getTransaction().begin();
			UserService userService = new UserService(em);
			
			if (userSession != null) {
				updateUser(userSession, userService, firstname, lastname, birthDate, email, phone, street, number, box, locality, country, isActive);
				log.debug("date=" + userSession.getBirthday());
				em.getTransaction().commit();
			}
			response.sendRedirect("/VenteLivresBDD/book");
		}
		catch (UserServiceException e) {
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
		LocalityService localityService = new LocalityService(em);
		request.setAttribute("allLocalities", localityService.findAllLocalities());
		em.close();
	}
	/*
	private void createUser(User user, UserService userService, String firstname, String lastname, Date birthDate, String email, String phone,
			String street, int number, String box, Locality locality, String country, boolean isActive) throws UserServiceException {
		userService.updateUser(user, firstname, lastname, birthDate, email, phone, street, number, box, locality, country, isActive);
		userService.createUser(user);
	}
	 */
	private void updateUser(User user, UserService userService, String firstname, String lastname, Date birthDate, String email, String phone,
			String street, int number, String box, Locality locality, String country, boolean isActive) throws UserServiceException {
		log.debug("user.id=" + user.getIdUser() + " user.lastname=" + user.getLastName());
		userService.updateUser(user, firstname, lastname, birthDate, email, phone, street, number, box, locality, country, isActive);
	}
}