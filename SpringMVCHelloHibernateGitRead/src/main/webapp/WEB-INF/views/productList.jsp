

<!--<%@taglib prefix= "form" uri="http://www.springframework.org/tags/form"%>-->
<!-- imports the spring tags -->



<%@include file="/WEB-INF/views/template/header.jsp"%>





<!-- is the spring tag for which the above tag is written -->
<!-- <div class="container-wrapper">
    <div class="container">
    <div class="page-header">
        <h1>All Products</h1>

       <p class="lead">Check out all the awesome products available now!</p>
      </div>

  
     <table class = "table table-bordered">
     <thead>
     
     <tr class="bg-success">
     
     <th>Photo Thumb</th>
      <th>Product Name</th>
       <th>Category</th>
        <th>Condition</th>
         <th>Price</th>
         <th>Information</th>
     </tr>
     </thead>
     
     <c:forEach items="${products}" var = "products">
     
     <tr>
     <td><img src="#" alt="image" /></td>
     <td>${products.productName}</td>
      <td>${products.productCategory}</td>
       <td>${products.productCondition}</td>
        <td>${products.productPrice} USD</td>
        <td><span class ="glyphicon glyphicon-play"></span></td>
     </tr>
     
     </c:forEach>
  
  </table>-->













<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>



<div class="container-wrapper">
	<div class="container">
		<div class="page-header">
			<h1>All Products</h1>

			<p class="lead">Checkout all the awesome products available now!</p>
		</div>

		<table class="table table-striped table-hover">
			<thead>
				<tr class="bg-success">
					<th>Photo Thumb</th>
					<th>Product Name</th>
					<th>Category</th>
					<th>Condition</th>
					<th>Price</th>
					<th></th>
				</tr>
			</thead>
			<c:forEach items="${products}" var="product">
				<tr>
					<td><img src="#" alt="image" /></td>
					<td>${product.productName}</td>
					<td>${product.productCategory}</td>
					<td>${product.productCondition}</td>
					<td>${product.productPrice}USD</td>
					 <td><a href="<spring:url value="/productList/viewProduct/${product.productId}" />"
                    ><span class="glyphicon glyphicon-info-sign"></span></a></td>			
					</tr>
			</c:forEach>
		</table>
</div>
</div>
		
		
		<!--  <%@ include file="/WEB-INF/views/template/footer.jsp" %>-->
		