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
        <jsp:include page="head.jspf"/>
    </head>
    <body>
    <%@include file="navbar.jspf"%>
        <div id="div-buscar-sistemas" class="form-group container">
            <br></br>
            <div class="row form-inline">
                <div class="col-sm-5">
                    <input id="filtro-busca-sistemas" class="form-control" type="text">
                    <button id="buscar-sistemas" type="submit" class="btn btn-primary"><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.buscar" /></button>
                </div>
            </div>
            <br>
            <div class="row">
                <table class="table table-bordered table-condensed">
                    <thead>
                        <tr>
                            <th><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.titulo1" /></th>
                            <th><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.servidor" /></th>
                            <th><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.format" /></th>
                            <th><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.perio" /></th>
                            <th><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.prox" /></th>
                            <th><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.alterar" /></th>
                            <th><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.excluir" /></th>
                        </tr>
                    </thead>
                    <tbody>
                       
                    </tbody>
                </table>
            </div>
            <br></br>
            <div class="row">
                <button type="button" class="btn btn-secondary btn-lg center-block"><fmt:message key="br.cefetrj.webdep.jsp.form.sistema.voltar" /></button>
            </div>
        </div>
        <jsp:include page="scripts.jspf"/>
    </body>
</html>