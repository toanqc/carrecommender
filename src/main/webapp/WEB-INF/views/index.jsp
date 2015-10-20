<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Car Recommender</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="resources/css/main.css" />" rel="stylesheet">
<link rel="shortcut icon" type="image/png" sizes="128x128"
	type="image/png" href="<c:url value="/resources/img/favicon.png" />" />
<script type="text/javascript"
	src="<c:url value="resources/js/lib/jquery-1.11.3.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="resources/js/facebook.js" />"></script>
<script type="text/javascript"
	src="<c:url value="resources/js/car.js" />"></script>
</head>
<body>
	<div class="container">
		<div class="header-container">
			<div class="header-left">Logo!</div>
			<div class="header-right">
				<fb:login-button scope="public_profile,email" autologoutlink="true"
					onlogin="checkLoginState();">
				</fb:login-button>
				<div id="status"></div>
			</div>
		</div>
		<div class="background">
			<div class="search-container">
				<div class="header-text">Find your dream car</div>
				<div class="type-search-container">
					<ul>
						<li>Buy</li>
						<li>Rent</li>
					</ul>
				</div>
				<div class="search-box-container">
					<div>
						<select name="makes" id="makes">
							<option value="" selected="selected">-- Select Make --</option>
							<c:if test="${not empty makes}">
								<c:forEach var="make" items="${makes}">
									<option value="${make.id}">${make.name}</option>
								</c:forEach>
							</c:if>
						</select> <br> <span class="eg-make">e.g. Nissan</span>
					</div>
					<div id="txtHint"></div>
					<div class="model-box">
						<select name="models" id="models">
							<option value="">-- Select Model --</option>
						</select> <br> <span class="eg-model">e.g. Maxima</span>
					</div>
					<div>
						<select name="years" id="years">
							<option value="">-- Select Year --</option>
						</select> <br>&nbsp;
					</div>
				</div>
				<div class="button-container">
					<div class="go-button">
						<button id="search">Go</button>
					</div>
				</div>
			</div>
		</div>

		<div class="search-result-container">
			<div class="result-header" id="result"></div>
			<div id="cars"></div>
		</div>

		<div style="clear: both"></div>

		<div class="recommendation-container search-result-container" style="display: none">
			<div class="result-header">
				<h2>Recommendations</h2>
			</div>
			<div id="recommendation"></div>
		</div>
	</div>
</body>
</html>