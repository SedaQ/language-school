<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<my:pagetemplate title="Language school Lecturer">
	<jsp:attribute name="body">
			<h1 class="page-header">
				Edit Lecturer <small></small>
			</h1>
			<div class="container center_div">
				<form:form class="table table-striped" method="post" enctype="multipart/form-data;charset=UTF-8"
				action="${pageContext.request.contextPath}/lecturer/update/${lecturer.id}"
				modelAttribute="lecturer">
					<label path="nickname">Nickname</label>
					<div>
						<form:input path="nickname" />
                                                <form:errors
						path="nickname" />
					</div>
					<label path="firstName">First name</label>
					<div>
						<form:input path="firstName" />
                                                <form:errors
						path="firstName" />
					</div>
					<label path="surname">Surname</label>
					<div>
						<form:input path="surname" />
                                                <form:errors
						path="surname" />
					</div>
					<div>
						<form:input type="hidden" path="userRole" />
						<form:input type="hidden" path="passwordHash" />
						<form:input type="hidden" path="email" />
					</div>
					<br />
					<button class="btn btn-primary" type="submit">Update
						lecturer</button>
				</form:form>
			</div>
	</jsp:attribute>
</my:pagetemplate>