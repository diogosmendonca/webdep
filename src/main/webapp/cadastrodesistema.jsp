<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- Variável criada para auxiliar na identificação do locale -->
<c:set var="lang" scope="session" value="${not empty param.lang ? param.lang : not empty lang ? lang : pageContext.request.locale}"/>

<!-- Necessário para utilizar o i18N, informar o locale e o bundle -->
<fmt:setLocale value="${ lang }"/>
<fmt:setBundle basename="Messages" />
<!DOCTYPE html>
<html><head>
        <jsp:include page="head.jspf"/>
    </head>
    <body>
    <%@include file="navbar.jspf"%>
        <form id="sistema-form" class="form-horizontal container">
            <div class="row">
                <fieldset class="form-group">
                    <legend><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.titulo1"/></legend>
                    <div class="col-sm-6">
                        <label class="text-right col-sm-6"><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.nome" /></label><div id="div-nome" class="input-group"><input class="form-control" id="nome" name="nome" type="text"/></div><br>
                        <label class="text-right col-sm-6"><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.servidor" /></label><div class="input-group"><select class="form-control" id="selection" name="selection"><option>Apache</option></select></div>
                    </div>
                </fieldset>
            </div>
            <div class="row">
                <fieldset class="form-group">
                    <legend>Logs</legend>
                    <div class="col-sm-6">
                        <label class="text-right col-sm-6"><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.fmtLogs" /></label><div class="input-group"><select class="form-control" id="fmtLogs" name="fmtLogs"><option>Common</option></select></div>
                        <br>
                        <label class="text-right col-sm-6"><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.ptLogs" /></label><div id="div-pasta-acesso" class="input-group"><input class="form-control" id="ptLogs" name="ptLogs" type="text"/></div>
                        <br>
                        <label class="text-right col-sm-6"><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.pxLogs" /></label>
                        <div  id="div-prefixo-acesso" class="input-group">
                            <input type="text" id="pxLogs" name="pxLogs" class="form-control">
                            <span class="input-group-btn">
                              <button class="btn btn-info" type="button"><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.btnTeste" /></button>
                            </span>
                         </div>
                        <br>
                        <label class="text-right col-sm-6"><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.ptErroLogs" /></label><div  id="div-pasta-erro" class="input-group"><input class="form-control" id="ptLogs2" name="ptLogs2" type="text"/></div>
                        <br>
                        <label class="text-right col-sm-6"><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.pxErroLogs" /></label>
                        <div  id="div-prefixo-erro" class="input-group">
                            <input type="text" class="form-control" id="pxLogs2" name="pxLogs2">
                            <span class="input-group-btn">
                              <button class="btn btn-info" type="button"><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.btnTeste" /></button>
                            </span>
                         </div>
                        <br>
                    </div>
                </fieldset>
            </div>
		 <div class="row">
                <fieldset class="form-group">
                    <legend><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.titulo3" /></legend>
                    <div class="col-sm-6">
                        <label class="text-right col-sm-6"><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.data" /></label>
                        	<div class="input-group date form_date col-sm-6" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                        		<div id="div-data" class="input-group">
                        			<input class="form-control" type="text" id="data" name="data" readonly/>
                        			<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
								</div>
							</div><br>
						
                        <label class="text-right col-sm-6"><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.hora" /></label>
                        	<div class="input-group date form_time col-sm-6" data-date="" data-date-format="hh:mm:ss" data-link-field="dtp_input3" data-link-format="hh:ii">
                        		<div id="div-time" class="input-group">
                        		<input class="form-control" type="text" id="time" name="time" readonly/>
                        		<span class="input-group-addon">
                        		<span class="glyphicon glyphicon-remove"></span></span>
								<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                        		</div>
							</div><br>
					
                        <label class="text-right col-sm-6"><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.nova" /></label>
                       		<div class="input-group date form_time col-sm-6" data-date="" data-date-format="hh:mm:ss" data-link-field="dtp_input3" data-link-format="hh:ii">
                        		<div id="div-nova" class="input-group">
                        		<input class="form-control" type="text" id="novaData" name="novaData" readonly/>
                        		<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
								<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
							</div>
                        </div> <br>
                    </div>
                </fieldset>
            </div>
            <div class="row text-center">
            <span id="mensagem"></span>
            <input id="update" type="hidden" value="false"/>
            <input id="id-sistema-update" type="hidden" value=""/>
            <button id="cadastro-sistema-submit" type="submit" class="btn btn-primary "><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.btnSalvar" /></button>
            <button class="btn btn-secondary "type="button"><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.btnCancelar" /></button>
            </div>
            
	</form>
	
        <jsp:include page="scripts.jspf"/>
</body>
</html>
