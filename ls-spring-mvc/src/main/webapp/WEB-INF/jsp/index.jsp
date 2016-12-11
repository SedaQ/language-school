<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<title>Language School</title>

<%@ include file="common/head.jsp" %>

</head>
<body>
	<!--banner-->
	<div class="banner">
		<%@ include file="common/header.jsp" %>
		<div class="container">
			<div class="banner-info">
				<section class="slider">
					<div class="flexslider">
						<ul class="slides">
							<li>
								<div class="banner-info1">
									<h1>A Place Of Light,<br> <span> Of Liberty,and Learning</span></h1>
									<p>Many desktop publishing packages and Web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many</p>
									
								</div>
							</li>
							<li>
								<div class="banner-info1">
									<h1>Our Task is <br> <span> Creation Of Future</span></h1>
									<p>Many desktop publishing packages and Web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many</p>
									
								</div>
							</li>
							<li>
								<div class="banner-info1">
									<h1>Developing Your <br>  <span> Strong Points</span></h1>
									<p>Many desktop publishing packages and Web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many</p>
									
								</div>
							</li>
						</ul>
					</div>
				</section>
					<!-- FlexSlider -->
							  <script defer="" src="${pageContext.request.contextPath}/assets/js/jquery.flexslider.js"></script>
							  <script type="text/javascript">
								$(function(){
								 
								});
								$(window).load(function(){
								  $('.flexslider').flexslider({
									animation: "slide",
									start: function(slider){
									  $('body').removeClass('loading');
									}
								  });
								});
							  </script>
						<!-- FlexSlider -->
			</div>
		</div>
	</div>
	<!--//banner-->
<!--students-->
	<div class="students">
		<div class="col-md-8 students-left wow fadeInLeft animated" data-wow-delay=".5s">
			<h2>Science and Research</h2>
			<h3>We are among the leading research</h3>
			<p>Many desktop publishing packages and Web page editors now use Lorem Ipsum  Ipsum as their default model text now use Lorem Ipsum as their default model text, packages and Web page editors now use Lorem Ipsum now use Lorem Ipsum as their default model text, and a as their default model text, lorem ipsum' will uncover many</p>
		</div>
		<div class="col-md-4 students-right wow fadeInRight animated" data-wow-delay=".5s">
			<ul>
				<li><div class="history-grid-image">
						<img src="${pageContext.request.contextPath}/assets/images/t10.jpg" class="img-responsive zoom-img" alt="">
					</div></li>
				<li><div class="history-grid-image">
						<img src="${pageContext.request.contextPath}/assets/images/t11.jpg" class="img-responsive zoom-img" alt="">
					</div></li>
				<li><div class="history-grid-image">
						<img src="${pageContext.request.contextPath}/assets/images/t12.jpg" class="img-responsive zoom-img" alt="">
					</div></li>
				<li><div class="history-grid-image">
						<img src="${pageContext.request.contextPath}/assets/images/t13.jpg" class="img-responsive zoom-img" alt="">
					</div></li>
			</ul>
		</div>
			<div class="clearfix"></div>
	</div>
<!--//students-->
<!--best-->
	<div class="best">
		<div class="container">
			<div class="col-md-6 best-left wow fadeInLeft animated" data-wow-delay=".5s">
				<h3>Latest News</h3>
				<div class="bes-top">
					<div class="bes-lft">
						<div class="history-grid-image">
						<img src="${pageContext.request.contextPath}/assets/images/t8.jpg" class="img-responsive zoom-img" alt="">
					</div>
					</div>
					<div class="bes-rgt">
						<h4><a href="${pageContext.request.contextPath}/singlepage">Lorem ipsum dolor sit</a></h4>
						<h5>Monday, 13 September 2015</h5>
						<p>Consectetuer adi piscing elit, sed diam nonummy nibh euismod tincidunt ut.</p>
					</div>
						<div class="clearfix"></div>
				</div>
				<div class="bes-top1">
					<div class="bes-lft">
						<div class="history-grid-image">
						<img src="${pageContext.request.contextPath}/assets/images/t9.jpg" class="img-responsive zoom-img" alt="">
					</div>
					</div>
					<div class="bes-rgt">
						<h4><a href="${pageContext.request.contextPath}/singlepage">Lorem ipsum dolor sit</a></h4>
						<h5>Monday, 13 September 2015</h5>
						<p>Consectetuer adi piscing elit, sed diam nonummy nibh euismod tincidunt ut.</p>
					</div>
						<div class="clearfix"></div>
				</div>
			</div>
			<div class="col-md-6 best-left wow fadeInRight animated" data-wow-delay=".5s">
				<h3>Our Best Students</h3>
				<p>Lorem ipsum dolor sit amet, consectetu er adipiscing elit, sed diam nonummy nibh eu ismod tincidunt ut laoreetd.</p>
				<div class="bes-top">
					<ul>
						<li><div class="history-grid-image">
						<img src="${pageContext.request.contextPath}/assets/images/t1.jpg" class="img-responsive zoom-img" alt="">
					</div>
						<h6><a href="${pageContext.request.contextPath}/singlepage">Sarah Nilson</a></h6>
						<h5>2016</h5></li>
						<li><div class="history-grid-image">
						<img src="${pageContext.request.contextPath}/assets/images/t2.jpg" class="img-responsive zoom-img" alt="">
					</div>
						<h6><a href="singlepage.html">JessicaMcQuay</a></h6>
						<h5>2015</h5></li>
						<li><div class="history-grid-image">
						<img src="${pageContext.request.contextPath}/assets/images/t3.jpg" class="img-responsive zoom-img" alt="">
					</div>
						<h6><a href="${pageContext.request.contextPath}/singlepage">Neil Johnson</a></h6>
						<h5>2014</h5></li>
					</ul>
				</div>
			</div>
				<div class="clearfix"></div>
		</div>
	</div>
