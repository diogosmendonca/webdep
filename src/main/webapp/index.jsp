<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Taglib necessária para acessar as funções de formatação -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- Variável criada para auxiliar na identificação do locale -->
<c:set var="lang" scope="session" value="${not empty param.lang ? param.lang : not empty lang ? lang : pageContext.request.locale}"/>

<!-- Necessário para utilizar o i18N, informar o locale e o bundle -->
<fmt:setLocale value="${ lang }"/>
<fmt:setBundle basename="Messages" />

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>WebDep</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container-default">
        <div class="panel panel-default">
            <div class="panel-heading" align="left">
                <h4><b><font color="black" style="font-family: Times;">WebDep</font> </b></h4>
            </div>
            <div class="panel-body"align="center">
                <div class="container " style="margin-top: 10%; margin-bottom: 10%;">
                	<div class="panel panel-default" style="max-width: 35%;" align="left">
                        <div class="panel-heading form-group">
                            <b><font color="black" style= "font-family: Times; font-size: 18px">Autenticação</font> </b>
                        </div>
                    
                        <div class="panel-body" >

                        <form class="form-horizontal" action="FrontControllerServlet" method="post" >
                            <div class="form-group">
    							<label class="control-label col-sm-2" for="login">Login</label>
    							<div class="col-sm-10">
      								<input type="text" class="form-control" name="login" id="login" required="required">
    							</div>
  							</div>
  							<div class="form-group">
    							<label class="control-label col-sm-2" for="senha">Senha</label>
    							<div class="col-sm-10"> 
      								<input type="password" class="form-control" name="senha" id="senha" required="required">
    							</div>
  							</div>
  							
                            <button type="submit" style="width: 50%; font-size:1.1em;" class="btn btn-default" name="action" value ="autenticaUsuario" ><b>Entrar</b></button>
							
							<div class="container">
								<br/>
      							<span class="psw"><a href="#">Esqueci minha senha</a></span>
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
