<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>

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
				<td>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<a
						href="${pageContext.request.contextPath}/lecturer/edit/${lecturer.id}"
						class="btn btn-primary">Edit</a>
					<a
						href="${pageContext.request.contextPath}/lecturer/delete/${lecturer.id}"
						class="btn btn-primary">Delete</a>
				</sec:authorize></td>
			</tr>
		</tbody>
	</table>
        <table class="table">
		<thead>
			<tr>
				<th>id</th>
				<th>language</th>
				<th>proficiency level</th>
			</tr>
		</thead>
                <tbody>
                    <c:forEach items="${lecturer.listOfLanguages}" var="language">
			<tr>
				<td>${language.id}</td>
				<td><c:out value="${language.language}" /></td>
				<td><c:out value="${language.proficiencyLevel}" /></td>
                                <td>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <a
                                        href="${pageContext.request.contextPath}/language/edit/${language.id}"
                                        class="btn btn-primary">Edit</a>
                                <a
                                        href="${pageContext.request.contextPath}/language/delete/${language.id}"
                                        class="btn btn-primary">Delete</a>
				</sec:authorize>
                                </td>
			</tr>
                    </c:forEach>
		</tbody>
        </table>
	
		
	<!-- 
	Lecturers lectures:
	<table class="table table-striped">
		<tbody>
			<c:forEach items="${lectures}" var="lecture">
				<tr>
					<td><c:out value="${lecture.id}" /></td>
					<td><c:out value="${courseLecturer.dayTime}" /></td>
					<td><c:out value="${courseLecturer.classroomId}" /></td>
					<td><c:out value="${courseLecturer.topic}" /></td>
					<td><my:a href="/lecturer/view/${lecture.id}"
							class="btn btn-primary">view</my:a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	 -->

	<%@ include file="../common/footer.jsp"%>
</body>
</html>