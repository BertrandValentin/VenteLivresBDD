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
import be.atc.entities.Author;
import be.atc.services.AuthorService;

/**
 * Servlet implementation class ServletAuthor
 */
@WebServlet("/ServletAuthor")
public class ServletAuthor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ServletAuthor. class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAuthor() {
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
		/*EntityManagerFactory emf = Persistence.createEntityManagerFactory("AuthorService");
		EntityManager em = emf.createEntityManager();*/
		EntityManager em = EMF.getEM();
		try{
			//bloque pour lancer un rollback si erreur
			em.getTransaction().begin();
			AuthorService serv = new AuthorService(em);
			//fin de bloque
			em.getTransaction().commit();
			Author fn = new Author();
			
			fn.setIdAuthor(1);
			fn = serv.findAuthor(fn);
			
			out.println("<html><body>");
			out.println("<h1>" + fn.getLastName() + " " + fn.getFirstName() + "</h1>");
			out.println("</body></html>");
			out.flush();
			out.close();
		}
		finally {
			if (em.getTransaction().isActive()){
				em.getTransaction().rollback();
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