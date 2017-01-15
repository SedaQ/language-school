<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>

<my:pagetemplate title="Language school Lecture">
	<jsp:attribute name="body">
			<h1 class="page-header">
				List of all lectures <small></small>
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
					<c:forEach items="${lectures}" var="lecture">
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
			<sec:authorize access="hasRole('ROLE_LECTURER')">
				<my:a href="/lecture/new/" class="btn btn-primary">New lecture</my:a>
			</sec:authorize>
	</jsp:attribute>
</my:pagetemplate>