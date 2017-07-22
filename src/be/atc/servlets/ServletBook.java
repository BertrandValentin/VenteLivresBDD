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
import be.atc.entities.Book;
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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("trying to display Nietzsche");
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		/*EntityManagerFactory emf = Persistence.createEntityManagerFactory("BookService");
		EntityManager em = emf.createEntityManager();*/
		EntityManager em = EMF.getEM();
		try{
			log.debug("bloque pour lancer un rollback si erreur - debut");
			em.getTransaction().begin();
			BookService serv = new BookService(em);
			log.debug("bloque pour lancer un rollback si erreur - fin");
			em.getTransaction().commit();
			Book books = new Book();
			
			books.setIdBook(0);
			books = serv.findBook(books);
			
			out.println("<html><body>");
			out.println("<h1>" + books.getAuthor() + " " + books.getTitle() + "</h1>");
			out.println("</body></html>");
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}