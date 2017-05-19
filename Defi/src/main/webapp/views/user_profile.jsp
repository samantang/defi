<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>userhome</title>
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
						<a href="userhome"><img src="photoUser?id=${id }" height="80px" width="80px"/></a>
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
	
					<p><strong> ${model.moi.nom} &nbsp; ${model.moi.prenom}</strong></p>
					<p> <a href="abcSoloHome"> le nombre de jeux solos est ${tailleSolos}</a></p>
					<p><a href="abcChallengeHome"> le nombre de jeux chanllenges est ${tailleChallenges}</a></p>
					 <%-- <c:forEach items="${model}" var="s"> --%>
					 <!-- <a href="photoUser">la photo</a> -->
					 <!-- <tr>
					 	<td>
					 	</td>
					 </tr> -->
					<img src="photoUser?id=${id }"/>
					<%--  </c:forEach>  --%>
					
					<div>
						<p>changer la photo </p>
						<f:form action="changerPhoto" method="POST" enctype="multipart/form-data" modelAttribute="sm">
							<input type="file" name="file">
							<input type="submit" value="Valider">
						</f:form>
						
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