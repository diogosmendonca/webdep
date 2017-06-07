<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- Variável criada para auxiliar na identificação do locale -->
<c:set var="lang" scope="session" value="${not empty param.lang ? param.lang : not empty lang ? lang : pageContext.request.locale}"/>

<!-- Necessário para utilizar o i18N, informar o locale e o bundle -->
<fmt:setLocale value="${ lang }"/>
<fmt:setBundle basename="Messages" />
<!DOCTYPE html>
<html>
<head>
<title><fmt:message key="br.cefetrj.webdep.jsp.ListaDefeito.title" /></title>
<jsp:include page="head.jspf" />
</head>
<body class="container-full ">
	<jsp:include page="scripts.jspf" />
	<%@include file="navbar.jspf"%>
	<div class="container">
		<form class="form-horizontal" method="post" action="FrontControllerServlet">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h5><fmt:message key="br.cefetrj.webdep.jsp.ListaDefeito.header" /></h5>
				</div>
				<div class="panel-body">
					<div class="form-group">
						<label class="control-label col-sm-2" for="pwd"><fmt:message key="br.cefetrj.webdep.jsp.ListaDefeito.initialDate" /></label>
							<div class="input-group">
								<input class="form-control" type="text" name="initialDate" id="initialDate" />
<!-- 								<span class="input-group-addon"> -->
<!-- 									<span class="glyphicon glyphicon-remove"></span> -->
<!-- 								</span>  -->
<!-- 								<span class="input-group-addon"> -->
<!-- 									<span class="glyphicon glyphicon-calendar"></span> -->
<!-- 								</span> -->
							</div>
						<label class="control-label col-sm-2" for="pwd"><fmt:message key="br.cefetrj.webdep.jsp.ListaDefeito.finalDate" /></label>
							<div class="input-group">
								<input class="form-control" type="text" name="finalDate" id="finalDate" />
<!-- 								<span class="input-group-addon"> -->
<!-- 									<span class="glyphicon glyphicon-remove"></span> -->
<!-- 								</span>  -->
<!-- 								<span class="input-group-addon"> -->
<!-- 									<span class="glyphicon glyphicon-calendar"></span> -->
<!-- 								</span> -->
							</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="pwd"><fmt:message key="br.cefetrj.webdep.jsp.ListaDefeito.urlPatterns" /></label>
							<div class="col-xs-2">
								<select  class="form-control padrao-select" name="url">
									<option value=" ">1</option>
								</select>
								<button class="btn btn-primary myModal" type="button" data-toggle="modal"
									data-target="#myModal">+</button>
									<button id="deletePadraoURL" name="deletepadraourl" class="btn btn-primary" 
									 type="button">-</button>
							</div>
						<button type="submit" class="btn btn-primary btn-md" name="action" value="ListaDefeitos"><fmt:message key="br.cefetrj.webdep.jsp.ListaDefeito.search" /></button>
					</div>
					<c:if test="${not empty dataIn and not dataIn }">
								<span class="help-block">
									<fmt:message key="br.cefetrj.webdep.jsp.ListaDefeito.dataError"/>
								</span>
					</c:if>
				</div>
			</div>
		</form>
			<div id="exTab2" class="panel panel-default">
				<ul class="nav nav-tabs ">
					<li class="active"><a href="#1a" data-toggle="tab"><fmt:message key="br.cefetrj.webdep.jsp.ListaDefeito.table" /></a></li>
				</ul>
				<div class="tab-content clearfix">
					<div class="tab-pane fade in active" id="1a">
						<table class="table table-striped">
						
							<thead>
								<tr>
									<th><fmt:message key="br.cefetrj.webdep.jsp.ListaDefeito.arquivo" /></th>
									<th><fmt:message key="br.cefetrj.webdep.jsp.ListaDefeito.funcao" /></th>
									<th><fmt:message key="br.cefetrj.webdep.jsp.ListaDefeito.linha" /></th>
									<th><fmt:message key="br.cefetrj.webdep.jsp.ListaDefeito.falhas" /></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${ listaDefeitos }" var="linha">
									<tr>
										<td>${linha.arquivo }</td>
										<td>${linha.nomArquivo }</td>
										<td>${linha.linhaCod }</td>
										<td>${linha.numFalha }</td>
									</tr>
								</c:forEach>
							</tbody>
						
						</table>
					</div>
				</div>
	 			${msg }
			</div>
	</div>
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
	
</body>
</html>