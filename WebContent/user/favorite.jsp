<%@page import="com.shop.shop.util.Script"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/layout/header.jsp"%>
<%
	if (session.getAttribute("principal") == null) {
		Script.back(response, "잘못된 접근입니다.");
	}
%>

<!-- 전체 박스 시작 -->
<div class="frame">
	<!-- main 타이틀 시작 -->
	<div class="favor-head border-btm-black">찜한 상품</div>
	<!-- main 타이틀 끝 -->
	<!-- sidebar 시작 -->
	<div class="favor-sidebar">
		<div class="favor-sidebar-title">상품 옵션</div>
		<div class="custom-control custom-checkbox favor-content">
			<input type="checkbox" class="custom-control-input" id="notSoldout">
			<label class="custom-control-label" for="notSoldout">품절 제외</label>
		</div>
		<div class="mgt10 favor-sidebar-title">찜한 상품</div>
		<div class="favor-content pdl0">BRAND</div>
		<ul class="favor-sidebar-compList">
			<c:forEach var="comp" items="${favorCompList}">
				<input type="radio" name="compList" onclick="getItemByCompId(${comp.companyId}, ${sessionScope.principal.id });" /> ${comp.companyName }
				<br/>
			</c:forEach>
			<c:if test="${fn:length(favorCompList) != 0 }">
				<input type="radio" name="compList" onclick="getDefault(${sessionScope.principal.id});" /> 전체보기
			</c:if>
		</ul>
	</div>
	<!-- sidebar 끝 -->
	<!-- 좌측박스 시작 -->
	<main id="main-prd">
	<div class="favor-item-box" id="favor-item-box">
		<div class="favor-prd-list" id="favor-prd-list">
			<c:forEach var="product" items="${favorList}">
				<div class="favor-prd-box">
				<a class="favor-link-prod"
					href="/shop/product?cmd=detail&prodNo=${product.productId}">
				</a>
				<img src="${product.imgUrl_1}" class="main-prd-item-img" />
					<ul class="favor-prd-item">
						<!-- 상품이미지 -->
						<li class="prd-item-company">${product.companyName}</li>
						<!-- 제조사 -->
						<li class="prd-item-name">${product.productName}</li>
						<!-- 상품명 -->
						<li class="prd-item-price"><fmt:formatNumber value="${product.price}" type="number" /></li>
						<!-- 가격 -->
						<li class="prd-item-soldCount">${product.soldCount}개구매중</li>
						<!-- 판매량 default = 0 -->
					</ul>
				</div>
			</c:forEach>
		</div>
	</div>
	<!-- 찜 있으면 위 -->
	<!-- 찜 없으면 아래 -->
	<c:if test="${fn:length(favorList) == 0}">
	<div>
		<div class="main-pagetitle">
		<h1 class="main-pagetitle-word">
			찜한 상품이 없습니다.
		</h1>
	</div>
	<div class="main-pagetitle">
		<h1 class="main-pagetitle-word mgt150 mgb50">
			이런 상품들은 어떠신가요?
		</h1>
	</div>
	<div class="favor-item-box" id="favor-item-box">
		<div class="favor-prd-list" id="favor-prd-list">
			<c:forEach var="product" items="${productList30}">
				<div class="favor-prd-box">
				<a class="favor-link-prod"
					href="/shop/product?cmd=detail&prodNo=${product.productId}">
				</a>
				<img src="${product.imgUrl_1}" class="main-prd-item-img" />
					<ul class="favor-prd-item">
						<!-- 상품이미지 -->
						<li class="prd-item-company">${product.companyName}</li>
						<!-- 제조사 -->
						<li class="prd-item-name">${product.productName}</li>
						<!-- 상품명 -->
						<li class="prd-item-price"><fmt:formatNumber value="${product.price}" type="number" /></li>
						<!-- 가격 -->
						<li class="prd-item-soldCount">${product.soldCount}개구매중</li>
						<!-- 판매량 default = 0 -->
					</ul>
				</div>
			</c:forEach>
		</div>
	</div>
	</div>
		</c:if>
	
	</main>
	
	
	<!-- 좌측박스 끝 -->

</div>
<!-- 전체 박스 끝 -->

<script type="text/javascript" src="/shop/js/favorite.js"></script>

<%@ include file="/layout/footer.jsp"%>