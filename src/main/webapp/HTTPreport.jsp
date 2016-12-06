<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Variável criada para auxiliar na identificação do locale -->
<c:set var="lang" scope="session"
	value="${not empty param.lang ? param.lang : not empty lang ? lang : pageContext.request.locale}" />

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/css/bootstrap-select.min.css">

<!-- Variável criada para auxiliar na identificação do locale -->
<c:set var="lang" scope="session"
	value="${not empty param.lang ? param.lang : not empty lang ? lang : pageContext.request.locale}" />

<!-- Necessário para utilizar o i18N, informar o locale e o bundle -->
<fmt:setLocale value="${ lang }" />
<fmt:setBundle basename="Messages" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/http-report.css">
<title><fmt:message key="br.cefetrj.webdep.jsp.http.title" /></title>
<jsp:include page="head.jspf" />

<c:if test="${okhttp ==null}">
	<jsp:forward page="/FrontControllerServlet">
		<jsp:param name="action" value="getListsParameter" />

	</jsp:forward>

</c:if>


</head>
<body class="container-full">
	<%@include file="navbar.jspf"%>
	<!-- 
	<form id="hidden_form" action="FrontControllerServlet" method="POST">
		<input type="hidden" name="getListsParameter" value="getListsParameter"
			id="hidden-form"  onload="document.getElementById('hidden_form').submit()"/>
	</form>
	 -->
	<!-- Campo de Filtros -->
	<div class="container-fluid">
	<form class="form-horizontal" action="FrontControllerServlet"
		method="POST">
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
						<select id="selectPadraoURL" class="form-control">
			<!-- essa parte está no meu caso de teste pode deixar que eu preencho conforme o usuario logado. Ass: Luan -->
						</select>
						<button class="btn btn-primary" type="button" data-toggle="modal"
							data-target="#myModal">+</button>
							<button id="deletePadraoURL" name="deletepadraourl" class="btn btn-primary" 
							 type="button">-</button>
					</div>
					<label class="control-label col-sm-2" for="pwd"><fmt:message
							key="br.cefetrj.webdep.jsp.http.HTTPerror" /></label>
					<div class="col-xs-2">
						<select name="errorList" class="selectpicker" multiple title=""
							id="errorList">
							<c:forEach items="${ errorList }" var="error">
								<option value="${ error.id }">${ error.codigo }</option>
							</c:forEach>
						</select>

					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-sm-2" for="pwd"><fmt:message
							key="br.cefetrj.webdep.jsp.http.version" /></label>
					<div class="col-xs-2">
						<select name="versionList" class="selectpicker" multiple title=""
							id="versionlist">
							<c:forEach items="${ versionList }" var="version">
								<option value="${ version.id }">${ version.nome }</option>
							</c:forEach>
						</select>
					</div>

					<label class="control-label col-sm-2" for="pwd"><fmt:message
							key="br.cefetrj.webdep.jsp.http.CodeHTTPok" /></label>
					<div class="col-xs-2">
						<select name="okList" class="selectpicker" multiple title=""
							id="oklist">
							<c:forEach items="${ okList }" var="ok">
								<option value="${ ok.id }">${ ok.codigo }</option>
							</c:forEach>
						</select>
						
							<button name="action" value="errorParameter" type="submit"
								class="btn btn-primary btn-md">
								<fmt:message key="br.cefetrj.webdep.jsp.apr.search" />
							</button>
					
					</div>

				</div>
				<c:if test="${ not empty versionValidate and not versionValidate }">
					<div class="alert alert-danger fade in alert-dismissible">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong><fmt:message key="br.cefetrj.webdep.jsp.http.atention" /></strong> <fmt:message key="br.cefetrj.webdep.jsp.http.versionField" />
					</div>
				</c:if>
				<c:if test="${ not empty okValidate and not okValidate }">
					<div class="alert alert-danger fade in alert-dismissible">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong><fmt:message key="br.cefetrj.webdep.jsp.http.atention" /></strong> <fmt:message key="br.cefetrj.webdep.jsp.http.okCode" />
					</div>
				</c:if>
				<c:if test="${ not empty errorValidate and not errorValidate }">
					<div class="alert alert-danger fade in alert-dismissible">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong><fmt:message key="br.cefetrj.webdep.jsp.http.atention" /></strong> <fmt:message key="br.cefetrj.webdep.jsp.http.errorCode" />
					</div>
				</c:if>
				
				<c:if test="${ empty sessionScope.idsistema}">
					<div class="alert alert-danger fade in alert-dismissible">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong><fmt:message key="br.cefetrj.webdep.jsp.http.atention" /></strong> <fmt:message key="br.cefetrj.webdep.jsp.http.sistemMes" /> 
					</div>
				</c:if>
			
				<c:if test="${ not empty errohttp }">
					<div class="alert alert-danger fade in alert-dismissible">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong><fmt:message key="br.cefetrj.webdep.jsp.http.atention" /></strong> <fmt:message key="br.cefetrj.webdep.jsp.http.dbError" /> 
					</div>
				</c:if>
			
				
			</div>
		</div>
	</form>

	<!-- Tabela  -->

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
			</div>

			<!-- Gráfico -->

			<div class="tab-pane fade" id="2a">
				<svg id="svg-chart" class="chart"></svg>
			</div>
		</div>
		
		<form action="home.jsp" method="POST">
			<button type="submit" class="btn btn-primary btn-md" value="redirectParameter" style="display: block; margin: 0 auto;">
				<fmt:message key="br.cefetrj.webdep.jsp.http.back" />
			</button>
		</form>
		
	</div>
	</div>

	<!-- MODAL NOVO PADRAO URL -->

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Novo Padrão de URL</h4>
				</div>
				<div class="modal-body">
					<div class="container">
						<div id="form-padrao-url" class="form-horizontal col-sm-11">
							<div class="row">
								<div id="div-nome" class="form-group has-feedback">
									<label class="col-sm-2 text-right control-label" for="padrao-url-nome">Nome: </label>
									<div class="col-sm-3">
										<input name="padrao-url-nome" placeholder="Por exemplo: 'Contém números'" id="padrao-url-nome" type="text"
											data-error="Favor preencher este campo." class="form-control" />
											<div id="nome-error" class="text-center help-block with-errors"></div>
									</div>
									
								</div>
								<div id="div-regex" class="form-group has-feedback">
									<label class="col-sm-2 text-right control-label" for="regex">Expressão Regular:</label>
									<div class="col-sm-4 input-group">
										<input id="regex" placeholder="Por exemplo: [0-9]" name="regex" type="text"
											class="form-control" required>
											<span class="input-group-btn text-right">
												<button id="submit-regex" class="btn btn-primary"
													type="button">Buscar</button>
											</span>
									</div>
									<div id="regex-error" class="col-sm-7 text-center help-block with-errors"></div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<table class="table table-bordered">
										<thead>
											<tr>
												<th>URLs Encontrados</th>
											</tr>
										</thead>
										<tbody id="table-url">

										</tbody>
									</table>
								</div>
							</div>
							<div class="modal-footer col-sm-6">
								<button id="submitpadraourl" type="button"
									class="btn btn-primary">Salvar</button>
								<button id="cancela-padrao-url" type="button"
									class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	 
	<jsp:include page="scripts.jspf" />
</body>
</html>