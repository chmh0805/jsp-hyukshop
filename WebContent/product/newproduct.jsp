<%@page import="com.shop.shop.domain.user.User"%>
<%@page import="com.shop.shop.util.Script"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/layout/header.jsp" %>
<%
	User principal = (User) session.getAttribute("principal");
	if ((principal == null) || !(principal.getAuth().equals("admin"))) {
		Script.back(response, "잘못된 접근입니다.");
	}
%>

<div class="frame">
	<table class="product-insert-table">
	  <tr>
	    <th>작성자</th>
	    <th>상품명</th>
	    <th>회사코드</th>
	    <th>가격</th>
	  </tr>
	  <tr>
	    <td><input id="writerId" value="${sessionScope.principal.id}" readonly required /></td>
	    <td><input id="productName" type="text" required/></td>
	    <td><input id="companyId" type="text" required/></td>
	    <td><input id="price" type="text" required/></td>
	   <tr>
	    <th>이미지url(필수)</th>
	    <th>이미지url</th>
	    <th>이미지url</th>
	    <th>이미지url</th>
	  </tr>
	  <tr>
	    <td><input id="imgUrl_1" type="text" required/></td>
	    <td><input id="imgUrl_2" type="text" /></td>
	    <td><input id="imgUrl_3" type="text" /></td>
	    <td><input id="imgUrl_4" type="text" /></td>
	  </tr>
	</table>
	<textarea id="detail" rows="10"></textarea>
	<button onclick="upload();">상품등록</button>
</div>

<script type="text/javascript" src="/shop/js/newproduct.js"></script>

<%@include file="/layout/footer.jsp" %>