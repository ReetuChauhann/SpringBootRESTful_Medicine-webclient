<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1 style="color: orange; background-color: blue;">Medicine App</h1>
<hr>
<hr>
<h3 style="color: orange; background-color: blue;">Add Medicine</h3><hr>
${result}
<form action="addmed" method="post" enctype="multipart/form-data">
Medicine Id: <input type="number", name="mid" required="Enter the Id" /><br><br>
Medicine Name: <input type="text" name="name" required="Enter the name" /><br><br>
Medicine Price: <input type="text" name="price" required="Enter the Price"><br><br>
Upload Image: <input type="file" name="image" /><br><br>
              <button>Add</button>
</form>
<hr>
<hr>
<a href="viewall">View All Med here!</a>
<hr>
<hr>
<a href="UPDATE">To Update By Ids</a>
</body>
</html>