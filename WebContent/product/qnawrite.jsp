<%@page import="com.shop.shop.util.Script"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%
	if (session.getAttribute("principal") == null) {
		Script.back(response, "잘못된 접근입니다.");
	}
%>

<div class="frame">
	<input type="hidden" id="userId" value="${sessionScope.principal.id}" required/>
	<input type="hidden" id="productId" value="${prodNo}" required/>
	
	<div class="qna-writer-title">
		<div class="optionNo">
			<select id="optionNo">
				<option value="1">배송관련</option>
				<option value="2">상품관련</option>
				<option value="3">기타</option>
			</select>
		</div>
		<div class="qna-name-header">작성자명 : </div>
		<div class="qna-name">
			<input type="text" value="${sessionScope.principal.name}" readonly />
		</div>
		<div class="qna-password-header">QnA 글 비밀번호를 입력하세요(4자 이내)</div>
		<div class="qna-password">
			<input type="password" id="password" required maxlength="4" />
		</div>
	</div>
	
	<textarea id="qna-detail"></textarea>
	<div class="qna-upload-btn" onclick="upload();">QnA글 등록</div>
</div>

<script type="text/javascript" src="/shop/js/qnawrite.js"></script>


<%@ include file="../layout/footer.jsp"%>