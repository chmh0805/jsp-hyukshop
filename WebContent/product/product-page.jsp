<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%	int qnaNo = 1; 
	int reviewNo = 1;
%>

<!-- 전체 박스 시작 -->
<div class="products-detail">
	<!-- 상단 박스 시작 -->
	<div class="products-detail-box">
		<div class="products-box-info">
			<!-- 이미지, 브랜드, 상품명, 가격, 사이즈선택  -->
			<div class="products-info-image swiper-container">
				<!-- 이미지 -->
				<div class="swiper-wrapper">
					<c:if test="${prodDto.imgUrl_1 != ''}">
					<div class="swiper-slide">
						<img src="${prodDto.imgUrl_1}" />
						<!-- 동기적으로 불러오기 -->
					</div>
					</c:if>
					<c:if test="${prodDto.imgUrl_2 != ''}">
					<div class="swiper-slide">
						<img src="${prodDto.imgUrl_2}" />
						<!-- 동기적으로 불러오기 -->
					</div>
					</c:if>
					<c:if test="${prodDto.imgUrl_3 != ''}">
					<div class="swiper-slide">
						<img src="${prodDto.imgUrl_3}" />
						<!-- 동기적으로 불러오기 -->
					</div>
					</c:if>
					<c:if test="${prodDto.imgUrl_4 != ''}">
					<div class="swiper-slide">
						<img src="${prodDto.imgUrl_4}" />
						<!-- 동기적으로 불러오기 -->
					</div>
					</c:if>
				</div>
			</div>
			<span class="products-info-desciption">※ 이미지를 좌우로 드래그하면 더 많은 이미지를 확인하실 수 있습니다.</span>
		</div>
		<div class="products-box-detail">
			<div class="products-box-detail-company">
				<!-- 제조사 -->
				<a href="${prodDto.url}"> <!-- 제조사 URL 동기적 -->
					<span class="products-box-detail-company-color">${prodDto.companyName}</span>
				</a>
			</div>
			<div class="products-box-detail-name">
				<!-- 상품명 -->
				<span>${prodDto.productName}</span>
			</div>
			<div class="products-box-detail-price">
				<!-- 가격 -->
				<span class="products-box-detail-price-figure">${prodDto.price}</span>
				<span>원</span>
			</div>
			<div class="products-box-detail-soldCount border-btm-e1e1e1">
				<!-- 판매량 -->
				<span class="products-box-detail-soldCount-figure">${prodDto.soldCount }</span> <span
					class="products-box-detail-soldCount-figure">개 구매중</span>
			</div>
			<div class="products-box-detail-postInfo border-btm-e1e1e1">
				<span class="products-box-detail-postInfo-title">배송정보</span> <span
					class="products-box-detail-postInfo-content">일반배송</span>
			</div>
			<div class="products-box-detail-realInfo border-btm-e1e1e1">
				<span class="products-box-detail-realInfo-title">정품인증</span> <span
					class="products-box-detail-realInfo-content">Hyukshop 내 모든 상품은 100%
					정품입니다.</span> <span class="products-box-detail-realInfo-popover"
					onclick="realInfoBox();"> ∨ </span>
				<div id="realInfo-box">
					&lt;Hyukshop 정품인증&gt;<br />
					Hyukshop에서 판매되는 모든 브랜드 상품은 정식제조, <br />
					정식수입원을 통해 유통되는 100% 정품임을 보증합니다.
				</div>
			</div>
			<div class="products-box-detail-allPrice">
				<span class="products-box-detail-allPrice-title">상품 금액</span>
				<span class="products-box-detail-allPrice-figure">
					<fmt:formatNumber value="${prodDto.price}" type="number" />
				</span>
			</div>
			<!-- 버튼 시작 -->
			<c:choose>
				<c:when test="${sessionScope.principal != null}">
					<button type="button" class="buy-btn" onclick="location.href='<%=request.getContextPath()%>/user?cmd=directBuy&prodId=${prodDto.prodId}&userId=${sessionScope.principal.id}';">바로 구매</button>
				</c:when>
				<c:otherwise>
					<button type="button" class="buy-btn" onclick="needLogin();">바로 구매</button>
				</c:otherwise>
			</c:choose>
			<!-- 장바구니 버튼 시작 -->
			<c:choose>
			<c:when test="${sessionScope.principal != null}">
				<c:choose>
					<c:when test="${isCart eq true }">
						<button type="button" class="cart-btn" onclick="rmvCart(${sessionScope.principal.id}, ${prodDto.prodId});">
							<i class="material-icons" style="color: red;">shopping_cart</i>
						</button>
					</c:when>
					<c:otherwise>
						<button type="button" class="cart-btn" onclick="addCart(${sessionScope.principal.id}, ${prodDto.prodId});">
							<i class="material-icons">shopping_cart</i>
						</button>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<button type="button" class="cart-btn" onclick="needLogin();">
					<i class="material-icons">shopping_cart</i>
				</button>
			</c:otherwise>
			</c:choose>
			<!-- 장바구니 버튼 끝 -->
			<!-- 찜 버튼 시작 -->
			<c:choose>
			<c:when test="${sessionScope.principal != null}">
				<c:choose>
					<c:when test="${isFavor eq true }">
						<button type="button" class="fav-btn" onclick="rmvFavor(${sessionScope.principal.id}, ${prodDto.prodId});">
							<i class="material-icons" style="color: red;">favorite</i>
						</button>
					</c:when>
					<c:otherwise>
						<button type="button" class="fav-btn" onclick="addFavor(${sessionScope.principal.id}, ${prodDto.prodId});">
							<i class="material-icons">favorite_border</i>
						</button>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<button type="button" class="fav-btn" onclick="needLogin();">
					<i class="material-icons">favorite_border</i>
				</button>
			</c:otherwise>
			</c:choose>
			<!-- 찜 버튼 끝 -->
			<!-- 버튼 끝 -->
		</div>
	</div>
	<!-- 상단 박스 끝 -->
	<!-- 아래 박스 전체 시작-->
	<div class="wrap-detail-info">
		<!-- 상품정보/리뷰/Q&A/주문정보 시작 -->
		<div class="tab-detail-info">
			<ul class="tab">
				<li class="active" id="tab-img-text"><a
					href="#detail-img-text-box" id="tab-img-text-a">상품정보</a></li>
				<li class="active" id="tab-review"><a href="#detail-review-box"
					id="tab-review-a">리뷰</a></li>
				<li class="active" id="tab-qna"><a href="#detail-qna-box"
					id="tab-qna-a">Q&amp;A</a></li>
				<li class="active" id="tab-purchaseInfo"><a
					href="#detail-purchaseInfo-box" id="tab-purchaseInfo-a">주문정보</a></li>
			</ul>
		</div>
		<!-- 상품정보/리뷰/Q&A/주문정보 끝 -->

		<!-- 상품 상세 설명 이미지/글 시작 -->
		<!-- 우측 하단 sticky  -->
		<div class="detail-sticky-go-to-top-btn-box">
			<a href="#" class="detail-sticky-go-to-top-btn-a">
				<img class="detail-sticky-go-to-top-btn-img" src="/shop/images/build/arrowUp.png" />
			</a>
		</div>
		<!-- 우측 하단 sticky 끝 -->
		<div id="detail-img-text-box">
			${prodDto.detail}
		</div>
		<!-- 상품 상세 설명 이미지/글 끝 -->

		<!-- 리뷰 시작 -->
		<div id="detail-review-box">
			<div class="detail-review-header">
				리뷰 (${countReview})
				<c:if test="${sessionScope.principal != null}">
					<a href="<%=request.getContextPath()%>/review?cmd=reviewWrite&prodNo=${prodDto.prodId}" class="detail-qna-header-a" id="detail-qna-write">리뷰작성</a>
				</c:if>
				<a class="detail-qna-header-a" href="<%=request.getContextPath()%>/review?cmd=reviewAll&prodNo=${prodDto.prodId}">전체보기</a>
			</div>
			<div class="detail-qna-body">
				<c:if test="${reviewList != null}">
					<c:forEach var="review" items="${reviewList}">
						<div class="detail-qna-item">
							<span class="detail-qna-item-number"><%=reviewNo %></span>
							<a href="<%=request.getContextPath()%>/review?cmd=openReviewDetail&reviewId=${review.id}" target="_blank">
							<span class="detail-qna-item-detail">${review.detail}</span>
							</a>
							<span class="detail-qna-item-writerName">
								${fn:substring(review.name, 0, fn:length(review.name)-1)}*
							</span>
							<span class="detail-qna-item-createDate">
								<fmt:formatDate value="${review.createDate}" type="date"/>
							</span>
						</div>
						<%reviewNo += 1;%>
					</c:forEach>
				</c:if>
			</div>
			
		</div>
		<!-- 리뷰 끝 -->

		<!-- Q&A 시작 -->
		<div id="detail-qna-box">
			<div class="detail-qna-header">
				Q&amp;A (${countQna})
				<c:if test="${sessionScope.principal != null}">
					<a href="<%=request.getContextPath()%>/qna?cmd=qnawrite&prodNo=${prodDto.prodId}" class="detail-qna-header-a" id="detail-qna-write">문의하기</a>
				</c:if>
				<a class="detail-qna-header-a" href="<%=request.getContextPath()%>/qna?cmd=qnaAll&prodNo=${prodDto.prodId}">전체보기</a>
			</div>
			<div class="detail-qna-body">
				<c:if test="${qnaList != null}">
					<c:forEach var="qna" items="${qnaList}">
						<div class="detail-qna-item">
							<span class="detail-qna-item-number"><%=qnaNo %></span>
							<a href="<%=request.getContextPath()%>/qna?cmd=openQnaDetail&qnaId=${qna.id}" target="_blank">
							<c:choose>
								<c:when test="${empty qna.password}">
									<span class="detail-qna-item-detail">${qna.detail}</span>
								</c:when>
								<c:otherwise>
									<span class="detail-qna-item-detail">비밀글입니다.</span>
								</c:otherwise>
							</c:choose>
							</a>
							<c:choose>
								<c:when test="${empty qna.password}">
									<span class="detail-qna-item-writerName">
										${fn:substring(qna.name, 0, fn:length(qna.name)-1)}*
									</span>
								</c:when>
								<c:otherwise>
									<span class="detail-qna-item-writerName">
										
									</span>
								</c:otherwise>
							</c:choose>
							<span class="detail-qna-item-createDate">
								<fmt:formatDate value="${qna.createDate}" type="date"/>
							</span>
						</div>
						<%qnaNo += 1;%>
					</c:forEach>
				</c:if>
			</div>

		</div>
		<!-- Q&A 끝 -->

		<!-- 주문정보 시작 -->
		<div id="detail-purchaseInfo-box">
			<div class="detail-purchaseInfo-header">
				주문정보 <span class="products-box-detail-realInfo-popover"
					onclick="addressInfoBox();"> ∨ </span>
			</div>
			<div id="addressInfo-box">
				<b>[배송 정보 ]</b><br />
				<p>
					고객센터 연락이 어려우니 게시판에 문의주시면 빠르게 답변드리도록 하겠습니다.<br />
					CJ대한통운(1588-1255)택배를 이용하며, 매일 오후 1시 전 주문까지만 당일발송합니다.<br /> 발송한날로부터
					1~3일 이내 받아보실수 있습니다.<br /> (택배사의 영업사정에 따라 배송지연이 있을 수 있습니다.)
				</p>
				<b>[ 교환/환불 정보 ]</b><br />
				<p>
					상품가치가 현저히 훼손된 경우를 제외한 모든 사유에 대해 환불이 가능합니다.<br /> 환불요청 가능 기간은 상품 수령
					후(배송완료 시점으로부터) 7일 이내입니다.<br /> 교환/환불이 발생하는 경우 그 원인을 제공한 자가 배송비를
					부담합니다.<br /> - 고객변심 : 최초 배송비+반품 배송비+(교환의 경우) 교환 배송비는 고객이 부담<br />
					- 판매자귀책 : 최초 배송비+반품 배송비+(교환의 경우) 교환 배송비는 판매자가 부담<br /> 다음의 경우는
					예외적으로 교환 및 환불이 불가능합니다.<br /> - 상품가치가 소비자의 귀책사유로 인해 현저하게 감소한 경우<br />
					- 소비자 과실로 인한 옷의 변색(예 : 착색, 화장품, 오염 등)<br /> - 착용으로 인한 니트류 상품의 늘어남
					발생 및 가죽 제품의 주름 발생<br /> - 기타 착용 흔적 : 택 제거 등<br /> - 구매확정된 주문의 경우<br />
					- 귀금속류의 경우는 소비자분쟁조정기준에 의거 교환만 가능합니다.<br /> (단, 함량미달의 경우에는 환불이 가능함)<br />
					구매자 단순변심은 상품수령후 7일이내(구매자 반품배송비 부담)
				</p>
			</div>
		</div>
		<!-- 주문정보 끝 -->

	</div>
	<!-- 아래 박스 전체 끝 -->

</div>

<script type="text/javascript" src="/shop/js/product.js"></script>

<%@ include file="../layout/footer.jsp"%>