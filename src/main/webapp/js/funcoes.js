$(document).ready(
		function() {
			if (window.location.href.indexOf("cadastrodesistema.jsp?u=") > -1) {
				$("#update").val("update");
				var url = window.location.href;
				var id = url.substring(window.location.href.indexOf("=")+1, url.length);
				$("#id-sistema-update").val(id);
				$("#action").val("updateSistema");
				$.ajax({
					type : "POST",
					url : "FrontControllerServlet",
					data : {
						action : "fillSistema",
						filtro : id
						},
						success : function(response) {
							var resposta = $.parseJSON(response);
							$("#nome").val(resposta.sistema.nome);
							$("#servidor").val(resposta.sistema.servidor).change();
							$("#formatoLog").val(resposta.sistema.formatolog);
							$("#ptLogs").val(resposta.sistema.ptLogs);
							$("#pxLogs").val(resposta.sistema.pxLogs);
							$("#ptLogs2").val(resposta.sistema.ptLogs2);
							$("#pxLogs2").val(resposta.sistema.pxLogs2);
							$("#dataLeitura").val(resposta.sistema.data);
							$("#horarioLeitura").val(resposta.sistema.time);
							
							$("#novaLeituraDia").val(resposta.sistema.novaData.split(" ")[0]);
							$("#novaLeituraHora").val(resposta.sistema.novaData.split(" ")[1]);
							}
						});
				} else if (window.location.href.indexOf("gerenciadorsistema.jsp") > -1) {
					$.ajax({
						type : "POST",
						url : "FrontControllerServlet",
						data : {
							action : "listSistema",
							filtro : "all"
								},
								success : function(response) {
									var resposta = $.parseJSON(response);
									var sistemas = resposta.sistemas;
									var erro = resposta.Erro;
									if (resposta.hasOwnProperty("sistemas")) {
										$("#table-sistemas").children().remove();
										sistemas.forEach(function(el) {
											$("#table-sistemas").append("<tr><td>"
													+ el.nome + "</td><td>"
													+ el.servidor + "</td><td>"
													+ el.formatolog + "</td><td>"
													+ el.periodicidade + "</td><td>"
													+ el.proximaleitura + "</td>" 
													+ "<td><a onclick=alterar(\""
													+ el.id + "\"); id=\"" + el.id
													+ "-alterar\" class=\"alterar-sistema\">Alterar</a></td>"
													+ "<td><a onclick=excluir(\""
													+ el.id + "\"); id=\"" + el.id 
													+ "-excluir\" class=\"excluir-sistema\">Excluir</a></td></tr>");
											});
										} else if (resposta.hasOwnProperty("Erro")) {
											alert(resposta.Erro);
											}
									}
								});
					} else if (window.location.href.indexOf("HTTPreport.jsp") > -1) {
						$.ajax({
							type : "POST",
							url : "FrontControllerServlet",
							data : {
								action : "fillPadraoURL"
							},
							success : function(response) {
								var resposta = $.parseJSON(response);
								$("#selectPadraoURL").children().remove();
								if (resposta.hasOwnProperty("padroes")) {
									var padroes = resposta.padroes;
									padroes.forEach(function (padrao){
										$("#selectPadraoURL").append("<option value='"+padrao.id+"'>"+padrao.nome+"</option>");
									});
								} else if (resposta.hasOwnProperty("Erro")) {
									$("#selectPadraoURL").append("<option>Adicione um Padrão URL<option>");
									alert(resposta.Erro);
								}
							}
						});
					}
			
				/* LISTAGEM DE SISTEMA */
				$("#buscar-sistemas").click(function() {
					var filtro = $("#filtro-busca-sistemas").val();
					// validação form
					if (filtro.trim() === "") {
						$("#div-buscar-sistemas").addClass("has-error");
						$("#div-buscar-sistemas span").addClass("glyphicon glyphicon-remove form-control-feedback");
						alert("Favor preencher o filtro de busca");
					} else {
						if (filtro.trim().length > 100) {
							$("#div-buscar-sistemas").addClass("has-error");
							$("#div-buscar-sistemas span").addClass("glyphicon glyphicon-remove form-control-feedback");
							alert("Tamanho do filtro excedido. \nFavor não ultrapassar 100 caractéres");
						}else{
							$("#div-buscar-sistemas").removeClass("has-error");
							$("#div-buscar-sistemas span").removeClass("glyphicon glyphicon-remove form-control-feedback");
								$.ajax({
									type : "POST",
									url : "FrontControllerServlet",
									data : {
										action : "listSistema",
										filtro : filtro
										},
										success : function(response) {
											var resposta = $.parseJSON(response);
											var sistemas = resposta.sistemas;
											var erro = resposta.Erro;
											$("#table-sistemas").children().remove();
											if (resposta.hasOwnProperty("sistemas")) {
												sistemas.forEach(function(el) {
													$("#table-sistemas").append("<tr><td>"
															+ el.nome
															+ "</td><td>"
															+ el.servidor
															+ "</td><td>"
															+ el.formatolog
															+ "</td><td>"
															+ el.periodicidade
															+ "</td><td>"
															+ el.proximaleitura
															+ "</td>"
															+ "<td><a onclick=alterar(\""
															+ el.id
															+ "\"); id=\""
															+ el.id
															+ "-alterar\" class=\"alterar-sistema\">Alterar</a></td>"
															+ "<td><a onclick=excluir(\""
															+ el.id
															+ "\"); id=\""
															+ el.id
															+ "-excluir\" class=\"excluir-sistema\">Excluir</a></td></tr>");
													});
												} else if (resposta.hasOwnProperty("Erro")) {
													alert(resposta.Erro);
													
													}
											}
										});
								}
						}
				});
				/* FIM LISTAGEM DE SISTEMA */

				/* MODAL PADRAO URL */
				// abrir modal Novo Padrão de URL
				$('#myModal').on('shown.bs.modal', function() {
					$('#myInput').focus();
				});
				
				// Buscar Expressão Regular
				$("#submit-regex").click(function() {
					var regex = $("#regex").val();
					var regex = $("#regex").val();
					var nome = $("#padrao-url-nome").val();
					if (regex.trim() === ""){
						$("#div-regex").addClass("has-error");
						$("#regex-error").html("Favor preencher este campo.");
					} else {
						$("#div-regex").removeClass("has-error");
						$("#regex-error").html("");
					}
					if (nome.trim() === ""){
						$("#div-nome").addClass("has-error");
						$("#nome-error").html("Favor preencher este campo.");
					} else {
						$("#div-nome").removeClass("has-error");
						$("#nome-error").html("");
					}
					if (regex.trim() !== "" && nome.trim() !== "") {
						if (regex.length > 255){
							$("#div-regex").addClass("has-error");
							$("#regex-error").html("Limite de caractéres excedido.");
						} 
						if (nome.length > 50){
							$("#div-nome").addClass("has-error");
							$("#nome-error").html("Limite de caractéres excedido.");
						} 
						if (regex.length <= 255 && nome.length <= 50){
							$("#div-regex").removeClass("has-error");
							$("#regex-error").html("");
							$("#div-nome").removeClass("has-error");
							$("#nome-error").html("");
							$.ajax({
								type : "POST",
								url : "FrontControllerServlet",
								data : {
									action : "regexPadraoURL",
									regex : regex
									},
									success : function(response) {
										var resposta = $.parseJSON(response);
										console.log(response)
										if (resposta.hasOwnProperty("url")) {
											$("#table-url").children().remove();
											var urls = resposta.url;
											urls.forEach(function(el) {
												$("#table-url").append("<tr><td>"
														+ el
														+ "</td></tr>");
												});
											refresh = false;
											} else if (resposta.hasOwnProperty("Erro")) {
												alert(resposta.Erro);
												}
									}
							});
						}
					}
					return false;
				});
				// Salvar Padrão URL
				$("#submitpadraourl").on("click", function() {
					var regex = $("#regex").val();
					var regex = $("#regex").val();
					var nome = $("#padrao-url-nome").val();
					if (regex.trim() === ""){
						$("#div-regex").addClass("has-error");
						$("#regex-error").html("Favor preencher este campo.");
					} else {
						$("#div-regex").removeClass("has-error");
						$("#regex-error").html("");
					}
					if (nome.trim() === ""){
						$("#div-nome").addClass("has-error");
						$("#nome-error").html("Favor preencher este campo.");
					} else {
						$("#div-nome").removeClass("has-error");
						$("#nome-error").html("");
					}
					if (regex.trim() !== "" && nome.trim() !== "") {
						if (regex.length > 255){
							$("#div-regex").addClass("has-error");
							$("#regex-error").html("Limite de caractéres excedido.");
						} 
						if (nome.length > 50){
							$("#div-nome").addClass("has-error");
							$("#nome-error").html("Limite de caractéres excedido.");
						} 
						if (regex.length <= 255 && nome.length <= 50){
							$("#div-regex").removeClass("has-error");
							$("#regex-error").html("");
							$("#div-nome").removeClass("has-error");
							$("#nome-error").html("");
							$.ajax({
								type : "POST",
								url : "FrontControllerServlet",
								data : {
									action : "insertPadraoURL",
									nome : nome,
									regex : regex
									},
									success : function(response) {
										var resposta = $.parseJSON(response);
										if (resposta.hasOwnProperty("mensagem") > -1) {
											var mensagem = resposta.mensagem;
											alert(mensagem);
											window.location.replace("HTTPreport.jsp");
										} else if (resposta.indexOf("Erro")) {
											alert("Erro de conexão com o servidor");
											}
										}
									});
						}
						return false;
					}
				});
				
				$("#deletePadraoURL").on("click", function() {
					var idPadrao = $("#selectPadraoURL").val();
					if(idPadrao != null){
						if (confirm("Tem certeza que deseja excluir este padrão URL?")){
							$.ajax({
								type : "POST",
								url : "FrontControllerServlet",
								data : {
									action : "deletePadraoURL",
									id : idPadrao
									},
								success : function(response) {
									var resposta = $.parseJSON(response);
									if (resposta.hasOwnProperty("mensagem") > -1) {
										var mensagem = resposta.mensagem;
										alert(mensagem);
										window.location.replace("HTTPreport.jsp");
									} else if (resposta.indexOf("Erro")) {
										alert("Erro de conexão com o servidor");
										}
								}
							});
						}
					} else {
						alert("Exclusão não executada. Não há registros no banco.");
					}
					
				});
				/* FIM PADRAO URL */
				/* DATEPICKER */
				// não é necessário cada grupo colocar os
				// codigos javascript em cada página
				// isoladamente
				$('.form_datetime').datetimepicker({
					language : 'pt-BR',
					weekStart : 1,
					todayBtn : 1,
					autoclose : 1,
					todayHighlight : 1,
					startView : 2,
					forceParse : 0,
					showMeridian : 1,
					format : 'YYYY-MM-DD HH:mm:ss'
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
					forceParse : 0,
                    minuteStep: 1 
				});
				/* FIM DATEPICKER */
			
				/* MASCARA PARA INPUT DE DATA E HORA NO CADASTRO SISTEMA */	
				$("#horarioLeitura").mask("99:99");
				$("#dataLeitura").mask("9999-99-99");
				$("#novaLeituraDia").mask("99");
				$("#novaLeituraHora").mask("99:99");
				
				/*CADASTRO DE SISTEMA*/
				$("#servidor").on('change keyup mouseup', function (){
					var open = $(this).data("isopen");

				    if(open) {
						var serverId = $("#servidor").val();
						$.ajax({
							type : "POST",
							url : "FrontControllerServlet",
							data : {
								action: "fillFormatoLog",
								filtro: serverId
							}, // action fica no
							// jsp porque o form
							// está sendo
							// serializado(o
							// action está
							// dentro de
							// sistemaForm)
							success : function(response) {
								var resposta = $
										.parseJSON(response);
								if (resposta.hasOwnProperty("formatoLogs")) {
									$("#formatoLog").children().remove();
									var formatoLogs = resposta.formatoLogs;
									$("#formatoLog").append("<option value='' selected disabled></option>");
									for (var i = 0; i < formatoLogs.length; i += 1 ){
										$("#formatoLog").append("<option value='"+
												formatoLogs[i].id +"'>"+ 
												formatoLogs[i].nome +"</option>");
									}
								}
							}
						});
				    }

				    $(this).data("isopen", !open);
				});
				
				$("#cadastro-sistema-submit")
						.click(
								function() {
									var nome = $("#nome").val();
									var servidor = $("#servidor").val();
									var formatoLog = $("#formatoLog").val();
									var ptLogs = $("#ptLogs").val();
									var pxLogs = $("#pxLogs").val();
									var ptLogs2 = $("#ptLogs2").val();
									var pxLogs2 = $("#pxLogs2").val();
									var dataPrimeiraLeitura =  $("#dataLeitura").val();
                                    var horaPrimeiraLeitura = $("#horarioLeitura").val();
                                    var novaLeituraDia = $("#novaLeituraDia").val();
                                    var novaLeituraHora = $("#novaLeituraHora").val();
									var action = $("#action").val();
									var id_sistema_update = $(
											"#id-sistema-update").val();
									// validação form
									if (nome.trim() === "") {
										$("#div-nome").addClass(
												"has-error");
									} else {
										$("#div-nome").removeClass(
										"has-error");
									}
									if (servidor === null || servidor.trim() === "") {
										$("#div-servidor").addClass(
												"has-error");
									} else {
										$("#div-servidor").removeClass(
										"has-error");
									}
									if (formatoLog === null || formatoLog.trim() === "") {
										$("#div-formatoLog").addClass(
												"has-error");
									} else {
										$("#div-formatoLog").removeClass(
										"has-error");
									}
									if (ptLogs.trim() === "") {
										$("#div-pasta-acesso").addClass(
												"has-error");
									} else {
										$("#div-pasta-acesso").removeClass(
										"has-error");
									}
									if (pxLogs.trim() === "") {
										$("#div-prefixo-acesso").addClass(
												"has-error");
									} else {
										$("#div-prefixo-acesso").removeClass(
										"has-error");
									}
									if (ptLogs2.trim() === "") {
										$("#div-pasta-erro").addClass(
												"has-error");
									} else {
										$("#div-pasta-erro").removeClass(
										"has-error");
									}
									if (pxLogs2.trim() === "") {
										$("#div-prefixo-erro").addClass(
												"has-error");
									} else {
										$("#div-prefixo-erro").removeClass(
										"has-error");
									}
									if (dataPrimeiraLeitura.trim() === "") {
										$("#div-dataLeitura").addClass(
												"has-error");
									} else {
										$("#div-dataLeitura").removeClass(
										"has-error");
									}
									if (horaPrimeiraLeitura.trim() === "") {
										$("#div-horarioLeitura").addClass(
												"has-error");
									} else {
										$("#div-horarioLeitura").removeClass(
										"has-error");
									}
									if (novaLeituraDia.trim() === "" && novaLeituraHora.trim() === "") {
										$("#div-novaLeitura").addClass(
												"has-error");
									} else {
										$("#div-novaLeitura").removeClass(
										"has-error");
									}
									
									if (nome.trim() !== "" &&  servidor.trim() !== "" && formatoLog.trim() !== "" &&
										ptLogs.trim() !== "" && ptLogs2.trim() !== "" && pxLogs.trim() !== "" && pxLogs2.trim() !== "" &&
										dataPrimeiraLeitura.trim() !== "" && horaPrimeiraLeitura.trim() !== "" 
											&& novaLeituraDia.trim() !== "" && novaLeituraHora.trim() !== "") {
									//////Validar limite caracter
										if (nome.trim().length > 100) {
											alert("Campo 'Nome' execedeu limite de caracteres")
											return false;
										}
										if (servidor.trim().length > 100) {
											alert("Campo 'Servidor' execedeu limite de caracteres")
											return false;
										}
										if (formatoLog.trim().length > 50) {
											alert("Campo 'Formato Log' execedeu limite de caracteres")
											return false;
										}
										if (ptLogs.trim().length > 255) {
											alert("Campo 'Pasta Log de Acesso' execedeu limite de caracteres")
											return false;
										}
										if (pxLogs.trim().length > 100) {
											alert("Campo 'Prefixo Log de Acesso' execedeu limite de caracteres")
											return false;
										}
										if (ptLogs2.trim().length > 255) {
											alert("Campo 'Pasta Log de Erro' execedeu limite de caracteres")
											return false;
										}
										if (pxLogs2.trim().length > 100) {
											alert("Campo 'Prefixo Log de Erro' execedeu limite de caracteres")
											return false;
										}
										
										$("#div-nome").removeClass("has-error");
										$("#div-servidor").removeClass("has-error");
										$("#div-formatoLog").removeClass("has-error");
										$("#div-pasta-acesso").removeClass("has-error");
										$("#div-prefixo-acesso").removeClass("has-error");
										$("#div-pasta-erro").removeClass("has-error");
										$("#div-prefixo-erro").removeClass("has-error");
										$("#div-dataLeitura").removeClass("has-error");
										$("#div-horarioLeitura").removeClass("has-error");
										$("#div-novaLeitura").removeClass("has-error");
										$.ajax({
													type : "POST",
													url : "FrontControllerServlet",
													data : {
														nome : nome,
														servidor : servidor,
														formatoLog : formatoLog,
														ptLogs : ptLogs,
														pxLogs : pxLogs,
														ptLogs2 : ptLogs2,
														pxLogs2 : pxLogs2,
														dataPrimeiraLeitura : dataPrimeiraLeitura,
                                                        horaPrimeiraLeitura : horaPrimeiraLeitura,
                                                        novaLeituraDia : novaLeituraDia,
                                                        novaLeituraHora: novaLeituraHora,
                                                        horaPrimeiraLeitura : horaPrimeiraLeitura,
														action : action,
														id_sistema_update : id_sistema_update
													}, // action fica no
													// jsp porque o form
													// está sendo
													// serializado(o
													// action está
													// dentro de
													// sistemaForm)
													success : function(
															response) {
														var resposta = $
																.parseJSON(response);
														if (resposta
																.hasOwnProperty("mensagem")) {
															var mensagem = resposta.mensagem;
															alert(mensagem);
															window.location.replace("gerenciadorsistema.jsp");
															
															}
														}
													});
											return false;
										} else {
											$("#cadastro-mensagem").html("Favor preencher os campos destacados.");
										}
									});
				});

