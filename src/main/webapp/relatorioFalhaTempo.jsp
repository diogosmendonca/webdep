<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- Variável criada para auxiliar na identificação do locale -->
<c:set var="lang" scope="session" value="${not empty param.lang ? param.lang : not empty lang ? lang : pageContext.request.locale}"/>

<!-- Necessário para utilizar o i18N, informar o locale e o bundle -->
<fmt:setLocale value="${ lang }"/>
<fmt:setBundle basename="Messages" />
<!DOCTYPE html>
<html>
<head>
<title><fmt:message key="br.cefetrj.webdep.jsp.falhatempo.title" /></title>
<jsp:include page="head.jspf" />
</head>
<body class="container-full ">
	<jsp:include page="scripts.jspf" />
	<%@include file="navbar.jspf"%>
	<div class="container">
		<form class="form-horizontal" method="post" action="FrontControllerServlet">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h5><fmt:message key="br.cefetrj.webdep.jsp.falhatempo.header" /></h5>
				</div>
				<div class="panel-body">
					<div class="form-group">
						<label class="control-label col-sm-2" for="pwd"><fmt:message key="br.cefetrj.webdep.jsp.falhatempo.initialDate" /></label>
						<div style="float: left" class="input-group date form_date col-sm-2" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
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
						<div style="float: left" class="input-group date form_date col-sm-2" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
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
					<div class="form-group">
						<label class="control-label col-sm-2" for="pwd"><fmt:message key="br.cefetrj.webdep.jsp.falhatempo.urlPatterns" /></label>
							<div class="col-xs-2">
								<select  class="form-control padrao-select">
								</select>
								<button class="btn btn-primary myModal" type="button" data-toggle="modal"
									data-target="#myModal">+</button>
									<button id="deletePadraoURL" name="deletepadraourl" class="btn btn-primary" 
									 type="button">-</button>
							</div>
						<button type="submit" class="btn btn-primary btn-md" name="action" value="relatorioFalhaTempo"><fmt:message key="br.cefetrj.webdep.jsp.falhatempo.search" /></button>
					</div>
					<c:if test="${not empty dataIn and not dataIn }">
								<span class="help-block">
									<fmt:message key="br.cefetrj.webdep.jsp.falhatempo.dataError"/>
								</span>
					</c:if>
				</div>
			</div>
		</form>
			<!-- Tabela  -->

	<div id="exTab1" class="panel panel-default">
		<ul class="nav nav-tabs ">
			<li class="active"><a href="#1a" data-toggle="tab"><fmt:message
						key="br.cefetrj.webdep.jsp.falhatempo.table" /></a></li>
			<li><a href="#2a" data-toggle="tab"><fmt:message
						key="br.cefetrj.webdep.jsp.falhatempo.graphic" /></a></li>
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
	
	<!-- MOVIDO PARA SCRIPTS.JSPF POIS CAUSA CONFLITOS
	script type="text/javascript" src="jquery/jquery-1.8.3.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
	<script type="text/javascript" src="js/locales/bootstrap-datetimepicker.<fmt:message key="br.cefetrj.webdep.jsp.datepicker" />.js" charset="UTF-8"></script>
	<script type="text/javascript">
	MOVIDO PARA FUNCOES.JS LINHA 66
		$('.form_date').datetimepicker({
			language : 'pt-BR',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0,
			format: "yyyy-mm-dd"
		});
		$('.form_time').datetimepicker({
			language : 'pt-BR',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 1,
			minView : 0,
			maxView : 1,
			forceParse : 0,
			format : "hh:ii:ss"
		});
	</script -->
	
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
