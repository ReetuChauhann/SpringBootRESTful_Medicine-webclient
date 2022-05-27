<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<c:out value="${result}"></c:out>
<h1 style="color: blue; background-color: blue;">Update Here</h1>
<body>
	<form action="updatemed" method="post" enctype="multipart/form-data">
		Select Id:<select name="mid">
			<c:forEach items="${ids}" var="x">
				<option>${x}</option>
			</c:forEach>
		</select><br><br>
	   Med Name:  <input type="text" name="name" required="required"><br><br>
	   Med Price: <input type="number" name="price" required="required"><br><br>
	              <button>Update</button>
		
	</form>
<hr>
<hr>
</body>
</html>