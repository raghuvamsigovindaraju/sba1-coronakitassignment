<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Add/Modify Product(Admin)</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	
	<h3>${isNew?"New Product":"Edit Product" }</h3>
	
	<form action='${isNew?"addProduct":"saveProduct"}' method="POST">
	
		<div>
			<label>Product Id</label>
			<input type="number" name="productId" value="${product.id }" ${isNew?"":"readonly" } required/>
		</div>
		<div>	
			<label>Product Name</label>
			<input type="text" name="productName" value="${product.productName }" required/>
		</div>
		<div>
			<label>Cost</label>
			<input type="text" name="cost" value="${product.cost }" required/>
		</div>
		<div>
			<label>Product Description</label>
			<input type="text" name="productDescription" value="${product.productDescription }" required/>
		</div>
		<button>SAVE</button>
	</form>
</body>
</html>