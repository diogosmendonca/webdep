$(document).ready(function () {
	if (window.location.href.indexOf("cadastrodesistema.jsp?u=") > -1){
		$("#update").val("update");
		var url = window.location.href;
		var id = url.substring(url.length -1, url.length)
		$("#id-sistema-update").val(id);
		$("#action").val("updateSistema");
		$.ajax({
            type: "POST",
            url: "FrontControllerServlet",
            data: {
                action: "fillSistema",
                filtro: id
            },
            success: function (response) {
            	console.log(response.sistema)
            	$("#nome").val(response.sistema.nome);
            	$("#servidor option").val(response.sistema.servidor);
            	$("#formatoLog option").val(response.sistema.formatolog);
            	$("#ptLogs").val(response.sistema.ptLogs);
            	$("#pxLogs").val(response.sistema.pxLogs);
            	$("#ptLogs2").val(response.sistema.ptLogs2);
            	$("#pxLogs2").val(response.sistema.pxLogs2);
            	$("#data").val(response.sistema.data);
            	$("#time").val(response.sistema.time);
            	$("#novaData").val(response.sistema.novaData);
            }
		});
	} else if (window.location.href.indexOf("gerenciadorsistema.jsp") > -1) {
			$.ajax({
                type: "POST",
                url: "FrontControllerServlet",
                data: {
                    action: "listSistema",
                    filtro: "all"
                },
                success: function (response) {
                		var resposta = $.parseJSON(response);
                        var sistemas = resposta.sistemas;
                		var erro = resposta.Erro;
                        if (resposta.hasOwnProperty("sistemas")){
                        	$("#table-sistemas").children().remove();
                        sistemas.forEach(function (el) {
                        	$("#table-sistemas").append("<tr><td>" 
                        			+ el.nome + "</td><td>" 
                        			+ el.servidor + "</td><td>" 
                        			+ el.formatolog + "</td><td>" 
                        			+ el.periodicidade + "</td><td>" 
                        			+ el.proximaleitura + "</td>" +
                        					"<td><a onclick=alterar(\""+ el.id +"\"); id=\""+ el.id +"-alterar\" class=\"alterar-sistema\">Alterar</a></td>" +
                        					"<td><a onclick=excluir(\""+ el.id +"\"); id=\""+ el.id +"-excluir\" class=\"excluir-sistema\">Excluir</a></td></tr>");
                        });
                        } else if (resposta.hasOwnProperty("Erro")){
                        	alert(resposta.Erro);
                        }
                }
            });
	}
	
	/*LISTAGEM DE SISTEMA*/
	$("#buscar-sistemas").click(function () {
		var filtro = $("#filtro-busca-sistemas").val();
		//validação form
		if (filtro.trim() === ""){
			$("#div-buscar-sistemas").toggleClass("has-error");
		} else {
			$.ajax({
                type: "POST",
                url: "FrontControllerServlet",
                data: {
                    action: "listSistema",
                    filtro: filtro
                },
                success: function (response) {
                        var sistemas = response.sistemas;
                		var erro = response.Erro;
                        if (response.hasOwnProperty("sistemas")){
                        	$("#table-sistemas").children().remove();
                        sistemas.forEach(function (el) {
                        	$("#table-sistemas").append("<tr><td>" 
                        			+ el.nome + "</td><td>" 
                        			+ el.servidor + "</td><td>" 
                        			+ el.formatolog + "</td><td>" 
                        			+ el.periodicidade + "</td><td>" 
                        			+ el.proximaleitura + "</td>" +
                        					"<td><a onclick=alterar(\""+ el.id +"\"); id=\""+ el.id +"-alterar\" class=\"alterar-sistema\">Alterar</a></td>" +
                        					"<td><a onclick=excluir(\""+ el.id +"\"); id=\""+ el.id +"-excluir\" class=\"excluir-sistema\">Excluir</a></td></tr>");
                        });
                        } else if (response.hasOwnProperty("Erro")){
                        	alert(response.Erro);
                        }
                }
            });
		}
		
	});
	/*FIM LISTAGEM DE SISTEMA*/
	
	/* MODAL PADRAO URL*/
	//abrir modal Novo Padrão de URL
    $('#myModal').on('shown.bs.modal', function () {
        $('#myInput').focus();
    });
    //Buscar Expressão Regular
    $("#submit-regex").click(function () {
        var regex = $("#regex").val();
        if (regex.trim() === "") {
            $("#div-regex").toggleClass("has-error");
        } else {
            $("#div-regex").removeClass("has-error");
            $.ajax({
                type: "POST",
                url: "FrontControllerServlet",
                data: {
                    action: "regexPadraoURL",
                    regex: regex
                },
                success: function (response) {
                	var resposta = $.parseJSON(response);
                    if (resposta.hasOwnProperty("url")) {
                        var urls = resposta.url;
                        urls.forEach(function (el) {
                            $("#table-url").append("<tr><td>" + el + "</td></tr>");
                        });
                        refresh = false;
                    } else if (resposta.hasOwnProperty("Erro")) {
                    	alert(resposta.Erro);
                    }
                }
            });
        }
        return false;
    });
    //Salvar Padrão URL
    $("#submit-padrao-url").click(function () {
        var regex = $("#regex").val();
        var nome = $("#padrao-url-nome").val();
        if (regex.trim() === "")
            $("#div-regex").toggleClass("has-error");
        if (nome.trim() === "")
            $("#div-nome").toggleClass("has-error");
        if (regex.trim() !== "" && nome.trim() !== "") {
            $("#div-regex").removeClass("has-error");
            $("#div-nome").removeClass("has-error");
            $.ajax({
                type: "POST",
                url: "FrontControllerServlet",
                data: {
                    action: "insertPadraoURL",
                    nome: nome,
                    regex: regex
                },
                success: function (response) {
                    if (response.hasOwnProperty("mensagem") > -1) {
                        var mensagem = response.mensagem;
                        alert(mensagem);
                    } else if (response.indexOf("Erro")) {
                    	alert("Erro de conexão com o servidor");
                    }
                }
            });
        }
        return false;
    });
    /*FIM PADRAO URL*/
    /*DATEPICKER*/ //não é necessário cada grupo colocar os codigos javascript em cada página isoladamente
    $('.form_datetime').datetimepicker({
        language:  'pt-BR',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1,
        format: 'YYYY-MM-DD HH:mm:ss'
    });
	$('.form_date').datetimepicker({
        language:  'pt-BR',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
    });
	$('.form_time').datetimepicker({
        language:  'pt-BR',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 1,
		minView: 0,
		maxView: 1,
		forceParse: 0
    });
	
		
	$("#cadastro-sistema-submit").click(function () {
		var sistemaForm = $("#sistema-form").serialize();
		var nome = $("#nome").val();
		var servidor = $("#selection").val();
		var fmtLogs = $("#fmtLogs").val();
		var ptLogs = $("#ptLogs").val();
		var pxLogs = $("#pxLogs").val();
		var ptLogs2 = $("#ptLogs2").val();
		var pxLogs2 = $("#pxLogs2").val();
		var data = $("#data").val();
		var hora = $("#time").val();
		var nova = $("#novaData").val();
		//validação form
		if (nome.trim() === ""){
			$("#div-nome").toggleClass("has-error");
		} 
		if (data.trim() === ""){
			$("#div-data").toggleClass("has-error");
		}
		if (hora.trim() === ""){
			$("#div-time").toggleClass("has-error");
		}
		if (nova.trim() === ""){
			$("#div-nova").toggleClass("has-error");
		}
		else {
			$.ajax({
	            type: "POST",
	            url: "FrontControllerServlet",
	            data: sistemaForm, //action fica no jsp porque o form está sendo serializado(o action está dentro de sistemaForm)
	            success: function (response) {
	            	var resposta = $.parseJSON(response);
	                if (resposta.hasOwnProperty("mensagem")) {
	                    var mensagem = resposta.mensagem;
	                    alert(mensagem);
	                    if (!confirm("Você deseja cadastrar outro Sistema? \n Sim, para cadastrar outro Sistema \nNão, para ser sair desta página.")){
	                    	window.location.replace("gerenciadorsistema.jsp");
	                    }
	                }
                }
            });
			return false;
          }
	});
		
});

