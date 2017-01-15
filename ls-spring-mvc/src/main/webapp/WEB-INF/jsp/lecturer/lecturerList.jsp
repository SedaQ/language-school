<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<my:pagetemplate title="Language school Lecturer">
	<jsp:attribute name="body">
			<h1 class="page-header">
				List of all lecturers <small></small>
			</h1>
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
				<my:a href="/lecturer/new/" class="btn btn-primary">New lecturer</my:a>
			</sec:authorize>
	</jsp:attribute>
</my:pagetemplate>