<!DOCTYPE html>
<html>
<head>
 <meta charset="utf-8">
       <title>Encheres</title>
       <link rel="stylesheet" type="text/css" href="style.css" media="all" />
</head>
<body>
  
<h2>ENI-ENCHERES</h2>
<p><a href="http://localhost:8080/Beilvert-Gaid-Garibay-projetEnchere/Connexion">
					<img src="${pageContext.request.contextPath}/images/encheres.jpg"
					alt="logo" height="130px" width="150px" style="float:left;margin-left:20px"/>
					</p>
<center><h1>Liste des Encheres</h1></center>


<div class="col-lg-12 col-sm-12 d-flex justify-content-around">
				<a href="Vendre">Vendre un article</a> <a href="profil">Mon
					profil</a> <a href="logout">Déconnexion</a>

</div>
<div class="col-lg-6 col-sm-12">
				<p>${sessionScope.utilisateur.pseudo} est connecté(e)</p>
			</div>
					
					<p>Achats</p>
					<input type="radio" id="encheresOuvertes" name="filtre" value="encheresOuvertes"> <label for="encheresOuvertes">enchères ouvertes</label> 
					<input type="radio" id="mesEncheres" name="filtre" value="mesEncheres"> <label for="mesEncheres">mes enchères en cours</label> 
					<input type="radio" id="encheresRemportees" name="filtre" value="encheresRemportees"> <label for="encheresRemportees">mes enchères remportées</label>
					
					<p>Ventes</p> 
					<input type="radio" id="mesVentes" name="filtre" value="mesVentes"> <label for="mesVentes">mes ventes en cours</label> 
					<input type="radio" id="ventesNonDebutees" name="filtre" value="ventesNonDebutees"> <label for="ventesNonDebutees">ventes non débutées</label> 
					<input type="radio" id="ventesTerminees" name="filtre" value="ventesTerminees"> <label for="ventesTerminees">ventes terminées</label> 
			
			
		
			<br> 
			<input class="col-lg-2 col-sm-6" type="text" placeholder="Recherche" id="nomArticleContient" name="Recherche"> 
			
			<button class="col-lg-2 col-sm-12" id="research" name="research">Rechercher</button>
			<br>
		

<label for="site-search">Filtres :</label>
<input type="search" id="site-search" name="q"
       aria-label="Search through site content"
	   type="text" placeholder="nom de l'article contient...">
<button>Appliquer</button>


    <div>  
    <p>Categories</p>         
    <select name="my_html_select_box">

	<option>Informatique</option>
	<option selected="yes">Toutes</option>
	<option>Vetement</option>
	<option>Ameublement</option>
	<option>Sports et Loisirs</option>
</select>
</div>
<br>

<div style="width: 450px;margin-left:34% ; padding-top:5px; padding-bottom:20px;border: 3px solid #A0A0A0; text-align: left;background: #C0C0C0;border-radius: 10px;"> 
<p class="flotte">
<img src="${pageContext.request.contextPath}/images/ordi.jpg"
     width= 140px;
     height= 140px;/>
</p>

<p>
<h1>Ordinateur Gaming</h1>
<ul>
<li>Prix : 210 points</li>
<li>Fin de l'enchere : 10/12/2020</li>
<li>Vendeur : Joelle.B</li>
</ul>
<button>Ajouter au Panier</button>
<button>Faire une Proposition</button>
</p>
</div>  
<br>

<div style="width: 450px;margin-left:34% ; padding-top:5px; padding-bottom:20px;border: 3px solid #A0A0A0; text-align: left;background: #C0C0C0;border-radius: 10px;"> 
<p class="flotte">
<img src="${pageContext.request.contextPath}/images/velo.jpg"
     width= 140px;
     height= 140px;/>   
</p>
<p>
<h1>VTT</h1><ul>
<li>Prix : 185 points</li>
<li>Fin de l'enchere : 21/01/2021</li>
<li>Vendeur : Kevin.G</li>
</ul>
<button>Ajouter au Panier</button>
<button>Faire une Proposition</button>
</p>
</div>
<br>
 <div style="width: 450px; margin-left:34%;  padding-top:5px; padding-bottom:20px;border: 3px solid #A0A0A0; text-align: left;background: #C0C0C0;border-radius: 10px;"> 
<p class="flotte">
<img src="${pageContext.request.contextPath}/images/baskets.jpg"
     width= 140px;
     height= 140px;/>   
</p>
<p>
<h1>Paire de Basket</h1><ul>
<li>Prix : 115 points</li>
<li>Fin de l'enchere : 29/12/2020</li>
<li>Vendeur : Kevin.G</li>
</ul>
<button>Ajouter au Panier</button>
<button>Faire une Proposition</button>
</p>
</div>  
<br>
<div style="width: 450px; margin-left:34%;  padding-top:5px; padding-bottom:20px;border: 3px solid #A0A0A0; text-align: left;background: #C0C0C0;border-radius: 10px;"> 
<p class="flotte">
<img src="${pageContext.request.contextPath}/images/TV.jpg"
     width= 140px;
     height= 140px;/>   
</p>
<p>
<h1>Television</h1><ul>
<li>Prix : 400 points</li>
<li>Fin de l'enchere : 03/02/2021</li>
<li>Vendeur : Joelle.B</li>
</ul>
<button>Ajouter au Panier</button>
<button>Faire une Proposition</button>
</p>
</div>            
</body>
<br>
<footer>
<div style="text-align:center"><p>Eni-Encheres</p>
</div>
</footer>
</html>
