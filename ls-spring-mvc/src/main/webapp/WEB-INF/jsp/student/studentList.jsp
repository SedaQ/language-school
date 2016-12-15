<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<title>Language School students</title>
<%@ include file="../common/head.jsp"%>
</head>
<body>
	<%@ include file="../common/header.jsp"%>

	<table class="table table-striped">
		<thead>
			<tr>
				<th>id</th>
				<th>email</th>
				<th>first name</th>
				<th>surname</th>
				<th>birth number</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${students}" var="student">
				<tr>
					<td><c:out value="${student.id}" /></td>
					<td><c:out value="${student.email}" /></td>
					<td><c:out value="${student.firstName}" /></td>
					<td><c:out value="${student.surname}" /></td>
					<td><c:out value="${student.birthNumber}" /></td>
					<td><my:a href="/student/view/${student.id}"
							class="btn btn-primary">view</my:a></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<my:a href="/student/new/" class="btn btn-primary">new</my:a>
	</sec:authorize>

	<%@ include file="../common/footer.jsp"%>
</body>
</html>