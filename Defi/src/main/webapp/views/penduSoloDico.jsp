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
					<h3>LE JEU DU PENDU DICO</h3>
					<div style="text-align: center;">
						<a class="btn btn-success" href="penduSoloDicoJeu">commencer une nouvellle partie</a><br>
					</div><br>
					<%-- <div>
						<ul class="list-group">
						  <a href="#" class="list-group-item ">
						    <h4 class="list-group-item-heading">Pendu Dico Solo</h4>
						    <p  class="list-group-item-text pull-right">Jouer avec les mots du dictionnaire, vous en avez plus de 36 000,
						    le mot avec lequel vous allez jouer est tiré au sort et une littre vous ai indiqué dans le mot pour vous guider.</p>
						    <img src="<%=request.getContextPath()%>/resources/images/user.png" height="50px" width="50px">
						  </a>
						 </ul> 
					</div> --%>
					<div>
						  <ul class="media-list">
						    <li class="media thumbnail">
						      <a class="pull-left" href="#">
						        <img class="media-object" src="<%=request.getContextPath()%>/resources/images/user.png" height="50px" width="50px">
						      </a>
						      <div class="media-body">
						        <h4 class="media-heading">Pendu Dico Solo</h4>
						        <p>Jouer avec les mots du dictionnaire, vous en avez plus de 36 000,
						    le mot avec lequel vous allez jouer est tiré au sort et une littre vous ai indiqué dans le mot pour vous guider.</p>
						      </div>  
						   </li>
						 </ul>
					</div>
					<!-- bouton détails sur les jeux ------------------------------------------------------------------------------------------- -->
					 <button data-toggle="modal" href="infosPenduDicoSolo" data-target="#infos" class="btn btn-primary">
				        Détails sur vos jeux en solo DICO
				      </button><br><br>
				      <div class="modal fade" id="infos">
				        <div class="modal-dialog modal-lg">  
				          <div class="modal-content"></div>  
				        </div> 
				      </div>
				</div>
				<div>
					<h2 style="text-align: center; color: white; text-shadow: 2px 2px 4px #000000;  ">Vos dix derniers jeux</h2>
				</div>
				<div id="infoPublicationSolo">
				</div>
				<div>
					<table class="table table-bordered table-striped table-condensed" style="text-align: center;">
						<thead>
								<tr>
									<th>Date</th>
									<th style="text-align: center;">Le Mot</th>
									<th>Lettre</th>
									<th>Score</th>
									<!-- <th>Publier</th>
									<th>Supprimer</th> -->
									<th>Publier</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${penduModel.mesDicoSolo }" var="solo" begin="0" end="9">
									<tr>
										<td>${solo.dateString }</td>
										<td>${solo.mot }</td>
										<td>${solo.lettreString }</td>
										<td>${solo.score }/${solo.scoreMax }</td>
										<%-- <td><a href="publierSoloDico?id=${solo.id}">pubier</a></td>
										<td><a href="supprimerSoloDico?id=${solo.id}">Supp</a></td> --%>
										<td><button id="${solo.id}" onclick="publierUnSolo(this,${solo.id} )"  class="btn btn-primary" >
									        	<span class="glyphicon glyphicon-share"></span>
									     	 </button>
									     	 <%-- <a  class="btn btn-ifo" href="publierSoloDico?id=${solo.id}">Publier</a> --%>
								      	</td>
									</tr>
								</c:forEach>
							</tbody>
					</table>
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
	<script type="text/javascript">
	function publierUnSolo(lui, id){
		lui.disabled='true';
		var param = 'idSolo='+id+'';
		$("#infoPublicationSolo").load("infoPublicationSolo", param);
	};
	</script>
	

	
	
</body>
</html>