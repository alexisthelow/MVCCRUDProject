<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Pokedex</title>
	</head>
	<body>
		<c:if test="${not empty activeList}">
			${activeList[1].name}
		</c:if>
		<c:if test="${not empty activePokemon}">
			${activePokemon.name}
		</c:if>
		<c:if test="${not empty userTeam}">
			<c:forEach var="pokemon" items="${userTeam}">
				${pokemon.name}
			</c:forEach>
		</c:if>
	</body>
</html>