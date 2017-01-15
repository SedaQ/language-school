<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>

<my:pagetemplate title="Language school Lecture">
	<jsp:attribute name="body">
			<h1 class="page-header">
				Lecture details 
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
			<h3 class="page-header">
				This Lecture is in following Courses 
			</h3>
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
					<c:forEach items="${lectureInCourse}" var="course">
						<tr>
							<td><c:out value="${course.id}" /></td>
							<td><c:out value="${course.name}" /></td>
							<td><c:out value="${course.language}" /></td>
							<td><c:out value="${course.proficiencyLevel}" /></td>
							<td><my:a href="/course/view/${course.id}"
								class="btn btn-primary">view</my:a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<h3 class="page-header">
				This Lecture is teached by following Lecturers 
			</h3>
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
					<c:forEach items="${courseLecturers}" var="lecturer">
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
			
			<h3 class="page-header">
				Student enrolled to this lecture 
			</h3>
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
					<c:forEach items="${courseStudents}" var="student">
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
	</jsp:attribute>
</my:pagetemplate>