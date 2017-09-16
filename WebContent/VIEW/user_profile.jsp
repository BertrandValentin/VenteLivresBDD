<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="profile" method="post">
		<fieldset>
			<legend>Modifier le profile</legend>
			<label for="firstname">Nom</label>
			<input type="text" name="firstname" tabindex="10" value="${sessionScope.user.getFirstName()}" />
			<br />
			<label for="lastname">Pr&eacute;nom</label>
			<input type="text" name="lastname" tabindex="20" value="${sessionScope.user.getLastName()}" />
			<br />
			<label for="birthDate">Date de naissance</label>
			<fmt:formatDate value="${sessionScope.user.getBirthday()}" var="formattedBirthdayDate" type="date" pattern="dd-mm-yyyy" />
			<input type="text" name="birthDate" tabindex="30" value="${formattedBirthdayDate}" />
			<br />
			<label for="email">Email</label>
			<input type="text" name="email" tabindex="40" value="${sessionScope.user.getEmail()}" />
			<br />
			<label for="phone">Num&eacute;ro de t&eacute;l&eacute;phone</label>
			<input type="text" name="phone" tabindex="50" value="${sessionScope.user.getPersonalPhone()}" />
			<br />
			<label for="street">Rue</label>
			<input type="text" name="street" tabindex="60" value="${sessionScope.user.getStreet()}" />
			<br />
			<label for="number">Num&eacute;ro</label>
			<input type="text" name="number" tabindex="70" value="${sessionScope.user.getNumber()}" />
			<br />
			<label for="box">Boite</label>
			<input type="text" name="box" tabindex="80" value="${sessionScope.user.getBox()}" />
			<br />
			<label for="city">Ville</label>
			
			<select name="locality" tabindex="90" >
				<c:forEach items="${allLocalities}" var="locality">
					<option 
						<c:if test="${locality.getIdLocality() == sessionScope.user.getLocality().getIdLocality()}">selected</c:if>
						value="${locality.getIdLocality()}"><c:out value="${locality.getZipCode()} ${locality.getCity()}"
					/></option>
				</c:forEach>
			</select>

			<br />
			<label for="country">Pays</label>
			<input type="text" name="country" tabindex="100" value="${sessionScope.user.getCountry()}" />
			<br />
			<label for="isActive">Actif</label>
			<input type="text" name="isActive" tabindex="110" value="${sessionScope.user.getIsActive()}" />
			<br />
			<input type="submit" value="Valider" />
		</fieldset>
	</form>
	<br />
	<a href="/VenteLivresBDD/password">Modifier le mot de passe</a>
	<br />
	<a href="/VenteLivresBDD/book">Annuler</a>
</body>
</html>