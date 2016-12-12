<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>

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
				<th>action</th>
			</tr>
		</thead>

		<tbody>
			<tr>
				<td>${course.id}</td>
				<td><c:out value="${course.name}" /></td>
				<td><c:out value="${course.language}" /></td>
				<td><c:out value="${course.proficiencyLevel}" /></td>
				<td><a
					href="${pageContext.request.contextPath}/course/edit/${course.id}"
					class="btn btn-primary">Edit</a> <a
					href="${pageContext.request.contextPath}/course/delete/${course.id}"
					class="btn btn-primary">Delete</a></td>
			</tr>
		</tbody>
	</table>


</body>
<%@ include file="../common/footer.jsp"%>
</html>