<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<title>Language School Course</title>
<%@ include file="../common/head.jsp"%>
</head>
<body>
	<%@ include file="../common/header.jsp"%>

	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<h1 class="page-header">
				Create new Course <small></small>
			</h1>
			<div class="container center_div">
				<form:form method="post"
					action="${pageContext.request.contextPath}/course/create"
					modelAttribute="courseCreate" cssClass="form-horizontal">
					<div class="form-group">
						<label path="name">Name</label>
						<form:input path="name" />
                                                <form:errors path="name" />
					</div>
					<div class="form-group row">
						<label path="language">Language</label>
						<form:input path="language" />
                                                <form:errors path="language" />
					</div>
					<div class="form-group row">
						<label path="proficiencyLevel">Proficiency level</label>
						<form:select path="proficiencyLevel">
							<c:forEach items="${proficiencylevels}" var="proflvl">
								<form:option value="${proflvl}">${proflvl}</form:option>
							</c:forEach>
						</form:select>
                                                <form:errors path="proficiencyLevel" />
					</div>
					<div>
						<button class="btn btn-primary" type="submit">Create
							course</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
<%@ include file="../common/footer.jsp"%>
</html>