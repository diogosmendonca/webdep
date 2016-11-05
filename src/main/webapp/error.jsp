<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- Variável criada para auxiliar na identificação do locale -->
<c:set var="lang" scope="session" value="${not empty param.lang ? param.lang : not empty lang ? lang : pageContext.request.locale}"/>

<!-- Necessário para utilizar o i18N, informar o locale e o bundle -->
<fmt:setLocale value="${ lang }"/>
<fmt:setBundle basename="Messages" />

<!DOCTYPE html>
<html>
	<head>
	    <title><fmt:message key="br.cefetrj.webdep.jsp.error.title" /></title>
		<jsp:include page="head.jspf"/>
  	</head>
	<body>
		<h2><fmt:message key="br.cefetrj.webdep.jsp.error.msg" /></h2>
		<jsp:include page="scripts.jspf"/>
	</body>
</html>
