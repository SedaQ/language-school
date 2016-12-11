<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>

<!DOCTYPE html>
<html>
<head>
<title>Language School courses</title>
<%@ include file="../common/head.jsp"%>
</head>
<body>
	<%@ include file="../common/header.jsp"%>

	<table class="table table-striped">
		<thead>
			<tr>
				<th>id</th>
				<th>name</th>
				<th>language</th>
				<th>proficiency level</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${courses}" var="course">
				<tr>
					<td><c:out value="${course.id}" /></td>
					<td><c:out value="${course.name}" /></td>
					<td><c:out value="${course.language}" /></td>
					<td><c:out value="${course.proficiencyLevel}" /></td>
					<td><my:a href="/course/view/${course.id}"
						class="btn btn-primary">view</my:a></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
        <my:a href="/course/new/" class="btn btn-primary">new</my:a>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>