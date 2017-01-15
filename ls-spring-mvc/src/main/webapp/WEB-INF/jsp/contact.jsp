<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>LS Contact Us</title>
<%@ include file="common/head.jsp"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/contactus.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/bottom.css">
</head>
<%@ include file="common/header.jsp"%>
<body>
	<section id="contact-info">
		<div class="container">
			<div class="row">
				<div class="col-sm-11 text-center">
					<h2>Kontaktujte lektory</h2>
					<p class="lead">Lorem ipsum dolor</p>
				</div>
			</div>
		</div>
		<div class="gmap-area">
			<div class="container">
				<div class="row">
					<div class="col-sm-5 text-center">
						<div class="gmap">
							<iframe frameborder="0" scrolling="no" marginheight="0"
								marginwidth="0"
								src="https://maps.google.com/maps?f=q&amp;source=s_q&amp;hl=en&amp;geocode=&amp;q=JoomShaper,+Dhaka,+Dhaka+Division,+Bangladesh&amp;aq=0&amp;oq=joomshaper&amp;sll=37.0625,-95.677068&amp;sspn=42.766543,80.332031&amp;ie=UTF8&amp;hq=JoomShaper,&amp;hnear=Dhaka,+Dhaka+Division,+Bangladesh&amp;ll=23.73854,90.385504&amp;spn=0.001515,0.002452&amp;t=m&amp;z=14&amp;iwloc=A&amp;cid=1073661719450182870&amp;output=embed"></iframe>

						</div>
					</div>

					<div class="col-sm-7 map-content">
						<ul class="row">
							<li class="col-sm-6">
								<address>
									<h5>Main office</h5>
									<p>
										Patockova 1235 <br> Brno
									</p>
									<p>
										Phone:+420-776-700-858 <br> Email:testtest@email.cz
									</p>
								</address>

								<address>
									<h5>Local office</h5>
									<p>
										Braniborska<br> Brno
									</p>
									<p>
										Phone:+420-776-700-857 <br> Email:test@email.cz
									</p>
								</address>
							</li>

							<li class="col-sm-6">
								<address>
									<h5>Local office</h5>
									<p>
										Rerychova 4 <br> Brno
									</p>
									<p>
										Phone:+420-776-700-856 <br> Email:testtesttest@email.cz
									</p>
								</address>

							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--/gmap_area -->

	<section id="contact-page">
		<div class="container">
			<div class="row">
				<div class="col-sm-11 text-center">
					<h2>Zanechte e-mailovou zprávu</h2>
					<p class="lead">Lorem ipsum dolor</p>
				</div>
			</div>
			<div class="row contact-wrap">
				<div class="status alert alert-success" style="display: none"></div>
				<form id="main-contact-form" class="contact-form"
					name="contact-form" method="post" action="sendemail.php">
					<div class="col-sm-5 col-sm-offset-1">
						<div class="form-group">
							<label>Name *</label> <input type="text" name="name"
								class="form-control" required="required">
						</div>
						<div class="form-group">
							<label>Email *</label> <input type="email" name="email"
								class="form-control" required="required">
						</div>
						<div class="form-group">
							<label>Phone</label> <input type="number" class="form-control">
						</div>
						<div class="form-group">
							<label>Company Name</label> <input type="text"
								class="form-control">
						</div>
					</div>
					<div class="col-sm-5">
						<div class="form-group">
							<label>Subject *</label> <input type="text" name="subject"
								class="form-control" required="required">
						</div>
						<div class="form-group">
							<label>Message *</label>
							<textarea name="message" id="message" required="required"
								class="form-control" rows="8"></textarea>
						</div>
						<div class="form-group">
							<button type="submit" name="submit"
								class="btn btn-primary btn-lg" required="required">Submit
								Message</button>
						</div>
					</div>
				</form>
			</div>
			<!--/.row-->
		</div>
		<!--/.container-->
	</section>
	<!--/#contact-page-->

	<p></p>
	<!-- bottom -->
	<jsp:include page="common/bottom.jsp" />
	<!--/bottom-->

	<script src="${pageContext.request.contextPath}/assets/js/jquery.js"></script>
	<!--
	<script	src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
-->
	<script
		src="${pageContext.request.contextPath}/assets/js/jquery.prettyPhoto.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/jquery.isotope.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/wow.min.js"></script>

</body>
</html>