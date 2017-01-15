<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
<title>Language School Lecture</title>
<%@ include file="../common/head.jsp"%>
<link rel="stylesheet" type="text/css" media="screen"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link href="./css/prettify-1.0.css" rel="stylesheet">
<link href="./css/base.css" rel="stylesheet">
<link
	href="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/build/css/bootstrap-datetimepicker.css"
	rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
<script type="text/javascript"
	src="//code.jquery.com/jquery-2.1.1.min.js"></script>
	<!-- 
<script type="text/javascript"
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
	 -->
<script
	src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
<script
	src="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js"></script>

<script type="text/javascript">
	$(function() {
		$('#datetimepicker2').datetimepicker({
			locale : 'en-gb'
		});
	});
</script>

</head>
<body>
	<%@ include file="../common/header.jsp"%>

	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<h1 class="page-header">
				Edit Lecture <small></small>
			</h1>
			<div>
				<form:form class="table table-striped" method="post"
					action="${pageContext.request.contextPath}/lecture/update/${lecture.id}"
					modelAttribute="lecture">

					<label path="dayTime">DateTime</label>
					<div>
						<div class='input-group date' id="datetimepicker2">
                                                    <input type="text" name="dayTime" class="form-control" value="${dateTime}"/> <span
								class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
						<form:errors path="dayTime" />
					</div>
					<label path="classroomId">Classroom id</label>
					<div>
						<form:input path="classroomId" />
                                                <form:errors path="classroomId" />
					</div>
					<label path="topic">Topic</label>
					<div>
						<form:input path="topic" />
                                                <form:errors path="topic" />
					</div>
					<br />
					<button class="btn btn-primary" type="submit">Update
						lecture</button>
				</form:form>
			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>