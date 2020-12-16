<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" style="text/css"
	href="${pageContext.request.contextPath}/ficheInscription.css">
<title>Afficher compte</title>

</head>
	<h2><center>Mon profil</center></h2>
<body>
	<fieldset class="formCadre">
	<div>
		<p>${success}</p>
		<c:if test="${error != null}">
			<div class="col-lg-6 col-sm-12">
				<div class="alert alert-danger" role="alert">
					<p>${errorLogin}</p>
				</div>
			</div>
		</c:if>
		<div class="col-lg-6 col-sm-12">
	
			<h3 class="my-4 col-lg-12 col-sm-12">${utilisateur.pseudo}</h3>
	
	 
          
	            <form action="#" method="post">
	   			
	                
	                <label for="pseudo">Pseudo :</label>
	                <p>${utilisateur.pseudo}<p>
					<br>
					
					<p><a href="http://localhost:8080/Beilvert-Gaid-Garibay-projetEnchere/Connexion">
					<img src="${pageContext.request.contextPath}/images/encheres.jpg"
					alt="logo" height="130px" width="150px" style="float:right" align="top"/>
					</p>
	                <label for="name">Nom :</label>
	                <p>${utilisateur.nom}</p>
					<br>
	
					<label for="firstname">Prénom :</label>
	             	<p>${utilisateur.prenom}</p>
					<br>
					
					<label for="mail">Email :</label>
	                <p>${utilisateur.email}</p>
					<br>
					
					<label for="tel">Téléphone :</label>
	                <p>${utilisateur.telephone}</p>
					<br>
					
					<label for="address">Rue :</label>
	                <p>${utilisateur.rue}</p>
					<br>
					
					<label for="cpo">Code Postal :</label>
	                <p>${utilisateur.codePostal}</p>
					<br>
					
					<label for="city">Ville :</label>
	                <p>${utilisateur.ville}</p>
					<br>
					 
					</form>
					</fieldset class>
				</div>	
				<div class="col-lg-6 col-sm-10">
						
						<a id="modifProfil" href="modifierProfil" class="btn btn-primary">Modifier</a>
	        		</div>
	        	
		
	</div>	
	
</body>
</html>