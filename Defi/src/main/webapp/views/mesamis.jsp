<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://cdn.datatables.net/1.10.15/css/dataTables.bootstrap.min.css" rel="stylesheet">

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
	td, th {
	text-align: center;
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
					
				<c:choose>
					<c:when test=" ${empty amisJeuxModel }">
						<h2 style="text-align: center; color: white; text-shadow: 2px 2px 4px #000000;  ">VOUS N'AVEZ AUCUN AMI POUR L'INSTANT</h2>
					</c:when>
					<c:otherwise>
						<div>
							<h2 style="text-align: center; color: white; text-shadow: 2px 2px 4px #000000;  ">VOS AMIS ET LES CHALLENGES</h2>
							<table id="tableauDataTable" class="table table-bordered table-striped table-condensed" >
								<thead>
									<tr class="success">
										<th style="text-align: center;">AMIS</th>
										<th>ABC Challenge</th>
										<th>DICO Challenge</th>
										<th>SUJETS Challenge</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${amisJeuxModel }" var="ami">
										<tr>
											<td><a href="voirAmi?id=${ami.id }"><c:out value="${ami.nom }" /> &nbsp; <c:out value="${ami.prenom }" /></a></td>
											<td>
												<c:choose>
													<c:when test="${ami.abcChallenger ==true }">
														<button onclick="challengerABC(this, ${ami.id})" class="btn btn-primary" data-toggle="tooltip" title="envoyez lui une demande">challenger</button>
													</c:when>
													<c:when test="${ami.abcRefuser ==true }">
														<button onclick="accepterABC(this, ${ami.id})" class="btn btn-success btn-xs" data-toggle="tooltip" title="accepter la demande">Accepter</button>
														<button onclick="refuserABC(this, ${ami.id})" class="btn btn-danger btn-xs" data-toggle="tooltip" title="refuser la demande">Refuser</button>
													</c:when>
													<c:when test="${ami.abcAnnuler ==true }">
														<button onclick="annulerEnvoiABC(this, ${ami.id})" class="btn btn-warning" data-toggle="tooltip" title="annulez la demande envoyée">Annuler</button>
													</c:when>
													<c:otherwise>
														<button onclick="jouerABC(this, ${ami.id})" class="btn btn-info" data-toggle="tooltip" title="aller au duel contre lui">jouer</button>
													</c:otherwise>
												</c:choose>
											</td>
											<td>
												<c:choose>
													<c:when test="${ami.dicoChallenger ==true }">
														<button onclick="challengerDICO(this, ${ami.id})" class="btn btn-primary" data-toggle="tooltip" title="envoyez lui une demande">challenger</button>
													</c:when>
													<c:when test="${ami.dicoRefuser ==true }">
														<button onclick="accepterDICO(this, ${ami.id})" class="btn btn-success btn-xs" data-toggle="tooltip" title="accepter la demande">Accepter</button>
														<button onclick="refuserDICO(this, ${ami.id})" class="btn btn-danger btn-xs" data-toggle="tooltip" title="refuser la demande">Refuser</button>
													</c:when>
													<c:when test="${ami.dicoAnnuler ==true }">
														<button onclick="annulerEnvoiDICO(this, ${ami.id})" class="btn btn-warning" data-toggle="tooltip" title="annuler la demande envoyée">Annuler</button>
													</c:when>
													<c:otherwise>
														<button onclick="jouerDICO(this, ${ami.id})" class="btn btn-info" data-toggle="tooltip" title="aller au duel contre lui">jouer</button>
													</c:otherwise>
												</c:choose>
											</td>
											<td>
												<c:choose>
													<c:when test="${ami.sujetsChallenger ==true }">
														<button onclick="challengerSUJETS(this, ${ami.id})" class="btn btn-primary" data-toggle="tooltip" title="envoyez lui une demande">challenger</button>
													</c:when>
													<c:when test="${ami.sujetsRefuser ==true }">
														<button onclick="accepterSUJETS(this, ${ami.id})" class="btn btn-success btn-xs" data-toggle="tooltip" title="accepter la demande">Accepter</button>
														<button onclick="refuserSUJETS(this, ${ami.id})" class="btn btn-danger btn-xs" data-toggle="tooltip" title="refuser la demande">Refuser</button>
													</c:when>
													<c:when test="${ami.sujetsAnnuler ==true }">
														<button onclick="annulerEnvoiSUJETS(this, ${ami.id})" class="btn btn-warning" data-toggle="tooltip" title="annuler la demande envoyée">Annuler</button>
													</c:when>
													<c:otherwise>
														<button onclick="jouerSUJETS(this, ${ami.id})" class="btn btn-info" data-toggle="tooltip" title="aller au duel contre lui">jouer</button>
													</c:otherwise>
												</c:choose>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</c:otherwise>
				</c:choose>
				
				
			</div></div>
			<div class="col-md-3 col-lg-3">
				<div>
					<jsp:include page="menuDroit.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
	    $('#tableauDataTable').DataTable({
	    	"language": {
	    		"sProcessing": "Traitement en cours...",
	    		"sSearch": "Rechercher&nbsp;:",
	    		"sLengthMenu": "Afficher _MENU_ &eacute;l&eacute;ments",
	    		"sInfo": "Affichage de l'&eacute;l&eacute;ment _START_ &agrave; _END_ sur _TOTAL_ &eacute;l&eacute;ments",
	    		"sInfoEmpty": "Affichage de l'&eacute;l&eacute;ment 0 &agrave; 0 sur 0 &eacute;l&eacute;ment",
	    		"sInfoFiltered": "(filtr&eacute; de _MAX_ &eacute;l&eacute;ments au total)",
	    		"sInfoPostFix": "",
	    		"sLoadingRecords": "Chargement en cours...",
	    		"sZeroRecords": "Aucun &eacute;l&eacute;ment &agrave; afficher",
	    		"sEmptyTable": "Aucune donn&eacute;e disponible dans le tableau",
	    		"oPaginate": {
	    		"sFirst": "Premier",
	    		"sPrevious": "Pr&eacute;c&eacute;dent",
	    		"sNext": "Suivant",
	    		"sLast": "Dernier"
	    		},
	    		"oAria": {
	    		"sSortAscending": ": activer pour trier la colonne par ordre croissant",
	    		"sSortDescending": ": activer pour trier la colonne par ordre d&eacute;croissant"
	    		}
	    		}
	    });
	    
	} );
</script>
<script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/1.10.15/js/dataTables.bootstrap.min.js" type="text/javascript"></script>
</body>
</html>