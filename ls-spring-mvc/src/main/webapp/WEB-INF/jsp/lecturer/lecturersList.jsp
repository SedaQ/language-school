<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Language School Lecturers</title>
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
			<c:forEach items="${lecturers}" var="lecturer">
				<tr>
					<td><c:out value="${lecturer.nickname}" /></td>
					<td><c:out value="${lecturer.firstName}" /></td>
					<td><c:out value="${lecturer.surname}" /></td>
					<td><a href="/lecturer/view/${lecturer.id}"
						class="btn btn-primary">view</a></td>
				</tr>
			</c:forEach>
		</tbody>
		 
	</table>


	<%@ include file="../common/footer.jsp"%>
</body>
</html>