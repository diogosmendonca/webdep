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
<body class="container-full">
	<%@include file="navbar.jspf"%>
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
							<button class="btn btn-primary" type="button" data-toggle="modal"
								data-target="#myModal">+</button>
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

				<button type="submit" class="btn btn-primary btn-md">
					<fmt:message key="br.cefetrj.webdep.jsp.http.back" />
				</button>

			</div>
			<div class="tab-pane fade" id="2a">Gráfico Aqui</div>
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
						<form id="form-padrao-url" class="form-horizontal col-sm-11">
							<div class="row">
								<div id="div-nome" class="form-group">
									<label class="col-sm-2 text-right control-label"
										for="padrao-url-nome">Nome: </label>
									<div class="col-sm-3">
										<input name="padrao-url-nome" id="padrao-url-nome" type="text"
											class="form-control" />
									</div>
								</div>
								<div id="div-regex" class="form-group">
									<label class="col-sm-2 text-right control-label" for="regex">Expressão
										Regular:</label>
									<div class="col-sm-3 input-group">
										<input id="regex" name="regex" type="text"
											class="form-control" /> <span
											class="input-group-btn text-right">
											<button id="submit-regex" name="action"
												class="btn btn-primary" value="buscaRegex" type="submit">Buscar</button>
										</span>
									</div>
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
								<button id="submit-padrao-url" name="action" type="submit"
									class="btn btn-primary" value="salvarPadrao">Salvar</button>
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Cancelar</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="scripts.jspf" />
</body>
</html>
