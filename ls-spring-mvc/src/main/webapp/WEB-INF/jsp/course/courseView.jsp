<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<title>Language School Course</title>
<%@ include file="../common/head.jsp"%>
</head>
<body>
	<%@ include file="../common/header.jsp"%>

	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<h1 class="page-header">
				Course details <small></small>
			</h1>
			<table class="table table-striped">
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
						<td><sec:authorize access="hasRole('ROLE_LECTURER')">
								<a
									href="${pageContext.request.contextPath}/course/edit/${course.id}"
									class="btn btn-primary">Edit</a>
								<a
									href="${pageContext.request.contextPath}/course/delete/${course.id}"
									class="btn btn-primary"
									onclick="return confirm('Are you sure you want to delete this course?')">Delete</a>
							</sec:authorize> <sec:authorize access="hasRole('ROLE_STUDENT')">
								<c:set var="enrollable" value="false" />
								<c:forEach items="${course.listOfLectures}" var="lecture">
									<c:if test="${!studentEnrolledLectures.contains(lecture)}">
										<c:set var="enrollable" value="true" />
									</c:if>
								</c:forEach>
								<c:choose>
									<c:when test="${enrollable == 'true'}">
										<a
											href="${pageContext.request.contextPath}/student/enrollToCourse/${course.id}"
											class="btn btn-primary">Enroll to Course</a>
									</c:when>
									<c:otherwise>
										<a
											href="${pageContext.request.contextPath}/student/unenrollCourse/${course.id}"
											class="btn btn-primary">Unenroll</a>
									</c:otherwise>
								</c:choose>
							</sec:authorize></td>
					</tr>
				</tbody>
			</table>

			<br />
			<h3>Lectures in Course</h3>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>id</th>
						<th>dayTime</th>
						<th>classroomId</th>
						<th>topic</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${course.listOfLectures}" var="lecture">
						<tr>
							<td><c:out value="${lecture.id}" /></td>
							<td><c:out value="${lecture.dayTime}" /></td>
							<td><c:out value="${lecture.classroomId}" /></td>
							<td><c:out value="${lecture.topic}" /></td>
							<td><my:a href="/lecture/view/${lecture.id}"
									class="btn btn-primary">view</my:a></td>
							<td><sec:authorize access="hasRole('ROLE_LECTURER')">
									<a
										href="${pageContext.request.contextPath}/lecture/edit/${lecture.id}"
										class="btn btn-primary">Edit</a>
									<a
										href="${pageContext.request.contextPath}/lecture/delete/${lecture.id}"
										class="btn btn-primary"
										onclick="return confirm('Are you sure you want to delete this lecture?')">Delete</a>
								</sec:authorize> <sec:authorize access="hasRole('ROLE_STUDENT')">
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
			<sec:authorize access="hasRole('ROLE_LECTURER')">
				<my:a href="/lecture/newLectureInCourse/${course.id}" class="btn btn-primary">New lecture</my:a>
			</sec:authorize>
		</div>
	</div>
</body>
<%@ include file="../common/footer.jsp"%>
</html>