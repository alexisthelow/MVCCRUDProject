<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
		
		
		
		
		
		
	</body>
</html>