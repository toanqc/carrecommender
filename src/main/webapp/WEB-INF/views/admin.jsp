<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin</title>
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
</body>
</html>