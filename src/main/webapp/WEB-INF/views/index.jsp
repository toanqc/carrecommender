<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Car Recommender</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="header-container">
			<div class="header-left">Logo!</div>
			<div class="header-right">Login with facebook</div>
		</div>
		<div class="search-container">
			<div class="header-text">
				Find your dream car <sub> &reg;</sub>
			</div>
			<div class="type-search-container">
				<ul>
					<li>Buy</li>
					<li>Rent</li>
				</ul>
			</div>
			<div class="search-box-container">
				<div>
					<select name="make" id="make">
						<option value="">&nbsp;Select Make&nbsp;</option>
					</select>
					<br><span class="eg-make">e.g. Nissan</span>
				</div>
				<div class="model-box">
					<select name="model" id="model">
						<option value="">&nbsp;Select Model&nbsp;</option>
					</select>
					<br><span class="eg-model">e.g. Maxima</span>
				</div>
				<div>
					<select name="model" id="model">
						<option value="">&nbsp;Select Year&nbsp;</option>
					</select>
					<br>&nbsp;
				</div>
			</div>
			<div class="button-container">
				<div class="go-button"><button>Go</button></div>
			</div>
		</div>
	</div>
</body>
</html>
