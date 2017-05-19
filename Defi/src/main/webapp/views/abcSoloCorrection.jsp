<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
<title>Correction Solo</title>
<style type="text/css">
.menuGauche ul {
    background: #CCCCFF;
    padding: 20px;
    margin-top: 10px;
    list-style-type: none;
}
.menuGauche ul li {
    background: #cce5ff;
    margin: 5px;
}
.menuGauche ul li a{
	color: #000000;
    text-decoration: none;
}
.menuGauche ul li a:hover{
	color: green;
}
.menuDroit {
	position: fixed;
}
.menuGauche{
	position: fixed;
}
</style>
</head>

<body>
				
	<jsp:include page="entete.jsp"></jsp:include>
 <div style="margin-top: 55px" >		
	<div class="container">
		<div class="row">
			<div class="col-md-3 col-lg-3">
				<c:choose>
					<c:when test="${moi.nomPhoto == null}">
						<img height="50px" width="50px" src="<%=request.getContextPath()%>/resources/images/user.png" alt="">
						<p>Ajouter de photo de profil </p>
						<f:form action="changerPhoto" method="POST" enctype="multipart/form-data" modelAttribute="sm">
							<input type="file" name="file">
							<input type="submit" value="changer">
						</f:form>
					</c:when>
					<c:otherwise>
						<a href="userhome"><img src="photoUser?id=${id }" height="80px" width="80px"/></a>
					</c:otherwise>
				</c:choose>
					<div class="menuGauche">
						<c:import url="menuGauche.jsp"></c:import>
					</div>
			</div>
			<div class="col-md-9 col-lg-9">
				<p style="text-align: center;">vous avez obtenu un score de <strong>${score}</strong>  sur <strong>${scorePossible}</strong> possible
	
				<form  action="abcGame">
						 		<div class="row">
								 	<c:if test="${not empty listePays }">
								 		<c:if test="${empty paysTest}"> vous n'avez pas essayé de trouver les pays</c:if><br />
								 		<c:if test="${not empty paysTest }"> 
								 		<div class="col-md-3 col-lg-3">
									 			<table class="table table-bordered table-striped table-condensed">
									 				<caption><h3>Vos Propositions de Pays</h3></caption>
									 				<tr>
									 					<th style="border: 1px solid black;">PAYS</th>
									 				</tr>
									 					<c:forEach items="${paysTest}" var="e">
									 				<tr>
									 					<c:if test="${not empty e }">
									 						<td style="border: 1px solid black;"><c:out value="${e}"></c:out></td>
									 					</c:if>
									 				</tr>
									 				</c:forEach>
									 			</table>
								 		</div>	
								 		<div class="col-md-3 col-lg-3">	
								 			<c:if test="${not empty paysRestants}">
								 			<table class="table table-bordered table-striped table-condensed">
								 				<caption><h3>Les Pays Restants</h3></caption>
								 				<tr>
								 					<th style="border: 1px solid black;">PAYS</th>
								 				</tr>
								 					<c:forEach items="${paysRestants}" var="e">
										 				<tr>
										 					<td style="border: 1px solid black;"><c:out value="${e}"></c:out></td>
										 				</tr>
								 					</c:forEach>
								 			</table>
								 			</c:if>
								 		</div>	
								 			<c:if test="${empty paysRestants }">
								 				<div class="col-md-6">
								 					<p>Bravo !!!! vous avez trouvez tous les pays  </p>
								 				</div>
								 			</c:if>
								 			
								 		</c:if>
								 		<c:if test="${not empty form.getPays}">
								 				<p><strong>les pays </strong></p>
								 				<p>vous pouvez mieux apprendre sur les pays avec les liens ci-desous
								 			<table>
								 				<tr>
								 					<th style="border: 1px solid black;">PAYS</th>
								 				</tr>
								 					<c:forEach items="${form.getPays}" var="e">
								 				<tr>
								 					<td style="border: 1px solid black;"><a href="${e.lien}"><c:out value="${e}"></c:out></a></td>
								 				</tr>
								 					</c:forEach>
								 			</table>
								 			</c:if>
								 	</c:if>	
								<!-- ----------------------------------------------capitales------------------------------------------------------------------------------- -->	
								 	<c:if test="${not empty listeCapitales }">
								 		<c:if test="${empty choixCapitales}"> vous n'avez pas choisit de capitale</c:if><br />
								 		<c:if test="${not empty choixCapitales }">
								 			<div class="col-md-3 col-lg-3"> 
									 			<table class="table table-bordered table-striped table-condensed">
									 				<caption><h3>Vos Propostions de Capitales</h3></caption>
									 				<tr>
									 					<th style="border: 1px solid black;">CAPITALES</th>
									 				</tr>
									 					<c:forEach items="${choixCapitales}" var="e">
									 				<tr>
									 					<c:if test="${not empty e }">
									 						<td style="border: 1px solid black;"><c:out value="${e}"></c:out></td>
									 					</c:if>
									 				</tr>
									 				</c:forEach>
									 			</table>
								 			</div>
								 			<div class="col-md-3 col-lg-3"> 
									 			<c:if test="${not empty capitalesRestantes}">
										 			<table class="table table-bordered table-striped table-condensed">
										 				<caption><h3>Les Capitales Restantes</h3></caption>
										 				<tr>
										 					<th style="border: 1px solid black;">CAPITALES</th>
										 				</tr>
										 					<c:forEach items="${capitalesRestantes}" var="e">
										 				<tr>
										 					<td style="border: 1px solid black;"><c:out value="${e}"></c:out></td>
										 				</tr>
										 					</c:forEach>
										 			</table>
									 			</c:if>
								 			</div>
								 			<c:if test="${empty capitalesRestantes}">
								 					<div class="col-md-6">
								 				<p>Bravo!!! vous avez trouvé toutes les capitales</p>
								 				</div>
								 			</c:if>
								 		</c:if>
								 		<c:if test="${not empty form.getCapitale}">
								 				<p>les capitales </p>
									 			<table>
									 				<tr>
									 					<th style="border: 1px solid black;">CAPITALES</th>
									 				</tr>
									 					<c:forEach items="${form.getCapitale}" var="e">
									 				<tr>
									 					<td style="border: 1px solid black;"><a href="${e.lien}"><c:out value="${e.nomCapitale}"></c:out></a></td>
									 				</tr>
									 					</c:forEach>
									 			</table>
								 			</c:if>
								 	</c:if>	
								<!-- -----------------------------------------------VILLES------------------------------------------------------------------------------ -->
								 	<c:if test="${not empty listeVillesFrance }">
								 		<c:if test="${empty choixVilles}"> vous n'avez pas choisit de villes</c:if><br />
								 		<c:if test="${not empty choixVilles }"> 
								 		<div class="col-md-3 col-lg-3"> 
								 			<table class="table table-bordered table-striped table-condensed">
								 				<caption><h3>Vos propositions de Villes</h3></caption>
								 				<tr>
								 					<th style="border: 1px solid black;">VILLES</th>
								 				</tr>
								 					<c:forEach items="${choixVilles}" var="e">
								 				<tr>
								 					<c:if test="${not empty e }">
								 						<td style="border: 1px solid black;"><c:out value="${e}"></c:out></td>
								 					</c:if>
								 				</tr>
								 				</c:forEach>
								 			</table>
								 		</div>
								 			
								 		<div class="col-md-3 col-lg-3"> 	
								 			<c:if test="${not empty villesRestantes}">
								 			<table class="table table-bordered table-striped table-condensed">
								 				<caption><h3>Les Villes Restantes</h3></caption>
								 				<tr>
								 					<th style="border: 1px solid black;">VILLES</th>
								 				</tr>
								 					<c:forEach items="${villesRestantes}" var="e">
								 				<tr>
								 					<td style="border: 1px solid black;"><c:out value="${e}"></c:out></td>
								 				</tr>
								 					</c:forEach>
								 			</table>
								 			</c:if>
								 		</div>	
								 			
								 			<c:if test="${empty villesRestantes}">
								 				<div class="col-md-6">
								 					<p>Bravo!!! Vous avez trouvé toutes les villes</p>
								 				</div>
								 			</c:if>
								 		</c:if>
								 		<c:if test="${not empty form.getVilleFrance}">
								 				<p>les villes </p>
									 			<table>
									 				<tr>
									 					<th style="border: 1px solid black;">VILLES</th>
									 				</tr>
									 					<c:forEach items="${form.getVilleFrance}" var="e">
									 				<tr>
									 					<td style="border: 1px solid black;"><a href="${e.lien}"><c:out value="${e.nomVilleFrance}"></c:out></a></td>
									 				</tr>
									 					</c:forEach>
									 			</table>
								 			</c:if>
								 	</c:if>	
								<!-- --------------------------------------------------------NOBELS--------------------------------------------------------------------- -->
								 	<c:if test="${not empty listeNobels }">
								 		<c:if test="${empty choixNobels}"> vous n'avez pas choisit de prix nobel</c:if><br />
								 		<c:if test="${not empty choixNobels }"> 
								 		<div class="col-md-3 col-lg-3">
								 			<table class="table table-bordered table-striped table-condensed">
								 				<caption><h3>Vos Propositions de Nobels</h3></caption>
								 				<tr>
								 					<th style="border: 1px solid black;">P Nobels</th>
								 				</tr>
								 					<c:forEach items="${choixNobels}" var="e">
								 				<tr>
								 					<c:if test="${not empty e }">
								 						<td style="border: 1px solid black;"><c:out value="${e}"></c:out></td>
								 					</c:if>
								 				</tr>
								 				</c:forEach>
								 			</table>
								 		</div>
								 		
								 		<div class="col-md-3 col-lg-3">
								 			<c:if test="${not empty nobelsRestants}">
								 			<table class="table table-bordered table-striped table-condensed">
								 				<caption><h3>Les Nobels Restants</h3></caption>
								 				<tr>
								 					<th style="border: 1px solid black;">NOBELS</th>
								 				</tr>
								 					<c:forEach items="${nobelsRestants}" var="e">
								 				<tr>
								 					<td style="border: 1px solid black;"><c:out value="${e}"></c:out></td>
								 				</tr>
								 					</c:forEach>
								 			</table>
								 			</c:if>
								 		</div>	
								 			<c:if test="${empty nobelsRestants}">
								 				<div class="col-md-6">
								 					<p>Bravo!!! vous avez trouvé tous les Nobels</p>
								 				</div>
								 			</c:if>
								 		</c:if>
								 		<c:if test="${not empty form.getNobel}">
								 				<p>les Nobels </p>
								 			<table>
								 				<tr>
								 					<th style="border: 1px solid black;">NOBEL</th>
								 				</tr>
								 					<c:forEach items="${form.getNobel}" var="e">
								 				<tr>
								 					<td style="border: 1px solid black;"><a href="${e.lien}"><c:out value="${e.nomNobel}"></c:out></a></td>
								 				</tr>
								 					</c:forEach>
								 			</table>
								 			</c:if>
								 	</c:if>	
								<!-- -----------------------------------------------------PRESIDENTS------------------------------------------------------------------------ -->
								 	<c:if test="${not empty listePresidentsA }">
								 		<c:if test="${empty choixPresidents}"> vous n'avez pas choisit de presidents</c:if><br />
								 		<c:if test="${not empty choixPresidents }"> 
								 		<div class="col-md-3 col-lg-3">
								 			<table class="table table-bordered table-striped table-condensed">
								 				<caption><h3>Vos propositions de Présidents</h3></caption>
								 				<tr>
								 					<th style="border: 1px solid black;">PRESIDENTS</th>
								 				</tr>
								 					<c:forEach items="${choixPresidents}" var="e">
								 				<tr>
								 					<c:if test="${not empty e }">
								 						<td style="border: 1px solid black;"><c:out value="${e}"></c:out></td>
								 					</c:if>
								 				</tr>
								 				</c:forEach>
								 			</table>
								 		</div>	
								 		<div class="col-md-3 col-lg-3">	
								 			<c:if test="${not empty presidentsRestants}">
								 				<p>les presidents restants</p>
								 			<table class="table table-bordered table-striped table-condensed">
								 				<caption><h3>Les Présidents Restants</h3></caption>
								 				<tr>
								 					<th style="border: 1px solid black;">PRESIDENTS</th>
								 				</tr>
								 					<c:forEach items="${presidentsRestants}" var="e">
								 				<tr>
								 					<td style="border: 1px solid black;"><c:out value="${e}"></c:out></td>
								 				</tr>
								 					</c:forEach>
								 			</table>
								 			</c:if>
								 		</div>
								 			
								 			<c:if test="${empty presidentsRestants}">
								 				<div class="col-md-6">
								 					<p>Bravo!!! vous avez trouvez tous les présidents</p>
								 				</div>
								 			</c:if>
								 		</c:if>
								 		<c:if test="${not empty form.getPays}">
								 				<p>les presidents </p>
									 			<table>
									 				<tr>
									 					<th style="border: 1px solid black;">PRESIDENTS</th>
									 				</tr>
									 					<c:forEach items="${form.getPresident}" var="e">
									 				<tr>
									 					<td style="border: 1px solid black;"><a href="${e.lien}"><c:out value="${e.nomPresident}"></c:out></a></td>
									 				</tr>
									 					</c:forEach>
									 			</table>
								 			</c:if>
								 	</c:if>	
								<!-- --------------------------------------------------------ANCIENS P--------------------------------------------------------------------- -->
								 	<c:if test="${not empty listeAPresidents }">
								 		<c:if test="${empty choixAPresidents}"> vous n'avez pas choisit d'ancien P</c:if><br />
								 		<c:if test="${not empty choixAPresidents }"> 
								 		<div class="col-md-3 col-lg-3">
								 			<table class="table table-bordered table-striped table-condensed">
								 				<caption><h3>Vos propositions d'anciens Prési</h3></caption>
								 				<tr>
								 					<th style="border: 1px solid black;">Ancien</th>
								 				</tr>
								 					<c:forEach items="${choixAPresidents}" var="e">
								 				<tr>
								 					<c:if test="${not empty e }">
								 						<td style="border: 1px solid black;"><c:out value="${e}"></c:out></td>
								 					</c:if>
								 				</tr>
								 				</c:forEach>
								 			</table>
								 		</div>
								 		
								 		<div class="col-md-3 col-lg-3">	
								 			<c:if test="${not empty ApresidentsRestants}">
								 			<table class="table table-bordered table-striped table-condensed">
								 				<caption><h3>Les A Présidents Restants</h3></caption>
								 				<tr>
								 					<th style="border: 1px solid black;">ANCIENS P</th>
								 				</tr>
								 					<c:forEach items="${ApresidentsRestants}" var="e">
								 				<tr>
								 					<td style="border: 1px solid black;"><c:out value="${e}"></c:out></td>
								 				</tr>
								 					</c:forEach>
								 			</table>
								 		</c:if>
								 		</div>
								 		
								 			<c:if test="${empty ApresidentsRestants}">
								 				<div class="col-md-6">
								 					<p>Bravo!!! Vous avez trouvé tous les anciens Prési</p>
								 				</div>	
								 			</c:if>
								 		</c:if>
								 		<c:if test="${not empty form.getanciensP}">
								 				<p>les anciens P </p>
									 			<table>
									 				<tr>
									 					<th style="border: 1px solid black;">anciens P</th>
									 				</tr>
									 					<c:forEach items="${form.getanciensP}" var="e">
									 				<tr>
									 					<td style="border: 1px solid black;"><a href="${e.lien}"><c:out value="${e.nomAncienPresident}"></c:out></a></td>
									 				</tr>
									 					</c:forEach>
									 			</table>
								 			</c:if>
								 	</c:if>	
								<!-- --------------------------------------------------------------ANIMAUX--------------------------------------------------------------- -->
								 	<c:if test="${not empty listeAnimaux }">
								 		<c:if test="${empty choixAnimaux}"> vous n'avez pas choisit d'animal</c:if><br />
								 		<c:if test="${not empty choixAnimaux }"> 
								 		<div class="col-md-3 col-lg-3">
								 			<table class="table table-bordered table-striped table-condensed">
								 				<caption><h3>Vos propositions d'animaux</h3></caption>
								 				<tr>
								 					<th style="border: 1px solid black;">ANIMAUX</th>
								 				</tr>
								 					<c:forEach items="${choixAnimaux}" var="e">
								 				<tr>
								 					<c:if test="${not empty e }">
								 						<td style="border: 1px solid black;"><c:out value="${e}"></c:out></td>
								 					</c:if>
								 				</tr>
								 				</c:forEach>
								 			</table>
								 		</div>	
								 		
								 		<div class="col-md-3 col-lg-3">	
								 			<c:if test="${not empty animauxRestants}">
								 			<table class="table table-bordered table-striped table-condensed">
								 				<caption><h3>Les Animaux Restants</h3></caption>
								 				<tr>
								 					<th style="border: 1px solid black;">ANIMAUX</th>
								 				</tr>
								 					<c:forEach items="${animauxRestants}" var="e">
								 				<tr>
								 					<td style="border: 1px solid black;"><c:out value="${e}"></c:out></td>
								 				</tr>
								 					</c:forEach>
								 			</table>
								 			</c:if>
								 		</div>	
								 			
								 			<c:if test="${empty animauxRestants}">
								 				<div class="col-md-6">
								 					<p>Bravo!!! vous avez trouvé tous les animaux</p>
								 				</div>
								 			</c:if>
								 		</c:if>
								 		<c:if test="${not empty form.getAnimaux}">
								 				<p>les pays </p>
									 			<table>
									 				<tr>
									 					<th style="border: 1px solid black;">ANIMAUX</th>
									 				</tr>
									 					<c:forEach items="${form.getAnimaux}" var="e">
									 				<tr>
									 					<td style="border: 1px solid black;"><a href="${e.lien}"><c:out value="${e.nomAnimaux}"></c:out></a></td>
									 				</tr>
									 					</c:forEach>
									 			</table>
								 			</c:if>
								 	</c:if>	
								<!-- -------------------------------------------------------AGGLOMERATIONS---------------------------------------------------------------------- -->
								 	<c:if test="${not empty listeAglo }">
								 		<c:if test="${empty choixAgglo}"> vous n'avez pas choisit d'agglomeration</c:if><br />
								 		<c:if test="${not empty choixAgglo }"> 
								 		<div class="col-md-3 col-lg-3">
								 			<table class="table table-bordered table-striped table-condensed">
								 				<caption><h3>Vos propositions d'agglos</h3></caption>
								 				<tr>
								 					<th style="border: 1px solid black;">AGGLO</th>
								 				</tr>
								 					<c:forEach items="${choixAgglo}" var="e">
								 				<tr>
								 					<c:if test="${not empty e }">
								 						<td style="border: 1px solid black;"><c:out value="${e}"></c:out></td>
								 					</c:if>
								 				</tr>
								 				</c:forEach>
								 			</table>
								 		</div>	
								 		
								 		<div class="col-md-3 col-lg-3">	
								 			<c:if test="${not empty AggloRestantes}">
								 			<table class="table table-bordered table-striped table-condensed">
								 				<caption><h3>Les Agglos Restantes</h3></caption>
								 				<tr>
								 					<th style="border: 1px solid black;">AGGLO</th>
								 				</tr>
								 					<c:forEach items="${AggloRestantes}" var="e">
								 				<tr>
								 					<td style="border: 1px solid black;"><c:out value="${e}"></c:out></td>
								 				</tr>
								 					</c:forEach>
								 			</table>
								 			</c:if>
								 		</div>	
								 			
								 			<c:if test="${empty AggloRestantes}">
								 				<div class="col-md-6">
								 					<p>Bravo!!! vous avez trouvé toutes les aggolomérations</p>
								 				</div>
								 			</c:if>
								 		</c:if>
								 		<c:if test="${not empty form.getAgglo}">
								 				<p>les agglo </p>
									 			<table>
									 				<tr>
									 					<th style="border: 1px solid black;">AGGLO</th>
									 				</tr>
									 					<c:forEach items="${form.getAgglo}" var="e">
									 				<tr>
									 					<td style="border: 1px solid black;"><a href="${e.lien}"><c:out value="${e.nomAglo}"></c:out></a></td>
									 				</tr>
									 					</c:forEach>
									 			</table>
								 			</c:if>
								 	</c:if>	
								<!-- --------------------------------------------------------------ARTISTES--------------------------------------------------------------- -->
								 <div class="paysCorrection">
								 	<c:if test="${not empty listeChanteurs }">
								 		<c:if test="${empty choixArtistes}"> vous n'avez pas choisit d'artiste</c:if><br />
								 		<c:if test="${not empty choixArtistes }"> 
								 		<div class="col-md-3 col-lg-3">
								 			<table class="table table-bordered table-striped table-condensed">
								 				<caption><h3>Vos propositions d'artistes</h3></caption>
								 				<tr>
								 					<th style="border: 1px solid black;">ARTISTES</th>
								 				</tr>
								 					<c:forEach items="${choixArtistes}" var="e">
								 				<tr>
								 					<c:if test="${not empty e }">
								 						<td style="border: 1px solid black;"><c:out value="${e}"></c:out></td>
								 					</c:if>
								 				</tr>
								 				</c:forEach>
								 			</table>
								 		</div>
								 			
								 		<div class="col-md-3 col-lg-3">	
								 			<c:if test="${not empty ArtistesRestants}">
								 			<table class="table table-bordered table-striped table-condensed">
								 				<caption><h3>Les Artistes Restants</h3></caption>
								 				<tr>
								 					<th style="border: 1px solid black;">ARTISTES</th>
								 				</tr>
								 					<c:forEach items="${ArtistesRestants}" var="e">
								 				<tr>
								 					<td style="border: 1px solid black;"><c:out value="${e}"></c:out></td>
								 				</tr>
								 					</c:forEach>
								 			</table>
								 			</c:if>
								 		</div>	
								 			
								 			<c:if test="${empty ArtistesRestants}">
								 				<div class="col-md-6">
								 					<p>Bravo!!! vous avez trouvé tous les artistes</p>
								 				</div>
								 			</c:if>
								 		</c:if>
								 		<c:if test="${not empty form.getArtistes}">
								 				<p>les artistes </p>
									 			<table>
									 				<tr>
									 					<th style="border: 1px solid black;">ARTISTES</th>
									 				</tr>
									 					<c:forEach items="${form.getArtiste}" var="e">
									 				<tr>
									 					<td style="border: 1px solid black;"><a href="${e.lien}"><c:out value="${e.nomArtiste}"></c:out></a></td>
									 				</tr>
									 					</c:forEach>
									 			</table>
								 			</c:if>
								 	</c:if>	
								</div>
								</div>	
							</form>
					</div>		
		</div>
	</div>
</div>
</body>
</html>