$("#cart-middle-toolTip").tooltip();

function rmvCart(id, userId, prodId) {
	$("#cart-table-content-row-"+id).remove();
	
	var dto = {
		userId: userId,
		prodId: prodId
	};

	$.ajax({
		type: "POST",
		url: "/shop/cart?cmd=cartRmv",
		data: JSON.stringify(dto),
		contentType: "application/json; charset=utf-8",
		dataType: "text"
	}).done(function(result){
		alert('장바구니에서 삭제되었습니다.');
		location.reload();
	})
};

function addFavor(userId, prodId) {
	var dto = {
		userId: userId,
		prodId: prodId
	};

	$.ajax({
		type: "POST",
		url: "/shop/favor?cmd=favAdd",
		data: JSON.stringify(dto),
		contentType: "application/json; charset=utf-8",
		dataType: "text"
	}).done(function(result){
		if (result === '200') {
			location.reload();
		}
	})
};

function rmvFavor(userId, prodId) {
	var dto = {
		userId: userId,
		prodId: prodId
	};

	$.ajax({
		type: "POST",
		url: "/shop/favor?cmd=favRmv",
		data: JSON.stringify(dto),
		contentType: "application/json; charset=utf-8",
		dataType: "text"
	}).done(function(result){
		if (result === '200') {
			location.reload();
		}
	})
};