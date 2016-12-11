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
                                <th>id</th>
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
        <my:a href="${pageContext.request.contextPath}/course/edit/${course.id}" class="btn btn-primary">Edit</my:a>
        <my:a href="${pageContext.request.contextPath}/course/delete/${course.id}" class="btn btn-primary">Delete</my:a>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>