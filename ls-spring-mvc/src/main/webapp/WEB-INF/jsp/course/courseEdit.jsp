<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<title>Language School Course</title>
<%@ include file="../common/head.jsp"%>
</head>
<body>
	<%@ include file="../common/header.jsp"%>

	<form:form class="table table-striped" method="post"
		action="${pageContext.request.contextPath}/course/update/${course.id}"
		modelAttribute="course">

		<div>
			<form:label path="name">Name</form:label>
			<div class="col-sm-10">
				<input path="name" />
			</div>
		</div>
		<div>
			<label path="language">Language</label>
			<div class="col-sm-10">
				<form:input path="language" />
			</div>
		</div>
		<div>
			<form:label path="proficiencyLevel">Proficiency level</form:label>
			<div class="col-sm-10">
				<form:select path="proficiencyLevel">
					<c:forEach items="${proficiencylevels}" var="proflvl">
						<form:option value="${proflvl}">${proflvl}</form:option>
					</c:forEach>
				</form:select>
			</div>
		</div>
		<button class="btn btn-primary" type="submit">Update course</button>
	</form:form>

	<%@ include file="../common/footer.jsp"%>
</body>
</html>
