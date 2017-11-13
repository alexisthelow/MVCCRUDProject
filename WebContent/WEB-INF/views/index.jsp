<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
		<title>Pokedex</title>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col">
					<h3>Display Pokemon by Pokedex ID</h3>
					<form action="showDetail.do" method="get">
						<select name="id">
							<option value="0">--Select a Pokemon--</option>
							<c:forEach var="pokemon" items="${activeList}">
								<c:if test="${pokemon.id != 0}">
									<option value="${pokemon.id}">${pokemon.id}: ${pokemon.name}</option>
								</c:if>
							</c:forEach>
						</select> <input type="submit" value="View Details">
					</form>
				</div>
				<div class="col">
					<h3>Filter Pokemon by Type</h3>
					<form action="showDetail.do" method="get">
						<select name="typeFilter1">
							<option value="none">--Type 1--</option>
							<c:forEach var="type" items="${types}">
								<option value="${type}">${type}</option>
							</c:forEach>
							<option value="none">None</option>
						</select> <select name="typeFilter2">
							<option value="none">--Type 2--</option>
							<c:forEach var="type" items="${types}">
								<option value="${type}">${type}</option>
							</c:forEach>
							<option value="none">None</option>
						</select> <input type="submit" value="Filter by Type">
					</form>
				</div>
				<div class="col">
					<h3>Add A Pokemon to the Pokedex</h3>
					<form:form action="processAdd.do" method="post" modelAttribute="modelPokemon">
						Name: <form:input path="name" /><form:errors path="name" /><br>
						Type 1: <form:input path="type1" /><br>
						Type 2: <form:input path="type2" /><br>
						Description: <form:input path="description" /><br>
						<input type="hidden" value="id" />
						<input type="submit" value="Add to Pokedex">
					</form:form>
				</div>
			</div>
		</div>
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
	</body>
</html>