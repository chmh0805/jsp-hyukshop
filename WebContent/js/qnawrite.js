$(document).ready(function() {
	$('#qna-detail').summernote({
		height: 500,
		toolbar: [
    ['style', ['bold', 'italic', 'underline', 'clear']],
    ['font', ['strikethrough', 'superscript', 'subscript']],
    ['fontsize', ['fontsize']],
    ['color', ['color']],
    ['para', ['ul', 'ol', 'paragraph']],
    ['height', ['height']]
  ]
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
			successMessage();
		} else {
			failMessage();
		}
	})
}

function successMessage() {
	Swal.fire({
		html: '문의글이 등록되었습니다.',
		icon: 'success',
		timer: 2000,
		timerProgressbar: true,
		willClose: () => {
			clearInterval(timerInterval)
		}
	}).then((result) => {
		location.href = document.referrer;
	})
}

function failMessage() {
	Swal.fire({
		html: '문의글 등록 중 오류가 발생하였습니다.',
		icon: 'error',
		timer: 2000,
		timerProgressbar: true,
		willClose: () => {
			clearInterval(timerInterval)
		}
	}).then((result) => {
		location.href = document.referrer;
	})
}
