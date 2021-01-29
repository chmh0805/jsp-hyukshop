$(document).ready(function() {
  $('#detail').summernote({
  	height: 500
  });
});

function upload() {
	var data = {
			productName: $("#productName").val(),
			companyId: $("#companyId").val(),
			price: $("#price").val(),
			detail: $("#detail").val(),
			imgUrl_1: $("#imgUrl_1").val(),
			imgUrl_2: $("#imgUrl_2").val(),
			imgUrl_3: $("#imgUrl_3").val(),
			imgUrl_4: $("#imgUrl_4").val(),
			writerId: $("#writerId").val()
	};

	$.ajax({
		type: "POST",
		url: "/shop/product?cmd=insert",
		data: JSON.stringify(data),
		contentType: "application/json; charset=utf-8",
		dataType: "text"
	}).done(function(result){
		if (result === '200') {
			alert('상품등록 성공');
			location.href = document.referrer;
		} else {
			alert('상품등록 실패');
			location.href = document.referrer;
		}
	})
}