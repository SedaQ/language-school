<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<table class="table">
		<thead>
			<tr>
				<th>id</th>
				<th>dayTime</th>
				<th>classroomId</th>
				<th>topic</th>
			</tr>
		</thead>

		<tbody>
			<tr>
				<td>${lecture.id}</td>
				<td><c:out value="${lecture.dayTime}" /></td>
				<td><c:out value="${lecture.classroomId}" /></td>
				<td><c:out value="${lecture.topic}" /></td>
				<sec:authorize access="hasRole('ROLE_LECTURER')">
					<td><a
						href="${pageContext.request.contextPath}/lecture/edit/${lecture.id}"
						class="btn btn-primary">Edit</a> 
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<a
						href="${pageContext.request.contextPath}/lecture/delete/${lecture.id}"
						class="btn btn-primary">Delete</a>
				</sec:authorize>
			</td>
			</tr>
		</tbody>
	</table>

	<%@ include file="../common/footer.jsp"%>
</body>
</html>