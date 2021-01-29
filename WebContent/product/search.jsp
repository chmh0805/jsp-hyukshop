<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/layout/header.jsp" %>

<body>
	<!-- Start of Container -->
	<div id="container">
	<!-- Start of main-prd -->
	<main id="main-prd" class="frame">
		<div class="main-item-box">
			<div class="main-prd-list">
				<c:forEach var="product" items="${searchedProductList}">
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
		<c:if test="${fn:length(searchedProductList) == 0}">
			<div>
				<div class="main-pagetitle">
				<h1 class="main-pagetitle-word">
					검색어에 해당하는 결과가 없습니다.
				</h1>
			</div>
			<div class="main-pagetitle">
			<h1 class="main-pagetitle-word mgt150 mgb50">
				이런 상품들은 어떠신가요?
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
							<li class="prd-item-price">${product.price}</li> <!-- 가격 -->
							<li class="prd-item-soldCount">${product.soldCount}개 구매중</li> <!-- 판매량 default = 0 -->
						</ul>
					</div>
				</c:forEach>
			</div>
		</div>
		</div>
		</c:if>
	</main>
	<!-- End of main-prd -->
	</div>
	<!-- End of Container -->

<%@ include file="../layout/footer.jsp"%>