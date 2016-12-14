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

	<div class="container center_div">
		<form:form class="table table-striped" method="post"
			action="${pageContext.request.contextPath}/course/update/${course.id}"
			modelAttribute="course">

			<div>
				<form:label path="name">Name</form:label>
				<input path="name" />
			</div>
			<div>
				<label path="language">Language</label>
				<form:input path="language" />
			</div>
			<div>
				<form:label path="proficiencyLevel">Proficiency level</form:label>
				<form:select path="proficiencyLevel">
					<c:forEach items="${proficiencylevels}" var="proflvl">
						<form:option value="${proflvl}">${proflvl}</form:option>
					</c:forEach>
				</form:select>
			</div>
			<button class="btn btn-primary" type="submit">Update course</button>
		</form:form>
	</div>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>
