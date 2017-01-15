<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>

<my:pagetemplate title="Language school Lecture">
	<jsp:attribute name="body">
			<h1 class="page-header">
				Edit Lecture <small></small>
			</h1>
			<div class="container center_div">
				<form:form class="table table-striped" method="post"
					action="${pageContext.request.contextPath}/lecture/update/${lecture.id}"
					modelAttribute="lecture">

					<label path="dayTime">DateTime</label>
					<div>
						<form:input path="dayTime" />
                                                <form:errors path="dayTime" />
					</div>
					<label path="classroomId">Classroom id</label>
					<div>
						<form:input path="classroomId" />
                                                <form:errors path="classroomId" />
					</div>
					<label path="topic">Topic</label>
					<div>
						<form:input path="topic" />
                                                <form:errors path="topic" />
					</div>
					<br />
					<button class="btn btn-primary" type="submit">Update
						lecture</button>
				</form:form>
			</div>
	</jsp:attribute>
</my:pagetemplate>