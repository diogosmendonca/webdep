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
		key="br.cefetrj.webdep.jsp.manteremail.cadastro" /></title>
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
				<input type="hidden" name="action" value="CadastroEmailNotificacao"/>
				<div class="panel panel-default">
					<div class="panel-heading">
						<!-- #################### CABEÇALHO DOS FILTROS  ############################## -->
						<h5><fmt:message key="br.cefetrj.webdep.jsp.apr.header" /></h5>
					</div>
					<div class="panel-body">
						<div class="form-group ${ codigosHttpErroStatus }">
							<!-- ################ CÓDIGO HTTP ERRO ##################################### -->
							<label class="control-label col-sm-2" for="codigosHttpErro">
								<fmt:message key="br.cefetrj.webdep.jsp.manteremail.campo.falha" />
							</label>
							<div class="col-sm-3 ">
								<cmp:CodigoHTTPCombo nome="codigosHttpErro" sistemaId="${ idsistema }" codigosSelecionados="${ paramValues.codigosHttpErro }" />
							</div>
						</div>
						<div class="form-group ${ padraoUrlStatus }">
							<!-- ################ CAMPO DE PADRÕES URL ################################ -->
							<label class="control-label col-sm-2" for="padroesUrl">
								<fmt:message key="br.cefetrj.webdep.jsp.manteremail.campo.padraourl" />
							</label>
							<div class="col-sm-3 ">
								<nobr>
									<cmp:PadraoURLCombo nome="padraoUrl" usuarioId="${ usuario.id }" padraoSelecionado="${ param.padraoUrl }"/>
								</nobr>
							</div>
						</div>
						<div class="form-group ${ sistemaStatus }">
							<label class="control-label col-sm-2" for="name"><fmt:message key="br.cefetrj.webdep.jsp.vr.system" /></label>
							<div class="col-xs-2">
								<cmp:ComboSistema userId="${ id }" selectedList="${ sys }" classCss="form-control"/>
								<c:if test="${not empty systemIn and not systemIn }">
									<span class="help-block">
										<fmt:message key="br.cefetrj.webdep.jsp.vr.systemError"/>
									</span>
								</c:if>	
							</div>
						</div>
						<div class="form-group ${ emailsStatus }">
							<!-- ################ EMAILS ################################# -->
							<label class="control-label col-sm-2" for="emails">
								<fmt:message key="br.cefetrj.webdep.jsp.manteremail.campo.email" />
							</label>
							<div class="col-sm-3 ">
								<input type="text" name="emails" maxlength="1000" class="form-control" placeholder="Insira os e-mails separados por vírgula"/>
							</div>
						</div>
						<div class="form-group col-sm-5">
							<!-- ################ BOTÃO DE BUSCAR ##################################### -->
							<button type="submit" class="btn btn-primary btn-md pull-right btn-space" name="action" value=""><fmt:message key="br.cefetrj.webdep.jsp.manteremail.campo.cadastrar"/></button>
						</div>
					</div>
				</div>
			</form>
		
		
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