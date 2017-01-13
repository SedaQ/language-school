<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<title>Language School language</title>
<%@ include file="../common/head.jsp"%>
</head>
<body>
	<%@ include file="../common/header.jsp"%>

	<div class="row">
		<div class="col-md-8 col-md-offset-2">
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
					</div>
					<form:label path="proficiencyLevel">Proficiency level</form:label>
					<div>
						<form:select path="proficiencyLevel">
							<c:forEach items="${proficiencylevels}" var="proflvl">
								<form:option value="${proflvl}">${proflvl}</form:option>
							</c:forEach>
						</form:select>
					</div>
					<br />
					<button class="btn btn-primary" type="submit">Create
						language</button>
				</form:form>
			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>
