<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="password" method="post">
		<fieldset>
			<legend>Modifier le mot de passe</legend>
			<label for="passwordOld">Ancien mot de passe</label>
			<input type="text" name="passwordOld" tabindex="10" value="${userToEdit.getPassword()}" />
			<br />
			<label for="passwordNew">Nouveau mot de passe</label>
			<input type="text" name="passwordNew" tabindex="20" value="${userToEdit.getPassword()}" />
			<br />
			<label for="passwordNew">Nouveau mot de passe</label>
			<input type="text" name="passwordNew" tabindex="30" value="${userToEdit.getPassword()}" />
			<br />
			<input type="submit" value="Valider" />
		</fieldset>
	</form>
	<br />
	<a href="/VenteLivresBDD/profile">Annuler</a>
</body>
</html>