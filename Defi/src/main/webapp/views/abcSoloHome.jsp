<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Abc Solo</title>
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
				<div>
					<div class="menuGauche">
						<c:import url="menuGauche.jsp"></c:import>
					</div>
					<%-- <%@include file="menuGauche.jsp" %> --%>	
				</div>	
			</div>
			<div class="col-md-6 col-lg-6">
				<div align="center">
				<!-- bouton détails sur les jeux ------------------------------------------------------------------------------------------- -->
				 <button data-toggle="modal" href="infoJeuSolo" data-target="#infos" class="btn btn-primary">
			        Détails sur les jeux en Solo
			      </button><br><br>
			      <div class="modal fade" id="infos">
			        <div class="modal-dialog modal-lg">  
			          <div class="modal-content"></div>  
			        </div> 
			      </div>
			      <!-- FIN     bouton détails sur le jeu --------------------------------------------------------------------------------- -->
					<div>
						<table class="table table-bordered table-striped table-condensed">
							<thead>
								<tr>
									<th>Date</th>
									<th>Lettre</th>
									<th>Aide</th>
									<th>Temps</th>
									<th>Score</th>
									<th>Publier</th>
									<th>Supprimer</th>
									<th>Détails</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${gm.mesSolos }" var="solo">
									<tr>
										<td>${solo.abcsolojeux.datestring }</td>
										<td>${solo.abcsolojeux.lettrechar }</td>
										<td>${solo.abcsolojeux.help }</td>
										<td>${solo.abcsolojeux.time }</td>
										<td>${solo.abcsolojeux.score }/${solo.abcsolojeux.scoreMax }</td>
										<td><a href="publierSolo?id=${solo.abcsolojeux.id}">pubier</a></td>
										<td><a href="supprimerSolo?id=${solo.id}">Sup</a></td>
										<%-- <td><a href="detailSolo?id=${solo.id}">Detail</a></td> --%>
										<td><button data-toggle="modal" href="detailSolo?id=${solo.id}" data-target="#infos" class="btn btn-primary">
									        	Détail
									     	 </button>
								      	</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div>
						<strong>Commencez à jouer maintenant !!! 
						(Pour pouvoir jouer, vous devez absolument coher au moins un intitulé)<br>
						La lettre avec laquelle vous devez jouer est titrée automatiquement et le temps est calculé en fonction du nombre
						de réponses possibles (18 secondes par réponse): exemple: s'il y a 10 réponses possibles => 10 * 18 = 180 secondes = 3 minutes
						</strong><br><br>
						
						<form action="abcSoloJeu" >
								<table>
									<tr>
										
										<td>
											<input type="checkbox"   name="pays" value="pays" /> pays &nbsp;&nbsp;
											<input type="checkbox"  name="capitales" value="capitales"/> capitales&nbsp;&nbsp;
											<input type="checkbox" name="presidents" value="presidents"/> présidents ou chefs de gouvernement actuels&nbsp;&nbsp;
											<input type="checkbox" name="villesFrance" value="villesFrance" /> Villes de France de plus de 30 000 hts&nbsp;&nbsp;
											<input type="checkbox" name="nobels" value="nobels" /> prix nobel depuis 2 000&nbsp;&nbsp;
											<input type="checkbox" name="ancienPresidents" value="ancienPresidents" /> anciens prési ou chefs de gouv dépuis 2000&nbsp;&nbsp;
											<input type="checkbox" name="aglomerations" value="aglomerations" /> 100 plus grandes agglomerations mondiales&nbsp;&nbsp;
											<input type="checkbox" name="chanteurs" value="chanteurs" /> chanteurs ou groupe avec plus 200 millions de disques vendus&nbsp;&nbsp;
											<input type="checkbox" name="animaux" value="animaux" /> animaux nationaux <br><br>
											
											<input type="submit" value="valider">
										</td>						
									</tr>
								</table>
							</form>
						
					</div>
				</div>	
			</div>
			<div class="col-md-3 col-lg-3">
				<div>
					<jsp:include page="menuDroit.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
</div>
		
	
</body>
</html>