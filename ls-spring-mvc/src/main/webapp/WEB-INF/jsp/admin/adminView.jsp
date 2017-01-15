<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>

<my:pagetemplate title="Language school Admin">
	<jsp:attribute name="body">
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
	</jsp:attribute>
</my:pagetemplate>