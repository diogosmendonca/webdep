<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="cmp" uri="WEB-INF/components.tld"%>

<!-- Variável criada para auxiliar na identificação do locale -->
<c:set var="lang" scope="session"
	value="${not empty param.lang ? param.lang : not empty lang ? lang : pageContext.request.locale}" />

<!-- Necessário para utilizar o i18N, informar o locale e o bundle -->
<fmt:setLocale value="${ lang }" />
<fmt:setBundle basename="Messages" />
<!DOCTYPE html>
<html>
<head>
<jsp:include page="head.jspf" />
<title> <fmt:message key="br.cefetrj.webdep.jsp.form.sistema.cadastro_sistema"/></title>
</head>
<body>
	<%@include file="navbar.jspf"%>
	<div class="alert alert-info" role="alert">
		<fmt:message key="br.cefetrj.webdep.jsp.form.sistema.cadastro_sistema"/>
	</div>
	<div id="sistema-form" class="form-horizontal container">
		<div class="row">
			<fieldset class="form-group">
				<legend>
					<fmt:message key="br.cefetrj.webdep.jsp.form.sistema.titulo1" />
				</legend>
				<div class="col-sm-6">
					<label class="text-right col-sm-6"><fmt:message
							key="br.cefetrj.webdep.jsp.form.sistema.nome" /></label>
					<div id="div-nome" class="input-group">
						<input class="form-control" id="nome" name="nome" type="text" />
					</div>
					<br> <label class="text-right col-sm-6"><fmt:message
							key="br.cefetrj.webdep.jsp.form.sistema.servidor" /></label>
					<div id="div-servidor" class="input-group">
						<!--  <select class="form-control" id="selection" name="selection"><option>Apache</option></select>-->
						<cmp:ComboCadastroSistemaServidor serverId="${ id }"
							classCss="form-control" />
					</div>
				</div>
			</fieldset>
		</div>
		<div class="row">
			<fieldset class="form-group">
				<legend>Logs</legend>
				<div class="col-sm-6">
					<label class="text-right col-sm-6"><fmt:message
							key="br.cefetrj.webdep.jsp.form.sistema.fmtLogs" /></label>
					<div id="div-formatoLog" class="input-group">
						<select class="form-control" id="formatoLog" name="formatoLog"></select>
					</div>
					<br> <label class="text-right col-sm-6"><fmt:message
							key="br.cefetrj.webdep.jsp.form.sistema.ptLogs" /></label>
					<div id="div-pasta-acesso" class="input-group">
						<input class="form-control" id="ptLogs" name="ptLogs" type="text" />
					</div>
					<br> <label class="text-right col-sm-6"><fmt:message
							key="br.cefetrj.webdep.jsp.form.sistema.pxLogs" /></label>
					<div id="div-prefixo-acesso" class="input-group">
						<input type="text" id="pxLogs" name="pxLogs" class="form-control">
						<span class="input-group-btn">
							<button id="pxLogs-teste-btn" class="btn btn-info" type="button">
								<fmt:message key="br.cefetrj.webdep.jsp.form.sistema.btnTeste" />
							</button>
						</span>
					</div>
					<br> <label class="text-right col-sm-6"><fmt:message
							key="br.cefetrj.webdep.jsp.form.sistema.ptErroLogs" /></label>
					<div id="div-pasta-erro" class="input-group">
						<input class="form-control" id="ptLogs2" name="ptLogs2"
							type="text" />
					</div>
					<br> <label class="text-right col-sm-6"><fmt:message
							key="br.cefetrj.webdep.jsp.form.sistema.pxErroLogs" /></label>
					<div id="div-prefixo-erro" class="input-group">
						<input type="text" class="form-control" id="pxLogs2"
							name="pxLogs2"> <span class="input-group-btn">
							<button id="pxLogs2-teste-btn" class="btn btn-info" type="button">
								<fmt:message key="br.cefetrj.webdep.jsp.form.sistema.btnTeste" />
							</button>
						</span>
					</div>
					<br>
				</div>
			</fieldset>
		</div>
		<div class="row">
			<fieldset class="form-group">
				<legend>
					<fmt:message key="br.cefetrj.webdep.jsp.form.sistema.titulo3" />
				</legend>
				<div class="col-md-12">
					<label class="text-right col-md-3"><fmt:message
							key="br.cefetrj.webdep.jsp.form.sistema.data" /></label>
					<div class="input-group date form_date col-sm-2"
						id="div-dataLeitura" data-date="" data-date-format="yyyy-mm-dd"
						data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
						<div class="input-group">
							<input type="text" id="dataLeitura" class="form-control"
								placeholder="aaaa-mm-dd" name="dataLeitura" /> <span
								class="input-group-addon"> <span
								class="glyphicon glyphicon-remove"></span>
							</span> <span class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
					<br> <label class="text-right col-md-3"><fmt:message
							key="br.cefetrj.webdep.jsp.form.sistema.hora" /></label>
					<div class="input-group date form_time col-sm-2" data-date=""
						data-date-format="hh:ii" data-link-field="dtp_input3"
						data-link-format="hh:ii">
						<div id="div-horarioLeitura" class="input-group">
							<input type="text" id="horarioLeitura" class="form-control"
								placeholder="hh:mm" name="horarioLeitura" /> <span
								class="input-group-addon"> <span
								class="glyphicon glyphicon-remove"></span>
							</span> <span class="input-group-addon"> <span
								class="glyphicon glyphicon-time"></span>
							</span>
						</div>
					</div>
					<br>
					<!--    <label class="text-right col-md-3"><fmt:message
                            key="br.cefetrj.webdep.jsp.form.sistema.nova" /></label>
                            <div class="input-group form-inline">
                            <input type="text" id="novaLeituraDia" size="2" class="form-control"/>
                            <input type="text" id="novaLeituraHora" class="form-control"/>
                            </div>
                            <br> -->
					<div class="form-inline">
						<div id="div-novaLeitura" class="form-group col-md-12">
							<label class="text-right col-md-3"><fmt:message
									key="br.cefetrj.webdep.jsp.form.sistema.nova" /></label> <input
								type="text" id="novaLeituraDia" size="2" name="novaLeituraDia"
								class="form-control" placeholder="dd" />
							<div class="input-group date form_time col-sm-2" data-date=""
								data-date-format="hh:ii" data-link-field="dtp_input3"
								data-link-format="hh:ii">
								<input type="text" id="novaLeituraHora" class="form-control"
									placeholder="hh:mm" name="novaLeituraHora" /> <span
									class="input-group-addon"> <span
									class="glyphicon glyphicon-remove"></span>
								</span> <span class="input-group-addon"> <span
									class="glyphicon glyphicon-time"></span>
								</span>
							</div>
						</div>
					</div>
				</div>
				<br>
			</div>
		<div class="row">
			<fieldset class="form-group">
				<legend>Periodicidade</legend>
				<div class="col-sm-6" id="div-periodicidade">
					<label class="text-right col-sm-6"><fmt:message
							key="br.cefetrj.webdep.jsp.form.sistema.periodicidade" /></label>
					<div class="input-group">
						<input type="number" min="1" max="999" name="periodicidade" id="periodicidade" class="form-control" />
					</div>
				</div>
			</fieldset>
		</div>
		<div class="row text-center">
			<span class="text-center help-block with-errors"
				id="cadastro-mensagem"></span> 
				<input id="update" name="update" type="hidden" value="false" /> 
				<input id="id-sistema-update" name="id-sistema-update" type="hidden" value="" /> 
				<input	id="action" name="action" type="hidden" value="insertSistema" />
			<button id="cadastro-sistema-submit" type="submit"
				class="btn btn-primary ">
				<fmt:message key="br.cefetrj.webdep.jsp.form.sistema.btnSalvar" />
			</button>
			<button id="cancela-btn" class="btn btn-secondary " type="button">
				<fmt:message key="br.cefetrj.webdep.jsp.form.sistema.btnCancelar" />
			</button>
		</div>
	</div>

	<jsp:include page="scripts.jspf" />
</body>
</html>
