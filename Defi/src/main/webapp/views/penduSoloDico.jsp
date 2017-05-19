<%@ page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

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


<title>PenduSolo</title>
</head>
<body>
		<jsp:include page="entete.jsp"></jsp:include>
 <div style="margin-top: 55px" >		
	<div class="container">
		<div class="row">
			<div class="col-md-3 col-lg-3 menuGaucheFixe">
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
					<!-- bouton détails sur les jeux ------------------------------------------------------------------------------------------- -->
					 <button data-toggle="modal" href="infoJeuSolo" data-target="#infos" class="btn btn-primary">
				        Détails sur vos jeux en solo DICO
				      </button><br><br>
				      <div class="modal fade" id="infos">
				        <div class="modal-dialog modal-lg">  
				          <div class="modal-content"></div>  
				        </div> 
				      </div>
				</div>
				<div>
					<table class="table table-bordered table-striped table-condensed">
						<thead>
								<tr>
									<th>Date</th>
									<th>Mot</th>
									<th>Lettre</th>
									<th>Temps</th>
									<th>Aide</th>
									<th>Score</th>
									<th>Publier</th>
									<th>Supprimer</th>
									<th>Détails</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${penduModel.mesDicoSolo }" var="solo">
									<tr>
										<td>${solo.datestring }</td>
										<td>${solo.mot }</td>
										<td>${solo.lettre }</td>
										<td>${solo.temps }</td>
										<td>${solo.aide }</td>
										<td>${solo.score }/${solo.scoreMax }</td>
										<td><a href="publierSoloDico?id=${solo.id}">pubier</a></td>
										<td><a href="supprimerSoloDico?id=${solo.id}">Supp</a></td>
										<td><button data-toggle="modal" href="detailSoloDico?id=${solo.id}" data-target="#infos" class="btn btn-primary">
									        	Détail
									     	 </button>
								      	</td>
									</tr>
								</c:forEach>
							</tbody>
					</table>
				</div>	
				<div>
					<a href="penduSoloDicoJeu">commencer à jouer maintenant</a>
				</div>
			</div>
			<div class="col-md-3 col-lg-3">
				<div>
					<%-- <jsp:include page="menuDroit.jsp"></jsp:include> --%>
					<c:import url="menuDroit.jsp"></c:import>
				</div>
			</div>
		</div>
	</div>
</div>
	
	

	
	
</body>
</html>