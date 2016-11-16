$(document).ready(function () {
	/*LISTAGEM DE SISTEMA*/
	$("#buscar-sistemas").click(function () {
		var filtro = $("#filtro-busca-sistemas").val();
		//validação form
		if (filtro.trim() === ""){
			$("#div-buscar-sistemas").toggleClass("has-error");
		} else {
			$.ajax({
                type: "POST",
                url: "GerenciadorSistema",
                data: {
                    action: "buscaSistema",
                    filtro: filtro
                },
                success: function (response) {
                    if (response.indexOf("sistemas") > -1) {
                        var sistemas = JSON.parse(response).sistemas;
                        sistemas.forEach(function (el) {
                        	$("#table-url").append("<tr><td>" 
                        			+ el.nome + "</td><td>" 
                        			+ el.servidor + "</td><td>" 
                        			+ el.formatolog + "</td><td>" 
                        			+ el.periodicidade + "</td><td>" 
                        			+ el.proximaleitura + "</td>" +
                        					"<td><a id=\""+ el.nome +"-alterar\" class=\"alterar-sistema\">Alterar</a></td>" +
                        					"<td><a id=\""+ el.nome +"-excluir\" class=\"excluir-sistema\">Excluir</a></td></tr>");
                        });
                        refresh = false;
                        
                    } else if (response.indexOf("Erro")) {

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
                url: "PadraoURL",
                data: {
                    action: "buscaRegex",
                    regex: regex
                },
                success: function (response) {
                    if (response.indexOf("url") > -1) {
                        var urls = JSON.parse(response).url;
                        urls.forEach(function (el) {
                            $("#table-url").append("<tr><td>" + el + "</td></tr>");
                        });
                        refresh = false;
                    } else if (response.indexOf("Erro")) {

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
                url: "PadraoURL",
                data: {
                    action: "salvarPadrao",
                    nome: nome,
                    regex: regex
                },
                success: function (response) {
                    if (response.indexOf("url") > -1) {
                        var urls = JSON.parse(response).url;
                        console.log(urls);
                        urls.forEach(function (el) {
                            $("#table-url").append("<tr><td>" + el + "</td></tr>");
                        });
                    } else if (response.indexOf("Erro")) {

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
        showMeridian: 1
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
});


