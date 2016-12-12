<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Language School lecture</title>
<%@ include file="../common/head.jsp"%>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<table class="table">
		<thead>
			<tr>
				<th>id</th>
				<th>nickname</th>
				<th>first Name</th>
				<th>surname</th>
			</tr>
		</thead>

		<tbody>
			<tr>
				<td>${lecturer.id}</td>
				<td><c:out value="${lecturer.nickname}" /></td>
				<td><c:out value="${lecturer.firstName}" /></td>
				<td><c:out value="${lecturer.surname}" /></td>
				<td><a
					href="${pageContext.request.contextPath}/lecturer/edit/${lecturer.id}"
					class="btn btn-primary">Edit</a> <a
					href="${pageContext.request.contextPath}/lecturer/delete/${lecturer.id}"
					class="btn btn-primary">Delete</a></td>
			</tr>
		</tbody>
	</table>

	<%@ include file="../common/footer.jsp"%>
</body>
</html>