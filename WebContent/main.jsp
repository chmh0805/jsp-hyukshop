<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="layout/header.jsp" %>

<body>
	<!-- Start of Container -->
	<div id="container">
	<!-- Start of Carousel -->
	<div id="demo" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ul class="carousel-indicators">
		  <li data-target="#demo" data-slide-to="0" class="active"></li>
		  <li data-target="#demo" data-slide-to="1"></li>
		  <li data-target="#demo" data-slide-to="2"></li>
		</ul>
        
		<!-- The slideshow -->
		<div class="carousel-inner">
		  <div class="carousel-item active">
		    <img src="images/header/bannerImage0.jpg" alt="ban-img-0" width="1100" height="500">
		  </div>
		  <div class="carousel-item">
		    <img src="images/header/bannerImage1.jpg" alt="ban-img-1" width="1100" height="500">
		  </div>
		  <div class="carousel-item">
		    <img src="images/header/bannerImage2.jpg" alt="ban-img-2" width="1100" height="500">
		  </div>
		</div>
        
	     <!-- Left and right controls -->
	     <a class="carousel-control-prev" href="#demo" data-slide="prev">
	       <span class="carousel-control-prev-icon"></span>
	     </a>
	     <a class="carousel-control-next" href="#demo" data-slide="next">
	       <span class="carousel-control-next-icon"></span>
	     </a>
     </div>
     <!-- End of Carousel -->

	<!-- Start of main-prd -->
	<main id="main-prd" class="frame">
		<div class="main-pagetitle">
			<h1 class="main-pagetitle-word">
			<c:choose>
				<c:when test="${sessionScope.principal eq null }">
					당신을 위한 추천
				</c:when>
				<c:otherwise>
					${sessionScope.principal.name }님을 위한 추천
				</c:otherwise>
			</c:choose>
			</h1>
		</div>
		<div class="main-item-box">
			<div class="main-prd-list">
				<c:forEach var="product" items="${productList30}">
					<div class="main-prd-box">
					<a class="link-prod" href="/shop/product?cmd=detail&prodNo=${product.productId}"></a>
						<img src="${product.imgUrl_1}" class="main-prd-item-img" /> <!-- 상품이미지 -->
						<ul class="main-prd-item">
							<li class="prd-item-company">${product.companyName}</li> <!-- 제조사 -->
							<li class="prd-item-name">${product.productName}</li> <!-- 상품명 -->
							<li class="prd-item-price"><fmt:formatNumber value="${product.price}" type="number" /></li> <!-- 가격 -->
							<li class="prd-item-soldCount">${product.soldCount}개 구매중</li> <!-- 판매량 default = 0 -->
						</ul>
					</div>
				</c:forEach>
			</div>
		</div>
	</main>
	<!-- End of main-prd -->
	</div>
	<!-- End of Container -->

<%@ include file="../layout/footer.jsp"%>