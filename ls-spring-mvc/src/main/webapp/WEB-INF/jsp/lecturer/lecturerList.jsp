<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
<!DOCTYPE html>
<html>
<head>
<title>Language School Lecturers</title>
<%@ include file="../common/head.jsp"%>
</head>
<body>
	<%@ include file="../common/header.jsp"%>

	<table class="table table-striped">
		<thead>
			<tr>
				<th>id</th>
				<th>email</th>
				<th>nickname</th>
				<th>first Name</th>
				<th>surname</th>
				<th>action</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${lecturers}" var="lecturer">
				<tr>
					<td><c:out value="${lecturer.id}" /></td>
					<td><c:out value="${lecturer.email}" /></td>
					<td><c:out value="${lecturer.nickname}" /></td>
					<td><c:out value="${lecturer.firstName}" /></td>
					<td><c:out value="${lecturer.surname}" /></td>
					<td><my:a href="/lecturer/view/${lecturer.id}"
							class="btn btn-primary">view</my:a></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <my:a href="/lecturer/new/" class="btn btn-primary">New lecture</my:a>
    </sec:authorize>

	<%@ include file="../common/footer.jsp"%>
</body>
</html>