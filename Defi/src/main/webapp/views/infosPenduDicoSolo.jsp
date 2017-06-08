<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PENDU SOLO</title>
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
		  <h2>LES DETAILS DE VOS JEUX EN DICO SOLO</h2>
		</div>
		<div id="infoPublicationSolo">
		</div>
			<div class="modal-body">
 				 <div>
						<table class="table table-bordered table-striped table-condensed">
							<thead>
								<tr>
									<th>Date</th>
									<th>Lettre</th>
									<th>Mot</th>
									<th>Temps Restant</th>
									<th>Score</th>
									<th>Score Max</th>
									<th>Aide</th>
									<th>NB Erreurs</th>
									<th>Etat</th>
									<th>Publier</th>
									<th>Supprimer</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${penduModel.mesDicoSolo }" var="solo">
									<tr>
										<td>${solo.dateString }</td>
										<td>${solo.lettreString }</td>
										<td>${solo.mot }</td>
										<td>${solo.tempsRestant } <em>secondes</em></td>
										<td>${solo.score }</td>
										<td>${solo.scoreMax }</td>
										<td>
											<c:choose>
												<c:when test="${solo.aide ==true }">
													OUI
												</c:when>
												<c:otherwise>
													NON
												</c:otherwise>
											</c:choose>	
										</td>
										<td>${solo.nbErreurs }</td>
										<td>
											<c:choose>
												<c:when test="${solo.publie ==true }">
													<em style="color: green;">Publié</em>
												</c:when>
												<c:otherwise>
													Non Publié
												</c:otherwise>
											</c:choose>	
										</td>
										<td>
											<button id="${solo.id}" onclick="publierUnSolo(this,${solo.id} )"  class="btn btn-primary" >
									        	<span class="glyphicon glyphicon-share"></span>
									     	 </button>
										<td><a href="#">Supprimer</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
					<script type="text/javascript">
						function publierUnSolo(lui, id){
							lui.disabled='true';
							var param = 'idSolo='+id+'';
						//	alert(id);
							$("#infoPublicationSolo").load("infoPublicationSolo", param);
						};
						</script>
	</body>
</html>