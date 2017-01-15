<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs"
	trimDirectiveWhitespaces="true"%>
<%@ attribute name="title" required="false"%>
<%@ attribute name="subtitle" required="false"%>
<%@ attribute name="head" fragment="true"%>
<%@ attribute name="body" fragment="true" required="true"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
<!--  head starts -->
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><c:out value="${title}" /></title>
	
	<link href="<c:url value="/assets/bootstrap/css/bootstrap.css"/>"
		type="text/css" rel="stylesheet" media="all">
	<!-- Custom Theme files -->
	
	<meta name="keywords"
		content="Mentors Responsive Web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible Web template, 
			Smartphone Compatible Web template, free Webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola Web design" />
	
	<!--fonts-->
	<link href='//fonts.googleapis.com/css?family=Julius+Sans+One'
		rel='stylesheet' type='text/css'>
	<link href='//fonts.googleapis.com/css?family=Candal' rel='stylesheet'
		type='text/css'>
	<link href='//fonts.googleapis.com/css?family=Roboto+Slab'
		rel='stylesheet' type='text/css'>
	
	<link rel="stylesheet"
		href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
	<!--/fonts-->
	<!--//end-animate-->
	<jsp:invoke fragment="head" />
</head>
<!-- head ends -->

<body>
	<!--header-->
	<div class="header">
		<nav class="navbar navbar-inverse">
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand"
						href="${pageContext.request.contextPath}/home">Language School</a>
				</div>
				<ul class="nav navbar-nav">
					<li class="active"><a
						href="${pageContext.request.contextPath}/home">Home</a></li>
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">Language School <span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="${pageContext.request.contextPath}/course/list">Courses</a></li>
							<li><a
								href="${pageContext.request.contextPath}/lecturer/list">Lecturers</a></li>
							<li><a
								href="${pageContext.request.contextPath}/student/list">Students</a></li>
							<li><a
								href="${pageContext.request.contextPath}/lecture/list">Lectures</a></li>
						</ul></li>
					<li><a href="${pageContext.request.contextPath}/about">About</a></li>
					<li><a href="${pageContext.request.contextPath}/contact">Contact</a></li>
				</ul>
				<!-- 
			<form class="navbar-form navbar-left">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Search">
					<div class="input-group-btn">
						<button class="btn btn-default" type="submit">
							<i class="glyphicon glyphicon-search"></i>
						</button>
					</div>
				</div>
			</form>
			 -->

				<ul class="nav navbar-nav navbar-right">
					<li><a href="${pageContext.request.contextPath}/logout"><span
							class="glyphicon glyphicon-log-in"> Logout</span></a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="${pageContext.request.contextPath}/userDetail">
							<sec:authorize var="loggedIn" access="isAuthenticated()">
								<c:choose>
									<c:when test="${loggedIn}">
										<%=request.getUserPrincipal().getName()%>
									</c:when>
								</c:choose>
							</sec:authorize>
					</a></li>
				</ul>
			</div>
		</nav>

	</div>
	<!--//header-->
	
<div class="container">

<!-- 
	<div>
         <c:if test="${not empty title}">
            <h1><c:out value="${title}"/>
                &nbsp;
                <small><c:out value="${subtitle}" /></small>
            </h1>
        </c:if>
	</div>
 -->
	<!-- page body -->
	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<!-- page body -->
			<jsp:invoke fragment="body" />
		</div>
	</div>

</div>

	<script type="application/x-javascript">
		 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
	</script>
	<!-- //Custom Theme files -->

	<!-- js -->
	<script src="<c:url value="/assets/bootstrap/js/bootstrap.js"/>"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="<c:url value="/assets/js/jquery-1.11.1.min.js"/>"></script>
	<!-- <script src="<c:url value="/assets/bootstrap/js/bootstrap.min.js"/>"></script> -->
	<script src="<c:url value="/assets/js/jquery.backstretch.min.js"/>"></script>
	<script src="<c:url value="/assets/js/scripts.js"/>"></script>

</body>
</html>