<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" style="text/css"
	href="${pageContext.request.contextPath}/ficheInscription.css">
<!--  "../../ficheInscription.css"  />-->
<title>Inscription au site ENI-Encheres</title>
<head />

<body>
	

	<form method="post" action="formulaireInscription">

		<header>
			<strong>Eni-Encheres</strong>
		</header>
		<br> <img
			src="${pageContext.request.contextPath}/images/encheres.jpg"
			alt="logo" height="130px" width="150px" />

		<h2>
			<center>Bienvenue sur le site ENI-Encheres</center>
		</h2>

		<h3>
			<center>
				Les champs obligatoires sont suivis par un
				<title="required"><em>*</em>
			</center>
		</h3>
		
		
		<fieldset class="formCadre">
		<h2>
			<center>Formulaire d'Inscription</center>
		</h2>

		<br>
		<!-- tableau d'inscription -->

		<center>Veuillez entrer les champs ci-dessous :</center>
		<br>
		<p>
		<label for="pseudo">Pseudo: <title="required"><em>*</em></label><br>
		 <input style="text-align: center" class="label" type="text"
				id="pseudo" name="pseudo" placeholder="Votre pseudo" autofocus=""
				requiered=""> <br> <br>
				
			<label for="nom">Nom: <title="required"><em>*</em></label><br> 
			<input style="text-align: center" class="label" type="text"
				id="nom" name="nom" placeholder="votre nom" autofocus=""
				requiered=""> <br> <br>
		</p>
		<p>
			<label for="prenom">Prénom:</label><br> 
			<input style="text-align: center" type="text" id="prenom" name="prenom"
				placeholder="votre prénom"> <br> <br>
		</p>
		<p>
			<label for="telephone">Telephone: </label><br> 
			<input  type="tel" id="telephone" placeholder="06xxxxxxxx" pattern="06[0-9]{8}"> 
		</p>
		<br><br>
		<p>
			<label for="email">Email:
				<title="required"><em>*</em></label><br>
				<input type="email" id="email" name="usermail">
			 <br><br>
		</p>
		<p>
			<label for="password" motDePasse>Mot de passe:
				<title="required"><em>*</em></label><br> 
				<input type="password" id="pwd" name="password">
			 <br><br>
			<U><strong>Adresse:</strong></strong></U><br><br>
			<label>Rue: </label><br><input id="rue" name="rue" type="text"/><br><br>
			<label>Code postale: </label><br><input id="code-postale" name="code-postale" type="text" /><br><br>
			<label>Ville: </label><br><input id="ville" type="text"/><br><br>
 			
			
		</p>

		<p>
			<button type="submit">Créer</button>
			<button type="reset">Annuler</button>
			
		</p>
		</fieldset class>


	</form>


</body>
</html>