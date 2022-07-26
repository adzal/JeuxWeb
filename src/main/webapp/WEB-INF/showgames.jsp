<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="style.css" type="text/css" media="screen" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">


<meta charset="UTF-8">
<title>Liste jeux</title>
</head>
<body>

<div class="container bg-dark text-white">	
	<h1 class="titre">Liste des jeux</h1>
	<div class="row">			
				
		<form action="ListJeux" method="get">
			<label for="genres">Choisir un genre : </label>
			<select name="genres" id="genres">
				<option value="all">Tout les genres</option>
				<c:forEach var="genre" items="${genres}">
					<option value="${genre.genreId}"
					<c:if test="${not empty selected and genre.genreId == selected}">
						selected
					</c:if>
					>${genre.description}</option>			
				</c:forEach>
				<option value="new">Ajouter un genre</option>
			</select> 
			
			<label for="plateformes">Choisir une plateforme : </label>
			<select name="plateformes" id="plateformes">
				<option value="all">Toutes les plateformes</option>
				<c:forEach var="plateforme" items="${plateformes}">
				<option value="${plateforme.plateformeId}"
					<c:if test="${not empty selectedPlateforme and plateforme.plateformeId == selectedPlateforme}">
					selected
					</c:if>
					>${plateforme.nom}</option>			
				</c:forEach>
				<option value="new">Ajouter une plateforme</option>
			</select> 			
		
			<input type="submit" value="Envoyer">
		</form>
					
	</div>
	<div class="row">
		<a href="GamePage"class="btn btn-primary mt-3">Create new game</a>
	</div>
	<div class="row">
		<table>
			<thead>
				<tr>
					<th>Titre</th>
					<th>Description</th>
					<th>Genre</th>
					<th>Plateforme</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="game" items="${games}">
					<tr>
						<td><a class="colorlist" href="GamePage?jeuxId=${game.jeuxId}">${game.titre}</a></td>
						<td class="colorlist">${game.description}</td>
						<td><a class="colorlist" href="NewGenre?genreId=${game.genreId}">${game.genreDescription}</a></td>
						<td><a class="colorlist" href="NewPlateformes?plateformeId=${game.plateformeId}">${game.plateformeNom}</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</body>
</html>