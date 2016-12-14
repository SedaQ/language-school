<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
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
	<table class="table">
		<thead>
			<tr>
				<th>first name</th>
				<th>surname</th>
				<th>birth number</th>
			</tr>
		</thead>

		<tbody>
			<tr>
				<td>${student.id}</td>
				<td><c:out value="${student.firstName}" /></td>
				<td><c:out value="${student.surname}" /></td>
				<td><c:out value="${student.birthNumber}" /></td>
				<td><a
					href="${pageContext.request.contextPath}/student/edit/${student.id}"
					class="btn btn-primary">Edit</a> 
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<a
						href="${pageContext.request.contextPath}/student/delete/${student.id}"
						class="btn btn-primary">Delete</a>
				</sec:authorize></td>

			</tr>
		</tbody>
	</table>

	<%@ include file="../common/footer.jsp"%>
</body>
</html>