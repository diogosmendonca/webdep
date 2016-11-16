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
        <form class="form-horizontal container">
            <div class="row">
                <fieldset class="form-group">
                    <legend>Sistema</legend>
                    <div class="col-sm-6">
                        <label class="text-right col-sm-6">Nome do Sistema: </label><div class="input-group"><input class="form-control" type="text"/></div><br>
                        <label class="text-right col-sm-6">Servidor: </label><div class="input-group"><select class="form-control"><option>Apache</option></select></div>
                    </div>
                </fieldset>
            </div>
            <div class="row">
                <fieldset class="form-group">
                    <legend>Logs</legend>
                    <div class="col-sm-6">
                        <label class="text-right col-sm-6">Formato Logs de Acesso: </label><div class="input-group"><select class="form-control"><option>Common</option></select></div>
                        <br>
                        <label class="text-right col-sm-6">Pasta dos Logs de Acesso: </label><div class="input-group"><input class="form-control" type="text"/></div>
                        <br>
                        <label class="text-right col-sm-6">Prefixo dos Logs de Acesso: </label>
                        <div class="input-group">
                            <input type="text" class="form-control">
                            <span class="input-group-btn">
                              <button class="btn btn-info" type="button">Testar Acesso</button>
                            </span>
                         </div>
                        <br>
                        <label class="text-right col-sm-6">Pasta dos Logs de Erro: </label><div class="input-group"><input class="form-control" type="text"/></div>
                        <br>
                        <label class="text-right col-sm-6">Prefixo dos Logs de Erro: </label>
                        <div class="input-group">
                            <input type="text" class="form-control">
                            <span class="input-group-btn">
                              <button class="btn btn-info" type="button">Testar Acesso</button>
                            </span>
                         </div>
                        <br>
                    </div>
                </fieldset>
            </div>
		 <div class="row">
                <fieldset class="form-group">
                    <legend>Periocidade de Leitura dos Logs</legend>
                    <div class="col-sm-6">
                        <label class="text-right col-sm-6">Data da Primeira Leitura: </label>
                        	<div class="input-group date form_date col-sm-6" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                        		<div class="input-group">
                        			<input class="form-control" type="text" readonly/>
                        			<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
								</div>
							</div><br>
						
                        <label class="text-right col-sm-6">Horário da Primeira Leitura: </label>
                        	<div class="input-group date form_time col-sm-6" data-date="" data-date-format="hh:ii" data-link-field="dtp_input3" data-link-format="hh:ii">
                        		<div class="input-group">
                        		<input class="form-control" type="text" readonly/><span class="input-group-addon">
                        		<span class="glyphicon glyphicon-remove"></span></span>
								<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                        		</div>
							</div><br>
					
                        <label class="text-right col-sm-6">Novas Leituras a Cada: </label>
                        <div class="input-group date form_datetime col-sm-6" data-date="2016-11-12T05:25:07Z" data-date-format="dd MM yyyy - HH:ii p" data-link-field="dtp_input1">
                        	<div class="input-group">
                        		<input class="form-control" type="text" readonly/>
                        		<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
								<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
							</div>
                        </div> <br>
                    </div>
                </fieldset>
            </div>
            <div class="row text-center">
            <button type="submit" class="btn btn-primary ">Salvar</button>
            <button class="btn btn-secondary "type="button">Cancelar</button>
            </div>
	</form>
        <jsp:include page="scripts.jspf"/>
</body>
</html>
