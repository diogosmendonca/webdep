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
		<form class="form-horizontal">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h5><fmt:message key="br.cefetrj.webdep.jsp.apr.header" /></h5>
				</div>
				<div class="panel-body">
					<div class="form-group">
						<label class="control-label col-sm-2" for="pwd"><fmt:message key="br.cefetrj.webdep.jsp.apr.initialDate" /></label>
						<div style="float: left" class="input-group date form_date col-sm-2" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
							<div class="input-group">
								<input class="form-control" type="text" readonly />
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
								<input class="form-control" type="text" readonly />
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
						<div style="float: left" class="input-group date form_time col-sm-2" data-date="" data-date-format="hh:ii" data-link-field="dtp_input3" data-link-format="hh:ii">
							<div class="input-group">
								<input class="form-control" type="text" readonly />
								<span class="input-group-addon"> 
									<span class="glyphicon glyphicon-remove"></span>
								</span> 
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-time"></span>
								</span>
							</div>
						</div>
						<label class="control-label col-sm-2" for="pwd"><fmt:message key="br.cefetrj.webdep.jsp.apr.finalTime" /></label>
						<div style="float: left" class="input-group date form_time col-sm-2" data-date="" data-date-format="hh:ii" data-link-field="dtp_input3" data-link-format="hh:ii">
							<div class="input-group">
								<input class="form-control" type="text" readonly />
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
							<select class="form-control">
								<option><fmt:message key="br.cefetrj.webdep.jsp.apr.select.day" /></option>
								<option><fmt:message key="br.cefetrj.webdep.jsp.apr.select.month" /></option>
								<option><fmt:message key="br.cefetrj.webdep.jsp.apr.select.year" /></option>
							</select>
						</div>
						<label class="control-label col-sm-2" for="pwd"><fmt:message key="br.cefetrj.webdep.jsp.apr.urlPatterns" /></label>
						<div class="col-xs-4">
							<select class="form-control">
								<option>URLs Sistema</option>
							</select>
						</div>
						<button type="submit" class="btn btn-primary btn-md"><fmt:message key="br.cefetrj.webdep.jsp.apr.search" /></button>
					</div>
				</div>
			</div>
		</form>
			<div id="exTab1" class="panel panel-default">
				<ul class="nav nav-tabs ">
					<li class="active"><a href="#1a" data-toggle="tab"><fmt:message key="br.cefetrj.webdep.jsp.apr.table" /></a></li>
					<li><a href="#2a" data-toggle="tab"><fmt:message key="br.cefetrj.webdep.jsp.apr.graphic" /></a></li>
				</ul>
				<div class="tab-content clearfix">
					<div class="tab-pane fade in active" id="1a">Tabela Aqui</div>
					<div class="tab-pane fade" id="2a">Gráfico Aqui</div>
				</div>
			</div>
	</div>
	
	<script type="text/javascript" src="jquery/jquery-1.8.3.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
	<script type="text/javascript" src="js/locales/bootstrap-datetimepicker.<fmt:message key="br.cefetrj.webdep.jsp.datepicker" />.js" charset="UTF-8"></script>
	<script type="text/javascript">
		$('.form_datetime').datetimepicker({
			language : 'pt-BR',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			forceParse : 0,
			showMeridian : 1
		});
		$('.form_date').datetimepicker({
			language : 'pt-BR',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0
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
