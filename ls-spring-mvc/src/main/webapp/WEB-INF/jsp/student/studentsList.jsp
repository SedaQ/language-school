<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Language School students</title>
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
			<c:forEach items="${students}" var="student">
				<tr>
					<td><c:out value="${student.firstName}" /></td>
					<td><c:out value="${student.surname}" /></td>
					<td><c:out value="${student.birthNumber}" /></td>
					<td><a href="/student/view/${student.id}"
						class="btn btn-primary">view</a></td>
				</tr>
			</c:forEach>
		</tbody>
		 
	</table>


	<%@ include file="../common/footer.jsp"%>
</body>
</html>