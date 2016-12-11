<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Language School Course</title>
<%@ include file="../common/head.jsp"%>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<table class="table">
		<thead>
			<tr>
				<th>name</th>
				<th>language</th>
				<th>proficiency level</th>
			</tr>
		</thead>

		<tbody>
			<tr>
				<td>${course.id}</td>
				<td><c:out value="${course.name}" /></td>
				<td><c:out value="${course.language}" /></td>
				<td><c:out value="${course.proficiencyLevel}" /></td>
			</tr>
		</tbody>
	</table>

	<%@ include file="../common/footer.jsp"%>
</body>
</html>