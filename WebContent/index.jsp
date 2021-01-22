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
				<c:when test="${sessionScope.name eq null }">
					당신을 위한 추천
				</c:when>
				<c:otherwise>
					${sessionScope.name }님을 위한 추천
				</c:otherwise>
			</c:choose>
			</h1>
		</div>
		<div class="main-item-box">
			<div class="main-prd-list">
				<a class="link-prod" href="/shop/product?cmd=detail&category=1&item=1">
					<ul class="main-prd-item">
						<img src="/shop/images/product/wallet/louisVuitton/0/0.jpg" class="main-prd-item-img" /> <!-- 상품이미지 -->
						<li class="prd-item-company">LOUIS VUITTON</li> <!-- 제조사 -->
						<li class="prd-item-name">루이비통 슬랜더 월릿 반지갑 모노그램 이클립스 M62294</li> <!-- 상품명 -->
						<li class="prd-item-price">756,000</li> <!-- 가격 -->
						<li class="prd-item-soldCount">0개 구매중</li> <!-- 판매량 default = 0 -->
					</ul>
				</a>
				<a href="#">
					<ul class="main-prd-item">
						<img src="/shop/images/product/wallet/louisVuitton/1/0.jpg" class="main-prd-item-img" /> <!-- 상품이미지 -->
						<li class="prd-item-company">LOUIS VUITTON</li> <!-- 제조사 -->
						<li class="prd-item-name">20FW 루이비통 남성 오거나이저 지갑</li> <!-- 상품명 -->
						<li class="prd-item-price">587,000</li> <!-- 가격 -->
						<li class="prd-item-soldCount">0개 구매중</li> <!-- 판매량 default = 0 -->
					</ul>
				</a>
				<a href="#">
					<ul class="main-prd-item">
						<img src="/shop/images/product/wallet/maisonMargiela/0/0.jpg" class="main-prd-item-img" /> <!-- 상품이미지 -->
						<li class="prd-item-company">MAISON MARGIELA</li> <!-- 제조사 -->
						<li class="prd-item-name">마르지엘라 스티치 가죽 블랙 아이보리 투톤 반지갑 S55UI0135 MAISON</li> <!-- 상품명 -->
						<li class="prd-item-price">321,000</li> <!-- 가격 -->
						<li class="prd-item-soldCount">0개 구매중</li> <!-- 판매량 default = 0 -->
					</ul>
				</a>
				<a href="#">
					<ul class="main-prd-item">
						<img src="/shop/images/product/wallet/saintLaurent/0/0.jpg" class="main-prd-item-img" /> <!-- 상품이미지 -->
						<li class="prd-item-company">SAINT LAURENT</li> <!-- 제조사 -->
						<li class="prd-item-name">20FW 생로랑 남성 모노그램 반지갑 블랙 607727 1JBOE 1000</li> <!-- 상품명 -->
						<li class="prd-item-price">665,000</li> <!-- 가격 -->
						<li class="prd-item-soldCount">0개 구매중</li> <!-- 판매량 default = 0 -->
					</ul>
				</a>
			</div>
		</div>
	</main>
	<!-- End of main-prd -->
	</div>
	<!-- End of Container -->

<%@ include file="../layout/footer.jsp"%>