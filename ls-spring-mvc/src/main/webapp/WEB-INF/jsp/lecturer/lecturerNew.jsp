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

	<div class="container center_div">
		<form:form method="post"
			action="${pageContext.request.contextPath}/lecturer/create"
			modelAttribute="lecturerCreate" cssClass="form-horizontal">
			<div>
				<label path="email">E-mail</label>
				<form:input path="email" />
			</div>
			<div>
				<label path="passwordHash">Password</label>
				<form:input path="passwordHash" />
			</div>
			<div>
				<label path="nickname">Nickname</label>
				<form:input path="nickname" />
			</div>
			<div>
				<label path="firstName">First name</label>
				<form:input path="firstName" />
			</div>
			<div>
				<label path="surname">Surname</label>
				<form:input path="surname" />
			</div>
			<button class="btn btn-primary" type="submit">Create
				lecturer</button>
		</form:form>
	</div>

	<%@ include file="../common/footer.jsp"%>
</body>
</html>