function alterar(nome) {
	window.location.replace("cadastrodesistema.jsp?u=" + nome);
}

function excluir(nome) {
	decisao = confirm("Tem certeza que deseja excluir este sistema?\nTodos os registros de logs serão apagados.");
if (decisao) {
	// alert("Não disponível");
	$.ajax({
		type : "POST",
		url : "FrontControllerServlet",
		data : {
			action : "deleteSistema",
			filtro : nome
		},
		success : function(response) {
			var resposta = $.parseJSON(response);
			if (resposta.mensagem.indexOf("sucesso") > -1) {
				alert(resposta.mensagem);
				window.location.replace("gerenciadorsistema.jsp");
				}

			}
		});
	}
}
// Teste acesso
$("#pxLogs-teste-btn").click(function() {
var pxLogs = $("#pxLogs").val();
var ptLogs = $("#ptLogs").val();
if (pxLogs === "" || ptLogs === "") {
	$("#div-pasta-acesso").addClass("has-error");
	$("#div-prefixo-acesso").addClass("has-error");
	alert("Favor preencher campos destacados para testar o acesso.");
} else {
	$("#div-pasta-acesso").removeClass("has-error");
	$("#div-prefixo-acesso").removeClass("has-error");
	$.ajax({
		type : "POST",
		url : "FrontControllerServlet",
		data : {
			action : "testAccessLogFolder",
				pxLogs : pxLogs,
				ptLogs : ptLogs
			},
			success : function(response) {
				var resposta = $.parseJSON(response);
				alert(resposta.mensagem);
			}
		});
	}
});

$("#cancela-btn").click(function() {
window.location.replace("home.jsp?");
});

$("#volta-btn").click(function() {
window.location.replace("home.jsp?");
});

$("#pxLogs2-teste-btn").click(function() {
var pxLogs2 = $("#pxLogs2").val();
var ptLogs2 = $("#ptLogs2").val();
if (pxLogs2 === "" || ptLogs2 === "") {
	$("#div-pasta-erro").addClass("has-error");
	$("#div-prefixo-erro").addClass("has-error");
	alert("Favor preencher campos destacados para testar o acesso.");
} else {
	$("#div-pasta-erro").removeClass("has-error");
	$("#div-prefixo-erro").removeClass("has-error");
	$.ajax({
		type : "POST",
		url : "FrontControllerServlet",
		data : {
			action : "testErrorLogFolder",
				pxLogs2 : pxLogs2,
				ptLogs2 : ptLogs2
			},
			success : function(response) {
				var resposta = $.parseJSON(response);
				alert(resposta.mensagem);
			}
		});
	}
});
$("#svg-chart").on("load", reportCharts());
function reportCharts(){
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
}
