<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<% int no = 1; %>

<!-- 메인 박스 시작 -->
<div class="frame">

<!-- 머리글 영역 시작 -->
<c:choose>
	<c:when test="${prodDto eq null }">
		<div class="favor-head border-btm-black">내가 작성한 Q&amp;A</div>
	</c:when>
	<c:otherwise>
		<div class="favor-head border-btm-black">현재 보고있는 상품</div>
		<div class="cart-table-content-row border-btm-black flex-center" id="cart-table-content-row-${prodDto.prodId}">
		<div class="cart-table-content-prodInfo">
			<div class="cart-table-content-prodInfo-img">
				<a class="cart-table-content-prodInfo-a" href="/shop/product?cmd=detail&prodNo=${prodDto.prodId}">
					<img src="${prodDto.imgUrl_1}" />
				</a>
			</div>
			<div class="cart-table-content-prodInfo-text">
				<p class="cart-table-content-prodInfo-p mgb10">${prodDto.companyName}</p>
				<strong class="cart-table-content-prodInfo-strong">${prodDto.productName}</strong>
			</div>
		</div>
		</div>
	</c:otherwise>
</c:choose>
<!-- 머리글 영역 끝 -->

<!-- 리뷰리스트 테이블 시작 -->
<div class="pageForAll-table border-btm-black mgb100">
	<div class="pageForAll-table-title f18-333">
	   <div class="pageForAll-table-title-no">번호</div>
	   <div class="pageForAll-table-title-detail pdl20">문의 내용</div>
	   <div class="pageForAll-table-title-writer">작성자</div>
	   <div class="pageForAll-table-title-date">날짜</div>
	</div>
	<div class="pageForAll-table-body">
		<c:forEach var="qna" items="${qnaList}">
		<div class="pageForAll-table-row">
			<div class="pageForAll-table-row-no"><%=no %></div>
			<div class="pageForAll-table-row-detail pdl20">
				<a href="<%=request.getContextPath()%>/qna?cmd=openQnaDetail&qnaId=${qna.id}">
				<c:choose>
					<c:when test="${empty qna.password}">
						${qna.detail}
					</c:when>
					<c:otherwise>
						비밀글입니다.
					</c:otherwise>
				</c:choose>
				</a>
			</div>
			<div class="pageForAll-table-row-writer">
				<c:if test="${empty qna.password}">
					${fn:substring(qna.name, 0, fn:length(qna.name)-1)}*
				</c:if>
			</div>
			<div class="pageForAll-table-row-date"><fmt:formatDate value="${qna.createDate}" type="date"/></div>
			<% no++; %>
		</div>
		</c:forEach>
	</div>
</div>
<!-- 리뷰리스트 테이블 끝 -->

</div>
<!-- 메인 박스 끝 -->

<%@ include file="../layout/footer.jsp"%>