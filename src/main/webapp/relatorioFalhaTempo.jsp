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
		key="br.cefetrj.webdep.jsp.acessofalha.title" /></title>
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
			<form class="form-horizontal" method="post" action="FrontControllerServlet">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h5><fmt:message key="br.cefetrj.webdep.jsp.falhatempo.header" /></h5>
				</div>
			<form class="form-horizontal" method="GET" action="FrontControllerServlet">
				<input type="hidden" name="action" value="relatorioFalhaTempo"/>
				<div class="panel-body">
					<div class="form-group">
						<label class="control-label col-sm-2" for="pwd"><fmt:message key="br.cefetrj.webdep.jsp.falhatempo.initialDate" /></label>
						<div style="float: left" class="input-group date form_date col-sm-2" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
							<div class="input-group">
								<input class="form-control" type="text" name="initialDate" id="initialDate" readonly />
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-remove"></span>
								</span> 
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
						</div>
						<label class="control-label col-sm-2" for="pwd"><fmt:message key="br.cefetrj.webdep.jsp.falhatempo.finalDate" /></label>
						<div style="float: left" class="input-group date form_date col-sm-2" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
							<div class="input-group">
								<input class="form-control" type="text" name="finalDate" id="finalDate" readonly />
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-remove"></span>
								</span> 
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
						</div>
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
							<div class="form-group col-sm-5">
							<!-- ################ BOTÃO DE BUSCAR ##################################### -->
							<button type="submit" class="btn btn-primary btn-md pull-right btn-space" name="action" value="relatorioFalhaTempo"><fmt:message key="br.cefetrj.webdep.jsp.acessofalha.btnBuscar"/></button>
							</div>
							</div>
						</div>
					</div>
					
					<c:if test="${not empty dataIn and not dataIn }">
								<span class="help-block">
									<fmt:message key="br.cefetrj.webdep.jsp.falhatempo.dataError"/>
								</span>
					</c:if>
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
										<th>URLs que retornaram pelo menos um código HTTP de Erro</th>
										<th><fmt:message key="br.cefetrj.webdep.jsp.http.access" /></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="url" items="${ contagemAcessosUrlsComFalha.keySet() }">
										<tr>
											<td>${ url }</td>
											<td>${ contagemAcessosUrlsComFalha.get(url) }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
			
						<!-- ########################################################################### -->
						<!-- ######################### GRÁFICO ######################################## -->
						<!-- ########################################################################### -->
						<div class="tab-pane fade" id="2a">
							<cmp:ChartTag tipoGrafico="scatterplot" dados="${ dadosGrafico }"/>
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
	<jsp:include page="scripts.jspf" />
</body>
</html>
