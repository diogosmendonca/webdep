<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Taglib necessária para acessar as funções de formatação -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- Variável criada para auxiliar na identificação do locale -->
<c:set var="lang" scope="session" value="${not empty param.lang ? param.lang : not empty lang ? lang : pageContext.request.locale}"/>

<!-- Necessário para utilizar o i18N, informar o locale e o bundle -->
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="Messages" />

<!DOCTYPE html>
<html>
<head>
<title>WebDep</title>
<!-- Evitando que browsers antigos alterem o encoding da página -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<!-- Latest compiled and minified JavaScript -->
<script src="js/bootstrap.min.js"></script>
</head>
<body class="container-full ">
    <div class="container-fluid wrapper">
        <div class="panel panel-default">
            <div class="panel-heading" align="left">
                <h4><b><font color="black" style="font-family: Times;">WebDep</font></b></h4>
            </div>
            <div class="panel-body"align="center">
                <div class="container " style="margin-top: 10%; margin-bottom: 10%;">
                	<div class="panel panel-default" style="max-width: 45%;" align="left">
                        <div class="panel-heading form-group">
                            <b><font style= "font-size: 16px"><fmt:message key="br.cefetrj.webdep.jsp.error.title" /></font></b>
                        </div>
                        <div class="panel-body" >
                        	<p><fmt:message key="br.cefetrj.webdep.jsp.error.msg" />
                        	<a href="/webdep/index.jsp">
                        	<fmt:message key="br.cefetrj.webdep.jsp.error.home" /></a>.</p>
                        </div>
                    </div>  
                </div>
            </div>
        </div>
    </div>
</body>
</html>
