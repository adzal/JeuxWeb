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
<h2>Game Page</h2>
<c:if test="${not empty message}">
<p class="invalid">${message}</p>
</c:if>

  <form action="gameform" method="post">
  	<div class="row align-items-center">
		<div class="col-12 col-md-3">Titre</div>
		<div class="col-12 col-md-9">
		<input type="text" class="form-control" id="titre"
			placeholder="Saisi titre" name="titre"
			value="${jeux.titre}" required />
		</div>
	</div>
 	<div class="row align-items-center">
		<div class="col-12 col-md-3">Description</div>
		<div class="col-12 col-md-9">	
		<input type="text" class="form-control" id="description"
			placeholder="Saisi description" name="description" 
			value="${jeux.description}" required />
		</div>
	</div>
  	<div class="row align-items-center">
		<div class="col-12 col-md-3">Prix</div>
		<div class="col-12 col-md-9">	
		<input type="text" id="prix" name="prix" 
			placeholder="Saisi prix"  
			class="form-control validate"
			value="${jeux.prix}" required />
		</div>
	</div>
	
	<div class="row align-items-center">
		<div class="col-12 col-md-3">dateSortie</div>
		<div class="col-12 col-md-9">	
		<input type="text" id="dateSortie" name="dateSortie" 
			placeholder="Saisi dateSortie"  
			class="form-control validate"
			value="${jeux.dateSortie}" required />
		</div>		
	</div>
  	<div class="row align-items-center">
		<div class="col-12 col-md-3">paysOrigine</div>
		<div class="col-12 col-md-9">	
		<input type="text" id="paysOrigine" name="paysOrigine" 
			placeholder="Saisi paysOrigine"  
			class="form-control validate"
			value="${jeux.paysOrigine}" required />
		</div>		
	</div>
  
  <div class="row align-items-center">
		<div class="col-12 col-md-3">connexion</div>
		<div class="col-12 col-md-9">	
		<input type="text" id="connexion" name="connexion" 
			placeholder="Saisi connexion"  
			class="form-control validate"
			value="${jeux.connexion}" required />
		</div>		
	</div>
  	<div class="row align-items-center">
		<div class="col-12 col-md-3">mode</div>
		<div class="col-12 col-md-9">	
		<input type="text" id="mode" name="mode" 
			placeholder="Saisi mode"  
			class="form-control validate"
			value="${jeux.jeuxMode}" required />
		</div>		
	</div>

	<div class="row align-items-center">
		<div class="col-12 col-md-3">Genre</div>
		<div class="col-12 col-md-9">	
			<select name="genres" id="genres">
			<c:forEach var="genre" items="${genres}">
				<option value="${genre.genreId}"
				<c:if test="${genre.genreId == jeux.genreId}">
					selected
				</c:if>
				>${genre.description}</option>			
			</c:forEach>
			</select> 
		</div>		
	</div>	

  
  	<div class="row align-items-center">
		<div class="col-12 col-md-9 offset-md-3">		
			<button type="sumbit" class="btn btn-primary mt-3">Update Game</button>
		</div>
	</div>
	
	
  </form>
</div>


</body>
</html>