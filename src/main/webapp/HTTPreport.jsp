<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Variável criada para auxiliar na identificação do locale -->
<c:set var="lang" scope="session"
	value="${not empty param.lang ? param.lang : not empty lang ? lang : pageContext.request.locale}" />

<!-- Necessário para utilizar o i18N, informar o locale e o bundle -->
<fmt:setLocale value="${ lang }" />
<fmt:setBundle basename="Messages" />

<!DOCTYPE html>
<html>
<head>
<title><fmt:message key="br.cefetrj.webdep.jsp.http.title" /></title>
<jsp:include page="head.jspf" />
</head>
<body class="container ">
	<form class="form-horizontal">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h5>
					<fmt:message key="br.cefetrj.webdep.jsp.apr.header" />
				</h5>
			</div>

			<div class="panel-body">
				<div class="form-group">
					<label class="control-label col-sm-2" for="pwd"><fmt:message
							key="br.cefetrj.webdep.jsp.http.URLpattern" /></label>
					<div class="col-xs-2">
						<select class="form-control">
							<option>URLs Sistema</option>
						</select>
					</div>
					<label class="control-label col-sm-2" for="pwd"><fmt:message
							key="br.cefetrj.webdep.jsp.http.HTTPerror" /></label>
					<div class="col-xs-2">
						<select class="form-control">
							<option>500 , 400</option>
						</select>
					</div>
				</div>


				<div class="form-group">
					<label class="control-label col-sm-2" for="pwd"><fmt:message
							key="br.cefetrj.webdep.jsp.http.version" /></label>
					<div class="col-xs-2">
						<select class="form-control">
							<option>1.7.1, 1.7.2</option>
						</select>
					</div>
					<label class="control-label col-sm-2" for="pwd"><fmt:message
							key="br.cefetrj.webdep.jsp.http.CodeHTTPok" /></label>
					<div class="col-xs-2">
						<select class="form-control">
							<option>200</option>
						</select>
						<button type="submit" class="btn btn-primary btn-md">
							<fmt:message key="br.cefetrj.webdep.jsp.apr.search" />
						</button>
					</div>
				</div>
			</div>

		</div>
	</form>
	<div id="exTab1" class="panel panel-default">
		<ul class="nav nav-tabs ">
			<li class="active"><a href="#1a" data-toggle="tab"><fmt:message
						key="br.cefetrj.webdep.jsp.apr.table" /></a></li>
			<li><a href="#2a" data-toggle="tab"><fmt:message
						key="br.cefetrj.webdep.jsp.apr.graphic" /></a></li>
		</ul>
		<div class="tab-content clearfix">
			<div class="tab-pane fade in active" id="1a">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>URLs</th>
							<th><fmt:message key="br.cefetrj.webdep.jsp.http.access" /></th>
							<th><fmt:message key="br.cefetrj.webdep.jsp.http.fail" /></th>
							<th><fmt:message key="br.cefetrj.webdep.jsp.http.probFail" /></th>
						</tr>
					</thead>
					<tbody>
						<tr>
						</tr>
					</tbody>
				</table>
				
				<button type="submit" class="btn btn-primary btn-md"><fmt:message key="br.cefetrj.webdep.jsp.http.back" /></button>

			</div>
			<div class="tab-pane fade" id="2a">Gráfico Aqui</div>
		</div>
	</div>
	<jsp:include page="scripts.jspf" />
</body>
</html>
