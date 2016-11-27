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
</head>
<body>
    <%@include file="navbar.jspf"%>
	<div style="padding: 5%">
		<form action="FrontControllerServlet" method="post">
			<div class="form-group form-inline" >
			
            	<input id="action" name="action" type="hidden" value="listaUsuario"/>
      			<input type="text" class="form-control" name="search" id="search" required="required">
    			<button type="submit" class="btn btn-primary" ><b><fmt:message key="br.cefetrj.psw.user.bt_buscar"/></b></button>
    		</div>
		</form>
	</div>
	<div class="table-responsive">
	  <table class="table table-striped">
	      <thead>
		      <tr>
		        <th><fmt:message key="br.cefetrj.psw.user.label.nome"/></th>
		        <th><fmt:message key="br.cefetrj.psw.user.titulo"/></th>
		        <th><fmt:message key="br.cefetrj.psw.user.label.email"/></th>
		        <th><fmt:message key="br.cefetrj.psw.user.label.talterar"/></th>
		        <th><fmt:message key="br.cefetrj.psw.user.label.texcluir"/></th>
		      </tr>
		  </thead>
		  <tbody>
		      <c:forEach items="${ usuario }" var="usu">					
			      <tr>
			        <td>${ usu.nome }</td>
			        <td>${ usu.login }</td>
			        <td>${ usu.email }</td>
			        
			        <td><a href="FrontControllerServlet?action=alteraUsuario&get=true&id=${ usu.id }"><fmt:message key="br.cefetrj.psw.user.label.talterar"/></a></td>
			        <!--  <td><a href="#" data-toggle="modal" data-target="#excluirModal"><fmt:message key="br.cefetrj.psw.user.label.texcluir"/></a></td>  -->
			     	<td><a href="FrontControllerServlet?action=deletaUsuario&get=true&id=${ usu.id }"><fmt:message key="br.cefetrj.psw.user.label.texcluir"/></a></td>
			      </tr>
		      </c:forEach>
		  </tbody>
	  </table>
    </div>
	<div class="text-center">
		<a href="home.jsp" class="btn btn-primary"><fmt:message key="br.cefetrj.psw.user.bt_voltar"/></a>
	</div>
	
	<div class="modal fade" id="excluirModal" role="dialog">
	    <div class="modal-dialog modal-sm">
	      <div class="modal-content">
	        <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	          <h4 class="modal-title"><fmt:message key="br.cefetrj.psw.user.label.deletehead" /></h4>
	        </div>
	        <div class="modal-body">  
	          <p><fmt:message key="br.cefetrj.psw.user.label.delete"/></p>
	        </div>
	        <div class="modal-footer">
	        <form action="FrontControllerServlet" method="POST" >	
	        	<input type="hidden" name="action" value="deletaUsuario" />
				<input type="hidden" name="id" value="${ usu.id }">
				<input type="submit" class="btn btn btn-primary" value="<fmt:message key="br.cefetrj.psw.user.bt_delete_sim"/>">
				<a href="FrontControllerServlet?action=listaUsuario&get=true" class="btn btn btn-danger" style="margin-left: 5px;"><fmt:message key="br.cefetrj.psw.user.bt_delete_nao"/></a>
	        </form>
	        </div>
	      </div>  
	    </div>
  	</div>
  
	<jsp:include page="scripts.jspf"/>
</body>
</html>
