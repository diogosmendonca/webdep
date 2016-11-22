<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import = "java.time.format.DateTimeFormatter" %>


<!-- Variável criada para auxiliar na identificação do locale -->
<c:set var="lang" scope="session" value="${not empty param.lang ? param.lang : not empty lang ? lang : pageContext.request.locale}" />

<c:choose>
	<c:when test="${ lang == 'pt_BR'}">
		<c:set var="dateTime" scope="page" value="dd-MM-yyyy hh:mm" />
	</c:when>
	
	<c:otherwise>
		<c:set var="dateTime" scope="page" value="MM-dd-yyyy hh:mm" />
	</c:otherwise>
</c:choose>

<!-- Necessário para utilizar o i18N, informar o locale e o bundle -->
<fmt:setLocale value="${ lang }" />
<fmt:setBundle basename="Messages" />
<!DOCTYPE html>
<html>
<head>
<title><fmt:message key="br.cefetrj.webdep.jsp.vs.title" /></title>
<jsp:include page="head.jspf" />
</head>
<body class="container-full ">
	<jsp:include page="scripts.jspf" />
	<%@include file="navbar.jspf"%>
	<div class="container">
		<form class="form-horizontal" method="post" action="FrontControllerServlet">
			<div class="form-group">
				<div class="col-sm-4">
					<input class="form-control" type="text" name="arg" id="arg">
				</div>
				<button class="btn btn-primary btn-md" type="submit" name="action" value="searchVersion"><fmt:message key="br.cefetrj.webdep.jsp.vs.search" /></button>
			</div>
		</form>
		
		<div class="tab-pane fade in active" id="1a">
			<form class="form-horizontal" method="post" action="FrontControllerServlet">
				<table class="table table-striped">
					<thead>
						<tr>
							<th><fmt:message key="br.cefetrj.webdep.jsp.vs.system" /></th>
							<th><fmt:message key="br.cefetrj.webdep.jsp.vs.version" /></th>
							<th><fmt:message key="br.cefetrj.webdep.jsp.vs.dateTime" /></th>
							<th><fmt:message key="br.cefetrj.webdep.jsp.vs.change" /></th>
							<th><fmt:message key="br.cefetrj.webdep.jsp.vs.delete" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ list }" var="item">
								<tr>
									<td>${ item.sistema }</td>
									<td>${ item.nome }</td>
									<td>${ item.timestampLiberacao.format(DateTimeFormatter.ofPattern(dateTime)) }</td>
									<td><button type="button" class="btn btn-default" data-toggle="modal" data-target="#changeModal" onclick="getIndex(${ list.indexOf(item) })"><span class="glyphicon glyphicon-edit"></span></button></td>
									<td><button type="button" class="btn btn-default" data-toggle="modal" data-target="#deleteModal" onclick="getIndex(${ list.indexOf(item) })"><span class="glyphicon glyphicon-remove"></span></button></td>
								</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
			<div class="row text-center">		
			<button type="submit" class="btn btn-primary btn-md"><fmt:message key="br.cefetrj.webdep.jsp.vs.back" /></button>
			</div>
	
		</div>
	</div>
	
	
	<div class="modal fade" id="changeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel"><fmt:message key="br.cefetrj.webdep.jsp.vs.changeHeader" /></h4>
                    </div>
                    <div class="modal-body">
                        <div class="container">
                            <form id="form-padrao-url" class="form-horizontal col-sm-11">
									<div class="panel-body">
										<div class="form-group">
											<label class="control-label col-sm-2" for="name"><fmt:message key="br.cefetrj.webdep.jsp.vr.system" /></label>
											<div class="col-xs-4">
												<select class="form-control" name="sistema" id="sistema">
													<option disabled selected><fmt:message key="br.cefetrj.webdep.jsp.vr.select" /></option>
													<option value="Segmentation Fault">Segmentation Fault</option>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-sm-2" for="pwd"><fmt:message key="br.cefetrj.webdep.jsp.vr.versionName" /></label>
											<div class="col-xs-4">
												<input type="text" class="form-control" name="nome" id="nome">
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-sm-2" for="pwd"><fmt:message key="br.cefetrj.webdep.jsp.vr.releaseDate" /></label>
											<div class="input-group date form_date col-sm-4" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
												<div class="input-group">
													<input class="form-control" type="text"  name="date" id="date" readonly />
													<span class="input-group-addon">
														<span class="glyphicon glyphicon-remove"></span>
													</span> 
													<span class="input-group-addon">
														<span class="glyphicon glyphicon-calendar"></span>
													</span>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-sm-2" for="pwd"><fmt:message key="br.cefetrj.webdep.jsp.vr.releaseTime" /></label>
											<div class="input-group date form_time col-sm-4" data-date="" data-date-format="hh:ii" data-link-field="dtp_input3" data-link-format="hh:ii">
												<div class="input-group">
													<input class="form-control" type="text" name="time" id="time" readonly />
													<span class="input-group-addon"> 
														<span class="glyphicon glyphicon-remove"></span>
													</span> 
													<span class="input-group-addon">
														<span class="glyphicon glyphicon-time"></span>
													</span>
												</div>
											</div>
										</div>
								</div>
                                <div class="modal-footer col-sm-6">
	                                	<input type="hidden" id="index" name="index">
	                                    <button id="submit-padrao-url" name="action" value="changeVersion" type="submit" class="btn btn-primary"><fmt:message key="br.cefetrj.webdep.jsp.vs.confirm" /></button>
	                                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="br.cefetrj.webdep.jsp.vs.cancel" /></button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
         </div>
         
         
         

  <div class="modal fade" id="deleteModal" role="dialog">
    <div class="modal-dialog">
    
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title"><fmt:message key="br.cefetrj.webdep.jsp.vs.deleteHeader" /></h4>
        </div>
        <div class="modal-body">
          <p><fmt:message key="br.cefetrj.webdep.jsp.vs.deleteMessage" /></p>
        </div>
        <div class="modal-footer">
	        <form class="form-horizontal" method="post" action="FrontControllerServlet">
	          <input type="hidden" id="index" name="index">
	          <button id="submit-padrao-url" name="action" value="deleteVersion" type="submit" class="btn btn-primary"><fmt:message key="br.cefetrj.webdep.jsp.vs.confirm" /></button>
	          <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="br.cefetrj.webdep.jsp.vs.cancel" /></button>
	        </form>
        </div>
      </div>
      
    </div>
  </div>
  
  <script type="text/javascript">
  function getIndex(index){
	  document.getElementById("index").value = index;
  }
  </script>
  <script type="text/javascript" src="jquery/jquery-1.8.3.min.js" charset="UTF-8"></script>
  <script type="text/javascript" src="js/bootstrap.min.js"></script>
  <script type="text/javascript" src="js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
  <script type="text/javascript" src="js/locales/bootstrap-datetimepicker.<fmt:message key="br.cefetrj.webdep.jsp.datepicker" />.js" charset="UTF-8"></script>
  <script type="text/javascript">
	
		$('.form_date').datetimepicker({
			language : 'pt-BR',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0,
			format: "yyyy-mm-dd"
			
		});
		$('.form_time').datetimepicker({
			language : 'pt-BR',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 1,
			minView : 0,
			maxView : 1,
			forceParse : 0
		});
	</script>      
         
         
</body>
</html>