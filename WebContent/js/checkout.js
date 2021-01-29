$("#checkout-middle-toolTip").tooltip();

$(window).on("scroll", function() {
	var scrollNow = window.scrollY;

    if(scrollNow > 900) {
        $(".checkout-wrp-right").css('position', 'absolute');
        $(".checkout-wrp-right").css('top', '67%');
    } else {
       $(".checkout-wrp-right").css('position', 'fixed');
       $(".checkout-wrp-right").css('top', '43%');
    }
});

var prodIdList = new Array();

$(function(){
	IMP.init('imp59232554');
	
	var prodIdBoxList = document.getElementsByClassName("check-out-box-prodId-for-js");
	for (var i = 0; i < prodIdBoxList.length; i++) {
		prodIdList.push(prodIdBoxList.item(i).value*1);
	}
});

function request_to_check(userId, allPrice, email, name, phone, address) {
	IMP.request_pay({
	    pg : 'inicis',
	    pay_method : 'card',
	    merchant_uid : userId + '_' + new Date().getTime(),
	    name : '주문명:결제테스트',
	    amount : allPrice,
	    buyer_email : email,
	    buyer_name : name,
	    buyer_tel : phone,
	    buyer_addr : address
	}, function(rsp) {
	    if ( rsp.success ) {
	    	prodIdList.forEach(function(element){
	    		$.ajax({
	    			type: "get",
	    			url: "/shop/product?cmd=soldCountUpdate&prodId="+element
	    		})
	    	});
	    	
	        var impTitle = '결제가 완료되었습니다.';
	        var impMsg = '고유ID : ' + rsp.imp_uid;
	        impMsg += '상점 거래ID : ' + rsp.merchant_uid;
	        impMsg += '결제 금액 : ' + rsp.paid_amount;
	        impMsg += '카드 승인번호 : ' + rsp.apply_num;

	        Swal.fire({
				title: impTitle,
				text: impMsg,
				icon: 'success',
				timer: 2000,
				timerProgressbar: true,
				willClose: () => {
					clearInterval(timerInterval)
				}
			}).then(() => {
				location.href='/shop/index.jsp';
			})
	    } else {
	        var impTitle = '결제에 실패하였습니다.';
	        var impMsg = rsp.error_msg;
	        
	        Swal.fire({
				title: impTitle,
				text: impMsg,
				icon: 'error',
				timer: 2000,
				timerProgressbar: true,
				willClose: () => {
					clearInterval(timerInterval)
				}
			}).then(() => {
				history.back();
			})
	    }
	});
};