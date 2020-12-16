<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" style="text/css"
	href="${pageContext.request.contextPath}/ficheInscription.css">
<title>Modifier compte</title>



</head>
<body>
	<section class="entrees">
	<div class="container-fluid">
			
		<p>${success}</p>
		<c:if test="${error != null}">
			<div class="col-lg-6 col-sm-12">
				<div class="alert alert-danger" role="alert">
					<p>${errorLogin}</p>
				</div>
			</div>
		</c:if>
		<div class="col-lg-6 col-sm-12">
		<h2>ENI-ENCHERES</h2>
		<p><a href="http://localhost:8080/Beilvert-Gaid-Garibay-projetEnchere/Connexion">
					<img src="${pageContext.request.contextPath}/images/encheres.jpg"
					alt="logo" height="130px" width="150px" style="float:left;margin-left:20px"/>
					</p>
			<h2 class="my-4 col-lg-12 col-sm-12"><center>Mon Profil</center></h2>
	
				
          
	            <form action="modifierProfil" method="post">
	   
	                <fieldset class= "modifProfil">
	               
	                <label for="pseudo">Pseudo :</label>
	                <input type="text" id="pseudo" name="pseudo" value="${utilisateur.pseudo}">
					<br>
	
	                <label for="nom">Nom :</label>
	                <input type="text" id="nom" name="nom" value="${utilisateur.nom}">
					<br>
	
					<label for="prenom">Prénom :</label>
	             	<input type="text" id="prenom" name="prenom" value="${utilisateur.prenom}">
					<br>
					
					<label for="mail">Email :</label>
	                <input type="email" id="mail" name="mail" value="${utilisateur.email}">
					<br>
					
					<label for="tel">Téléphone :</label>
	                <input type="tel" id="tel" name="tel" value="${utilisateur.telephone}">
					<br>
					
					<label for="rue">Rue :</label>
	                <input type="text" id="rue" name="rue" value="${utilisateur.rue}">
					<br>
					
					<label for="cpo">Code Postal :</label>
	                <input type="text"id="cpo" name="cpo" value="${utilisateur.codePostal}">
					<br>
					
					<label for="ville">Ville :</label>
	                <input type="text" id="ville" name="ville" value="${utilisateur.ville}">
					<br>
					
					
					<label for="password">Mot de passe :</label>
	                <input type="password" id="password" name="password" value="${utilisateur.motDePasse}">
					<br>
					
					<label for="checkPassword">Confirmation :</label>
	                <input type="password" id="checkPassword" name="checkPassword" value="${utilisateur.motDePasse}">
					<br>
					
					<label for="credit">Credit :</label>
	                <p>${utilisateur.credit}</p>
					<br>
					
					<div class="col-lg-6 col-sm-12">
						<p>${success}</p>
						<button id="save"class="button-record" type="submit" name="save">Enregistrer</button>
					</div>
				  </form>
				</div>	
			  
	         </fieldset class>
	            
	            <div class="col3-lg-6 col3-sm-12">
	            	<form action="suppression" method="post">
	            		<button id="delete" name="delete">
	            			Supprimer mon compte
	            		</button>
	            	</form>
	            </div>
	            	
	            <div class="col3-lg-6 col3-sm-12">
						
					<a id="previous" href="profil" class="btn btn-primary">Retour</a>
	       		</div>
	            	
		
	</div>	
	
</body>
</html>