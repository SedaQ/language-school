<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Language School error!</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath}/assets/css/errorpage-style.css" rel="stylesheet" type="text/css" media="all"/>
<link href='http://fonts.googleapis.com/css?family=Fenix' rel='stylesheet' type='text/css'>
</head>
<body>
  <div class="wrap">
	 <div class="main">
		<h3>Language School</h3>
		<h1>Oops Could not found</h1>
		<p>There's a lot of reasons why this page is<span class="error"> 404</span>.<br>
			<span>Don't waste your time enjoying the look of it</span></p>
			<div class="search">
				<form>
					<input type="text" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Enter ur email';}" value="Enter ur email">
					<input type="submit" value="Submit">		
				</form>
			</div>
		<div class="icons">
		<p>Follow us on:</p>
		  <ul>
		  	 <li><a href="#"><img src="${pageContext.request.contextPath}/assets/images/img1.png"></a></li>
		     <li><a href="#"><img src="${pageContext.request.contextPath}/assets/images/img2.png"></a></li>
		     <li><a href="#"><img src="${pageContext.request.contextPath}/assets/images/img3.png"></a></li>
		     <li><a href="#"><img src="${pageContext.request.contextPath}/assets/images/img4.png"></a></li>
		     <li><a href="#"><img src="${pageContext.request.contextPath}/assets/images/img5.png"></a></li>
		  </ul>	
	   </div>
   </div>
	<div class="footer">
		<p>PA165 2016/2017</p>
    </div>
  </div>
</body>
</html>