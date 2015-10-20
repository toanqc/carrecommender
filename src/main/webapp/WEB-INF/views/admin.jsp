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
	<p>Input: userId (i.e. 10206909610532827, 10153625538892856, 1076887648990784), Output: recommendation list</p>
	
	<form action="rec" method="POST">
		<input name="userId" value=""/>
		<input type="submit" value="Get Recommendations" />
	</form>

	<c:if test="${not empty output}">
		<p>
			Output: <br/>
			<table>
			    <c:forEach var="car" items="${output}">
			        <tr>
			            <td>${car.make}</td>
			            <td>${car.model}</td>
			            <td>${car.year}</td>
			            <td><img src="${car.photo}" width="50"/></td>
			        </tr> 
			    </c:forEach>
			</table>
		</p>
	</c:if>
	<hr>
		
	<p>
		Enter user id (i.e. 10206909610532827, 1076887648990784, 10153625538892856) and car id (200703618, 200703609) to simulate user click.
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