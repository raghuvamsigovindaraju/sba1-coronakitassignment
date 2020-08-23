<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona User selected kit</title>

</head>
<body>
<p><strong>Here are the list of items you selected for your kit!</strong></p>
	<jsp:include page="header.jsp" />
	<c:if test="${msg != null }">
	
		<p><strong>${msg }</strong>
	</c:if>
	<c:choose>
		<c:when test="${kitproducts == null || kitproducts.isEmpty() }">
			<p>No Contacts Found Try <a href="newContact">adding</a> one</p>
		</c:when>
		<c:otherwise>
			<table border="1" cellspacing="5px" cellpadding="5px">
				<tr>
					<th>KitProduct#</th>
					<th>KitProduct Name</th>
					<th>Cost</th>
					<th>KitProduct Description</th>
				</tr>
				<c:forEach items="${kitproducts }" var="product">
					<tr>
						<td>${product.id }</td>
						<td>${product.productName }</td>
						<td>${product.cost }</td>
						<td>${product.productDescription }</td>
						
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	<form action="ordersummary">
	<button>ConfirmandPlaceOrder</button>
	</form>
	<jsp:include page="footer.jsp"/>
</body>
</html>