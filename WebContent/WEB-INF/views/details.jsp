<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="h-100">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
		<title>${activePokemon.name} Details</title>
	</head>
	<body class="h-100">
		<div class="container h-100">
			<div class="row w-75 mx-auto">
				<!-- put image here --><br>
				<ul class="list-group">
					<li class="list-group-item text-center text-capitalize">${activePokemon.name}</li>
					<li class="list-group-item text-center">ID: ${activePokemon.id }</li>
					<li class="list-group-item text-center text-capitalize">${activePokemon.type1} <c:if test="${activePokemon.type2 != 'none'}">/ ${activePokemon.type2}</c:if></li>
					<li class="list-group-item text-center">${activePokemon.description}</li>
				</ul>
			</div>
			<div class="row w-75 mx-auto clearfix">
				<div class="col float-left">
					<c:if test="${previousPokemon.id != 0}">
						<form class="float-left" action="prev.do" method="get">
							<input class="btn text-capitalize" type="submit" value="Previous: #${previousPokemon.id} ${previousPokemon.name}">
						</form>
					</c:if>
				</div>
				<div class="col float-left">
					<c:if test="${nextPokemon.id != 0}">
						<form class="float-right" action="next.do" method="get">
							<input class="btn text-capitalize" type="submit" value="Next: #${nextPokemon.id} ${nextPokemon.name}">
						</form>
					</c:if>
				</div>
			</div>
			<div class="row w-75 mx-auto">
				<c:if test="${activePokemon.id != 0}">
						<ul class="list-group w-100">
							<form:form action="updatePokemon.do" method="post" modelAttribute="activePokemon">
								<form:hidden path="id" />
								<li class="list-group-item text-center">Update Pokemon:</li>
								<li class="list-group-item text-center text-capitalize">Name: <form:input class="text-capitalize" path="name" /></li>
								<li class="list-group-item text-center text-capitalize">Type 1: <form:input class="text-capitalize" path="type1" /></li>
								<li class="list-group-item text-center text-capitalize">Type 2: <form:input class="text-capitalize" path="type2" /></li>
								<li class="list-group-item text-center">Description: <form:textarea path="description" /></li>
								<li class="list-group-item text-center text-capitalize">
									<input class="btn text-capitalize" type="submit" value="Update ${activePokemon.name}">
									<c:if test="${activePokemon.id != 0}">
										<a href="delete.do" type="button" class="btn text-capitalize">Delete ${activePokemon.name}</a>
									</c:if>
								</li>
							</form:form>
						</ul>
				</c:if>
			</div>
			<div class="row w-75 mx-auto">
				
			</div>
			<div class="row w-75 mx-auto justify-content-center">
				<a type="button" role="button" class="btn btn-link" href="home.do">Return to Index</a>
			</div>
		</div>
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
	</body>
</html>