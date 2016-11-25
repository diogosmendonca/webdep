<%@ page import="br.cefetrj.webdep.services.SistemaServices" %>
<%@ page import="br.cefetrj.webdep.services.ServidorServices" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="lang" scope="session" value="${not empty param.lang ? param.lang : not empty lang ? lang : pageContext.request.locale}"/>
<fmt:setLocale value="${ lang }"/>
<fmt:setBundle basename="Messages" />
<c:set var="sistemas" scope="session" value="<%=SistemaServices.listarTodos()%>"/>
<c:set var="servidores" scope="session" value="<%=ServidorServices.listAllServidor()%>"/>
<html>
<head>
    <title>WebDep</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
</head>
<body>
<%@include file="navbar.jspf"%>
<div class="col-lg-8">
    <div class="panel-body">
        <form action="FrontControllerServlet" class="form-horizontal" >
            <fieldset class="form-group">
                <legend><fmt:message key="br.cefetrj.psw.importaLog.sistema" /></legend>
                <label><fmt:message key="br.cefetrj.psw.importaLog.nomeSistema" />:</label>
                <select name="sistema">
                    <c:forEach items="${ sistemas }" var="sis">
                        <option value="${ sis }" ${ sistema == sis ? 'selected' : '' }>${ sis.nome }</option>
                    </c:forEach>
                </select>
                <br><br>
                <label><fmt:message key="br.cefetrj.psw.importaLog.servidor" /></label>
                <select name="servidor">
                    <c:forEach items="${ servidores }" var="serv">
                        <option value="${ serv }" ${ servidor == serv ? 'selected' : '' }>${ serv.nome }</option>
                    </c:forEach>
                </select>
                <br>
            </fieldset>
            <fieldset class="form-group">
                <legend><fmt:message key="br.cefetrj.psw.importaLog.logs" /></legend>
                <label><fmt:message key="br.cefetrj.psw.importaLog.formatoLog" />:</label>
                <select name="log">
                    <option value="common" ${ log == 'common' ? 'selected' : ''}>Common</option>
                    <option value="combined" ${ log == 'combined' ? 'selected' : ''}>Combined</option>
                </select>
                <br><br>
                <label><fmt:message key="br.cefetrj.psw.importaLog.logAcesso" />:</label><input type="text" name="logAcesso" value="${ logAcesso }">
                <button type="submit" class="btn btn-primary  btn-sm" name="action" value="validaLogAcesso">
                    <fmt:message key="br.cefetrj.psw.importaLog.testarAcesso" />
                </button>
                <br>
                <c:if test="${ not empty logAcessoValido and not logAcessoValido }">
                    <fmt:message key="br.cefetrj.psw.importaLog.teste" />
                </c:if>
                <c:if test="${ not empty logAcessoInvalido and not logAcessoInvalido }">
                    <fmt:message key="br.cefetrj.psw.importaLog.logAcessoInvalido" />
                </c:if>
                <c:if test="${ not empty logAcessoVazio and not logAcessoVazio }">
                    <fmt:message key="br.cefetrj.psw.importaLog.preencherLogAcesso" />
                </c:if>
                <br>
                <label><fmt:message key="br.cefetrj.psw.importaLog.logErro" />:</label><input type="text" name="logErro" value="${ logErro }">
                <button type="submit" class="btn btn-primary  btn-sm" name="action" value="validaLogErro">
                    <fmt:message key="br.cefetrj.psw.importaLog.testarAcesso" />
                </button>
                <br>
                <c:if test="${ not empty logErroValido and not logErroValido }">
                    <fmt:message key="br.cefetrj.psw.importaLog.teste" />
                </c:if>
                <c:if test="${ not empty logErroInvalido and not logErroInvalido }">
                    <fmt:message key="br.cefetrj.psw.importaLog.logErroInvalido" />
                </c:if>
                <c:if test="${ not empty logErroVazio and not logErroVazio }">
                    <fmt:message key="br.cefetrj.psw.importaLog.preencherLogErro" />
                </c:if>
                <br>
            </fieldset>
            <div class="col-md-4 center-block">
                <button type="submit" class="btn btn-primary btn-sm" name="action" value="importaLog">
                    <fmt:message key="br.cefetrj.psw.importaLog.importar" />
                </button>
                <input type="button" class="btn btn-primary  btn-sm" value="Cancelar" onclick="history.back();">
            </div>
        </form>
        <br>
    </div>
    <c:if test="${ not empty testarAcesso and not testarAcesso }">
        <fmt:message key="br.cefetrj.psw.importaLog.logInvalid" />
    </c:if>
    <c:if test="${ not empty erro and not erro }">
        <fmt:message key="br.cefetrj.psw.importaLog.erro" />
    </c:if>
    <c:if test="${ not empty logAdicionado and not logAdicionado }">
        <fmt:message key="br.cefetrj.psw.importaLog.sucesso" />
    </c:if>
</div>
<jsp:include page="scripts.jspf"/>
</body>
</html>