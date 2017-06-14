<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cmp" uri="WEB-INF/components.tld"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="head.jspf"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ComboSistemaTestDriver</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/css/bootstrap-select.min.css">
</head>
<body>
	<!-- http://localhost:8080/webdep/comboSistemaTestDriver.jsp?userId=1&selected=1,2 -->
	<cmp:ComboSistema userId="${ param.userId }" selectedList="${param.selected }" 
			attributes="multiple onchange='javascript:showId()'" classCss="selectpicker" />
		
	<jsp:include page="scripts.jspf"/>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/js/bootstrap-select.min.js"></script>
</body>
</html>
<script>

	function showId(){
		alert(document.getElementById('sistema').value);
	}

</script>