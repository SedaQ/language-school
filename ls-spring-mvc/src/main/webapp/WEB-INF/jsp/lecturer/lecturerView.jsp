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
			</tr>
		</tbody>
	</table>

	<%@ include file="../common/footer.jsp"%>
</body>
</html>