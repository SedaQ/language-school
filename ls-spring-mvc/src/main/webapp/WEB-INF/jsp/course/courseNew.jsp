<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Language School Course</title>
<%@ include file="../common/head.jsp"%>
</head>
<body>
	<%@ include file="../common/header.jsp"%>

	<div class="container center_div">
		<form method="post"
			action="${pageContext.request.contextPath}/course/create"
			modelAttribute="courseCreate" cssClass="form-horizontal">
			<div class="form-group">
				<label path="name">Name</label> <input path="name" />
			</div>
			<div class="form-group row">
				<label path="language">Language</label> <input path="language" />
			</div>
			<div class="form-group row">
				<label path="proficiencyLevel">Proficiency level</label> <select
					path="proficiencyLevel">
					<c:forEach items="${proficiencylevels}" var="proflvl">
						<option value="${proflvl}">${proflvl}</option>
					</c:forEach>
				</select>
			</div>
			<button class="btn btn-primary" type="submit">Create course</button>
		</form>
	</div>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>
