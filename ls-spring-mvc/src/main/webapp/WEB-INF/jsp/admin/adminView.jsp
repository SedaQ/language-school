<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<title>Language school Student</title>
<%@ include file="../common/head.jsp"%>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<h1 class="page-header">
				Admin details <small></small>
			</h1>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>id</th>
						<th>email</th>
					</tr>
				</thead>

				<tbody>
					<tr>
						<td>${admin.id}</td>
						<td><c:out value="${admin.email}" /></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>