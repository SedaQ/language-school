<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<title>Language School lecture</title>
<%@ include file="../common/head.jsp"%>
</head>
<body>
	<%@ include file="../common/header.jsp"%>

	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<h1 class="page-header">
				Edit Lecturer <small></small>
			</h1>
			<div class="container center_div">
				<form:form class="table table-striped" method="post"
					action="${pageContext.request.contextPath}/lecturer/update/${lecturer.id}"
					modelAttribute="lecturer">
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
					<div>
						<form:input type="hidden" path="userRole" />
						<form:input type="hidden" path="passwordHash" />
						<form:input type="hidden" path="email" />
					</div>
					<button class="btn btn-primary" type="submit">Update
						lecturer</button>
				</form:form>
			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>
