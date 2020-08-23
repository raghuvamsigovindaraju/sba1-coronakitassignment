<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Order Summary</title>

</head>
<body>
	<jsp:include page="header.jsp" />
	<c:choose>
			<c:when test="${orders == null || orders.isEmpty() }">
			<p><strong>--------Order Summary -----------------</strong> </p>
			<p>No Items Found Try <a href="newContact">adding</a> one</p>
		</c:when>
		<c:otherwise>
		<p><strong>--------Order Summary -----------------</strong> </p>
		   <p> Your address details are below !</p>
		   <p><strong>User Name:: ${user} </strong></p>
		   <p><strong>Email:: ${email} </strong></p>
		   <p><strong>Contact:: ${contact} </strong></p>
		   <p> Your Order details are below !</p>
			<table border="1" cellspacing="5px" cellpadding="5px">
				<tr>
					<th>OrderProduct#</th>
					<th>OrderProduct Name</th>
					<th>OrderCost</th>
					<th>OrderProduct Description</th>
					<th>Order Quantity</th>
					<th>Order TotalCost></th>
				</tr>
				<c:forEach items="${orders }" var="order">
					<tr>
						<td>${order.orderproductnumber }</td>
						<td>${order.orderproductname }</td>
						<td>${order.orderproductcost }</td>
						<td>${order.orderproductdescription }</td>
						<td>${order.orderproductquantity }</td>
						<td>${order.orderproducttotalcost }</td>
					</tr>
				</c:forEach>
			</table>
			<p><strong>-------Your Total Order Cost !-----------</strong></p>
		   <p><strong>Total Order Cost:: ${totalordercost} </strong></p>
		</c:otherwise>
	</c:choose>
	<jsp:include page="footer.jsp"/>
</body>
</html>