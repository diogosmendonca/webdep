<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import = "java.time.format.DateTimeFormatter" %>


<!-- Variável criada para auxiliar na identificação do locale -->
<c:set var="lang" scope="session" value="${not empty param.lang ? param.lang : not empty lang ? lang : pageContext.request.locale}" />

<c:choose>
	<c:when test="${ lang == 'pt_BR'}">
		<c:set var="dateTime" scope="page" value="dd-MM-yyyy hh:mm" />
	</c:when>
	
	<c:otherwise>
		<c:set var="dateTime" scope="page" value="MM-dd-yyyy hh:mm" />
	</c:otherwise>
</c:choose>

<!-- Necessário para utilizar o i18N, informar o locale e o bundle -->
<fmt:setLocale value="${ lang }" />
<fmt:setBundle basename="Messages" />
<!DOCTYPE html>
<html>
<head>
<title><fmt:message key="br.cefetrj.webdep.jsp.vs.title" /></title>
<jsp:include page="head.jspf" />
</head>
<body class="container-full ">
	<jsp:include page="scripts.jspf" />
	<%@include file="navbar.jspf"%>
	<div class="container">
		<form class="form-horizontal" method="post" action="FrontControllerServlet">
			<div class="form-group">
				<div class="col-sm-4">
					<input class="form-control" type="text" name="arg" id="arg">
				</div>
				<input class="btn btn-primary btn-md" type="submit" value="<fmt:message key="br.cefetrj.webdep.jsp.vs.search" />">
			</div>
		</form>
		
		<div class="tab-pane fade in active" id="1a">
			<table class="table table-striped">
				<thead>
					<tr>
						<th><fmt:message key="br.cefetrj.webdep.jsp.vs.system" /></th>
						<th><fmt:message key="br.cefetrj.webdep.jsp.vs.version" /></th>
						<th><fmt:message key="br.cefetrj.webdep.jsp.vs.dateTime" /></th>
						<th><fmt:message key="br.cefetrj.webdep.jsp.vs.change" /></th>
						<th><fmt:message key="br.cefetrj.webdep.jsp.vs.delete" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ list }" var="item">
							<tr>
								<td>${ item.sistema }</td>
								<td>${ item.nome }</td>
								<td>${ item.timestampLiberacao.format(DateTimeFormatter.ofPattern(dateTime)) }</td>
								<td><span class="glyphicon glyphicon-edit"></span></td>
								<td><span class="glyphicon glyphicon-remove"></span></td>
							</tr>
					</c:forEach>
				</tbody>
			</table>
			
			 <div class="row text-center">		
			<button type="submit" class="btn btn-primary btn-md"><fmt:message key="br.cefetrj.webdep.jsp.vs.back" /></button>
			</div>
	
		</div>
	</div>
</body>
</html>