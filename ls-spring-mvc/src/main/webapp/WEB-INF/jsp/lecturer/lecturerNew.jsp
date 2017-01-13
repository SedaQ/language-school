<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<title>Language School lecturer</title>
<%@ include file="../common/head.jsp"%>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<h1 class="page-header">
				Create new Lecturer <small></small>
			</h1>
			<div class="container center_div">
				<form:form method="post"
					action="${pageContext.request.contextPath}/lecturer/create"
					modelAttribute="lecturerCreate" cssClass="form-horizontal">
					<label path="email">E-mail</label>
					<div>
						<form:input path="email" />
					</div>
					<label path="passwordHash">Password</label>
					<div>
						<form:input type="password" path="passwordHash" />
					</div>
					<label path="nickname">Nickname</label>s
					<div>
						<form:input path="nickname" />
					</div>
					<label path="firstName">First name</label>
					<div>
						<form:input path="firstName" />
					</div>
					<label path="surname">Surname</label>
					<div>
						<form:input path="surname" />
					</div>
					<br />
					<button class="btn btn-primary" type="submit">Create
						lecturer</button>
				</form:form>
			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>
