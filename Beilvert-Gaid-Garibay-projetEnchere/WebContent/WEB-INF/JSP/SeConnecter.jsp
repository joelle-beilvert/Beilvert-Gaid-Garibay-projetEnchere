<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" style="text/css"
href="${pageContext.request.contextPath}/ficheInscription.css">


<title>Se connecter</title>
</head>
<body>
 <section class="login-container">
 	<div>
 	<h1><strong><center>Bienvenue à Eni-Enchères</center></strong></h1><br>
 	<img class="image" src="${pageContext.request.contextPath}/images/encheres.jpg"
			 alt="logo" height="90px" width="90px"/>
 			<h2><strong><center> Se connecter</center></strong></h2>
 	<header>
 	</header>
 		
 		<form action="#" method="post">
 		
 			<input type="text" name="username" placeholder="Login" required="required">
 			<input type="password" name="password" placeholder="Mot de passe" required="required">
 		<p><button><a  type="submit" class="button-connexion">Connexion</a>
 			</button></p>
 		</form>
 	</div>
 </section>
 		
 	
 
</body>
</html>