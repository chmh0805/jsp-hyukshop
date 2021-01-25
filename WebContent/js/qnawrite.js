$(document).ready(function() {
	$('#qna-detail').summernote({
		height: 500
	});
});

function upload() {
	var data = {
		userId: $("#userId").val(),
		productId: $("#productId").val(),
		optionNo: $("#optionNo").val(),
		password: $("#password").val(),
		detail: $("#qna-detail").val()
	};

	$.ajax({
		type: "POST",
		url: "/shop/qna?cmd=insertQna",
		data: JSON.stringify(data),
		contentType: "application/json; charset=utf-8",
		dataType: "text"
	}).done(function(result){
		if (result === '200') {
			alert('문의글이 등록되었습니다.');
			history.back();
		} else {
			alert('문의글 등록 중 오류가 발생하였습니다.');
			history.back();
		}
	})
}
