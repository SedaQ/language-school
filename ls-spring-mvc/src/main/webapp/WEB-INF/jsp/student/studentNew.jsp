<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
<!DOCTYPE html>
<html>
<head>
<title>Language school Student</title>
<%@ include file="../common/head.jsp"%>
</head>
<body>
	<%@ include file="../common/header.jsp"%>

	<div class="container center_div">
		<form:form method="post"
			action="${pageContext.request.contextPath}/student/create"
			modelAttribute="studentCreate" cssClass="form-horizontal">
			<div>
				<label path="firstName">First name</label>
				<form:input path="firstName" />
			</div>
			<div>
				<label path="surname">Surname</label>
				<form:input path="surname" />
			</div>
			<div>
				<label path="birthNumber">Birth number</label>
				<form:input path="birthNumber" />
			</div>
			<button class="btn btn-primary" type="submit">Create course</button>
		</form:form>
	</div>

	<%@ include file="../common/footer.jsp"%>
</body>
</html>
