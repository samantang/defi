<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Abc Challenge</title>
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
						<a href="user_profile"><img src="photoUser?id=${id }" height="80px" width="80px"/></a>
					</c:otherwise>
				</c:choose>
					<div class="menuGauche">
						<c:import url="menuGauche.jsp"></c:import>
					</div>	
			</div>
			<div class="col-md-6 col-lg-6">
				<div align="center">
					<div>
						<c:forEach items="${mesChallenges }" var="cha">
							<p>l'id: <c:out value="${cha.id }"></c:out> </p>
						
						</c:forEach>
					</div>
					<div>
						<c:forEach items="${gm.mesChallengesRecus }" var="recu">
						<strong>Les recus</strong>
							<p>l'id du jeu est : ${recu.id }</p>
							<p>son nom: ${recu.nom }</p>
							<p><a href="accepteChallenge?idAmi=${recu.id}">accepter</a>	</p>		
						</c:forEach>
					</div>	
					<div>
						<c:forEach items="${gm.mesChallengesEnvoyes }" var="envoi">
						<strong>Les Envoyes</strong>
							<p>l'id du jeu est : ${envoi.id }</p>
							<p>son nom: ${envoi.nom }</p>			
						</c:forEach>
					</div>	
					<div>
						<c:forEach items="${gm.mesChallengesEnAttentes }" var="attente">
						<strong>CEUX QUI SONT EN ATTENTE DE JEU</strong>
							<p>l'id du jeu est : ${attente.id }</p>
							<p>son nom: ${attente.nom }</p>	
							<p><a href="AbcChallengeJeu?idAmi=${attente.id}">jouer contre lui</a>	</p>		
						</c:forEach>
					</div>
					<div>
						<c:if test="${not empty mesChallengesJoues }">
							<!-- bouton détails sur les jeux ------------------------------------------------------------------------------------------- -->
							 <!-- <button data-toggle="modal" href="infoJeuChallengeDuel" data-target="#infos" class="btn btn-primary">
						        Détails Duels
						      </button> -->
						      <button data-toggle="modal" href="infoJeuChallenge" data-target="#infos" class="btn btn-primary">
						        Détails de vos Challenges
						      </button>
						      <div class="modal fade" id="infos">
						        <div class="modal-dialog modal-lg">  
						          <div class="modal-content"></div>  
						        </div> 
						      </div>
						      <!-- FIN     bouton détails sur le jeu --------------------------------------------------------------------------------- -->
						</c:if>
					</div>
					<div class="col-md-6 col-lg-6">
						<div>
							<c:forEach items="${mesChallengesJoues }" var="joues">
							<strong>MES CHALLENGES JOUES</strong>
								<p>l'id du jeu est : ${joues.id }</p>
								<%-- <p>la lettre: ${joues.lettre }</p>
								<p>le score: ${joues.score }</p>
								<p>email: ${joues.email }</p> --%>
								<p>email: ${joues.dateString }</p>
								<p>Code: ${joues.codeIndentification }</p>	
							</c:forEach>
						</div>
					</div>
					<div class="col-md-6 col-lg-6">
						<div>
							<c:forEach items="${challengeAmisOrdreCoupe }" var="joues">
							<strong>CHALLENGES JOUES AMIS</strong>
								 <c:choose> 
									 <c:when test="${joues.codeIndentification == 'xxx' }"> 
										il n'a pas joué encore <br><br><br><br>
									</c:when> 
									 <c:otherwise> 
										<p>l'id du jeu est : ${joues.id }</p>
										<p>la lettre: ${joues.lettre }</p>
										<p>le score: ${joues.score }</p>
										<p>ami: ${joues.friend.nom }</p>
										<p>CODE: ${joues.codeIndentification }</p>	
									 </c:otherwise> 
								 </c:choose> 
							</c:forEach>
						</div>
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