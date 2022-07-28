<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">


<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="container bg-light border">	
	<h1>Show games</h1>
	<div class="row">			
				
		<form action="ListJeux" method="get">
			<label for="genres">Choose a Genre:</label>
			<select name="genres" id="genres">
				<option value="all">All Genre</option>
				<c:forEach var="genre" items="${genres}">
					<option value="${genre.genreId}"
					<c:if test="${not empty selected and genre.genreId == selected}">
						selected
					</c:if>
					>${genre.description}</option>			
				</c:forEach>
				<option value="new">Add New Genre</option>
			</select> 
			
			<label for="plateformes">Choose a plateforme:</label>
			<select name="plateformes" id="plateformes">
				<option value="all">All Plateformes</option>
				<c:forEach var="plateforme" items="${plateformes}">
				<option value="${plateforme.plateformeId}"
					<c:if test="${not empty selectedPlateforme and plateforme.plateformeId == selectedPlateforme}">
					selected
					</c:if>
					>${plateforme.nom}</option>			
				</c:forEach>
				<option value="new">Add New Plateforme</option>
			</select> 			
			
			<input type="submit" value="Submit">
		</form>
	</div>

	<div class="row">
		<table>
			<thead>
				<tr>
					<th>Title</th>
					<th>Description</th>
					<th>Genre</th>
					<th>Plateforme</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="game" items="${games}">
					<tr>
						<td><a href="GamePage?jeuxId=${game.jeuxId}">${game.titre}</a></td>
						<td>${game.description}</td>
						<td>${game.genreDescription}</td>
						<td>${game.plateformeNom}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</body>
</html>