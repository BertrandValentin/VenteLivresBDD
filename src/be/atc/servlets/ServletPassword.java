package be.atc.servlets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import be.atc.connection.EMF;
import be.atc.entities.User;
import be.atc.services.UserService;

@WebServlet("/password")
public class ServletPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ServletBook. class);

	public ServletPassword() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		if(user == null){
			response.sendRedirect("/VenteLivresBDD/login");
			return ;
		}
		this.getServletContext().getRequestDispatcher("/VIEW/user_password.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oldPassword = request.getParameter("oldPassword").isEmpty() ? "" : request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword").isEmpty() ? "" : request.getParameter("newPassword");
		String confirmNewPassword = request.getParameter("confirmNewPassword").isEmpty() ? "" : request.getParameter("confirmNewPassword");
		
		try{
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			
			oldPasswordVerified(user, oldPassword);
			verifyPasswordSize(newPassword);
			newPasswordsVerified(newPassword, confirmNewPassword);
			User userUpdated = updatePassword(user, newPassword);

			session.setAttribute("user", userUpdated);
			request.setAttribute("errors", "Password successfully changed");
		}
		catch(Exception e){
			request.setAttribute("errors", e.getMessage());
		}
		finally {
			doGet(request, response);
		}
	}
	
	/* ************************ */
	
	private void oldPasswordVerified(User user, String oldPassword) throws Exception{
		if (!user.getPassword().equals(oldPassword)){
			throw new Exception("Wrong current password");
		}
	}

	private void newPasswordsVerified(String newPassword, String confirmNewPassword) throws Exception{
		if (!newPassword.equals(confirmNewPassword)){
			throw new Exception("The two new passwords are different");
		}
	}
	
	private void verifyPasswordSize(String password) throws Exception{
		if (password.length() < 4 || password.length() > 16) {
			throw new Exception("The password must be between 4 and 16 characters");
		}
	}
	
	private User updatePassword(User user, String newPassword) throws Exception{
		EntityManager em = EMF.getEM();
		
		try{
			UserService userService = new UserService(em);
			User userInDB = userService.findUser(user);
			
			em.getTransaction().begin();
			userInDB.setPassword(newPassword);
			em.getTransaction().commit();
			
			return userInDB;
		}
		catch(Exception e){
			throw new Exception(e);
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