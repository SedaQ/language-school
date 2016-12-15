<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<title>Language School Lecture</title>
<%@ include file="../common/head.jsp"%>
</head>
<body>
	<%@ include file="../common/header.jsp"%>

	<div class="container center_div">
		<form:form class="table table-striped" method="post"
			action="${pageContext.request.contextPath}/lecture/update/${lecture.id}"
			modelAttribute="course">

			<div>
				<label path="dayTime">DateTime</label>
				<form:input path="dayTime" />
			</div>
			<div>
				<label path="classroomId">Classroom id</label>
				<form:input path="classroomId" />
			</div>
			<div>
				<label path="topic">Topic</label>
				<form:input path="topic" />
			</div>
			<button class="btn btn-primary" type="submit">Update lecture</button>
		</form:form>
	</div>

	<%@ include file="../common/footer.jsp"%>
</body>
</html>
