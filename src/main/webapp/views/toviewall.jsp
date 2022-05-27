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
<c:out value="${result}"></c:out>
<c:forEach items="${med}" var="m">
Med Id: <c:out value="${m.mid}"></c:out><br>
Med Name: <c:out value="${m.name}"></c:out><br>
Med Price: <c:out value="${m.price}"></c:out><br>

      <img alt="" src="getimage?mid=${m.mid}" height="50px" width="50px">
      
      <form action="Updateimage" method="post" enctype="multipart/form-data"/>
      <input type="hidden" name="mid" value="${m.mid}"/>
      <input type="file" name="image"/>
      <button>Update Image</button>
      </form>

<hr>
<hr>
</c:forEach>

</body>
</html>