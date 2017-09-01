<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="./css/style.css"/>
	<title>Fiche</title>
</head>
<body>
	<form method="post">
	<fieldset>
		<legend>Fiche du livre</legend>
		<label for="id"></label><!-- non modifiable -->
		<input type="text" id="id" tabindex="10" readonly />
		<br />
		<label for="title"></label>
		<input type="text" id="title" tabindex="20" />
		<br />
		<label for="author"></label>
		<input type="text" id="author" tabindex="30" />
		<br />
		<label for="category"></label>
		<input type="text" id="category" tabindex="40" />
		<br />
		<label for="editor"></label>
		<input type="text" id="editor" tabindex="50" />
		<br />
		<label for="price"></label>
		<input type="text" id="price" tabindex="60" />
		<br />
		<label for="isActive"></label>
		<input type="text" id="isActive" tabindex="70" />
		<br />
		<input type="submit" value="Commander" />
		<input type="submit" value="Modifier" />
		<input type="submit" value="Supprimer" />
	</fieldset>
	</form>
</body>
</html>