<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Your description here">
    <meta name="author" content="Your Name">
    <title>Inscription</title>

    <!-- Bootstrap CSS -->
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/resources/css/basic-template.css" rel="stylesheet" />

	<!-- BootstrapValidator CSS -->
    <link href="<%=request.getContextPath()%>/resources/css/bootstrapValidator.min.css" rel="stylesheet"/>
	
    <!-- jQuery and Bootstrap JS -->
	<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js" type="text/javascript"></script>
		
	<!-- BootstrapValidator -->
    <script src="<%=request.getContextPath()%>/resources/js/bootstrapValidator.min.js" type="text/javascript"></script>
		
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link href="<%=request.getContextPath()%>/resources/css/basic-template.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/resources/css/animate.min.css" rel="stylesheet"> 
  <link href="<%=request.getContextPath()%>/resources/css/font-awesome.min.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/resources/css/lightbox.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/resources/css/main.css" rel="stylesheet">
  <link id="css-preset" href="<%=request.getContextPath()%>/resources/css/presets/preset1.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/resources/css/responsive.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/entete.css" >
</head>
<body>
<div class="main-nav">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span> 
          </button>
          <a class="navbar-brand" href="accueil">
            <h1><img class="img-responsive" src="<%=request.getContextPath()%>/resources/images/logo.png" alt="logo"></h1>
          </a>                    
        </div>
        <div class="collapse navbar-collapse">
          <f:form class="navbar-form navbar-right inline-form" role="form" action="loginUser" modelAttribute="sm" method="post">
		            <div class="form-group">
		              <input type="text" class="input-sm form-control" placeholder="email" name="userid" size="5">
		               <input type="password" class="input-sm form-control" placeholder="mot de passe" name="password" size="5">
		              <input type="submit" class="btn btn-primary btn-sm" value="Login" style="font-weight: bold;">	              
		            </div>
		   </f:form>	
          <ul class="nav navbar-nav navbar-right">
		    <!-- <li><a href="#formulaireInscription" data-toggle="modal" data-backdrop="false"><span class="glyphicon glyphicon-user inscription"></span> Inscription</a></li> -->
		    <li><a data-toggle="modal" data-backdrop="false" class="btn" data-target="#formulaireInscription"><span class="glyphicon glyphicon-user inscription"></span> Inscription</a></li>
		  </ul> 	
          <ul class="nav navbar-nav navbar-right">                 
            <li class="scroll active"><a href="#home">Home</a></li>
            <li class="scroll"><a href="#services">Service</a></li> 
            <li class="scroll"><a href="#about-us">About Us</a></li> 
            <li class="scroll"><a href="testInscription">testInscription</a></li>                     
            <!-- <li class="scroll"><a href="#portfolio">Portfolio</a></li> -->
           <!--  <li class="scroll"><a href="#team">Team</a></li> -->
            <!-- <li class="scroll"><a href="#blog">Blog</a></li> 
            <li class="scroll"><a href="#contact">Contact</a></li> -->       
          </ul>
          
          	
        </div>
        
      </div>
    </div><!--/#main-nav-->
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">Inscription</div>
			<div class="panel-body">
				<f:form id="registration-form" method="post" class="form-horizontal" action="testInscription" modelAttribute="inscription">
					<div class="form-group">
						<label class="col-md-2 control-label" for="nom">Nom</label>
						<div class="col-md-4">
							<f:input type="text" class="form-control" id="nom" name="nom" placeholder="Votre nom" path="nom"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label" for="prenom">Prenom</label>
						<div class="col-md-4">
							<f:input type="text" class="form-control" id="prenom" name="prenom" placeholder="Votre prenom" path="prenom" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label" for="email">Addresse Email </label>
						<div class="col-md-4">
							<f:input type="text" class="form-control" id="email" name="email" placeholder="Votre addresse email" path="email"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label" for="password">Mot de Passe</label>
						<div class="col-md-4">
							<f:input type="password" class="form-control" id="password" name="password" placeholder="Mot de passe" path="password"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label" for="confirmpassword">Confirmez MDP</label>
						<div class="col-md-4">
							<input type="password" class="form-control" id="confirmpassword" name="confirmpassword" placeholder="Confirmez MDP" />	
						</div>
					</div>					
					<!-- <div class="form-group">
						<label class="col-md-2 control-label" for="membership">Membership</label>
						<div class="col-md-4">
							<select class="form-control" id="membership" name="membership">
								<option value="0">Choose One</option>
								<option value="1">Basic (Free)</option>
								<option value="2">Premium (Paid)</option>
							</select>
						</div>
					</div> -->
					<div class="form-group">
						<div class="col-md-6 col-md-offset-2">
							<button type="submit" class="btn btn-success">Inscription</button>
							<!-- <input type="submit" class="btn btn-success" value="inscriptionSubmit"/> -->
						</div>
					</div>
				</f:form>
				<!-- <div id="confirmation" class="alert alert-success hidden">
					<span class="glyphicon glyphicon-star"></span> Thank you for registering
				</div> -->
			</div>
		</div>
	</div>
	  <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrapValidator.min.js"></script>   
  <%-- <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>  --%>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>  
  
    <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script> 
  
  
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.inview.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/wow.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/mousescroll.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/smoothscroll.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.countTo.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/lightbox.min.js"></script> 
  
   <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/main.js"></script> 
  <%-- <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/html5shiv.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/respond.min.js"></script> --%> 
  
  <%-- <script src="<%=request.getContextPath()%>/resources/js/entete.js"></script> --%> 
</body>
<script type="text/javascript">
	$(document).ready(function () {
		var validator = $("#registration-form").bootstrapValidator({
			feedbackIcons: {
				valid: "glyphicon glyphicon-ok",
				invalid: "glyphicon glyphicon-remove", 
				validating: "glyphicon glyphicon-refresh"
			}, 
			fields : {
				email :{
					message : "Une adresse email est nécessaire",
					validators : {
						notEmpty : {
							message : "SVP Entrez une adresse email"
						}, 
						stringLength: {
							min : 6, 
							max: 35,
							message: "Une addresse email doit avoir entre 6 et 35 caractères "
						},
						emailAddress: {
							message: "Elle doit etre valide, de type: abc@def.gh"
						}
					}
				}, 
				password : {
					validators: {
						notEmpty : {
							message : "un mot de passe est nécessaire"
						},
						stringLength : {
							min: 8,
							message: "un mot de passe doit avoir au moins 8 caractères"
						}, 
						different : {
							field : "email", 
							message: "l'adresse email et le mot de passe ne peuvent pas etre similaires"
						}
					}
				}, 
				confirmpassword : {
					validators: {
						notEmpty : {
							message: "Confirmation du mot de passe"
						}, 
						identical : {
							field: "password", 
							message : "les mots de passe doivent être les mêmes"
						}
					}
				}, 
				prenom : {
					message : "Un nom est nécessaire",
					validators : {
						notEmpty :{
							message : "un prenom est nécessaire"
						},
						stringLength : {
							min : 3,
							message : "le prenom doit avoir au moins trois caractères"
						}
					}
				},
				nom : {
					message : "Un prenom est nécessaire",
					validators : {
						notEmpty :{
							message : "un nom est nécessaire"
						},
						stringLength : {
							min : 3,
							message : "le nom doit avoir au moins trois caractères"
						}
					}
				}
			}
		});
	
		/* validator.on("success.form.bv", function (e) {
			e.preventDefault();
			$("#registration-form").addClass("hidden");
			$("#confirmation").removeClass("hidden");
		});
 */		
	});
</script>
</html>