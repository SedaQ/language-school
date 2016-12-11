<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!--header-->
<div class="header">
	<nav class="navbar navbar-default">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"> </span> <span class="icon-bar"> </span> <span
					class="icon-bar"> </span>
			</button>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right wow fadeInLeft animated"
				data-wow-delay=".5s">
				<li><a href="${pageContext.request.contextPath}/index"
					class="active">Home</a></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown"
					href="${pageContext.request.contextPath}/school">Language
						School<span class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a href="${pageContext.request.contextPath}/course/list">Courses</a></li>
						<li><a href="${pageContext.request.contextPath}/lecturer/list">Lecturers</a></li>
						<li><a href="${pageContext.request.contextPath}/student/list">Students</a></li>
					</ul></li>
				<li><a href="${pageContext.request.contextPath}/about">About</a></li>
				<li><a href="${pageContext.request.contextPath}/contact">Contact</a></li>
			</ul>
		</div>
	</nav>

	<div class="logo">
		<a class="navbar-brand"
			href="${pageContext.request.contextPath}/index">LS PA165</a>
	</div>

	<div class="search-bar wow fadeInRight animated" data-wow-delay=".5s">
		<form action="#" method="post">
			<input type="text" name="search" value="Search"
				onfocus="this.value = '';"
				onblur="if (this.value == '') {this.value = 'Search';}"> <input
				type="submit" value="">
		</form>
	</div>
	<div class="clearfix"></div>
</div>
<!--//header-->
