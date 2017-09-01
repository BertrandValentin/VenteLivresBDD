<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link type="text/css" rel="stylesheet" href="<c:url value="css/style.css"/>" />
	<title>Livres</title>
</head>
<body>
	<h1>all books</h1>
	
	<table>
		<tr>
			<th>Identifiant</th>
			<th>Titre</th>
			<th>Auteur</th>
			<th>prix</th>
			<th>Cat&eacute;gorie</th>
			<th>Editeur</th>
			<th>Actif</th>
			<th>Commander</th>
			<c:forEach items="${allBooks}" var="book">
				<tr>
            		<td><c:out value="${book.getIdBook()}" /></td>
            		<td><c:out value="${book.getTitle()}" /></td>
            		<td><c:out value="${book.getAuthor().getLastName()}" />
						<c:out value="${book.getAuthor().getFirstName()}" /></td>
            		<td><c:out value="${book.getPrice()}" /></td>
            		<td><c:out value="${book.getCategory().getCategoryName()}" /></td>
            		<td><c:out value="${book.getEditor().getEditorName()}" /></td>
            		<td><c:out value="${book.getIsActive() ? 'disponible' : 'non disponible'}" /></td>
            		<td><input type="submit" value="Commander" /></td>
        		</tr>
			</c:forEach>
	</table>
</body>
</html>