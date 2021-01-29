<%@page import="com.shop.shop.domain.user.NaverUser"%>
<%@page import="com.shop.shop.domain.user.KakaoUser"%>
<%@page import="com.shop.shop.util.Script"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>
<%
	if (request.getAttribute("kakaoId") == null) { // userType이 없으면 로그인상태 아님!!
		Script.back(response, "잘못된 접근입니다.");
	}
%>

<div class="mgb20 join-frm-header-title">
	<span class="join-frm-header-title-span f30-bd-111">추가정보 입력하기</span>
</div>
<div class="frame join-frm">
<article class="card-body" style="max-width: 700px; margin: auto;">
    <!-- 회원가입 form태그 시작 -->
	<form method="post" action="user?cmd=kakaoJoin">
	<input type="hidden" name="kakaoId" value="${kakaoId}" />
	<div class="form-group input-group fg-x700">
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
		 </div>
        <input name="name" class="form-control" type="text" value="${name}" readonly/>
    </div> <!-- form-group// -->
    <div class="form-group input-group fg-x700">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
		 </div>
        <input name="email" class="form-control" placeholder="Email 입력" type="email" required />
    </div> <!-- form-group// -->
    <div class="form-group input-group fg-x700">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-phone"></i> </span>
		</div>
    	<input name="phone" class="form-control" placeholder="휴대폰번호 입력('-' 포함)" type="text" required />
    </div> <!-- form-group// -->
    <div class="form-group input-group fg-x700">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-building"></i> </span>
		</div>
		<input name="address" id="Addr" class="form-control" placeholder="주소 입력" type="text" required onkeypress="return false;" style="caret-color: transparent !important;" />
		<input type="button" value="주소 검색" onclick="goPopup();" />
	</div> <!-- form-group end.// -->
    <div class="form-group input-group fg-x700">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
		</div>
        <input name="password" class="form-control" placeholder="비밀번호 입력" type="password" required />
    </div> <!-- form-group// -->
    <div class="fg-x700 form-group">
        <button type="submit" class="btn btn-primary btn-block"> 카카오로 회원가입 </button>
    </div> <!-- form-group// -->      
</form>
</article>
</div> <!-- card.// -->
<!--container end.//-->

<br><br>

<script>

function goPopup(){
    var pop = window.open("/shop/user/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes");
}

function jusoCallBack(roadFullAddr){
	$("#Addr").val(roadFullAddr);
}

</script>

<%@ include file="../layout/footer.jsp"%>