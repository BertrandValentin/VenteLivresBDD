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
	<form action="editBook" method="post">
	<fieldset>
		<legend>Fiche du livre</legend>
		<label for="title">Titre</label>
		<input type="text" name="title" tabindex="20" value="${bookToEdit.getTitle()}" />
		<br />
		<label for="author">Auteur</label>
		<select name="author" tabindex="30" >
			<c:forEach items="${allAuthors}" var="author">
				<option 
					<c:if test="${author.getIdAuthor() == bookToEdit.getAuthor().getIdAuthor()}">selected</c:if>
					value="${author.getIdAuthor()}"><c:out value="${author.getLastName()} ${author.getFirstName()}"
				/></option>
			</c:forEach>
		</select>
		<br />
		<label for="category">Cat&eacute;gorie</label>
		<select name="category" tabindex="40" >
			<c:forEach items="${allCategories}" var="category">
				<option 
					<c:if test="${category.getIdCategory() == bookToEdit.getCategory().getIdCategory()}">selected</c:if>
					value="${category.getIdCategory()}"><c:out value="${category.getCategoryName()}"
				/></option>
			</c:forEach>
		</select>
		<br />
		<label for="editor">Editeur</label>
		<select name="editor" tabindex="50" >
			<c:forEach items="${allEditors}" var="editor">
				<option
					<c:if test="${editor.getIdEditor() == bookToEdit.getEditor().getIdEditor()}">selected</c:if>
					value="${editor.getIdEditor()}"><c:out value="${editor.getEditorName()}" />
				</option>
			</c:forEach>
		</select>
		<br />
		<label for="price">Prix</label>
		<input type="number" step="0.01" name="price" tabindex="60" value="${bookToEdit.getPrice()}" />
		<br />
		<label for="isActive">Disponible</label>
			<input tabindex="70" type="checkbox" name="isActive" value="True" <c:if test="${bookToEdit.getIsActive()}">checked</c:if> />
		<br />
		<input type="hidden" name="idBookToUpdate" value="${bookToEdit.getIdBook()}">
		<input type="submit" value="${bookToEdit.getIdBook() != null ? 'Modifier' : 'Cr&eacute;er'}" />
	</fieldset>
	</form>
	
	<c:out value="${errors}" />
</body>
</html>