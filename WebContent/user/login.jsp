<%@page import="java.security.SecureRandom"%>
<%@page import="java.math.BigInteger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>
<% 
	SecureRandom random = new SecureRandom();
	String state = new BigInteger(130, random).toString();
	session.setAttribute("state", state);
%>

<div class="frame user-frm">
<article class="card-body" style="max-width: 400px; margin: auto;">
    <!-- 로그인 form태그 시작 -->
	<form method="post" action="user?cmd=login">
	<div class="form-group input-group fg-x400">
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
		 </div>
        <input name="username" class="form-control" placeholder="Id 입력" type="text" />
    </div> <!-- form-group// -->
    <div class="form-group input-group fg-x400">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
		</div>
        <input name="password" class="form-control" placeholder="비밀번호 입력" type="password">
    </div> <!-- form-group// -->
    <div class="fg-x400 form-group">
        <button type="submit" class="btn btn-primary btn-block"> 로그인  </button>
    </div> <!-- form-group// -->
   	</form>
   	<!-- 로그인 form태그 끝 -->
   	<div class="fg-x400">
	   	<!-- 카카오 로그인 시작 -->
	   	<a href="https://kauth.kakao.com/oauth/authorize?client_id=70b588e15fb703e050cb0b079505506f&redirect_uri=http://localhost/shop/kakao_oauth&response_type=code">
	   		<img class="login-frm-btn" src="/shop/images/user/kakao_login_medium_narrow.png" />
	 	</a>
	 	<!-- 카카오 로그인 끝 -->
	 	<!-- 네이버 로그인 시작 -->
	 	<a href="https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=EnduV23LJzy3ERKxeAyL&redirect_uri=http://localhost/shop/naver_oauth&state=<%=state %>" style="margin-left: 8px; float: right;">
	 		<img class="login-frm-btn" src="/shop/images/user/naver_login_medium_narrow.PNG" />
	 	</a>
	 	<!-- 네이버 로그인 끝 -->
 	</div>
</article>
</div> <!-- card.// -->
<!--container end.//-->

<br><br>

<%@ include file="../layout/footer.jsp"%>