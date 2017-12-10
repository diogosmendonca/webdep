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
	<title><fmt:message key="br.cefetrj.webdep.config.head.titulo" /></title>
</head>


<body>
	<%@include file="navbar.jspf"%>
	<div class="alert alert-info" role="alert">
		<fmt:message key="br.cefetrj.webdep.config.head.titulo" />
	</div>	
	
	<div class="container">
			<div class="panel panel-default">
	<form action="FrontControllerServlet" name="form" id="form" class="form-horizontal" method="post">
		<fieldset>
		  			<legend><fmt:message key="br.cefetrj.webdep.config.panelBD.titulo"/></legend>
		  		  	<div class="panel-body">
		    	  		<div class="form-group">
			    		<label for="inputBD" class="col-sm-2 control-label"><fmt:message key="br.cefetrj.webdep.config.panelBD.select.title"/></label>
			    		<div class="col-sm-10">
			    		<select class="span2" id="inputBD" name="banco" >
						  	<option value="MySQL"><fmt:message key="br.cefetrj.webdep.config.panelBD.select.optionMYSQL"/></option>
						  	<option value="Postgres"><fmt:message key="br.cefetrj.webdep.config.panelBD.select.optionPostgres"/></option>
						  	<option value="HSQL"><fmt:message key="br.cefetrj.webdep.config.panelBD.select.optionHSQLDB"/></option>			  	
						</select>
						<c:if test="${not empty bdValido and not bdValido }">
						<span class="help-block">
							<fmt:message key="br.cefetrj.webdep.config.panelBD.select.error"/>
						</span>
					</c:if>
						</div>
						</div>
						<div class="form-group">
			    		<label for="inputPanelBDUrl" class="col-sm-2 control-label"><fmt:message key="br.cefetrj.webdep.config.panelBD.form.URLTitle"/></label>
		    			<div class="col-sm-10">
		      				<input type="text" size="80" class="input-large" id="inpuPanelBDUrl" name="urlBanco" value="${param.urlBanco }"/>
		      				<c:if test="${not empty urlValida and not urlValida }">
		      					<c:choose>
		      						<c:when test="${not empty urlInvalida and urlInvalida}">
		      							<span class="help-block">
											<fmt:message key="br.cefetrj.webdep.config.panelBD.form.URL.Invalid.error"/>
										</span>		      						
		      						</c:when>
		      						<c:when test="${not empty urlVazia and urlVazia }">
		      							<span class="help-block">
											<fmt:message key="br.cefetrj.webdep.config.panelBD.form.URL.vazia.error"/>
										</span>
		      						</c:when>		      					
		      					</c:choose>							
							</c:if>
		    			</div>
		    			</div>
		    			<div class="form-group">
		    			<label for="inputPanelBDUser" class="col-sm-2 control-label"><fmt:message key="br.cefetrj.webdep.config.panelBD.form.UserTitle"/></label>
		    			<div class="col-sm-10">
		      				<input type="text" size="50" class="input-large" id="inputPanelBDUser" name="usuarioBanco" value="${param.usuarioBanco }"/>
		      				<c:if test="${not empty userValido and not userValido }">
		      					<c:choose>
		      						<c:when test="${not empty userInvalido and userInvalido}">
		      							<span class="help-block">
											<fmt:message key="br.cefetrj.webdep.config.panelBD.form.user.Invalid.error"/>
										</span>		      						
		      						</c:when>
		      						<c:when test="${not empty userVazio and userVazio }">
		      							<span class="help-block">
											<fmt:message key="br.cefetrj.webdep.config.panelBD.form.user.vazio.error"/>
										</span>
		      						</c:when>		      					
		      					</c:choose>							
							</c:if>
		    			</div>
		    			</div>
		    			<div class="form-group">
		    			<label for="inputPanelBDPassword" class="col-sm-2 control-label"><fmt:message key="br.cefetrj.webdep.config.panelBD.form.PasswordTitle"/></label>
		    			<div class="col-sm-10">
		      				<input type="password" size="50" class="input-large" id="inputPanelBdPassword" name="senhaBanco" value="${param.senhaBanco }"/>
		      				<c:if test="${not empty passwordValido and not passwordValido }">
		      					<c:choose>
		      						<c:when test="${not empty passwordInvalido and passwordInvalido}">
		      							<span class="help-block">
											<fmt:message key="br.cefetrj.webdep.config.panelBD.form.password.Invalid.error"/>
										</span>		      						
		      						</c:when>
		      						<c:when test="${not empty passwordVazio and passwordVazio }">
		      							<span class="help-block">
											<fmt:message key="br.cefetrj.webdep.config.panelBD.form.password.vazio.error"/>
										</span>
		      						</c:when>		      					
		      					</c:choose>							
							</c:if>
		    			</div>
		    			</div>
		    			
		    			<div class="form-group">
	    				<div class="col-sm-offset-2 col-sm-10">
	      				<button onclick="javascript:enviar('FrontControllerServlet?action=ValidaBanco')" class="btn btn-default" ><fmt:message key="br.cefetrj.webdep.config.panelBD.form.button.test"/></button>
	    				<c:choose>
							<c:when test="${not empty bdInvalido and  bdInvalido}">
								<span class="help-block-warning">
									<fmt:message key="br.cefetrj.webdep.config.panelBD.form.buuton.Invalid.error"/>
								</span>		    				
							</c:when>
							<c:when test="${not empty bdInvalido and  not bdInvalido}">
								<span class="help-block-warning">
									<fmt:message key="br.cefetrj.webdep.config.panelBD.form.buuton.valid"/>
							</span>		
							</c:when>
						</c:choose>
	    				</div>
		    		</div>
		    	</div>
		  </fieldset>
		
			<fieldset>
		  			<legend><fmt:message key="br.cefetrj.webdep.config.panelADM.titulo"/></legend>
		  			<div class="panel-body">
			  		<div class="form-group">
				    		<label for="inputPanelUserNome" class="col-sm-2 control-label"><fmt:message key="br.cefetrj.webdep.config.panelADM.form.Nome"/></label>
			    			<div class="col-sm-10">
			      				<input type="text" size="50" class="input-large" id="inputPanelUserNome" name="nomeAdmin" value="${param.nomeAdmin }"/>
			      				<c:if test="${not empty nomeAdminValido and not nomeAdminValido }">
			      					<c:choose>
			      						<c:when test="${not empty nomeAdminInvalido and nomeAdminInvalido}">
			      							<span class="help-block">
												<fmt:message key="br.cefetrj.webdep.config.panelADM.form.nome.Invalid.error"/>
											</span>		      						
			      						</c:when>
			      						<c:when test="${not empty nomeAdminVazio and nomeAdminVazio }">
			      							<span class="help-block">
												<fmt:message key="br.cefetrj.webdep.config.panelADM.form.nome.vazio.error"/>
											</span>
			      						</c:when>		      					
			      					</c:choose>							
								</c:if>
			    			</div>
			    	</div>
			  		<div class="form-group">
				    		<label for="inputPanelUserEmail" class="col-sm-2 control-label"><fmt:message key="br.cefetrj.webdep.config.panelADM.form.Email"/></label>
			    			<div class="col-sm-10">
			      				<input type="email" size="50" class="input-large" id="inputPanelUserEmail" name="emailAdmin" value="${param.emailAdmin }"/>
			      				<c:if test="${not empty emailAdminValido and not emailAdminValido }">
			      					<c:choose>
			      						<c:when test="${not empty emailAdminInvalido and emailAdminInvalido}">
			      							<span class="help-block">
												<fmt:message key="br.cefetrj.webdep.config.panelADM.form.email.Invalid.error"/>
											</span>		      						
			      						</c:when>
			      						<c:when test="${not empty emailAdminVazio and emailAdminVazio }">
			      							<span class="help-block">
												<fmt:message key="br.cefetrj.webdep.config.panelADM.form.email.vazio.error"/>
											</span>
			      						</c:when>		      					
			      					</c:choose>							
								</c:if>
			    			</div>
			    	</div>
			  		<div class="form-group">
				    		<label for="inputPanelUserUser" class="col-sm-2 control-label"><fmt:message key="br.cefetrj.webdep.config.panelADM.form.User"/></label>
			    			<div class="col-sm-10">
			      				<input type="text" size="50" class="input-large" id="inputPanelUserUser" name="usuarioAdmin" value="${param.usuarioAdmin }"/>
			      				<c:if test="${not empty usuarioAdminValido and not usuarioAdminValido }">
			      					<c:choose>
			      						<c:when test="${not empty usuarioAdminInvalido and usuarioAdminInvalido}">
			      							<span class="help-block">
												<fmt:message key="br.cefetrj.webdep.config.panelADM.form.usuario.Invalid.error"/>
											</span>		      						
			      						</c:when>
			      						<c:when test="${not empty usuarioAdminVazio and usuarioAdminVazio }">
			      							<span class="help-block">
												<fmt:message key="br.cefetrj.webdep.config.panelADM.form.usuario.vazio.error"/>
											</span>
			      						</c:when>		      					
			      					</c:choose>							
								</c:if>
			    			</div>
			    	</div>
				    <div class="form-group">
			    			<label for="inputPanelUserPassword" class="col-sm-2 control-label"><fmt:message key="br.cefetrj.webdep.config.panelADM.form.PasswordTitle"/></label>
			    			<div class="col-sm-10">
			      				<input type="password" size="50" class="input-large" id="inputPanelUSerPassword" name="senhaAdmin" value="${param.senhaAdmin }"/>
			      				<c:if test="${not empty senhaAdminValido and not senhaAdminValido }">
			      					<c:choose>
			      						<c:when test="${not empty senhaAdminInvalido and senhaAdminInvalido}">
			      							<span class="help-block">
												<fmt:message key="br.cefetrj.webdep.config.panelADM.form.senha.Invalid.error"/>
											</span>		      						
			      						</c:when>
			      						<c:when test="${not empty senhaAdminVazio and senhaAdminVazio }">
			      							<span class="help-block">
												<fmt:message key="br.cefetrj.webdep.config.panelADM.form.senha.vazio.error"/>
											</span>
			      						</c:when>		      					
			      					</c:choose>							
								</c:if>
			    			</div>
				    </div>
				    <div class="form-group">
			    			<label for="inputPanelUserConfirmPassword" class="col-sm-2 control-label"><fmt:message key="br.cefetrj.webdep.config.panelADM.form.ConfirmPasswordTitle"/></label>
			    			<div class="col-sm-10">
			      				<input type="password" size="50" class="input-large" id="inputPanelUSerConfirPassword" name="confirmSenhaAdmin" value="${param.confirmSenhaAdmin }"/>
			      				<c:if test="${not empty confirmSenhaAdminValido and not confirmSenhaAdminValido }">
			      					<c:choose>
			      						<c:when test="${not empty confirmSenhaAdminInvalido and confirmSenhaAdminInvalido}">
			      							<span class="help-block">
												<fmt:message key="br.cefetrj.webdep.config.panelADM.form.senha.Invalid.error"/>
											</span>		      						
			      						</c:when>
			      						<c:when test="${not empty confirmSenhaAdminVazio and confirmSenhaAdminVazio }">
			      							<span class="help-block">
												<fmt:message key="br.cefetrj.webdep.config.panelADM.form.senha.vazio.error"/>
											</span>
			      						</c:when>		      					
			      					</c:choose>							
								</c:if>
			    			</div>
				    </div>
				    
				    <c:if test="${not empty senhaConfirmada and not senhaConfirmada }">
				    	<div class="alert">
				    	<span class="help-block">
							<fmt:message key="br.cefetrj.webdep.config.panelADM.form.senhaConfirmada.Invalid"/>
						</span>	
						</div>
				    </c:if>
				    </div>
			  		</fieldset>
			
			
				<fieldset>
		  		<legend><fmt:message key="br.cefetrj.webdep.config.panelEmail.titulo"/></legend>
		  		<div class="panel-body">		  			
		  					<div class="form-group">
			    				<label for="inputPanelEmailUser" class="col-sm-2 control-label"><fmt:message key="br.cefetrj.webdep.config.panelEmail.form.User"/></label>
		    					<div class="col-sm-10">
		      						<input type="email" size="50" class="input-large" id="inputPanelEmailUser" name="usuarioEmail" value="${param.usuarioEmail }"/>
		      						<c:if test="${not empty usuarioValido and not usuarioValido }">
			      					<c:choose>
			      						<c:when test="${not empty usuarioInvalido and usuarioInvalido}">
			      							<span class="help-block">
												<fmt:message key="br.cefetrj.webdep.config.panelEmail.form.email.Invalid.error"/>
											</span>		      						
			      						</c:when>
			      						<c:when test="${not empty usuarioVazio and usuarioVazio }">
			      							<span class="help-block">
												<fmt:message key="br.cefetrj.webdep.config.panelEmail.form.email.vazio.error"/>
											</span>
			      						</c:when>		      					
			      					</c:choose>							
								</c:if>
		    					</div>
		    			</div>
		    			 <div class="form-group">
		    				<label for="inputPanelEmailPassword" class="col-sm-2 control-label"><fmt:message key="br.cefetrj.webdep.config.panelEmail.form.PasswordTitle"/></label>
		    				<div class="col-sm-10">
		      					<input type="password" size="50" class="input-large" id="inputPanelEmailPassword" name="senhaEmail" value="${param.senhaEmail }"/>
		      					<c:if test="${not empty senhaValida and not senhaValida }">
			      					<c:choose>
			      						<c:when test="${not empty senhaInvalida and senhaInvalida}">
			      							<span class="help-block">
												<fmt:message key="br.cefetrj.webdep.config.panelEmail.form.senha.Invalid.error"/>
											</span>		      						
			      						</c:when>
			      						<c:when test="${not empty senhaVazia and senhaVazia }">
			      							<span class="help-block">
												<fmt:message key="br.cefetrj.webdep.config.panelEmail.form.senha.vazio.error"/>
											</span>
			      						</c:when>		      					
			      					</c:choose>							
								</c:if>
		    				</div>
			    		</div>
			    		    			
		    									
		    			
		    			<div class="form-group">
	    					<div class="col-sm-offset-2 col-sm-10">
	      						<button type="submit" onclick="javascript:enviar('FrontControllerServlet?action=ValidaEmail')" class="btn btn-default" ><fmt:message key="br.cefetrj.webdep.config.panelEmail.form.button.test"/></button>
	      						<c:choose>
									<c:when test="${not empty emailInvalido and  emailInvalido}">
			    						<span class="help-block-warning">
											<fmt:message key="br.cefetrj.webdep.config.panelEmail.form.buuton.Invalid.error"/>
										</span>		    				
									</c:when>
									<c:when test="${not empty emailInvalido and  not emailInvalido}">
										<span class="help-block-warning">
											<fmt:message key="br.cefetrj.webdep.config.panelEmail.form.buuton.valid"/>
									</span>		
									</c:when>
									
								</c:choose>
	    					</div>
		    			</div>	
		    			</div>	    		
		  			</fieldset>		  				
		
			<div class="form-group">
			    	<div class="col-sm-offset-2 col-sm-10">
			    	<button type="submit" class="btn btn btn-primary" name="action" value="ValidaConfig"><fmt:message key="br.cefetrj.webdep.config.form.button.finalizar"/></button>
			    	<c:if test="${not empty obrigatoriosVazios and obrigatoriosVazios}">
			    		<span class="help-block">
							<fmt:message key="br.cefetrj.webdep.config.finalizar.form.obg.vazio.error"/>
						</span>
			    	</c:if>
			    	</div>
				</div>
		
		
		
		
	</form>
	</div>
	</div>
	
	
	
	<script>

	function enviar(tela){
	
		var form = document.getElementById("form");
		form.action = tela;
		form.submit();
	}

</script>
<jsp:include page="scripts.jspf"/>
</body>
</html>