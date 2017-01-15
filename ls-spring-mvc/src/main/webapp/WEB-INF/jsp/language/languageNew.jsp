<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>

<my:pagetemplate title="Language school Lecture">
	<jsp:attribute name="body">
			<h1 class="page-header">
				New Language <small></small>
			</h1>
			<div class="container center_div">
				<form:form method="post"
				action="${pageContext.request.contextPath}/language/create"
				modelAttribute="language" cssClass="form-horizontal">
					<form:hidden path="lecturer.id" />
					<label path="language">Language</label>
					<div>
						<form:input path="language" />
                                                <form:errors
						path="language" />
					</div>
					<form:label path="proficiencyLevel">Proficiency level</form:label>
					<div>
						<form:select path="proficiencyLevel">
							<c:forEach items="${proficiencylevels}" var="proflvl">
								<form:option value="${proflvl}">${proflvl}</form:option>
							</c:forEach>
						</form:select>
                                                <form:errors
						path="proficiencyLevel" />
					</div>
					<br />
					<button class="btn btn-primary" type="submit">Create
						language</button>
				</form:form>
			</div>
	</jsp:attribute>
</my:pagetemplate>