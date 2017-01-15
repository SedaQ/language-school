<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>

<my:pagetemplate title="Language school Course">
	<jsp:attribute name="body">
			<h1 class="page-header">
				List of all courses <small></small>
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
					<c:forEach items="${courses}" var="course">
						<tr>
							<td><c:out value="${course.id}" /></td>
							<td><c:out value="${course.name}" /></td>
							<td><c:out value="${course.language}" /></td>
							<td><c:out value="${course.proficiencyLevel}" /></td>
							<td><my:a href="/course/view/${course.id}"
								class="btn btn-primary">view</my:a></td>
							<td><sec:authorize access="hasRole('ROLE_STUDENT')">
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
					</c:forEach>
				</tbody>
			</table>
			<sec:authorize access="hasRole('ROLE_LECTURER')">
				<my:a href="/course/new/" class="btn btn-primary">New Course</my:a>
			</sec:authorize>
	</jsp:attribute>
</my:pagetemplate>