<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${activePokemon.name} Details</title>
	</head>
	<body>
		<!-- put image here --><br>
		<ul>
			<li>Name: ${activePokemon.name}</li>
			<li>Pokedex ID: ${activePokemon.id }</li>
			<li>Type: ${activePokemon.type1} <c:if test="${activePokemon.type2 != 0}">/ ${activePokemon.type2}</c:if></li>
			<li>${activePokemon.description}</li>
		</ul>
		<c:if test="${activePokemon.id - 1 != 0}">
			<form action="showDetail.do?id=${activePokemon.id - 1}" method="get">
				<input type="submit" value="${activePokemon.id - 1}: ${activeList[activePokemon.id - 1].name}">
			</form>
		</c:if>
		<c:if test="${activePokemon.id + 1 not gt fn:length(activeList)}">
			<form action="showDetail.do?id=${activePokemon.id + 1}" method="get">
				<input type="submit" value="${activePokemon.id + 1}: ${activeList[activePokemon.id + 1].name}">
			</form>
		</c:if>
		
		
	</body>
</html>