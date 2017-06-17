<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PENDU DICO CHALLENGE</title>
<style type="text/css">
.modal-dialog {
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
}

.modal-content {
  height: auto;
  min-height: 100%;
  border-radius: 0;
}
.modal-body{
    height: 450px;
    overflow-y: auto;
    overflow-x: auto;
}
</style>
</head>
<body>
		<div class="modal-header">
		  <button type="button" class="close" data-dismiss="modal">&times;</button>
		  <h2>LES DETAILS JEU</h2>
		</div>
		<div id="infoPublicationChallenge">
		</div>
			<div class="modal-body">
 				 <div>
						<table class="table table-bordered table-striped table-condensed" style="text-align: center;">
								<thead>
									<tr>
										<th colspan="3">Le Jeu</th>
										<th colspan="3">Moi</th>
										<th colspan="4">Ami</th>
										<th rowspan="2">Publier</th>
									</tr>
									<tr>
										<th>Date</th>
										<th>Mot</th>
										<th>Lettre</th>
										<th>Score</th>
										<th>Temps Restant</th>
										<th>NB Err</th>
										<th>Nom</th>
										<th>Score</th>
										<th>Temps Restant</th>
										<th>NB Err</th>
										<!-- <th>Publier</th> -->
									</tr>
								</thead>
								<tbody>
									<%-- <c:forEach items="${challenge }" var="pendu"> --%>
										<tr>
											<td><c:out value="${challenge.dateString }"></c:out></td>
											<td><c:out value="${challenge.mot }"></c:out></td>
											<td><%-- <c:out value="${challenge.pendu.lettre }"></c:out> --%>???</td>
											<td><c:out value="${challenge.score }"></c:out>/<c:out value="${challenge.scoreMax }"></c:out></td>
											<td><c:out value="${challenge.mot }"></c:out></td>
											<td><c:out value="${challenge.mot }"></c:out></td>
											<td><a href="voirAmi?id=${challenge.monFriend.id }"><c:out value="${challenge.monFriend.nom }"></c:out></a></td>
											<td><c:out value="${challenge.scoreAmi }"></c:out>/<c:out value="${challenge.scoreMax }"></c:out></td>
											<td><c:out value="${challenge.mot }"></c:out></td>
											<td><c:out value="${challenge.mot }"></c:out></td>
											<td>
												<c:choose>
													<c:when test="${challenge.publie == true }">
														<button   class="btn btn-primary btn-success" >
											        		publié
											        	</button>
													</c:when>
													<c:otherwise>
														<button id="${challenge.id}" onclick="publierUnChallenge(this,${challenge.id} )"  class="btn btn-primary" >
											        		<span class="glyphicon glyphicon-share"></span>
											        	</button>
													</c:otherwise>
												</c:choose>
											</td>
										</tr>
									<%-- </c:forEach>	 --%>
								</tbody>
							</table>	
					</div>
				</div>
					<script type="text/javascript">
					function publierUnChallenge(lui, id){
						lui.disabled='true';
						var param = 'idChallenge='+id+'';
					//	alert(id);
						$("#infoPublicationChallenge").load("publierDicoChallengeEtInfos", param);
					};
						</script>
	</body>
</html>