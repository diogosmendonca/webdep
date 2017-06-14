<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang" scope="session" value="${not empty param.lang ? param.lang : not empty lang ? lang : pageContext.request.locale}"/>
<fmt:setLocale value="${ lang }"/>
<fmt:setBundle basename="Messages" />

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="head.jspf"/>
	<title><fmt:message key="br.cefetrj.psw.user.label.deletehead"/></title>
</head>
<body>
    <%@include file="navbar.jspf"%>
	<div style="padding: 5%">
		<div class="panel panel-default">
		  <div class="panel-heading"><fmt:message key="br.cefetrj.psw.user.label.deletehead" /></div>
		  <div class="panel-body">
			<div class="form-group" >
				<p><fmt:message key="br.cefetrj.psw.user.label.delete"/></p>
				<form action="FrontControllerServlet" method="POST" >			
					<input type="hidden" name="action" value="deletaUsuario" />
					<input type="hidden" name="id" value="${ usuario.id }">
					<input type="submit" class="btn btn btn-primary" value="<fmt:message key="br.cefetrj.psw.user.bt_delete_sim"/>">
					<a href="FrontControllerServlet?action=listaUsuario&get=true" class="btn btn btn-danger" style="margin-left: 5px;"><fmt:message key="br.cefetrj.psw.user.bt_delete_nao"/></a>
				</form>
		  	</div>
		  </div>
		</div>
	</div>
	<jsp:include page="scripts.jspf"/>
</body>
</html>
