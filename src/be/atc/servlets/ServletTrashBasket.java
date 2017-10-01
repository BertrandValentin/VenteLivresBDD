package be.atc.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletTrashBasket extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * empties the basket by setting in the session the basket to null, the total price to 0.0
	 * redirect to the book list page
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		session.setAttribute("basket", null);
		session.setAttribute("basketTotal", 0.0);
		response.sendRedirect("/VenteLivresBDD/book");
	}
}
