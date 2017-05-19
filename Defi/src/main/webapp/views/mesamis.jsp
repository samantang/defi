<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mes Amis</title>
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
						<img src="photoUser?id=${id }" height="80px" width="80px" />
					</c:otherwise>
				</c:choose>
				<div>
					<div class="menuGauche">
						<c:import url="menuGauche.jsp"></c:import>
					</div>
				</div>	
			</div>
			<div class="col-md-6 col-lg-6">
				<div align="center">
					<strong>MES AMIS A QUI J'AI ENVOYE UN CHALLENGE ET QUI N'ONT PAS ENCORE ACCEPTE "annuler"</strong>
					<c:forEach items="${amisAnnulerDemande }" var="ami">
						<p>${ami.nom } ${ami.prenom } => <a href="annulerChallenge?id=${ami.id }"> Annuler Demande</a></p>
					</c:forEach>
				</div><hr>
				<div align="center">
					<strong>MES AMIS QUI M'ONT ENVOYE UN CHALLENGE ET QUE JE N'AI PAS ENCORE ACCEPTE "repondre"</strong>
					<c:forEach items="${amisAccepterDemande }" var="ami">
						<p> ${ami.nom } =>
							<a href="accepterChallenge?id=${ami.id }">accepter</a> &nbsp;
							<a href="refuserChallenge?id=${ami.id }">refuser</a>
						</p>
					</c:forEach>
				</div><hr>
				<div align="center">
					<strong>MES AMIS QUI ATTENDENT QUE JE JOUE "jouer"</strong>
					<c:forEach items="${amisJouerAvec }" var="ami">
						<p>${ami.nom } ${ami.prenom } => <a href="AbcChallengeJeu?idAmi=${ami.id }">jouer contre lui</a></p>
					</c:forEach>
				</div><hr>
				<div align="center">
					<strong>MES AMIS A QUI JE PEUX ENVOYER UNE DEMANDE DE CHALLENGE "envoyer"</strong>
					<c:forEach items="${amisEnvoyerDemande }" var="ami">
						<p> ${ami.nom } ${ami.prenom } =>
							<a href="challengerChallenge?id=${ami.id }">Envoyer challenge</a>
						</p>
					</c:forEach>
				</div><hr>
				<div style="text-align: center;">
					<p> TOUS LES INSCRITS A QUI JE PEUX ENVOYER UNE DEMANDE</p>
					<div>
						<p> sur cette liste n'apparaitront pas toutes les personnes avec qui vous avez entamé
							un processus de jeu, c'est à dire les personnes à qui vous avez par exemple envoyé une demande,
							qui vous en ont envoyé ou ou bien si l'un d'entre vous attent que l'autre joue
						</p>
					</div>
					<c:forEach items="${tousLesInscrits }" var="tous">
						<%-- <p>
							<c:out value="${tous.nom }"> &nbsp; &nbsp;</c:out><c:out value="${tous.prenom }"></c:out>
							<a href="challengerInscrit?id=${tous.id }">jouer contre lui</a>
						</p> --%>
						<p>
							<strong>${tous.nom} &nbsp; ${tous.prenom}</strong> 
							<a href="challengerInscrit?id=${tous.id }">jouer contre lui</a>
						</p>
						
						
					</c:forEach>
				</div><hr>
				<%-- <div align="center">
					<strong>TOUS MES AMIS</strong>
	
					<c:forEach items="${sm.mesAmis }" var="ami">
						<p> ${ami.nom }
							${ami.prenom }
							<a href="supprimerAmi?id=${ami.id}">supprimer</a>
						</p>
					</c:forEach>

				</div> --%>	
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