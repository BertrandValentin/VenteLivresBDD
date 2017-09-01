package be.atc.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import be.atc.connection.EMF;
import be.atc.services.BookService;

/**
 * Servlet implementation class ServletBook
 */
@WebServlet("/ServletBook")
public class ServletBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ServletBook. class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletBook() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("trying to display all books");
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		EntityManager em = EMF.getEM();
		
		try{
			em.getTransaction().begin();
			BookService serv = new BookService(em);
			em.getTransaction().commit();
			
			/*
			//arrayBooks = "<table><tr><th>Identifiant</th><th>Titre</th><th>Auteur</th><th>prix</th><th>Cat&eacute;gorie</th><th>Editeur</th><th>Actif</th></tr>";
			List<Book> booksList = serv.findAllBooks();
			log.debug("starting to fill the array - " + arrayBooks);
			for (Book b : booksList)
				arrayBooks += b.toStringTable();
			//arrayBooks += "</table>";
			log.debug("array filled - " + arrayBooks);
			*/
			
			request.setAttribute("allBooks", serv.findAllBooks());
			this.getServletContext().getRequestDispatcher("/VIEW/books.jsp").forward(request, response);
			
			out.flush();
			out.close();
		}
		finally {
			if (em.getTransaction().isActive()){
				em.getTransaction().rollback();
				log.debug("transaction rolled back");
			}
			em.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}