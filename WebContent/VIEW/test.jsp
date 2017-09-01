<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>venteLivres</title>
</head>
<body>
		<h1>venteLivres</h1>
		<p>
			connexion :
		</p>
		<form action="ServletUser" method="post">
			<input type="text" name="userName"></input><!-- </value>  -->
			<input type="password" name="userPassword"></input>
			<input type="submit"/>
		</form>
		
		<p>
			ajouter roleNname dans la session
		</p>
		<form action="ServletRole" method="get">
			<input type="text" name="roleName"><!-- unknown tag value: </value>  -->
			<input type="text" name="roleName" value="${sessionScope['roleUpdate'].roleName}" />
			<!-- <input type="text" name="roleName">${sessionScope.roleUpdate.roleName}" </value> -->
			<input type="submit"/>
		</form>
		<a href="ServletAuthor">ServletAuthor</a><br />
		<a href="ServletBook">ServletBook</a><br />
		<a href="ServletOrder">order</a>
		<a href="book_description.jsp">fiche</a>
</body>
</html>