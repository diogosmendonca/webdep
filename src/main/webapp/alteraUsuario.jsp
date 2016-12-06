<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang" scope="session" value="${not empty param.lang ? param.lang : not empty lang ? lang : pageContext.request.locale}"/>
<fmt:setLocale value="${ lang }"/>
<fmt:setBundle basename="Messages" />
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="head.jspf"/>
	<title><fmt:message key="br.cefetrj.psw.user.titulo"/></title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/css/bootstrap-select.min.css">
</head>
<body>
    <%@include file="navbar.jspf"%>
	<div style="padding: 5%">
		<form action="FrontControllerServlet" method="POST" id="jqCad">
		<input type="hidden" name="id" value="${ usuario.id }">
		<input type="hidden" name="action" value="alteraUsuario" />
		<c:if test="${AlterErroUsu}">
				<h3><fmt:message key="br.cefetrj.psw.user.msg_erro.alter"/></h3>
			</c:if>			
		<c:if test="${not AlterErroUsu}">
		<div class="panel panel-default">
		  <div class="panel-heading"><fmt:message key="br.cefetrj.psw.user.panel" /></div>
		  <div class="panel-body">
				<div class="form-group form-inline ${ not empty nomeValido and not nomeValido ? "has-error" : "" }" >
					<label for="inputNome"><fmt:message key="br.cefetrj.psw.user.label.nome"/></label> 
					<input type="text" class="form-control" id="inputNome" name="nome" maxlength="100" value="${ usuario.nome }" />
					
					<c:if test="${not empty nomeValido and not nomeValido }">
						<span class="help-block">
							<fmt:message key="br.cefetrj.psw.user.msg_erro.nome"/>
						</span>
					</c:if>
				</div>
				<div class="form-group form-inline ${ not empty emailValido and not emailValido ? "has-error" : "" }" >
					<label for="inputEmail"><fmt:message key="br.cefetrj.psw.user.label.email"/></label> 
					<input type="email" class="form-control" id="inputEmail" maxlength="100" name="email" value="${ usuario.email }" />
					
					<c:if test="${not empty emailValido and not emailValido }">
						<span class="help-block">
							<fmt:message key="br.cefetrj.psw.user.msg_erro.email"/>
						</span>
					</c:if>
					
					<c:if test="${not empty emailValido2 and not emailValido2 }">
						<span class="help-block"  style="color:#a94442">
							<fmt:message key="br.cefetrj.psw.user.msg_erro.email2"/>
						</span>
					</c:if>
				</div>
				<div class="form-group form-inline ${ not empty loginValido and not loginValido ? "has-error" : "" }" >
					<label for="inputLogin"><fmt:message key="br.cefetrj.psw.user.label.login"/></label> 
					<input type="text" class="form-control" id="inputLogin" maxlength="50" name="login" value="${ usuario.login }" />
					
					<c:if test="${not empty loginValido and not loginValido }">
						<span class="help-block">
							<fmt:message key="br.cefetrj.psw.user.msg_erro.login"/>
						</span>
					</c:if>
					
					<c:if test="${not empty loginValido2 and not loginValido2 }">
						<span class="help-block"  style="color:#a94442">
							<fmt:message key="br.cefetrj.psw.user.msg_erro.login2"/>
						</span>
					</c:if>
				</div>
				<div class="form-group form-inline ${ not empty senhaValido1 and not senhaValido1 ? "has-error" : "" }" >
					<label for="inputSenha1"><fmt:message key="br.cefetrj.psw.user.label.senha1"/></label> 
					<input type="password" class="form-control" id="inputSenha1" maxlength="64" name="senha"/>
					
					<c:if test="${not empty senhaValido1 and not senhaValido1 }">
						<span class="help-block">
							<fmt:message key="br.cefetrj.psw.user.msg_erro.senha1"/>
						</span>
					</c:if>
				</div>
				<div class="form-group form-inline ${ not empty senhaValido2 and not senhaValido2 ? "has-error" : "" }" >
					<label for="inputSenha2"><fmt:message key="br.cefetrj.psw.user.label.senha2"/></label> 
					<input type="password" class="form-control" id="inputSenha2" maxlength="64" name="senha2"/>
					
					<c:if test="${not empty senhaValido2 and not senhaValido2 }">
						<span class="help-block">
							<fmt:message key="br.cefetrj.psw.user.msg_erro.senha2"/>
						</span>
					</c:if>
				</div>
				
				
				<div class="form-group form-inline" >
				<label for="sel1"><fmt:message key="br.cefetrj.psw.user.label.select"/></label>
				  <select class="form-control" name="perfil">
				    <option value="Analista"><fmt:message key="br.cefetrj.psw.user.label.optselect1"/></option>
				    <option value="Administrador"><fmt:message key="br.cefetrj.psw.user.label.optselect2"/></option>
				  </select>
				</div>
		  </div>
		</div>
		<div class="panel panel-default">
		  <div class="panel-heading"><fmt:message key="br.cefetrj.psw.user.permissoes" /></div>
		  <div class="panel-body">
				<div class="form-group form-inline">
				  <label for="sel1"><fmt:message key="br.cefetrj.psw.user.label.sistemas"/></label>
					<select name="sistema" class="selectpicker" multiple title="" id="versionlist">
				    <c:forEach items="${ cadsistemas }" var="sistema">
					    <option value="${ sistema.id }">${ sistema.nome }</option>
					</c:forEach>
					</select>
				</div>

			</div>
		</div>
		<div class="text-center">
			<input type="submit" class="btn btn-default" value="<fmt:message key="br.cefetrj.psw.user.bt_salvar"/>" />
			<a class="btn btn-default" href="FrontControllerServlet?action=listaUsuario&get=true"><fmt:message key="br.cefetrj.psw.user.bt_cancelar"/></a>
		</div>
		</c:if>
		</form>
		<input type="hidden" id="sis" value="${ sis }" />
	</div>
	<jsp:include page="scripts.jspf"/>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/js/bootstrap-select.min.js"></script>
	<script>
		$( window ).load(function() {
			var valores = $("#sis").val();
			var aux = valores.split(";"); 
			$('.selectpicker').selectpicker('val', aux);
		});
	</script>
</body>
</html>
