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
				Edit Course <small></small>
			</h1>
			<div class="container center_div">
				<form:form class="table table-striped" method="post"
					action="${pageContext.request.contextPath}/course/update/${course.id}"
					modelAttribute="course">
					<label path="name">Name</label>
					<div>
						<form:input path="name" />
					</div>
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
					<button class="btn btn-primary" type="submit">Update
						course</button>
				</form:form>
			</div>
		</div>
	</div>
</body>
<%@ include file="../common/footer.jsp"%>
</html>
