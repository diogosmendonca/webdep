<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- Variável criada para auxiliar na identificação do locale -->
<c:set var="lang" scope="session" value="${not empty param.lang ? param.lang : not empty lang ? lang : pageContext.request.locale}"/>

<!-- Necessário para utilizar o i18N, informar o locale e o bundle -->
<fmt:setLocale value="${ lang }"/>
<fmt:setBundle basename="Messages" />
<!DOCTYPE html>
<html>
<head>
<title><fmt:message key="br.cefetrj.webdep.jsp.apr.title" /></title>
<jsp:include page="head.jspf" />
</head>
<body class="container-full ">
	<jsp:include page="scripts.jspf" />
	<%@include file="navbar.jspf"%>
	<div class="container">
		<form class="form-horizontal" method="post" action="FrontControllerServlet">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h5><fmt:message key="br.cefetrj.webdep.jsp.apr.header" /></h5>
				</div>
				<div class="panel-body">
					<div class="form-group">
						<label class="control-label col-sm-2" for="pwd"><fmt:message key="br.cefetrj.webdep.jsp.apr.initialDate" /></label>
						<div style="float: left" class="input-group date form_date col-sm-2" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
							<div class="input-group">
								<input class="form-control" type="text" name="initialDate" id="initialDate" readonly />
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-remove"></span>
								</span> 
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
						</div>
						<label class="control-label col-sm-2" for="pwd"><fmt:message key="br.cefetrj.webdep.jsp.apr.finalDate" /></label>
						<div style="float: left" class="input-group date form_date col-sm-2" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
							<div class="input-group">
								<input class="form-control" type="text" name="finalDate" id="finalDate" readonly />
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
						<label class="control-label col-sm-2" for="pwd"><fmt:message key="br.cefetrj.webdep.jsp.apr.initialTime" /></label>
						<div style="float: left" class="input-group date form_time col-sm-2" data-date="" data-date-format="hh:ii:ss" data-link-field="dtp_input3" data-link-format="hh:ii:ss">
							<div class="input-group">
								<input class="form-control" type="text" name="initialTime" id="initialTime" placeholder = "HH:MM:SS" />
								<span class="input-group-addon"> 
									<span class="glyphicon glyphicon-remove"></span>
								</span> 
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-time"></span>
								</span>
							</div>
						</div>
						<label class="control-label col-sm-2" for="pwd"><fmt:message key="br.cefetrj.webdep.jsp.apr.finalTime" /></label>
						<div style="float: left" class="input-group date form_time col-sm-2" data-date="" data-date-format="hh:ii:ss" data-link-field="dtp_input3" data-link-format="hh:ii:ss">
							<div class="input-group">
								<input class="form-control" type="text" name="finalTime" id="finalTime" placeholder = "HH:MM:SS" />
								<span class="input-group-addon"> 
									<span class="glyphicon glyphicon-remove"></span>
								</span> 
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-time"></span>
								</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="pwd"><fmt:message key="br.cefetrj.webdep.jsp.apr.group" /></label>
						<div class="col-xs-2">
							<select class="form-control" name="groupApr" id="groupApr">
								<option value="0"><fmt:message key="br.cefetrj.webdep.jsp.apr.select.day" /></option>
								<option value="1"><fmt:message key="br.cefetrj.webdep.jsp.apr.select.month" /></option>
								<option value="2"><fmt:message key="br.cefetrj.webdep.jsp.apr.select.year" /></option>
							</select>
						</div>
						<label class="control-label col-sm-2" for="pwd"><fmt:message key="br.cefetrj.webdep.jsp.apr.urlPatterns" /></label>
							<div class="col-xs-2">
								<select id="selectPadraoURL" class="form-control">
					<!-- essa parte está no meu caso de teste pode deixar que eu preencho conforme o usuario logado. Ass: Luan -->
								</select>
								<button class="btn btn-primary" type="button" data-toggle="modal"
									data-target="#myModal">+</button>
									<button id="deletePadraoURL" name="deletepadraourl" class="btn btn-primary" 
									 type="button">-</button>
							</div>
						<button type="submit" class="btn btn-primary btn-md" name="action" value="accessProfileReport"><fmt:message key="br.cefetrj.webdep.jsp.apr.search" /></button>
					</div>
					<c:if test="${not empty dataIn and not dataIn }">
								<span class="help-block">
									<fmt:message key="br.cefetrj.webdep.jsp.apr.dataError"/>
								</span>
					</c:if>
				</div>
			</div>
		</form>
			<div id="exTab1" class="panel panel-default">
				<ul class="nav nav-tabs ">
					<li class="active"><a href="#1a" data-toggle="tab"><fmt:message key="br.cefetrj.webdep.jsp.apr.table" /></a></li>
					<li><a href="#2a" data-toggle="tab"><fmt:message key="br.cefetrj.webdep.jsp.apr.graphic" /></a></li>
				</ul>
				<div class="tab-content clearfix">
					<div class="tab-pane fade in active" id="1a">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>
										<c:choose>
											<c:when test="${ groupApr == '0' }">
												<fmt:message key="br.cefetrj.webdep.jsp.apr.select.day" />
											</c:when>
											<c:when test="${ groupApr == '1' }">
												<fmt:message key="br.cefetrj.webdep.jsp.apr.select.month" />
											</c:when>
											<c:when test="${ groupApr == '2' }">
												<fmt:message key="br.cefetrj.webdep.jsp.apr.select.year" />
											</c:when>
										</c:choose>
									</th>
									<th><fmt:message key="br.cefetrj.webdep.jsp.apr.accessCount" /></th>
								</tr>
							</thead>
							<tbody>
								<c:set var="aprList" value="${ aprMap.keySet() }" scope="page"></c:set>	
								<c:forEach items="${ aprList }" var="item">
										<tr>
											<td>${ item.format(aprfmt) }</td>
											<td>${ aprMap.get(item) }</td>
										</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="tab-pane fade" id="2a">Gráfico Aqui</div>
				</div>
			</div>
	</div>
	
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
			forceParse : 0,
			format : "hh:ii:ss"
		});
	</script>
</body>
</html>
