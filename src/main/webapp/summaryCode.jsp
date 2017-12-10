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
		key="br.cefetrj.webdep.jsp.summarycode.title" /></title>
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
	<div class="alert alert-info" role="alert">
		<fmt:message key="br.cefetrj.webdep.jsp.summarycode.title" />
	</div>

	<!-- ################## SE TEM SISTEMA SELECIONADO ############################# -->
<c:choose>
	<c:when test="${ not empty idsistema }">


		<div class="container">
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
						<c:if test="${ not empty padraoUrlInvalido and not padraoUrlInvalido }">
			                <fmt:message key="br.cefetrj.webdep.jsp.acessofalha.patternField" />
			            </c:if>
						<label><fmt:message key="br.cefetrj.psw.excluirLog.dataInicial" />:</label>
			            <div class="input-group date form_date col-sm-6" data-date=""
			                 data-date-format="yyyy-mm-dd" data-link-field="dtp_input2"
			                 data-link-format="yyyy-mm-dd">
			                <div id="div-data" class="input-group">
			                    <input class="form-control" type="text" name="dataInicial" value="${ dataInicial }"
			                           readonly /> <span class="input-group-addon"><span
			                        class="glyphicon glyphicon-remove"></span></span> <span
			                        class="input-group-addon"><span
			                        class="glyphicon glyphicon-calendar"></span></span>
			                </div>
			            </div>
			            <label><fmt:message key="br.cefetrj.psw.excluirLog.dataFinal" />:</label>
			            <div class="input-group date form_date col-sm-6" data-date=""
			                 data-date-format="yyyy-mm-dd" data-link-field="dtp_input2"
			                 data-link-format="yyyy-mm-dd">
			                <div id="div-data" class="input-group">
			                    <input class="form-control" type="text" name="dataFinal" value="${ dataFinal }"
			                           readonly /> <span class="input-group-addon"><span
			                        class="glyphicon glyphicon-remove"></span></span> <span
			                        class="input-group-addon"><span
			                        class="glyphicon glyphicon-calendar"></span></span>
			                </div>
			            </div>
			            <br>
			            <c:if test="${ not empty dataInvalida and not dataInvalida }">
			                <fmt:message key="br.cefetrj.psw.excluirLog.dataInvalida" />
			            </c:if>
			            <br>
						<div class="form-group col-sm-5">
							<!-- ################ BOTÃO DE BUSCAR ##################################### -->
							<button type="submit" class="btn btn-primary btn-md" name="action" value="genCodeSummary"><fmt:message key="br.cefetrj.webdep.jsp.apr.search" /></button>
						</div>
						
						<c:if test="${not empty dataIn and not dataIn }">
							<span class="help-block">
								<fmt:message key="br.cefetrj.webdep.jsp.apr.dataError"/>
							</span>
						</c:if>
						
					</div>
				</div>
			</form>
			<table class="table table-striped">
	            <thead>
	                <tr>
	                    <th><fmt:message key="br.cefetrj.webdep.jsp.summarycode.httpcode" /></th>
	                    <th><fmt:message key="br.cefetrj.webdep.jsp.summarycode.access" /></th>
	                    <th><fmt:message key="br.cefetrj.webdep.jsp.summarycode.percenttotal" /></th>
	                </tr>
	            </thead>
	            <c:forEach var="entry" items="${ mapCodeAccess }" varStatus="status">
				    <tr>      
				      <td>${entry.key}</td>
				      <td>${entry.value}</td>
				      <td>${(entry.value / accessTotals) * 100 }</td>
				    </tr>
			    </c:forEach>	            
        	</table>
		</div>		
	
	</c:when>
	<c:otherwise>
		<!-- ################## SE NÃO TEM SISTEMA SELECIONADO ######################### -->
		<div class="alert alert-danger fade in alert-dismissible">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<fmt:message key="br.cefetrj.webdep.jsp.acessofalha.sistemaRequired" />
		</div>
	</c:otherwise>
	
</c:choose>	

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