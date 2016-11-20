<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Variável criada para auxiliar na identificação do locale -->
<c:set var="lang" scope="session"
	value="${not empty param.lang ? param.lang : not empty lang ? lang : pageContext.request.locale}" />

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/css/bootstrap-select.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/js/bootstrap-select.min.js"></script>

<!-- Variável criada para auxiliar na identificação do locale -->
<c:set var="lang" scope="session"
	value="${not empty param.lang ? param.lang : not empty lang ? lang : pageContext.request.locale}" />

<!-- Necessário para utilizar o i18N, informar o locale e o bundle -->
<fmt:setLocale value="${ lang }" />
<fmt:setBundle basename="Messages" />

<!DOCTYPE html>

<style>
/*
.chart rect {
  fill: steelblue;
}
*/
.chart .legend {
	fill: black;
	font: 14px sans-serif;
	text-anchor: start;
	font-size: 12px;
}

.chart text {
	fill: white;
	font: 10px sans-serif;
	text-anchor: end;
}

.chart .label {
	fill: black;
	font: 14px sans-serif;
	text-anchor: end;
}

.bar:hover {
	fill: brown;
}

.axis path, .axis line {
	fill: none;
	stroke: #000;
	shape-rendering: crispEdges;
}
</style>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="br.cefetrj.webdep.jsp.http.title" /></title>
<jsp:include page="head.jspf" />
</head>
<body class="container-full">
	<%@include file="navbar.jspf"%>

	<!-- Campo de Filtros -->


	<form class="form-horizontal" action="FrontControllerServlet"
		method="POST">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h5>
					<fmt:message key="br.cefetrj.webdep.jsp.apr.header" />
				</h5>
			</div>

			<div class="panel-body">
				<div class="form-group">
					<label class="control-label col-sm-2" for="pwd"><fmt:message
							key="br.cefetrj.webdep.jsp.http.URLpattern" /></label>
					<div class="col-xs-2">
						<select class="form-control">
							<option>URLs Sistema</option>
						</select>
						<button class="btn btn-primary" type="button" data-toggle="modal"
							data-target="#myModal">+</button>
					</div>


					<label class="control-label col-sm-2" for="pwd"><fmt:message
							key="br.cefetrj.webdep.jsp.http.HTTPerror" /></label>
					<div class="col-xs-2">
						<select name="errorList" class="selectpicker" multiple title=""
							id="errorList">
							<option>500</option>
							<option>400</option>
							<option>404</option>
						</select>

					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-sm-2" for="pwd"><fmt:message
							key="br.cefetrj.webdep.jsp.http.version" /></label>
					<div class="col-xs-2">
						<select name="versionList" class="selectpicker" multiple title=""
							id="versionlist">
							<option>1.7.1</option>
							<option>1.7.2</option>
							<option>1.8.0</option>
						</select>
					</div>

					<label class="control-label col-sm-2" for="pwd"><fmt:message
							key="br.cefetrj.webdep.jsp.http.CodeHTTPok" /></label>
					<div class="col-xs-2">
						<select name="okList" class="selectpicker" multiple title=""
							id="oklist">
							<option>300</option>
							<option>200</option>
						</select>
						<div>
							<button name="action" value="errorParameter" type="submit"
								class="btn btn-primary btn-md pull-right">
								<fmt:message key="br.cefetrj.webdep.jsp.apr.search" />
							</button>
						</div>
					</div>

				</div>
				<c:if test="${ not empty versionValidate and not versionValidate }">
					<div class="alert alert-danger fade in alert-dismissible">
						 <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong>Atenção!</strong> O campo Versões precisa ser preenchido.
					</div>
				</c:if>
				<c:if test="${ not empty okValidate and not okValidate }">
					<div class="alert alert-danger fade in alert-dismissible">
						 <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong>Atenção!</strong> O campo Código HTTP OK precisa ser preenchido.
					</div>
				</c:if>
				<c:if test="${ not empty erorValidate and not errorValidate }">
					<div class="alert alert-danger fade in alert-dismissible">
						 <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong>Atenção!</strong> O campo Código HTTP de Erro precisa ser preenchido.
					</div>
				</c:if>
			</div>
		</div>
	</form>

	<!-- Tabela  -->

	<div id="exTab1" class="panel panel-default">
		<ul class="nav nav-tabs ">
			<li class="active"><a href="#1a" data-toggle="tab"><fmt:message
						key="br.cefetrj.webdep.jsp.apr.table" /></a></li>
			<li><a href="#2a" data-toggle="tab"><fmt:message
						key="br.cefetrj.webdep.jsp.apr.graphic" /></a></li>
		</ul>
		<div class="tab-content clearfix">
			<div class="tab-pane fade in active" id="1a">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>URLs</th>
							<th><fmt:message key="br.cefetrj.webdep.jsp.http.access" /></th>
							<th><fmt:message key="br.cefetrj.webdep.jsp.http.fail" /></th>
							<th><fmt:message key="br.cefetrj.webdep.jsp.http.probFail" /></th>
						</tr>
					</thead>
					<tbody>
						<tr>
						</tr>
					</tbody>
				</table>

				<button type="submit" class="btn btn-primary btn-md">
					<fmt:message key="br.cefetrj.webdep.jsp.http.back" />
				</button>

			</div>

			<!-- Gráfico -->

			<div class="tab-pane fade" id="2a">
				<svg class="chart"></svg>
				<script src="http://d3js.org/d3.v3.min.js"></script>
				<script>
					var data = {
						labels : [ 'resilience', 'maintainability',
								'accessibility', 'uptime', 'functionality',
								'impact' ],
						series : [ {
							label : '2012',
							values : [ 4, 8, 15, 16, 23, 42 ]
						}, {
							label : '2013',
							values : [ 12, 43, 22, 11, 73, 25 ]
						}, {
							label : '2014',
							values : [ 31, 28, 14, 8, 15, 21 ]
						}, ]
					};

					var chartWidth = 300, barHeight = 20, groupHeight = barHeight
							* data.series.length, gapBetweenGroups = 10, spaceForLabels = 150, spaceForLegend = 150;

					// Zip the series data together (first values, second values, etc.)
					var zippedData = [];
					for (var i = 0; i < data.labels.length; i++) {
						for (var j = 0; j < data.series.length; j++) {
							zippedData.push(data.series[j].values[i]);
						}
					}

					// Color scale
					var color = d3.scale.category20();
					var chartHeight = barHeight * zippedData.length
							+ gapBetweenGroups * data.labels.length;

					var x = d3.scale.linear().domain([ 0, d3.max(zippedData) ])
							.range([ 0, chartWidth ]);

					var y = d3.scale.linear().range(
							[ chartHeight + gapBetweenGroups, 0 ]);

					var yAxis = d3.svg.axis().scale(y).tickFormat('').tickSize(
							0).orient("left");

					// Specify the chart area and dimensions
					var chart = d3.select(".chart").attr("width",
							spaceForLabels + chartWidth + spaceForLegend).attr(
							"height", chartHeight);

					// Create bars
					var bar = chart
							.selectAll("g")
							.data(zippedData)
							.enter()
							.append("g")
							.attr(
									"transform",
									function(d, i) {
										return "translate("
												+ spaceForLabels
												+ ","
												+ (i * barHeight + gapBetweenGroups
														* (0.5 + Math
																.floor(i
																		/ data.series.length)))
												+ ")";
									});

					// Create rectangles of the correct width
					bar.append("rect").attr("fill", function(d, i) {
						return color(i % data.series.length);
					}).attr("class", "bar").attr("width", x).attr("height",
							barHeight - 1);

					// Add text label in bar
					bar.append("text").attr("x", function(d) {
						return x(d) - 3;
					}).attr("y", barHeight / 2).attr("fill", "red").attr("dy",
							".35em").text(function(d) {
						return d;
					});

					// Draw labels
					bar.append("text").attr("class", "label").attr("x",
							function(d) {
								return -10;
							}).attr("y", groupHeight / 2).attr("dy", ".35em")
							.text(
									function(d, i) {
										if (i % data.series.length === 0)
											return data.labels[Math.floor(i
													/ data.series.length)];
										else
											return ""
									});

					chart.append("g").attr("class", "y axis").attr(
							"transform",
							"translate(" + spaceForLabels + ", "
									+ -gapBetweenGroups / 2 + ")").call(yAxis);
				</script>
			</div>
		</div>
	</div>

	<!-- MODAL NOVO PADRAO URL -->

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Novo Padrão de URL</h4>
				</div>
				<div class="modal-body">
					<div class="container">
						<form id="form-padrao-url" class="form-horizontal col-sm-11">
							<div class="row">
								<div id="div-nome" class="form-group">
									<label class="col-sm-2 text-right control-label"
										for="padrao-url-nome">Nome: </label>
									<div class="col-sm-3">
										<input name="padrao-url-nome" id="padrao-url-nome" type="text"
											class="form-control" />
									</div>
								</div>
								<div id="div-regex" class="form-group">
									<label class="col-sm-2 text-right control-label" for="regex">Expressão
										Regular:</label>
									<div class="col-sm-3 input-group">
										<input id="regex" name="regex" type="text"
											class="form-control" /> <span
											class="input-group-btn text-right">
											<button id="submit-regex" name="action"
												class="btn btn-primary" value="buscaRegex" type="submit">Buscar</button>
										</span>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<table class="table table-bordered">
										<thead>
											<tr>
												<th>URLs Encontrados</th>
											</tr>
										</thead>
										<tbody id="table-url">

										</tbody>
									</table>
								</div>
							</div>
							<div class="modal-footer col-sm-6">
								<button id="submit-padrao-url" name="action" type="submit"
									class="btn btn-primary" value="salvarPadrao">Salvar</button>
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Cancelar</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="scripts.jspf" />
</body>
</html>
