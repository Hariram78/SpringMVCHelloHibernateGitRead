<%@taglib prefix= "form" uri="http://www.springframework.org/tags/form"%><!-- imports the spring tags -->
<%@ include file="/WEB-INF/views/template/header.jsp" %><!-- is the spring tag for which the above tag is written -->
    <div class="container-wrapper">
    <div class="container">
    <div class="page-header">
        <h1> Product Detail</h1>

       <p class="lead">Here is the detailed information of the products</p>
      </div>

  
    <div class = "container">
    
    <div class = "row">
    
    <div class ="col-md-5"><!-- determines the width of the part of the grid specified for the particular type of information -->
    
    <img src ="#" alt="image" style="width:100%: height: 300px"/><!-- image content -->
    
    </div>
    
    <div class ="col-md-5">
  
     
     <h3>${product.productName}</h3>
    <p>${product.productDescription}</p>
    <p>
    <strong>Manufacturer</strong> : ${product.productManufacturer}
    </p>
    <p>
    <strong>Category</strong> : ${product.productCategory}
    </p>
    <p>
    <strong>Condition</strong> : ${product.productCondition}
    </p>
    <h4>${product.productPrice} USD</h4>
    </div>
    </div>
    </div>
      
      
      <%@ include file="/WEB-INF/views/template/footer.jsp" %>