<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!-- Variável criada para auxiliar na identificação do locale -->
<c:set var="lang" scope="session" value="${not empty param.lang ? param.lang : not empty lang ? lang : pageContext.request.locale}" />

<!-- Necessário para utilizar o i18N, informar o locale e o bundle -->
<fmt:setLocale value="${ lang }" />
<fmt:setBundle basename="Messages" />
<!DOCTYPE html>
<html>
<head>
<title><fmt:message key="br.cefetrj.webdep.jsp.vr.title" /></title>
<jsp:include page="head.jspf" />
</head>
<body class="container-full ">
	<jsp:include page="scripts.jspf" />
	<%@include file="navbar.jspf"%>
	<div class="container">
		<form class="form-horizontal container" method="post" action="FrontControllerServlet">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3>
						<fmt:message key="br.cefetrj.webdep.jsp.vr.header" />
					</h3>
				</div>
				<div class="panel-body">
					<div class="form-group">
						<label class="control-label col-sm-2" for="name"><fmt:message key="br.cefetrj.webdep.jsp.vr.system" /></label>
						<div class="col-xs-2">
							<select class="form-control" name="sistema" id="sistema">
								<option disabled selected><fmt:message key="br.cefetrj.webdep.jsp.vr.select" /></option>
								<option value="Segmentation Fault">Segmentation Fault</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="pwd"><fmt:message key="br.cefetrj.webdep.jsp.vr.versionName" /></label>
						<div class="col-xs-4">
							<input type="text" class="form-control" name="nome" id="nome" >
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
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd"></label> 
				<label class="control-label col-sm-2" for="pwd"></label>
				<div class="col-xs-4">
					<button type="submit" class="btn btn-primary btn-md">
						<fmt:message key="br.cefetrj.webdep.jsp.vr.save" />
					</button>
					<button type="reset" class="btn btn-danger btn-md">
						<fmt:message key="br.cefetrj.webdep.jsp.vr.cancel" />
					</button>
				</div>
			</div>
		</form>
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
