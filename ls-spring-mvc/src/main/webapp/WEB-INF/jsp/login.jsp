<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Language School</title>

<!-- CSS -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/form-elements.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/style.css">

<!-- Favicon and touch icons -->
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/assets/ico/favicon.png">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="${pageContext.request.contextPath}/assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="${pageContext.request.contextPath}/assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="${pageContext.request.contextPath}/assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="${pageContext.request.contextPath}/assets/ico/apple-touch-icon-57-precomposed.png">
</head>

<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}
</style>

<body>

	<!-- Top content -->
	<div class="top-content">

		<div class="inner-bg">
			<div class="container">
				<div class="row">
					<div class="col-sm-8 col-sm-offset-2 text">
						<h1>
							<strong>Language School login</strong>
						</h1>
						<div class="description">
							<p>This is one of the best language schools in Czech Republic
								oriented on English and French languages.</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 form-box">
						<div class="form-top">
							<div class="form-top-left">
								<h3>Login to our site</h3>
								<p>Enter your username and password to log on:</p>
								
								<c:if test="${not empty error}">
									<div class="error">${error}</div>
								</c:if>
								
							</div>
							<div class="form-top-right">
								<i class="fa fa-lock"></i>
							</div>
						</div>
						<div class="form-bottom">
							<form role="form" action="" method="post" class="login-form">
								<div class="form-group">
									<label class="sr-only" for="form-username">Username</label> <input
										type="text" name="form-username" placeholder="Username..."
										class="form-username form-control" id="form-username">
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-password">Password</label> <input
										type="password" name="form-password" placeholder="Password..."
										class="form-password form-control" id="form-password">
								</div>
								<button type="submit" class="btn"
									href="${pageContext.request.contextPath}/login">Sign
									in!</button>
							</form>
						</div>
					</div>
				</div>
				<!-- Here could be later maybe loggin with facebook, google, ...
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 social-login">
						<h3>...or login with:</h3>
						<div class="social-login-buttons">
							<a class="btn btn-link-2" href="#"> <i class="fa fa-facebook"></i>
								Facebook
							</a> <a class="btn btn-link-2" href="#"> <i class="fa fa-twitter"></i>
								Twitter
							</a> <a class="btn btn-link-2" href="#"> <i
								class="fa fa-google-plus"></i> Google Plus
							</a>
						</div>
					</div>
				</div>
				 -->
			</div>
		</div>

	</div>


	<!-- Javascript -->
	<script
		src="${pageContext.request.contextPath}/assets/js/jquery-1.11.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/jquery.backstretch.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/scripts.js"></script>
</body>

</html>