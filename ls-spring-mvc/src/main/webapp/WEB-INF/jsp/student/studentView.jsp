<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<title>Language school Student</title>
<%@ include file="../common/head.jsp"%>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<h1 class="page-header">
				Student details <small></small>
			</h1>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>id</th>
						<th>email</th>
						<th>first name</th>
						<th>surname</th>
						<th>birth number</th>
						<th>action</th>
					</tr>
				</thead>

				<tbody>
					<tr>
						<td>${student.id}</td>
						<td><c:out value="${student.email}" /></td>
						<td><c:out value="${student.firstName}" /></td>
						<td><c:out value="${student.surname}" /></td>
						<td><c:out value="${student.birthNumber}" /></td>
						<td><sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_STUDENT')">
								<a
									href="${pageContext.request.contextPath}/student/edit/${student.id}"
									class="btn btn-primary">Edit</a>
							</sec:authorize></td>
							<sec:authorize access="hasRole('ROLE_ADMIN')">
								<a
									href="${pageContext.request.contextPath}/student/delete/${student.id}"
									class="btn btn-primary"
									onclick="return confirm('Are you sure you want to delete this student?')">Delete</a>
							</sec:authorize></td>							
					</tr>
				</tbody>
			</table>

			<h3>Student lectures</h3>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>id</th>
						<th>dayTime</th>
						<th>classroomId</th>
						<th>topic</th>
						<th>action</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${student.listOfLectures}" var="lecture">
						<tr>
							<td><c:out value="${lecture.id}" /></td>
							<td><c:out value="${lecture.dayTime}" /></td>
							<td><c:out value="${lecture.classroomId}" /></td>
							<td><c:out value="${lecture.topic}" /></td>
							<td><my:a href="/lecture/view/${lecture.id}"
									class="btn btn-primary">view</my:a></td>
							<td><sec:authorize access="hasRole('ROLE_STUDENT')">
									<c:if test="${loggedStudentId == student.id}">
										<a
											href="${pageContext.request.contextPath}/student/unenrollLecture/${lecture.id}"
											class="btn btn-primary">Unenroll</a>
									</c:if>
								</sec:authorize></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>