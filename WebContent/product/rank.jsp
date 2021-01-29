<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/layout/header.jsp" %>
<body>
	<!-- Start of Container -->
	<div id="container">
	<!-- Start of Title -->
	<div class="favor-head border-btm-black">판매량순 랭킹</div>
	<!-- End of Title -->
	<!-- Start of main-prd -->
	<main id="main-prd" class="frame mgt30">
		<div class="main-item-box">
			<div class="main-prd-list">
				<c:forEach var="product" items="${rankedProductList}">
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