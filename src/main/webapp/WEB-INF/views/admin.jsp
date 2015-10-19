<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Simulator</title>
</head>
<body>
	<p>For testing purposes </p>
	<p>Input: comma separated car id , Output: recommendation list</p>
	
	<form action="rec" method="POST">
		<input name="input" value=""/>
		<input type="submit" value="Get Recommendations" />
	</form>

	<c:if test="${not empty output}">
		<p>
			Output: <br/>
			${output} <br/>
			${query}
		</p>
	</c:if>
	<hr>
		
	<p>
		Enter user id (i.e. 10206909610532827) and car id (200703618, 200703609) to simulate user click.
	</p>
	
	<form action="carclick" method="POST">
		<input name="userId" value="10206909610532827"/>
		<input name="carId" value="200703618"/>
		<input type="submit" value="Click" />
	</form>	
	<c:if test="${not empty carTrace}">
		<p>
			Output: <br/>
			${carTrace} <br/>
		</p>
	</c:if>	
</body>
</html>