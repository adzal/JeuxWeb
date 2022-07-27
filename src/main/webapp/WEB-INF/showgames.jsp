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
	<h1>All games</h1>
	<div class="row">			
		<label for="genres">Choose a Genre:</label>
				
		<form action="ListJeux" method="get">
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
			<input type="submit" value="Submit">
		</form>
	</div>

	<div class="row">
		<table>
			<thead>
				<tr>
					<th>Title</th>
					<th>Description</th>
					<th>Details</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="game" items="${games}">
					<tr>
						<td>${game.jeuxId}</td>
						<td>${game.description}</td>
						<td><a href="GamePage?jeuxId=${game.jeuxId}">${game.description}</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</body>
</html>