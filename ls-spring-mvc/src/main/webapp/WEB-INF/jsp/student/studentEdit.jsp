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

	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<h1 class="page-header">
				Edit Student <small></small>
			</h1>
			<div class="container center_div">
				<form:form class="table table-striped" method="post"
					action="${pageContext.request.contextPath}/student/update/${student.id}"
					modelAttribute="student">
					<label path="firstName">First name</label>
					<div>
						<form:input path="firstName" />
                                                <form:errors path="firstName" />
					</div>
					<label path="surname">Surname</label>
					<div>
						<form:input path="surname" />
                                                <form:errors path="surname" />
					</div>
					<label path="birthNumber">Birth number</label>
					<div>
						<form:input path="birthNumber" />
                                                <form:errors path="birthNumber" />
					</div>
					<div>
						<form:input type="hidden" path="userRole" />
						<form:input type="hidden" path="passwordHash" />
						<form:input type="hidden" path="email" />
					</div>
					<br />
					<button class="btn btn-primary" type="submit">Update
						student</button>
				</form:form>
			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>