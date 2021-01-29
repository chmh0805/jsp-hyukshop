<%@page import="java.security.SecureRandom"%>
<%@page import="java.math.BigInteger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<div class="frame user-frm">
<article class="card-body" style="max-width: 400px; margin: auto;">
    <!-- 로그인 form태그 시작 -->
	<form method="post" action="qna?cmd=qnaDetail">
	<input type="hidden" name="qnaId" value="${qnaId}" />
	
    <div class="form-group input-group fg-x400" style="padding-top: 150px;">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
		</div>
        <input name="password" class="form-control" placeholder="비밀번호 입력" type="password">
    </div> <!-- form-group// -->
    <div class="fg-x400 form-group">
        <button type="submit" class="btn btn-primary btn-block"> 비밀번호 확인 </button>
    </div> <!-- form-group// -->
   	</form>
   	<!-- 로그인 form태그 끝 -->
</article>
</div> <!-- card.// -->
<!--container end.//-->

<br><br>

<script>

</script>

<%@ include file="../layout/footer.jsp"%>