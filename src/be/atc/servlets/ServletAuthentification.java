package be.atc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.atc.javabeans.Client;

public class ServletAuthentification extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//variables de classe
		String ident=null;
		String mdp=null;
		String emailadministrateur=null;
		public void init()
		{
			//récupérer les paramètres d’initialisation
			//de la Servlet dans le fichier web.xml
			ServletConfig config=getServletConfig();
			ident=(String)config.getInitParameter("defautIdentifiant");
			mdp=(String)config.getInitParameter("defautMotDePasse");
			//récupérer l’email de l’administrateur
			ServletContext servletContext=getServletContext();
			emailadministrateur=servletContext.getInitParameter
					("emailAdministrateur");
		}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//récupération de l’identifiant/login dans la requête
		String identifiant=request.getParameter("identifiant");
		//récupération du mot de passe dans la requête
		String motdepasse=request.getParameter("motdepasse");
		//flux de sortie
		PrintWriter out=response.getWriter();
		//pas d’identifiant
		if(identifiant==null)
		{
			out.println("Authentification incorrecte !");
		}
		//pas de mot de passe
		if(motdepasse==null)
		{
			out.println("Authentification incorrecte !");
		}
		//vérifier l’égalité des valeurs
		if ((identifiant!=null && identifiant.equals(ident))
				&& (motdepasse!=null && motdepasse.equals(mdp)) )
		{
			out.println("Authentification correcte,  bienvenue : " + identifiant);
		}
		else
		{
			out.println("Authentification incorrecte, mauvaise saisie des informations !");
			out.println("Veuillez contacter : " + emailadministrateur);
		}
		//+++
		if( (identifiant!=null && identifiant.equals(ident))
				&& (motdepasse!=null && motdepasse.equals(mdp)) )
		{
			out.println("Authentification correcte,  bienvenue : "+identifiant);
			//créer l’objet JavaBean Client
			Client client1=new Client();
			client1.setIdentifiant(identifiant);
			client1.setMotdepasse(motdepasse);
			//sauvegarder l’objet client dans le contexte de l’application
			ServletContext servletContext=getServletContext();
			servletContext.setAttribute("client1", client1);
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}