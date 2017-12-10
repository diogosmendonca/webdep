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
<jsp:include page="head.jspf" />
<title>
<fmt:message key="br.cefetrj.webdep.jsp.lista.sistema.title" />
</title>
</head>
<body>
    <%@include file="navbar.jspf"%>
    <div class="alert alert-info" role="alert">
		<fmt:message key="br.cefetrj.webdep.jsp.lista.sistema.title" />
	</div>
        <div class=" container">
            <div class="col-sm-6 row form-inline">
                <div id="div-buscar-sistemas" class="form-group has-feedback">
                    <input id="filtro-busca-sistemas" class="form-control" type="text">
                    <span class=""></span>
                </div>
              	<button id="buscar-sistemas" type="submit" class="btn btn-primary"><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.buscar" /></button>
            	<br>
            </div>
            <br></br>
            <br>
            <div class="row">
                <table class="table table-bordered table-condensed">
                    <thead>
                        <tr>
                            <th><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.titulo1" /></th>
                            <th><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.servidor" /></th>
                            <th><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.format" /></th>
                            <th><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.perioleitura" /></th>
                            <th><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.perio" /></th>
                            <th><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.prox" /></th>
                            <th><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.alterar" /></th>
                            <th><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.excluir" /></th>
                        </tr>
                    </thead>
                    <tbody id="table-sistemas">
                       
                    </tbody>
                </table>
            </div>
            <br></br>
            <div class="row">
                <button id="volta-btn" type="button" class="btn btn-secondary btn-lg center-block"><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.voltar" /></button>
            </div>
        </div>
        <jsp:include page="scripts.jspf"/>
    </body>
</html>