function alterar(nome){
	window.location.replace("cadastrodesistema.jsp?u="+nome);
}

function excluir(nome){
	decisao = confirm("Tem certeza que deseja excluir este sistema?\nTodos os registros de logs serão apagados.");
	              if(decisao){
	            	  //alert("Não disponível");
		              $.ajax({
		                      type: "POST",
		                       url: "FrontControllerServlet",
		                      data: {
		                      action: "deleteSistema",
		                      filtro: nome
		                  },
		                  success: function (response) {
		                	  var resposta = $.parseJSON(response);
		                	  alert(resposta.mensagem);
		                  }
		              });
	              }
}
//Teste acesso
$("#pxLogs-teste-btn").click(function () {
	var pxLogs = $("#pxLogs").val();
	var ptLogs = $("#ptLogs").val();
	if (pxLogs === "" || ptLogs === "") {
		$("#div-prefixo-acesso").toggleClass("has-error");
	} else {
		$.ajax({
            type: "POST",
            url: "FrontControllerServlet",
            data: {
            	action: "testAccessLogFolder",
            	pxLogs: pxLogs,
            	ptLogs: ptLogs
        },
        success: function (response) {
        	var resposta = $.parseJSON(response);
      	  	alert(resposta.mensagem);
        }
    });
	}
});

$("#pxLogs2-teste-btn").click(function () {
	var pxLogs2 = $("#pxLogs2").val();
	var ptLogs2 = $("#ptLogs2").val();
	if (pxLogs2 === "" || ptLogs2 === "") {
		$("#div-prefixo-erro").toggleClass("has-error");
	} else {
		$.ajax({
            type: "POST",
            url: "FrontControllerServlet",
            data: {
            action: "testErrorLogFolder",
            pxLogs2: pxLogs2,
            ptLogs2: ptLogs2
        },
        success: function (response) {
        	var resposta = $.parseJSON(response);
      	  	alert(resposta.mensagem);
        }
    });
	}
});
