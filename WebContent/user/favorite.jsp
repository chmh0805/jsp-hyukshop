<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/layout/header.jsp" %>

<!-- 전체 박스 시작 -->
<div class="frame" style="border:1px solid black; height: 800px;">

<div class="favor-head border-btm-red">
	찜한 상품
</div>

<div class="favor-sidebar" style="border: 1px solid black;">
	<div class="favor-sidebar-title">상품 옵션</div>
	<div class="custom-control custom-checkbox favor-content">
	    <input type="checkbox" class="custom-control-input" id="notSoldout">
	    <label class="custom-control-label" for="notSoldout">품절 제외</label>
	</div>
	<div class="mgt10 favor-sidebar-title">찜한 상품</div>
	<div class="favor-content pdl0">BRAND</div>
	<ul class="favor-sidebar-compList">
		<li class="custom-control custom-checkbox favor-sidebar-comp">
		    <input type="checkbox" class="custom-control-input" id="compNo1">
		    <label class="custom-control-label" for="compNo1">LOUIS VUITTON</label>
		</li>
	</ul>
</div>




</div>
<!-- 전체 박스 끝 -->
<%@ include file="/layout/footer.jsp"%>