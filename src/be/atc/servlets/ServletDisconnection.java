package be.atc.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ServletDisconnection")
public class ServletDisconnection extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * closes the session
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		//this.getServletContext().getRequestDispatcher("/VIEW/login.jsp").forward(request, response);
		//response.sendRedirect("/ServletLogin");
		this.getServletContext().getRequestDispatcher("/login").forward(request, response);
	}
}