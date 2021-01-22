<%@page import="com.shop.shop.domain.user.NaverUser"%>
<%@page import="com.shop.shop.domain.user.KakaoUser"%>
<%@page import="com.shop.shop.util.Script"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>
<%
	String name = "";
	String id = "";
	String email = "";
	String phone = "";
	
	if (session.getAttribute("userType") == null) { // userType이 없으면 로그인상태 아님!!
		Script.back(response, "잘못된 접근입니다.");
	} else if (session.getAttribute("userType") == "kakao") {
		KakaoUser kakaoInfo = (KakaoUser) session.getAttribute("kakaoUser");
		id = "KAKAO_"+kakaoInfo.getId();
		name = kakaoInfo.getNickname();
	} else if (session.getAttribute("userType") == "naver") {
		NaverUser naverInfo = (NaverUser) session.getAttribute("naverUser");
		id = "NAVER_"+naverInfo.getId();
		name = naverInfo.getName();
		email = naverInfo.getEmail();
		phone = naverInfo.getMobile();
	}
%>


<div class="frame join-frm">
<article class="card-body" style="max-width: 700px; margin: auto;">
    <!-- 회원가입 form태그 시작 -->
	<form method="post" action="user?cmd=update">
	<div class="form-group input-group fg-x700">
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
		 </div>
        <input name="name" class="form-control" type="text" value="<%=name %>" readonly/>
    </div> <!-- form-group// -->
	<div class="form-group input-group fg-x700">
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
		 </div>
        <input name="username" class="form-control" placeholder="id 입력" value="<%=id %>" type="text" required />
    </div> <!-- form-group// -->
    <div class="form-group input-group fg-x700">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
		 </div>
        <input name="email" class="form-control" placeholder="Email 입력" value="<%=email %>" type="email" required />
    </div> <!-- form-group// -->
    <div class="form-group input-group fg-x700">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-phone"></i> </span>
		</div>
    	<input name="phone" class="form-control" placeholder="휴대폰번호 입력" value="<%=phone %>" type="text" required />
    </div> <!-- form-group// -->
    <div class="form-group input-group fg-x700">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-building"></i> </span>
		</div>
		<input name="address" id="Addr" class="form-control" placeholder="주소 입력" type="text" readonly required />
		<input type="button" value="주소 검색" onclick="goPopup();" />
	</div> <!-- form-group end.// -->
    <div class="form-group input-group fg-x700">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
		</div>
        <input name="password" class="form-control" placeholder="비밀번호 입력" type="password" required />
    </div> <!-- form-group// -->
    <div class="fg-x700 form-group">
        <button type="submit" class="btn btn-primary btn-block"> 회원정보수정 </button>
    </div> <!-- form-group// -->      
</form>
</article>
</div> <!-- card.// -->
<!--container end.//-->

<br><br>

<script>

function goPopup(){
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
    var pop = window.open("/hyukshop/user/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes");
}

function jusoCallBack(roadFullAddr){
	$("#Addr").val(roadFullAddr);
}

</script>

<%@ include file="../layout/footer.jsp"%>