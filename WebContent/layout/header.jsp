<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<!-- material icon -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<!-- jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- bootstrap -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<!-- joinForm icons -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
<!-- swiper-wrapper script -->
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.css">
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css">
<script src="https://unpkg.com/swiper/swiper-bundle.js"></script>
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<!-- kakao script -->
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<!-- End of Script -->
<title>Hyukshop</title>
</head>

<body>
<a href="<%=request.getContextPath() %>/"><img src="/shop/images/header/bannerImage.jpg" id="header-banner" /></a>
<header>
	<div id="header-first">
	    <div id="header-logo">
	        <a href="<%=request.getContextPath() %>/"><img src="/shop/images/header/logo.png" id="header-logo-img" /></a>
	    </div>
	    <form id="headerSearchForm" method="GET" action="#">
	        <button class="headerSearchForm-btn">
	            <i class="tiny material-icons">search</i>
	        </button>
	        <input name="keyword" placeholder="돋보기" class="headerSearchForm-input"/>
	    </form>
	    <c:choose>
		    <c:when test="${sessionScope.principal != null}">
			    <div id="header-main-menu">
			    	<c:if test="${sessionScope.principal.auth eq 'admin' }">
			    	<a href="#" class="header-sub-menu">상품등록</a>
			    	<a href="#" class="header-sub-menu">상품수정</a>
			    	</c:if>
			        <a href="#" class="header-sub-menu">찜</a>
			        <a href="#" class="header-sub-menu">장바구니</a>
			        <a href="<%=request.getContextPath() %>/user?cmd=checkAgain" class="header-sub-menu">마이페이지</a>
			        <a href="<%=request.getContextPath() %>/user?cmd=logout" class="header-sub-menu">로그아웃</a>
			    </div>
		    </c:when>
		    <c:otherwise>
		    	<div id="header-main-menu">
			        <a href="#" class="header-sub-menu">찜</a>
			        <a href="#" class="header-sub-menu">장바구니</a>
			        <a href="<%=request.getContextPath() %>/user?cmd=loginForm" class="header-sub-menu">로그인</a>
			        <a href="<%=request.getContextPath() %>/user?cmd=joinForm" class="header-sub-menu">회원가입</a>
			    </div>
		    </c:otherwise>
	    </c:choose>
    </div>
    <!-- Start of Dropdown -->
    <div id="header-second">
	    <div class="btn-group header-second-btn-box">
		<c:choose>
			<c:when test="${pageContext.request.requestURI eq '/shop/' }">
				<button type="button" id="header-sec-home" onclick="location.href='/shop/';" class="btn btn-basic header-second-btn-group border-btm-red">
					홈
				</button>
			</c:when>
			<c:otherwise>
				<button type="button" id="header-sec-home" onclick="location.href='/shop/';" class="btn btn-basic header-second-btn-group">홈</button>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${pageContext.request.requestURI eq '/shop/rank/' }">
				<button type="button" id="header-sec-rank" style="" class="btn btn-basic header-second-btn-group border-btm-red">랭킹</button>
			</c:when>
			<c:otherwise>
				<button type="button" id="header-sec-rank" style="" class="btn btn-basic header-second-btn-group">랭킹</button>
			</c:otherwise>
		</c:choose>
			<div class="btn-group">
			  <button type="button" id="header-sec-items" class="btn btn-basic dropdown-toggle header-second-btn-group" data-toggle="dropdown">
			     전체상품
			  </button>
			  <div class="dropdown-menu">
			  	<h5 class="dropdown-header">신발</h5>
			    	<a class="dropdown-item" href="#">구두</a>
			    	<a class="dropdown-item" href="#">부츠</a>
			    	<a class="dropdown-item" href="#">러닝화</a>
			    	<a class="dropdown-item" href="#">캔버스/단화</a>
			    	<a class="dropdown-item" href="#">기타스니커즈</a>
			  </div>
			</div>
		</div>
	</div>
	<!-- End of Dropdown -->
</header>