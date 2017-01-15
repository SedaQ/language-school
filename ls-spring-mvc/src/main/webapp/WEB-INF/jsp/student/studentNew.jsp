<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<my:pagetemplate title="Language school Student">
	<jsp:attribute name="body">
			<h1 class="page-header">
				Create new Student <small></small>
			</h1>
			<div>
				<form:form method="post" enctype="multipart/form-data;charset=UTF-8"
				action="${pageContext.request.contextPath}/student/create"
				modelAttribute="studentCreate" cssClass="form-horizontal">
					<label path="email">E-mail</label>
					<div>
						<form:input path="email" />
                                                <form:errors
						path="email" />
					</div>
					<label path="passwordHash">Password</label>
					<div>
						<form:input type="password" path="passwordHash" />
                                                <form:errors
						path="passwordHash" />
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
					<label path="birthNumber">Birth number</label>
					<div>
						<form:input path="birthNumber" />
                                                <form:errors
						path="birthNumber" />
					</div>
					<br />
					<button class="btn btn-primary" type="submit">Create
						student</button>
				</form:form>
			</div>
	</jsp:attribute>
</my:pagetemplate>