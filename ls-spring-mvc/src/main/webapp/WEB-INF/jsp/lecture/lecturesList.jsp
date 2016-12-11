<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Language School Lectures</title>
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
			<c:forEach items="${lectures}" var="lecture">
				<tr>
					<td><c:out value="${lecture.id}" /></td>
					<td><c:out value="${lecture.dayTime}" /></td>
					<td><c:out value="${lecture.classroomId}" /></td>
					<td><c:out value="${lecture.topic}" /></td>
					<td><a href="/lecture/view/${lecture.id}"
						class="btn btn-primary">view</a></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>

	<%@ include file="../common/footer.jsp"%>
</body>
</html>