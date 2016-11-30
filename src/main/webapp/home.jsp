
<%
response.setHeader("Pragma","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Expires","0");
response.setDateHeader("Expires",-1);
%>

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
<title>Home</title>
<jsp:include page="head.jspf" />
</head>
<body>
	<!-- PARA TESTAR A PÁGINA SAIR NA FINALIZAÇÃO  -->
	<%--<c:set var="id" scope="session" value="3" />  --%>

	<%@include file="navbar.jspf"%>


	<h5>Vamos começar?</h5>
	<ol>
		<c:if
			test="${usuario.admGeral==true || usuario.perfil=='Administrador' }">
			<li>Inicie cadastrando um sistema a monitorar - <a
				href="cadastrodesistema.jsp">Clique aqui para cadastrar</a></li>
		</c:if>
		<c:if
			test="${usuario.admGeral==true || usuario.perfil=='Administrador'|| usuario.perfil=='Analista' }">
			<li><a href="./cadastrodesistema.jsp">Selecione um sistema
					para trabalhar</a></li>
		</c:if>
		<c:if
			test="${usuario.admGeral==true || usuario.perfil=='Administrador' }">
			<li><a href="./versionRegistration.jsp">Registre uma versão
					do sistema</a></li>
		</c:if>
		<c:if
			test="${usuario.admGeral==true || usuario.perfil=='Administrador' }">
			<li><a href="./importarLog.jsp">Importe manualmente os
					dados de logs históricos</a></li>
		</c:if>
		<c:if
			test="${usuario.admGeral==true || usuario.perfil=='Administrador'|| usuario.perfil=='Analista' }">
			<li><a href="./accessProfileReport.jsp">Emita Relatórios de
					Perfil de Acesso </a>e <a href="../HTTPreport.jsp"> Analise os Erros no Sistema (Código HTTP)</a></li>
		</c:if>
	</ol>
	<h5>Outras ações que você pode querer realizar</h5>
	<ul>
		<li><a
			href="FrontControllerServlet?action=cadastraUsuario&get=true">Cadastre
				usuários e atribua permissões a eles</a></li>
		<li><a href="./excluirLogErro.jsp">Selecione exclua dados
				históricos de logs</a></li>
	</ul>



	<!--<%-- 		<H1>logado como ${usuario.nome} ${usuario.admGeral} --%>
<%-- 		${usuario.perfil }</H1> --%>
<%-- 		${lista}</br> --%>
<%-- 		ID SISTEMA ${param.idsistema} ${sessionScope.idsistema} --%>-->

	<jsp:include page="scripts.jspf" />
</body>
</html>
