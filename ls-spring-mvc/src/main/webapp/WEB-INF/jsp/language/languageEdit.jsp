<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>

<my:pagetemplate title="Language school Language">
	<jsp:attribute name="body">
			<h1 class="page-header">
				Edit Language <small></small>
			</h1>
			<div>
				<form:form class="table table-striped" method="post"
				action="${pageContext.request.contextPath}/language/update/${language.id}"
				modelAttribute="language">
					<form:hidden path="id" />
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
							<c:forEach items="${proficiencylevels}" var="level">
								<form:option value="${level}">${level}</form:option>
							</c:forEach>
						</form:select>
                                                <form:errors
						path="proficiencyLevel" />
					</div>
					<br />
					<button class="btn btn-primary" type="submit">Update
						language</button>
				</form:form>
			</div>
	</jsp:attribute>
</my:pagetemplate>