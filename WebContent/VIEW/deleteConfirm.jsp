<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link type="text/css" rel="stylesheet" href="<c:url value="/VIEW/css/style.css"/>" />
	<title>Fiche</title>
</head>
<body>
	<fieldset>
		<legend>Confirmer la suppression</legend>
		<ul>
			<li>Auteur : <strong> <c:out value="${book.getAuthor().getLastName()} ${book.getAuthor().getFirstName()}" /></strong></li>
			<li>Titre : <strong> <c:out value="${book.getTitle()}" /></strong></li>
			<li>Editeur : <strong> <c:out value="${book.getEditor().getEditorName()}" /></strong></li>
			<li>Cat&eacute;gorie : <strong> <c:out value="${book.getCategory().getCategoryName()}" /></strong></li>
			<li>Prix : <strong> <c:out value="${book.getPrice()}" /></strong></li>
		</ul>
		
		<form action="/VenteLivresBDD/deleteBook" method="post">
			<input type="hidden" name="idBook" value="${book.getIdBook()}">
			<input type="submit" value="Supprimer" />
		</form>
		
		<a href="/VenteLivresBDD/book" >
			<input type="submit" value="Annuler" />
		</a>
	</fieldset>
</body>
</html>