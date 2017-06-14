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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/http-report.css">
<title><fmt:message
		key="br.cefetrj.webdep.jsp.http.title" /></title>
<jsp:include page="head.jspf" />
<style type="text/css">
	.btn-space {
	    margin-right: 20px;
	}
</style>
</head>
<body class="container-full">
<!-- ########################################################################### -->
<!-- ############################ MENU  ######################################## -->
<!-- ########################################################################### -->

	<%@include file="navbar.jspf"%>

<div class="container">
	<!-- ################## SE TEM SISTEMA SELECIONADO ############################# -->
	<c:choose>
		<c:when test="${ not empty idsistema }">
			<!-- ################ MSGS DE VALIDAÇÃO DO FORM ##################################### -->
			<c:if test="${ not empty msgKeys }">
				<div class="alert alert-danger fade in alert-dismissible">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<c:forTokens items="${ msgKeys }" delims="," var="key">
						<c:if test="${ not empty key }">
							<fmt:message key="${ key }"/><br/>
						</c:if>
						</c:forTokens>
				</div>
			</c:if>
			
			<!-- ########################################################################### -->
			<!-- ############################ FILTROS ###################################### -->
			<!-- ########################################################################### -->
			<form class="form-horizontal" method="GET" action="FrontControllerServlet">
				<input type="hidden" name="action" value="errorParameter"/>
				<div class="panel panel-default">
					<div class="panel-heading">
						<!-- #################### CABEÇALHO DOS FILTROS  ############################## -->
						<h5><fmt:message key="br.cefetrj.webdep.jsp.apr.header" /></h5>
					</div>
					<div class="panel-body">
						<div class="form-group ${ padraoUrlStatus }">
							<!-- ################ CAMPO DE PADRÕES URL ################################ -->
							<label class="control-label col-sm-2" for="padroesUrl">
								<fmt:message key="br.cefetrj.webdep.jsp.acessofalha.URLpattern" />
							</label>
							<div class="col-sm-3 ">
								<nobr>
									<cmp:PadraoURLCombo nome="padraoUrl" usuarioId="${ usuario.id }" padraoSelecionado="${ param.padraoUrl }"/>
									<button class="btn btn-primary myModal" type="button" data-toggle="modal" data-target="#myModal">+</button>
									<button id="deletePadraoURL" name="deletepadraourl" class="btn btn-primary" type="button">-</button>
								</nobr>
							</div>
						</div>
						<div class="form-group ${ versoesStatus }">
							<!-- ################ VERSÕES  ############################################ -->
							<label class="control-label col-sm-2" for="padroesUrl">
								<fmt:message key="br.cefetrj.webdep.jsp.acessofalha.version" />
							</label>
							<div class="col-sm-3 ">
								<cmp:VersaoSistemaCombo nome="versoes" sistemaId="${ idsistema }" versoesSelecionadas="${ paramValues.versoes }" />
							</div>
						</div>
						<div class="form-group ${ codigosHttpOkStatus }">
							<!-- ################ CÓDIGO HTTP OK      ################################# -->
							<label class="control-label col-sm-2" for="codigosHttpOk">
								<fmt:message key="br.cefetrj.webdep.jsp.acessofalha.CodeHTTPok" />
							</label>
							<div class="col-sm-3 ">
								<cmp:CodigoHTTPCombo nome="codigosHttpOk" sistemaId="${ idsistema }" codigosSelecionados="${ paramValues.codigosHttpOk }"  />
							</div>
						</div>
						<div class="form-group ${ codigosHttpErroStatus }">
							<!-- ################ CÓDIGO HTTP ERRO ##################################### -->
							<label class="control-label col-sm-2" for="codigosHttpErro">
								<fmt:message key="br.cefetrj.webdep.jsp.acessofalha.HTTPerror" />
							</label>
							<div class="col-sm-3 ">
								<cmp:CodigoHTTPCombo nome="codigosHttpErro" sistemaId="${ idsistema }" codigosSelecionados="${ paramValues.codigosHttpErro }" />
							</div>
						</div>
						<div class="form-group col-sm-5">
							<!-- ################ BOTÃO DE BUSCAR ##################################### -->
							<button type="submit" class="btn btn-primary btn-md pull-right btn-space" name="action" value=""><fmt:message key="br.cefetrj.webdep.jsp.acessofalha.btnBuscar"/></button>
						</div>
					</div>
				</div>
			</form>
		
			<c:if test="${ formValido eq true }">
				<div id="exTab1" class="panel panel-default">
					<ul class="nav nav-tabs ">
						<li class="active"><a href="#1a" data-toggle="tab"><fmt:message
									key="br.cefetrj.webdep.jsp.apr.table" /></a></li>
						<li><a href="#2a" data-toggle="tab"><fmt:message
									key="br.cefetrj.webdep.jsp.apr.graphic" /></a></li>
					</ul>
					<div class="tab-content clearfix">
						
						<!-- ########################################################################### -->
						<!-- ######################### LISTAGEM ######################################## -->
						<!-- ########################################################################### -->
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
									<c:forEach var="url" items="${ contagemAcessosUrls.keySet() }">
										<tr>
											<td>${ url }</td>
											<td>${ contagemAcessosUrls.get(url) }</td>
											<td>${ contagemAcessosUrlsComFalha.get(url) }</td>
											<td>${ probAcessosUrlsComFalha.get(url) }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
			
						<!-- ########################################################################### -->
						<!-- ######################### GRÁFICO ######################################## -->
						<!-- ########################################################################### -->
						<div class="tab-pane fade" id="2a">
							<cmp:ChartTag tipoGrafico="scatterplotHttpReport" dados="${ dadosGrafico }"/>
						</div>
					</div>
				</div>
			</c:if>
	</c:when>
		<c:otherwise>
			<!-- ################## SE NÃO TEM SISTEMA SELECIONADO ######################### -->
			<div class="alert alert-danger fade in alert-dismissible">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<fmt:message key="br.cefetrj.webdep.jsp.acessofalha.sistemaRequired" />
			</div>
		</c:otherwise>
	</c:choose>
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