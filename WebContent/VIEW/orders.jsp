<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link type="text/css" rel="stylesheet" href="<c:url value="/VIEW/css/style.css"/>" />
	<title>Insert title here</title>
</head>
<body>
	<fieldset>
		<legend>Confirmer la commande</legend>
		<ul>
			<li>Auteur : <strong> <c:out value="${sessionScope.book.getAuthor().getLastName()} ${sessionScope.book.getAuthor().getFirstName()}" /></strong></li>
			<li>Titre : <strong> <c:out value="${sessionScope.book.getTitle()}" /></strong></li>
			<li>quantit&eacute; : <strong>${sessionScope.quantity}</strong></li>
			<li>Prix total: <strong><c:out value="${sessionScope.basketTotal}" /></strong></li>
		</ul>
		
		<form action="/VenteLivresBDD/order" method="post">
			<input type="hidden" name="idUser" value="${user.getIdUser()}">
			<input type="submit" value="Payer" />
		</form>
		
		<a href="/VenteLivresBDD/book" >
			<input type="submit" value="Annuler" />
		</a>
	</fieldset>
</body>
</html>