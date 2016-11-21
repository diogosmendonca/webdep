<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- <!-- Variável criada para auxiliar na identificação do locale -->

<c:set var="lang" scope="session"
	value="${not empty param.lang ? param.lang : not empty lang ? lang : pageContext.request.locale}" />

<!-- Necessário para utilizar o i18N, informar o locale e o bundle -->
<fmt:setLocale value="${ lang }" />
<fmt:setBundle basename="Messages" />

<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="head.jspf" />
</head>
<body>
	<h2></h2>
	<jsp:include page="scripts.jspf" />

	<c:set var="id" scope="session" value="2" />


	<c:if test="${empty usuario }">

		<c:choose>
			<c:when test="${sessionScope.id==null && empty sessionScope.user}">
				<jsp:forward page="/index.jsp" />
			</c:when>

			<c:otherwise>

				<jsp:forward page="/FrontControllerServlet">
					<jsp:param name="action" value="getUsuario" />
					<jsp:param name="id" value="1" />
				</jsp:forward>


			</c:otherwise>

		</c:choose>
	</c:if>




	<!-- 
links barra menu -->
	<nav class="navbar navbar-default">
		<div class="container-fluid">

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">

				<ul class="nav navbar-nav">
					<!-- Testar perfil do usuário -->
					<c:if test="${usuario.admGeral==true || usuario.perfil=='Administrador' }">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"> WebDep <span class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<li><a href="#"><fmt:message
										key="br.cefetrj.webdep.jsp.navbar.webdep.cadastrarusuario" /></a></li>
							<li><a href="#"><fmt:message
										key="br.cefetrj.webdep.jsp.navbar.webdep.listaralterarexcluir" /></a></li>
							<li><a href="#"><fmt:message
										key="br.cefetrj.webdep.jsp.navbar.webdep.configuracoes" /></a></li>
						</ul></li>
					</c:if>
					<!-- END WEBDEP -->
					<!-- Testar perfil do usuário -->
					<c:if test="${usuario.admGeral==true || usuario.perfil=='Administrador' }">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"> <fmt:message
								key="br.cefetrj.webdep.jsp.navbar.sistemas" /><span
							class="caret"></span>
							<ul class="dropdown-menu">
								<li><a href='cadastrodesistema.jsp'><fmt:message
											key="br.cefetrj.webdep.jsp.navbar.sistemas.cadastrarsistema" /></a></li>
								<li><a href="gerenciadorsistema.jsp"><fmt:message
											key="br.cefetrj.webdep.jsp.navbar.sistemas.listaratualizarexcluir" /></a></li>
								<li><a href="versionRegistration.jsp"><fmt:message
											key="br.cefetrj.webdep.jsp.navbar.sistemas.cadastrarnovaversao" /></a></li>
								<li><a href="versionSearch.jsp"><fmt:message
											key="br.cefetrj.webdep.jsp.navbar.sistemas.listaratualizarexcluirversoes" /></a></li>

							</ul></li>
							</c:if>
					<!-- END SISTEMAS -->
					<!-- Testar perfil do usuário -->
					<c:if test="${usuario.admGeral==true && usuario.perfil=='Administrador' }">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"/> Logs<span class="caret"></span>
							<ul class="dropdown-menu">
								<li><a href="#"><fmt:message
											key="br.cefetrj.webdep.jsp.navbar.logs.importarlogs" /></a></li>
								<li><a href="#"><fmt:message
											key="br.cefetrj.webdep.jsp.navbar.logs.consultarexcluirregistrosdeacesso" /></a></li>
								<li><a href="#"><fmt:message
											key="br.cefetrj.webdep.jsp.navbar.logs.consultarexcluirregistrosdeerro" /></a></li>

							</ul></li>
							</c:if>
					<!-- END LOGS -->
				
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"> <fmt:message
								key="br.cefetrj.webdep.jsp.navbar.relatorios" /><span
							class="caret"></span>
							<ul class="dropdown-menu">
								<li><a href="#"><fmt:message
											key="br.cefetrj.webdep.jsp.navbar.relatorios.perfildeacesso" /></a></li>
								<li><a href="HTTPreport.jsp"><fmt:message
											key="br.cefetrj.webdep.jsp.navbar.relatorios.confiabilidadeurls" /></a></li>
							</ul></li>
							
					<!-- END Relatórios  -->
				</ul>

				<ul class="nav navbar-nav navbar-right">

					<li><form name="barralistbox" class="navbar-form navbar-left">
							<div class="form-group">
								<select class="form-control">
									<option value="sistema">Sistema</option>
								</select>
							</div>
						</form></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"><fmt:message
								key="br.cefetrj.webdep.jsp.navbar.idioma" /> <span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#"><fmt:message
										key="br.cefetrj.webdep.jsp.navbar.idioma.ingles" /></a></li>
							<li><a href="#"><fmt:message
										key="br.cefetrj.webdep.jsp.navbar.idioma.portugues" /></a></li>

						</ul></li>

					<li><form action ="/index.jsp" method = "post">
					
						<input type="submit" class="btn  navbar-btn " name="Sair"
						onclick="${sessionScope.id=''}" value="<fmt:message key="br.cefetrj.webdep.jsp.navbar.idioma.sair" />">
					</form> </li>
				</ul>

			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>

 <h5>Vamos começar?</h5>
	  <ol>
	    <li>Inicie cadastrando um sistema a monitorar - <a href="/cadastrousuario">Clique aqui para cadastrar</a></li>
	    <li><a href="#">Selecione um sistema para trabalhar</a></li>
	    <li><a href="#">Registre uma versão do sistema</a></li>
	    <li><a href="#">Importe manualmente os dados de logs históricos</a></li>
	    <li><a href="#">Emita Relatórios de Perfil de Acesso e Analise os Erros no Sistema (Código HTTP)</a></li>
	  </ol>
	  <h5>Outras ações que você pode querer realizar</h5>
	  <ul>
	    <li><a href="#">Cadastre usuários e atribua permissões a eles</a></li>
	    <li><a href="#">Selecione exclua dados históricos de logs</a></li>
	  </ul>




	<H1>logado como ${usuario.nome} ${usuario.admGeral} ${usuario.perfil }  </H1>
</body>
</html>