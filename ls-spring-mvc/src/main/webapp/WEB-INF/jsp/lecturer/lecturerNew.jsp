<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<my:pagetemplate title="Language school Lecturer">
	<jsp:attribute name="body">
			<h1 class="page-header">
				Create new Lecturer <small></small>
			</h1>
			<div class="container center_div">
				<form:form method="post"
					action="${pageContext.request.contextPath}/lecturer/create"
					modelAttribute="lecturerCreate" cssClass="form-horizontal">
					<label path="email">E-mail</label>
					<div>
						<form:input path="email" />
                        <form:errors path="email" />
					</div>
					<label path="passwordHash">Password</label>
					<div>
						<form:input type="password" path="passwordHash" />
                        <form:errors path="passwordHash" />
					</div>
					<label path="nickname">Nickname</label>s
					<div>
						<form:input path="nickname" />
                        <form:errors path="nickname" />
					</div>
					<label path="firstName">First name</label>
					<div>
						<form:input path="firstName" />
                        <form:errors path="firstName" />
					</div>
					<label path="surname">Surname</label>
					<div>
						<form:input path="surname" />
                        <form:errors path="surname" />
					</div>
					<br />
					<button class="btn btn-primary" type="submit">Create
						lecturer</button>
				</form:form>
			</div>
	</jsp:attribute>
</my:pagetemplate>