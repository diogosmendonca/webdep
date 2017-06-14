<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Taglib necessária para acessar as funções de formatação -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- Variável criada para auxiliar na identificação do locale -->
<c:set var="lang" scope="session" value="pt_BR"/>

<!-- Necessário para utilizar o i18N, informar o locale e o bundle -->
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="Messages" />

<!DOCTYPE html>
<html>
<head>
<title>WebDep</title>
<!-- Evitando que browsers antigos alterem o encoding da página -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>
<body>
    <div class="container-default">
        <div class="panel panel-default">
            <div class="panel-heading" align="left">
                <h4><b><font color="black" style="font-family: Times;">WebDep</font></b></h4>
            </div>
            <div class="panel-body"align="center">
                <div class="container " style="margin-top: 10%; margin-bottom: 10%;">
                	<div class="panel panel-default" style="max-width: 45%;" align="left">
                        <div class="panel-heading form-group">
                            <b><font color="black" style= "font-family: Times; font-size: 18px"><fmt:message key="br.cefetrj.webdep.jsp.index.nomeformulario" /></font></b>
                        </div>
                        <div class="panel-body" >
                        <p style="color:red">${msg}</p>
                        <form class="form-horizontal" action="FrontControllerServlet" method="POST" >
                            <div class="form-group">
    				<label class="control-label col-sm-2" for="login"><fmt:message key="br.cefetrj.webdep.jsp.index.login" /></label>
    				<div class="col-sm-10">
    				    <c:choose>
					<c:when test="${!empty loginUsuario}">
						<input type="text" class="form-control" name="login" id="login" value="${ loginUsuario }" pattern=".{1,}" 
						required oninvalid="setCustomValidity('<fmt:message key="br.cefetrj.webdep.form.required"/>')" oninput="setCustomValidity('')" >
					</c:when>    
					<c:otherwise>
						<input type="text" class="form-control" name="login" id="login" pattern=".{1,}" 
						required oninvalid="setCustomValidity('<fmt:message key="br.cefetrj.webdep.form.required"/>')" oninput="setCustomValidity('')" >
					</c:otherwise>
				    </c:choose>
    				</div>
  			    </div>
  			    <div class="form-group">
    				<label class="control-label col-sm-2" for="senha"><fmt:message key="br.cefetrj.webdep.jsp.index.senha" /></label>
    				<div class="col-sm-10"> 
      					<input type="password" class="form-control" name="senha" id="senha" pattern=".{1,}" 
					required oninvalid="setCustomValidity('<fmt:message key="br.cefetrj.webdep.form.required"/>')" oninput="setCustomValidity('')" >
    				</div>
  			    </div>			
                            	<button type="submit" style="width: 50%; font-size:1.1em;" class="btn btn-primary" name="action" value ="autenticaUsuario" >
                            	<b><fmt:message key="br.cefetrj.webdep.jsp.index.entrar" /></b></button>
							
				<div class="container">
				<br/>
      					<span class="psw"><a href="#"><fmt:message key="br.cefetrj.webdep.jsp.index.esqueci" /></a></span>
    				</div>
     			</form>
                        </div>
                    </div>  
                </div>
            </div>
            <div class="panel-footer" align="center"><font style="color: #111">Copyright @2016  <a>webdep.cefet-rj.br</a></font></div>
        </div>
    </div>
</body>
</html>
