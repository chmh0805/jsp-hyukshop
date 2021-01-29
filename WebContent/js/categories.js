function getItemByCompId(compId) {
	$.ajax({
		type: "GET",
		url: "/shop/product?cmd=cateSort&compId="+compId,
		dataType: "json"
	}).done(function(result){
		makeSortResult(result);
	});
};

function getDefault() {
	$.ajax({
		type: "GET",
		url: "/shop/product?cmd=cateListDefault",
		dataType: "json"
	}).done(function(result){
		makeSortResult(result);
	});
};

function makeSortResult(result) {
	var target = $("#favor-prd-list");
	target.empty();
	
	for(product of result) {
		var productId = product.productId;
		var imgUrl = product.imgUrl_1;
		var companyName = product.companyName;
		var productName = product.productName;
		var price = product.price;
		var soldCount = product.soldCount;
		
		var newProduct = `<div class="favor-prd-box">`;
		newProduct += `<a class="favor-link-prod" href="/shop/product?cmd=detail&prodNo=${productId}"></a>`;
		newProduct += `<img src="${imgUrl}" class="main-prd-item-img" />`;
		newProduct += `<ul class="favor-prd-item">`;
		newProduct += `<li class="prd-item-company">${companyName}</li>`;
		newProduct += `<li class="prd-item-name">${productName}</li>`;
		newProduct += `<li class="prd-item-price">${price}</li>`;
		newProduct += `<li class="prd-item-soldCount">${soldCount}개구매중</li></ul></div>`;
		
		target.append(newProduct);
	}
};