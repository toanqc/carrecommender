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
			<div class="header-left">Car Recommender</div>
			<div class="header-right">
				<fb:login-button scope="public_profile,email" autologoutlink="true"
					onlogin="checkLoginState();">
				</fb:login-button>
				<div id="status"></div>
			</div>
		</div>
		<div class="car-detail-container">
			<div class="car-detail-img">
				<img src='${car.photo}'>
			</div>
			<div class="car-detail-text">
				<div class="car-detail-make">${car.make} ${car.submodel} ${car.year}</div>
				<div class="car-detail-name">${car.name}</div>
				<div class="car-transmission">Transmission: ${car.transmission}</div>
				<div>Doors: ${car.doors}</div>
				<div>MSRP: $${car.msrp}</div>
				<div>Rating: ${car.rating}</div>
				<div>Consumer Rating: ${car.consumerRating}</div>
			</div>
			
			<input type="hidden" id="carid" value="${car.cid}" />
		</div>
		
		<script>
			$(document).ready(function(){
				$.getJSON("tracker?carid=" + $("#carid").val() );
			});
		</script>
	</div>
</body>
</html>