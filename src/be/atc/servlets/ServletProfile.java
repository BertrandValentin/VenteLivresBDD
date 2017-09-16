package be.atc.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User userSession = (User)session.getAttribute("user");
		
		String firstname = request.getParameter("firstname").isEmpty() ? "" : request.getParameter("firstname");
		String lastname = request.getParameter("lastname").isEmpty() ? "" : request.getParameter("lastname");
		Date birthDate = Utilities.getInstance().stringToDate(request.getParameter("birthDate"));
		
		log.debug(birthDate);
		String email = request.getParameter("email").isEmpty() ? "" : request.getParameter("email");
		String phone = request.getParameter("phone").isEmpty() ? "" : request.getParameter("phone");
		String street = request.getParameter("street").isEmpty() ? "" : request.getParameter("street");
		int number = Utilities.getInstance().convertStringRequestParameterToInt(request.getParameter("number"));
		String box = request.getParameter("box").isEmpty() ? "" : request.getParameter("box");
		int idLocality = Utilities.getInstance().convertStringRequestParameterToInt(request.getParameter("locality"));
		String country = request.getParameter("country").isEmpty() ? "" : request.getParameter("country");
		boolean isActive = Utilities.getInstance().convertStringRequestParameterToBoolean(request.getParameter("isActive"));
		log.debug(firstname + " - " + lastname + " - " + birthDate + " - " + email + " - " + phone +
				 " - " + street + " - " + number + " - " + box + " - " + idLocality + " - " + country + " - " + isActive);
		EntityManager em = EMF.getEM();
		
		Locality locality = new LocalityService(em).findLocalityById(idLocality);
		log.debug(locality.getIdLocality() + " & idLocality=" + idLocality);
		
		try{
			em.getTransaction().begin();
			UserService userService = new UserService(em);
			
			if (userSession != null) {
				
				User userUpdated = updateUser(userSession, userService, firstname, lastname, birthDate, email, phone, street, number, box, locality, country);
				updateIsActiveUser(userUpdated, userSession, isActive);
				em.getTransaction().commit();
				session.setAttribute("user", userUpdated);
			}
		}
		catch (UserServiceException e) {
			request.setAttribute("errors", e.getMessage());
		}
		finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
				log.debug("transaction rolled back");
			}
			em.close();
			prepareData(request);
			this.getServletContext().getRequestDispatcher("/VIEW/user_profile.jsp").forward(request, response);
		}
	}
	
	/* ************************************* */
	
	private void prepareData(HttpServletRequest request) {
		EntityManager em = EMF.getEM();
		LocalityService localityService = new LocalityService(em);
		request.setAttribute("allLocalities", localityService.findAllLocalities());
		
		HttpSession session = request.getSession();
		User userSession = (User)session.getAttribute("user");
		
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		request.setAttribute("birthdateFormatted", df.format(userSession.getBirthday()));
		
		em.close();
	}

	private User updateUser(User user, UserService userService, String firstname, String lastname, Date birthDate, String email,
			String phone, String street, int number, String box, Locality locality, String country) throws UserServiceException {
		User userToUpdate = userService.findUser(user);
		userService.updateNormalUser(userToUpdate, firstname, lastname, birthDate, email, phone, street, number, box, locality, country);
	
		return userToUpdate;
	}
	
	private void updateIsActiveUser(User user, User userWhoAskedUpdate, boolean isActiveNewStatus){
		if(userWhoAskedUpdate.getRole().getRoleName().equals("admin")){
			user.setIsActive(isActiveNewStatus);
		}
	}
}