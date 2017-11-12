<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Add a Pokemon to the Pokedex</title>
	</head>
	<body>
		<form:form action="processAdd.do" method="post" modelAttribute="modelPokemon">
			Name: <form:input path="name"/><form:errors path="name"/><br>
			Type 1: <form:input path="type1" />
			Type 2: <form:input path="type2" />
			Description: <form:input path="description" />
			<input type="hidden" value="id" />

			<input type="submit" value="Add to Pokedex">
		</form:form>
	</body>
</html>