<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!-- Variável criada para auxiliar na identificação do locale -->
<c:set var="lang" scope="session" value="${not empty param.lang ? param.lang : not empty lang ? lang : pageContext.request.locale}" />

<!-- Necessário para utilizar o i18N, informar o locale e o bundle -->
<fmt:setLocale value="${ lang }" />
<fmt:setBundle basename="Messages" />
<!DOCTYPE html>
<html>
<head>
<title><fmt:message key="br.cefetrj.webdep.jsp.vs.title" /></title>
<jsp:include page="head.jspf" />
</head>
<body class="container ">

	<form class="form-horizontal">
		<div class="form-group">
			<div class="col-sm-4">
				<input class="form-control" type="text">
			</div>
			<input class="btn btn-primary btn-md" type="submit" value="<fmt:message key="br.cefetrj.webdep.jsp.vs.search" />">
		</div>
	</form>
	
	<div class="tab-pane fade in active" id="1a">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>URLs</th>
					<th><fmt:message key="br.cefetrj.webdep.jsp.vs.system" /></th>
					<th><fmt:message key="br.cefetrj.webdep.jsp.vs.version" /></th>
					<th><fmt:message key="br.cefetrj.webdep.jsp.vs.dateTime" /></th>
					<th><fmt:message key="br.cefetrj.webdep.jsp.vs.change" /></th>
					<th><fmt:message key="br.cefetrj.webdep.jsp.vs.delete" /></th>
				</tr>
			</thead>
			<tbody>
				<tr>
				</tr>
			</tbody>
		</table>
		
		 <div class="row text-center">		
		<button type="submit" class="btn btn-primary btn-md"><fmt:message key="br.cefetrj.webdep.jsp.vs.back" /></button>
		</div>

	</div>

</body>
</html>