<!--best-->
<!--course-->
	<div class="course">
		<div class="container">
			<div class="col-md-4 course-left wow fadeInLeft animated" data-wow-delay=".5s">
				<h3>Why Join Us</h3>
				<div class="history-grid-image">
						<img src="${pageContext.request.contextPath}/assets/images/n2.jpg" class="img-responsive zoom-img" alt="">
					</div>
				<p>Lorem ipsum dolor sit amet, consectetu er adipiscing elit, sed diam nonummy nibh eu ismod tincidunt ut laoreetd.</p>
			</div>
			<div class="col-md-4 course-left animated wow fadeInUp animated animated" data-wow-duration="1200ms" data-wow-delay="500ms">
				<h3>Affordable</h3>
				<div class="history-grid-image">
						<img src="${pageContext.request.contextPath}/assets/images/n3.jpg" class="img-responsive zoom-img" alt="">
					</div>
				<p>Lorem ipsum dolor sit amet, consectetu er adipiscing elit, sed diam nonummy nibh eu ismod tincidunt ut laoreetd.</p>
			</div>
			<div class="col-md-4 course-left wow fadeInRight animated" data-wow-delay=".5s">
					<h3>All Courses</h3>
					<ul>
						<li><a href="${pageContext.request.contextPath}/singlepage"><span></span>Lorem ipsum dolor sit</a></li>
						<li><a href="${pageContext.request.contextPath}/singlepage"><span></span>Amet consectetuer </a></li>
						<li><a href="${pageContext.request.contextPath}/singlepage"><span></span>Diam nonummy nibh</a></li>
						<li><a href="${pageContext.request.contextPath}/singlepage"><span></span>Euismod tincidunt ut </a></li>
						<li><a href="${pageContext.request.contextPath}/singlepage"><span></span>Magna aliquam erat</a></li>
						<li><a href="${pageContext.request.contextPath}/singlepage"><span></span>Volutpat ut wisi enim</a></li>
						<li><a href="${pageContext.request.contextPath}/singlepage"><span></span>Diam nonummy nibh</a></li>
						<li><a href="${pageContext.request.contextPath}/singlepage"><span></span>Magna aliquam erat</a></li>
					</ul>
					<p>Lorem ipsum dolor sit amet, consectetu er adipiscing elit, sed diam nonummy nibh eu ismod tincidunt ut laoreetd.</p>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
<!--course-->
<!--semst-->
	<div class="semst">
		<div class="container">
			<div class="col-md-7 semst-left wow fadeInLeft animated" data-wow-delay=".5s">
				<h3>Semester's best teachers</h3>
				<ul>
					<li>
						<div class="history-grid-image">
						<img src="${pageContext.request.contextPath}/assets/images/t4.jpg" class="img-responsive zoom-img" alt="">
					</div>
						<p>Lorem ipsum dolor sit amet, consectetu er elit.</p>
					</li>
					<li>
						<div class="history-grid-image">
						<img src="${pageContext.request.contextPath}/assets/images/t5.jpg" class="img-responsive zoom-img" alt="">
					</div>
						<p>Consectetu er adipiscing elit, sed diam nonummy.</p>
					</li>
					<li>
						<div class="history-grid-image">
						<img src="${pageContext.request.contextPath}/assets/images/t6.jpg" class="img-responsive zoom-img" alt="">
					</div>
						<p>Sed diam nonummy nibh eu ismod tincidunt ut.</p>
					</li>
				</ul>
			</div>
			<div class="col-md-5 semst-right wow fadeInRight animated" data-wow-delay=".5s">
				<h3>NewsLetter</h3>
				<form action="#" method="post">
					<input type="text" name="search" value="Enter Your email" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Enter Your email';}">
					<input type="submit" value="Subscribe">
				</form>
			</div>
				<div class="clearfix"></div>
		</div>
	</div>
<!--semst-->

<%@ include file="common/footer.jsp" %>
</body>
</html>