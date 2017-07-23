<!-- <!DOCTYPE html> -->
<%@ page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
.jeuxPublies{
	border: dotted;
	border-color: green;
	margin: 10px;
	padding: 10px;
}
.post{
	margin: 10px;
	padding: 10px;
	border: aqua;
	background-color: #CCCCFF;
}

</style>
</head>
	<body>
		<strong style="margin: 10px; padding: 10px;">Exprimez vous librement</strong><br><br>
					<f:form method="post" modelAttribute="sm" action="posterPost" enctype="UTF-8" acceptCharset="UTF-8">
						<f:textarea rows="4" cols="65"  path="messagePost" name="messagePost"  placeholder="Quoi de nouveau aujourd'hui encore ..., n'hésitez à vous exprimer et de dire tout ce qui peut amuser vos amis "/>
						<input type="submit" value="Publier">
					</f:form ><br>
					<div class="post" style="text-align: left;"><strong>Les posts publiés par les membres</strong></div>
					<div class="jeuxPublies" style="text-align: left;"><strong>Les jeux publiés par les membres</strong></div><br>
					<div> 
						<c:forEach items="${sm.posts }" var="sm">
							<c:choose>
								<c:when test="${sm.jeu == true}">
									<div class="jeuxPublies">
										<p><img alt="" src="photoUser?id=${sm.friendpost.id }" height="30px" width="30px">&nbsp;${sm.friendpost.nom}</p>
										<p> ${sm.message }</p>
										<p> ${sm.date }</p>
										 <!-- si j'ai pas encore aimé ce poste on affiche "aimer", sinon on affiche "ne plus aimer" -->
										 	<%-- <c:if test="${moi.posts }"></c:if> --%>
											<a href="aimerPost?id=${sm.id }">aimer</a> &nbsp;&nbsp;
											<a href="neplusAimerPost?id=${sm.id }"> ne plus aimer</a><br>
											<a href="quiAimePost?id=${sm.id }">${sm.nbLikesPost} personnes aimment ça</a>
											<f:form modelAttribute="sm" action="commenterPost">
												<f:textarea path="commentaire"  placeholder=" votre commentaire ..." />
												<f:input path="idPost" type="hidden" value="${sm.id }" />
												<input type="submit" value="commenter">
											</f:form>	
											<p id="idcommentaire"></p><br>
												<c:forEach items="${sm.comments }" var="com">
													<p><strong> ${com.friends }</strong></p>
													<strong>le commentaire: ${com.commentaire }</strong><br><br><hr>
												</c:forEach>
									</div>
								</c:when>
								<c:otherwise>
									<div class="post">
										<p><img alt="" src="photoUser?id=${sm.friendpost.id }" height="30px" width="30px">&nbsp;<strong>${sm.friendpost.prenom}&nbsp;${sm.friendpost.nom}</strong> </p>
										<p><strong>${sm.message }</strong> </p>
										<p> ${sm.date }</p>
											<a href="aimerPost?id=${sm.id }">aimer</a>&nbsp;&nbsp;
											<a href="neplusAimerPost?id=${sm.id }"> ne plus aimer</a><br>
											<a href="quiAimePost?id=${sm.id }">${sm.nbLikesPost} personnes aimment ça</a><br><hr>
											<c:forEach items="${sm.comments }" var="com">
													<c:forEach items="${com.friends }" var="fr">
														<p><img alt="" src="photoUser?id=${fr.id }" height="30px" width="30px">  &nbsp; <strong>${fr.nom }&nbsp; ${fr.prenom }</strong></p>
													</c:forEach>
													<p> &nbsp; &nbsp; &nbsp; ${com.commentaire }</p><br>
												</c:forEach>
											<f:form modelAttribute="sm" action="commenterPost">
												<f:textarea path="commentaire"  placeholder=" votre commentaire ..." />
												<f:input path="idPost" type="hidden" value="${sm.id }" />
												<input type="submit" value="commenter">
											</f:form>	
											<p id="idcommentaire"></p><br>
									</div>	
								</c:otherwise>	
							</c:choose>			
						</c:forEach>
					</div>
					<div>
						<p><a href="penduSoloDico">testRest</a> </p>
					</div>	
	</body>
</html>