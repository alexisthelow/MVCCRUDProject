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
	
		<form action="showDetail.do" method="get">
			<select name="id">
				<option value="0">--Select a Pokemon--</option>
				<c:forEach var="pokemon" items="${activeList}">
					<c:if test="${pokemon.id != 0}">
						<option value="${pokemon.id}">${pokemon.id}: ${pokemon.name}</option>
					</c:if>
				</c:forEach>
			</select>
		
			<input type="submit" value="View Details">
		</form>
		
	</body>
</html>