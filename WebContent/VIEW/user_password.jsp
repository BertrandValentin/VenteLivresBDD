<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link type="text/css" rel="stylesheet" href="<c:url value="/VIEW/css/style.css"/>" />
	<title>Changement du mot de passe</title>
</head>
<body>
	<form action="password" method="post">
		<fieldset>
			<legend>Modifier le mot de passe</legend>
			<label for="oldPassword">Ancien mot de passe</label>
			<input type="password" name="oldPassword" tabindex="10" value="${userToEdit.getPassword()}" />
			<br />
			<label for="newPassword">Nouveau mot de passe</label>
			<input type="password" name="newPassword" tabindex="20" value="${userToEdit.getPassword()}" />
			<br />
			<label for="confirmNewPassword">Nouveau mot de passe</label>
			<input type="password" name="confirmNewPassword" tabindex="30" value="${userToEdit.getPassword()}" />
			<br />
			<input type="submit" value="Valider" />
		</fieldset>
	</form>
	<br />
	<a href="/VenteLivresBDD/profile">Retour au profile</a>
	<br />
	<c:out value="${errors}" />
</body>
</html>