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
                	<button id="buscar-sistemas" type="submit" class="btn btn-primary">Buscar</button>
            	</div>
            </div>
            <br>
            <div class="row">
                <table class="table table-bordered table-condensed">
                    <thead>
                        <tr>
                            <th>Sistema</th>
                            <th>Servidor</th>
                            <th>Formato do Log</th>
                            <th>Periodicidade</th>
                            <th>Próxima Leitura</th>
                            <th>Alterar</th>
                            <th>Excluir</th>
                        </tr>
                    </thead>
                    <tbody>
                        
                    </tbody>
                </table>
            </div>
            <br></br>
            <div class="row">
                <button type="button" class="btn btn-secondary btn-lg center-block">Voltar</button>
            </div>
        </div>
        <jsp:include page="scripts.jspf"/>
    </body>
</html>
