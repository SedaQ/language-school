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

	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<h1 class="page-header">
				Lecturer details <small></small>
			</h1>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>id</th>
						<th>email</th>
						<th>nickname</th>
						<th>first Name</th>
						<th>surname</th>
					</tr>
				</thead>

				<tbody>
					<tr>
						<td>${lecturer.id}</td>
						<td><c:out value="${lecturer.email}" /></td>
						<td><c:out value="${lecturer.nickname}" /></td>
						<td><c:out value="${lecturer.firstName}" /></td>
						<td><c:out value="${lecturer.surname}" /></td>
						<td><sec:authorize access="hasRole('ROLE_ADMIN')">
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

			<h3>Lecturer languages</h3>
			<table class="table">
				<thead>
					<tr>
						<th>id</th>
						<th>language</th>
						<th>proficiency level</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${lecturerLanguages}" var="language">
						<tr>
							<td>${language.id}</td>
							<td><c:out value="${language.language}" /></td>
							<td><c:out value="${language.proficiencyLevel}" /></td>
							<td><sec:authorize access="hasRole('ROLE_ADMIN')">
									<a
										href="${pageContext.request.contextPath}/language/edit/${language.id}"
										class="btn btn-primary">Edit</a>
									<a
										href="${pageContext.request.contextPath}/language/delete/${language.id}"
										onclick="return confirm('Are you sure you want to delete lecturer language?')"
										class="btn btn-primary">Delete</a>
								</sec:authorize></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_LECTURER')">
				<my:a href="/language/new/${lecturer.id}" class="btn btn-primary">Add new language</my:a>
			</sec:authorize>

			<h3>Lecturer lectures</h3>
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
					<c:forEach items="${lecturerLectures}" var="lecture">
						<tr>
							<td><c:out value="${lecture.id}" /></td>
							<td><c:out value="${lecture.dayTime}" /></td>
							<td><c:out value="${lecture.classroomId}" /></td>
							<td><c:out value="${lecture.topic}" /></td>
							<td><my:a href="/lecture/view/${lecture.id}"
									class="btn btn-primary">view</my:a></td>
							<td><sec:authorize access="hasRole('ROLE_STUDENT')">
									<c:choose>
										<c:when test="${!studentEnrolledLectures.contains(lecture)}">
											<a
												href="${pageContext.request.contextPath}/student/enrollToLecture/${lecture.id}"
												class="btn btn-primary">Enroll to Lecture</a>
										</c:when>
										<c:otherwise>
											<a
												href="${pageContext.request.contextPath}/student/unenrollLecture/${lecture.id}"
												class="btn btn-primary">Unenroll</a>
										</c:otherwise>
									</c:choose>
								</sec:authorize></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_LECTURER')">
				<my:a href="/lecture/newLecturers/${lecturer.id}" class="btn btn-primary">Add new lecture</my:a>
			</sec:authorize>
		</div>
	</div>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>
