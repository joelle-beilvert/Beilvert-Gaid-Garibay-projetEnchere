<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<p><a href="http://localhost:8080/Beilvert-Gaid-Garibay-projetEnchere/Connexion">
					<img src="${pageContext.request.contextPath}/images/encheres.jpg"
					alt="logo" height="130px" width="150px" style="float:left;margin-left:20px"/>
					</p>
<link rel="stylesheet" style="text/css"
	href="${pageContext.request.contextPath}/ficheInscription.css">
<!--  "../../ficheInscription.css"  />-->
<title>Afficher le détail d'un enchère</title>

</head>
<body>
<fieldset class="vendre">
	<div class="container-fluid">
			
		<p>${success}</p>
		<c:if test="${error != null}">
					<div class="col-lg-6 col-sm-12">
						<div class="alert alert-danger" role="alert">
							<p>${errorLogin}</p>
						</div>
					</div>
					
		</c:if></p>
		<div class="col-lg-6 col-sm-12">
			
			<h3 class="my-4 col-lg-12 col-sm-12"><p>${sessionScope.enchere.article.nomArticle}</h3></p>
			
				<p>${error}</p>
	
	                <p>Description : ${sessionScope.enchere.article.description}</p>
					<br>
	
					<p>Meilleure offre : ${sessionScope.montantEnchere}</p>
					<br>
					
					<p>Mise à prix : ${sessionScope.article.prixInitial}</p>
					<br>
					
					<p>Date de fin de l'enchère : ${enchere.article.dateFinEncheres}</p>
					<br>
					


					<c:choose>
						<c:when test="${enchere.article.retrait.rue != null}">
							<p>${enchere.article.retrait.rue}</p>
							<p>${enchere.article.retrait.codePostal} ${enchere.article.retrait.ville}</p>
						</c:when>
						<c:otherwise>
							<p>Retrait : ${enchere.article.vendeur.rue}</p>
							<p>${enchere.article.vendeur.codePostal} ${enchere.article.vendeur.ville}</p>
						</c:otherwise>
					</c:choose><br>

					<p>Vendeur de l'article : ${sessionScope.article.vendeur}</p> 
					<br>
				</fieldset>
				<div class="col-lg-6 col-sm-12">
					<a id="cancel" href="accueil" class="btn btn-primary">Annuler la vente</a>
				</div>
				
				<div class="col-lg-6 col-sm-12">
					<a href="${pageContext.request.contextPath}/modificationVente.jsp">Modifier ma vente></a>
				</div>
				
				<div class="col-lg-6 col-sm-12">
					<a id="back" href="accueil" class="btn btn-primary">Retour</a>
				</div>
		</div>
		
		
	</div>	
	
</body>
</html>