<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Language School</title>

<!-- CSS -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
<link rel="stylesheet"
	href="<c:url value="/assets/bootstrap/css/bootstrap.min.css"/>">
<link rel="stylesheet"
	href="<c:url value="/assets/font-awesome/css/font-awesome.min.css"/>">
<link rel="stylesheet"
	href="<c:url value="/assets/css/form-elements.css"/>">
<link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>">

<!-- Favicon and touch icons -->
<link rel="shortcut icon"
	href="<c:url value="/assets/ico/favicon.png"/>">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="<c:url value="/assets/ico/apple-touch-icon-144-precomposed.png"/>">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="<c:url value="/assets/ico/apple-touch-icon-114-precomposed.png"/>">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="<c:url value="/assets/ico/apple-touch-icon-72-precomposed.png"/>">
<link rel="apple-touch-icon-precomposed"
	href="<c:url value="/assets/ico/apple-touch-icon-57-precomposed.png"/>">

</head>

<body>

	<!-- Top content -->
	<div class="top-content">

		<div class="inner-bg">
			<div class="container">
				<div class="row">
					<div class="col-sm-8 col-sm-offset-2 text">
						<p></p>
						<h1>
							<strong>Language School</strong>
						</h1>
						<div class="description">
							<p></p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 form-box">
						<div class="form-top">
							<div class="form-top-left">
								<h3>
									<strong>Login to our site</strong>
								</h3>
								<p>
									<strong>Enter your username and password to log on:</strong>
								</p>
							</div>
							<div class="form-top-right">
								<i class="fa fa-lock"></i>
							</div>
						</div>
						<div class="form-bottom">
							<c:if test="${param.error != null}">
								<div class="alert alert-danger">
									<p>Invalid username and password.</p>
								</div>
							</c:if>
							<c:if test="${param.logout != null}">
								<div class="alert alert-success">
									<p>You have been logged out successfully.</p>
								</div>
							</c:if>
							<c:url value="/login" var="loginUrl" />
							<form role="form" action="${loginUrl}" method="post"
								class="login-form">
								<div class="form-group">
									<label class="sr-only" for="username">Username</label> <input
										type="text" name="username" placeholder="Username..."
										class="form-username form-control" id="form-username">
								</div>
								<div class="form-group">
									<label class="sr-only" for="password">Password</label> <input
										type="password" name="password" placeholder="Password..."
										class="form-password form-control" id="form-password">
								</div>
								<input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />
								<button type="submit" class="btn">Sign in!</button>
							</form>
						</div>
					</div>
				</div>

			</div>
		</div>

	</div>


	<!-- Javascript -->
	<script src="<c:url value="/assets/js/jquery-1.11.1.min.js"/>"></script>
	<script src="<c:url value="/assets/bootstrap/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/assets/js/jquery.backstretch.min.js"/>"></script>
	<script src="<c:url value="/assets/js/scripts.js"/>"></script>
</body>

</html>