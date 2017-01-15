<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>

<my:pagetemplate title="Language school Lecture">
	<jsp:attribute name="body">
			<h1 class="page-header">
				Lecture details <small></small>
			</h1>
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
					<tr>
						<td>${lecture.id}</td>
						<td><c:out value="${lecture.dayTime}" /></td>
						<td><c:out value="${lecture.classroomId}" /></td>
						<td><c:out value="${lecture.topic}" /></td>
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
				</tbody>
			</table>
	</jsp:attribute>
</my:pagetemplate>