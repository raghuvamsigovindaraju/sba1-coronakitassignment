<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-All Products(Admin)</title>

</head>
<body>
	<jsp:include page="header.jsp" />
   <jsp:include page="addproduct.jsp" />
	<c:if test="${msg != null }">
		<p><strong>${msg }</strong>
	</c:if>
	<c:choose>
		<c:when test="${products == null || products.isEmpty() }">
			<p>No Contacts Found Try <a href="newContact">adding</a> one</p>
		</c:when>
		<c:otherwise>
			<table border="1" cellspacing="5px" cellpadding="5px">
				<tr>
					<th>Product#</th>
					<th>Product Name</th>
					<th>Cost</th>
					<th>Product Description</th>
					<th>Action</th>
				</tr>
				<c:forEach items="${products }" var="product">
					<tr>
						<td>${product.id }</td>
						<td>${product.productName }</td>
						<td>${product.cost }</td>
						<td>${product.productDescription }</td>
						<td>
							
							<a href="editProduct?id=${product.id  }">EDIT</a>
							
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	<form action="logout">
	<button>LogOut</button>
	</form>
	<jsp:include page="footer.jsp"/>
</body>
</html>