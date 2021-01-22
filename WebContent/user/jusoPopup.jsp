<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>주소팝업</title>
<%
	String inputYn = request.getParameter("inputYn"); 
	String roadFullAddr = request.getParameter("roadFullAddr"); 
%>
</head>

<script language="javascript">

function init(){
	var url = location.href;
	var confmKey = "U01TX0FVVEgyMDIxMDEwNDE0MjAzMjExMDYyMDI=";
	var resultType = "4";
	var inputYn= "<%=inputYn%>";
	if(inputYn != "Y"){
		document.form.confmKey.value = confmKey;
		document.form.returnUrl.value = url;
		document.form.resultType.value = resultType; // resultType항목 추가(2016.10.06)
		document.form.action="https://www.juso.go.kr/addrlink/addrLinkUrl.do"; // 인터넷망
		document.form.submit();
	}else{
		/** API 서비스 제공항목 확대 (2017.02) **/
		opener.jusoCallBack("<%=roadFullAddr%>");
		window.close();
	}
}
</script>
<body onload="init();">
	<form id="form" name="form" method="post">
		<input type="hidden" id="confmKey" name="confmKey" value=""/>
		<input type="hidden" id="returnUrl" name="returnUrl" value=""/>
		<input type="hidden" id="resultType" name="resultType" value=""/>
	</form>
</body>
</html>