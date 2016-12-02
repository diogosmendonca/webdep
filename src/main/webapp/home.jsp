
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
<?xml version='1.0' encoding='UTF-8' ?>
<!DOCTYPE html>
<html>
<head>

<title>Home</title>
<jsp:include page="head.jspf" />
</head>
<body>
	<!-- PARA TESTAR A PÁGINA SAIR NA FINALIZAÇÃO  -->
	<%--<c:set var="id" scope="session" value="3" />  --%>
	<!-- Código que marca a caixa de seleção do sistema operacional -->
	<script type="text/javascript">
	
		function selecinarSistema(){
			
			document.getElementById("sistema").focus();
			
		}	
	
	
	</script>

	<%@include file="navbar.jspf"%>


	<h5>
		<fmt:message key="br.cefetrj.webdep.jsp.home.vamoscomecar" />
	</h5>
	<ol>
		<c:if
			test="${usuario.admGeral==true || usuario.perfil=='Administrador' }">
			<li><fmt:message
						key="br.cefetrj.webdep.jsp.home.iniciecadastrandoumsistemaamonitorar" /> - <a href="./cadastrodesistema.jsp"><fmt:message
						key="br.cefetrj.webdep.jsp.home.cliqueaquiparacadastrar" /></a></li>
		</c:if>
		
		<c:if
			test="${usuario.admGeral==true || usuario.perfil=='Administrador' }">
			<li><a href="javascript:void(0);" onclick="selecinarSistema();"><fmt:message
						key="br.cefetrj.webdep.jsp.home.selecioneumsistemaparatrabalhar" /></a></li>
		</c:if>
		<c:if
			test="${usuario.admGeral==true || usuario.perfil=='Administrador' }">
			<li><a href="./versionRegistration.jsp"><fmt:message
						key="br.cefetrj.webdep.jsp.home.registreumaversaodosistema" /></a></li>
		</c:if>
		<c:if
			test="${usuario.admGeral==true || usuario.perfil=='Administrador'|| usuario.perfil=='Analista' }">
			<li><a href="./importarLog.jsp"><fmt:message
						key="br.cefetrj.webdep.jsp.home.importemanualmenteosdadosdelogshistoricos" />
			</a> </li>
		</c:if>
		<c:if
			test="${usuario.admGeral==true || usuario.perfil=='Administrador'|| usuario.perfil=='Analista' }">
			<li><a href="./accessProfileReport.jsp"><fmt:message
						key="br.cefetrj.webdep.jsp.home.emitarelatoriosdeperfildeacesso" />
			</a> <fmt:message key="br.cefetrj.webdep.jsp.home.e" /> <a
				href="./HTTPreport.jsp"> <fmt:message
						key="br.cefetrj.webdep.jsp.home.analiseoserrosnosistema" /></a></li>
		</c:if>
		
	</ol>
	<h5><fmt:message
					key="br.cefetrj.webdep.jsp.home.outrasacoesquevocepodequererrealizar" /></h5>
	<ul>
		<li><a
			href="FrontControllerServlet?action=cadastraUsuario&get=true"><fmt:message
					key="br.cefetrj.webdep.jsp.home.cadastreusuarioseatribuapermissoesaeles" /></a></li>
		<li><a href="./excluirLogErro.jsp"><fmt:message
					key="br.cefetrj.webdep.jsp.home.selecioneexcluadadoshistoricosdelogs" /></a></li>
	</ul>



	<!--<%-- 		<H1>logado como ${usuario.nome} ${usuario.admGeral} --%>
<%-- 		${usuario.perfil }</H1> --%>
<%-- 		${lista}</br> --%>
<%-- 		ID SISTEMA ${param.idsistema} ${sessionScope.idsistema} --%>-->

	<jsp:include page="scripts.jspf" />
</body>
</html>
