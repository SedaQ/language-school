<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
						<li><a href="${pageContext.request.contextPath}/student/list">Students</a></li>
						<li><a href="${pageContext.request.contextPath}/lecture/list">Lectures</a></li>
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
				<li><a href="${pageContext.request.contextPath}/userDetail"> <sec:authorize
							var="loggedIn" access="isAuthenticated()">
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
