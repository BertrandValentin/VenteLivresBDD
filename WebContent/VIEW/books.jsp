<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link type="text/css" rel="stylesheet" href="<c:url value="/VIEW/css/style.css"/>" />
	<title>Livres</title>
</head>
<body>
	<h1>all books</h1><br />
	
	<a href="/VenteLivresBDD/ServletDisconnection">D&eacute;connexion</a>
	<a href="/VenteLivresBDD/profile">Modifier le profile <c:out value="${user.getLastName()}" /></a>
	<c:if test="${sessionScope.user.getRole().getRoleName() == 'admin'}">
		<a href="/VenteLivresBDD/editBook">Ajouter un livre</a><br />
	</c:if>
	
	<table>
		<caption>Pannier</caption>
		<tr>
			<th>Titre</th>
			<th>Prix</th>
		</tr>
			<c:forEach items="${sessionScope.basket}" var="book">
			<tr>
				<td><c:out value="${book.getTitle()}" /></td>
				<td><c:out value="${book.getPrice()}" /></td>
			</tr>
			</c:forEach>
			<tr>
				<td>Total</td>
				<td><strong><c:out value="${sessionScope.basketTotal}" /></strong></td>
			</tr>
	</table>
		<a href="/VenteLivresBDD/trashBasket" >
			<input type="button" value="Vider le panier" />
		</a>
	<br />
	<table>
		<caption>Liste des livres</caption>
		<tr>
			<th>Identifiant</th>
			<th>Titre</th>
			<th>Auteur</th>
			<th>prix</th>
			<th>Cat&eacute;gorie</th>
			<th>Editeur</th>
		</tr>
		<c:forEach items="${allBooks}" var="book">
			<tr>
           		<td><c:out value="${book.getIdBook()}" /></td>
           		<td><c:out value="${book.getTitle()}" /></td>
           		<td><c:out value="${book.getAuthor().getLastName()}" />
					<c:out value="${book.getAuthor().getFirstName()}" /></td>
           		<td><c:out value="${book.getPrice()}" /></td>
           		<td><c:out value="${book.getCategory().getCategoryName()}" /></td>
           		<td><c:out value="${book.getEditor().getEditorName()}" /></td>
				<td>
					<c:choose> 
					<c:when test="${book.getIsActive()}">
           				<a href="/VenteLivresBDD/basket?idBook=<c:out value='${book.getIdBook()}' />">
							<input type="button" value="Ajouter au pannier" />
						</a>
					</c:when>
					<c:otherwise>Indisponible</c:otherwise>
					</c:choose>
				</td>
           		<c:if test="${sessionScope.user.getRole().getRoleName() == 'admin'}">
					<td>
           				<a href="/VenteLivresBDD/editBook?idBook=<c:out value='${book.getIdBook()}' />" >
							<input type="button" value="Modifier" />
						</a>
						<a href="/VenteLivresBDD/deleteBook?idBook=<c:out value='${book.getIdBook()}' />" >
							<input type="button" value="Supprimer" />
						</a>
					</td>
				</c:if>
       		</tr>
		</c:forEach>
	</table>
</body>
</html>