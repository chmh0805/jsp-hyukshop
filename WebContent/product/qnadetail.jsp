<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="frame">
	
	<div class="qnadetail-box">
		<div class="qna-detail-box-header">
			<span class="qna-detail-box-header-title">문의분류</span>
			<span class="qna-detail-box-header-content">
				<c:choose>
					<c:when test="${detailQnaDto.optionNo == 1}">
						배송관련
					</c:when>
					<c:when test="${detailQnaDto.optionNo == 2}">
						상품관련
					</c:when>
					<c:otherwise>
						기타
					</c:otherwise>
				</c:choose>
			</span>		
					
			<span class="qna-detail-box-header-title">작성일시</span>
			<span class="qna-detail-box-header-content">
				${detailQnaDto.createDate }
			</span>
		</div>

		<div class="qna-detail-box-body-content">
			${detailQnaDto.detail }
		</div>
	</div>
	
	
</div>

<%@ include file="../layout/footer.jsp"%>