<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%-- <%@ page language="java" contentType="text/html;  pageEncoding="UTF-8"%> --%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"> -->
</head>
	<body>
		<div class="menuDroit">
			<p> <strong>Les derniers jeux que vous avez joué</strong></p>
			<div class="panel panel-default " style="margin-top: 15px;"> 
				<div class="panel-heading" style="text-align: center;">
			    	<a href="#solo" data-toggle="collapse">Vos Jeux SOLO</a><br>
			                	les 10 derniers
			    </div>
			       <div id="solo" class="panel-collapse collapse">
			         <div class="panel-body">
							<table class="table table-bordered table-striped table-condensed ">
								<!-- <thead> -->
									<tr>
										<th>Date</th>
									
										<th>Score</th>
									
										<th>Lettre</th>
									
										<th>Aide</th>
									</tr>
								
									<c:forEach items="${mesSolos}" var="solo" begin="0" end="${tailleSolos }">
										<tr>
											<td><c:out value="${solo.abcsolojeux.datestring}"></c:out></td>
										
											<td><c:out value="${solo.abcsolojeux.score}"></c:out>/<c:out value="${solo.abcsolojeux.scoreMax}"></c:out></td>
										
											<td><c:out value="${solo.abcsolojeux.lettrechar}"></c:out></td>
										
											<td><c:out value="${solo.abcsolojeux.help}"></c:out></td>
										</tr>
									</c:forEach>
							</table>
					</div>
				</div>
			</div>
			<div class="panel panel-default " style="margin-top: 15px;"> 
				<div class="panel-heading" style="text-align: center;">
			    	<a href="#cha" data-toggle="collapse">Vos Jeux CHALLENGE</a><br>
			                	les 10 derniers
			    </div>
			       <div id="cha" class="panel-collapse collapse">
			         <div class="panel-body">
							<table class="table table-bordered table-striped table-condensed ">
								<!-- <thead> -->
									<tr>
										<th>Date</th>
									
										<th>Score</th>
									
										<th>Lettre</th>
									
										<th>Aide</th>
									</tr>
								
									<c:forEach items="${mesChallenges}" var="cha" begin="0" end="${tailleChallenges }">
										<tr>
											<td><c:out value="${cha.date}"></c:out></td>
										
											<td><c:out value="${cha.score}"></c:out>/<c:out value="${cha.scoreMax}"></c:out></td>
										
											<td><c:out value="${cha.lettre}"></c:out></td>
										
											<td><c:out value="${cha.help}"></c:out></td>
										</tr>
									</c:forEach>
							</table>
					</div>
				</div>
			</div>		
		</div>
			
					
	</body>				
</html>