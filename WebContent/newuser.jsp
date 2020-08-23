<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New User Page</title>
</head>
<body>
<jsp:include page="header.jsp" />
	<h2>New User Page</h2>

	<%-- 
	<% String msg = (String) request.getAttribute("msg"); %>
	<% String age = (String) request.getAttribute("age"); %>
	
	<%if(msg!=null && age!=null) {%>
		<p><strong><%=msg %> <em><%=age %></em></strong></p>
	<%} %> --%>
	
	<c:choose>
		<c:when test="${msg == null }">
			<p>Welcome Please enter your details below to Continue!</p>
		</c:when>
		<c:otherwise>
			<p><strong>${msg } <em>${age }</em></strong></p>
		</c:otherwise>
	</c:choose>
	
	<form action="showproducts">
		<label>Full Name: <input type="text" name="fullName" required/></label> <br />
		<label>Email: <input type="text" name="email" required/></label> <br />
		<label>Contact <input type="text" name="contact" required/></label> <br />
		<Button>SEND</Button>
	</form>
</body>
</html>