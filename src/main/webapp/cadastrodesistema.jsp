<%-- 
    Document   : cadastrodesistema
    Created on : Nov 5, 2016, 8:24:05 PM
    Author     : Luan
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>

<!DOCTYPE html>
<html><head>
        <jsp:include page="head.jspf"/>
    </head>
    <body>
        <jsp:include page="scripts.jspf"/>
        <form class="form-horizontal container">
            <div class="row">
                <fieldset class="form-group">
                    <legend>Sistema</legend>
                    <div class="col-sm-6">
                        <label class="text-right col-sm-6">Nome do Sistema: </label><div class="input-group"><input class="form-control" type="text"/></div><br>
                        <label class="text-right col-sm-6">Servidor: </label><div class="input-group"><select class="form-control"><option>Apache</option></select></div>
                    </div>
                </fieldset>
            </div>
            <div class="row">
                <fieldset class="form-group">
                    <legend>Logs</legend>
                    <div class="col-sm-6">
                        <label class="text-right col-sm-6">Formato Logs de Acesso: </label><div class="input-group"><select class="form-control"><option>Common</option></select></div>
                        <br>
                        <label class="text-right col-sm-6">Pasta dos Logs de Acesso: </label><div class="input-group"><input class="form-control" type="text"/></div>
                        <br>
                        <label class="text-right col-sm-6">Prefixo dos Logs de Acesso: </label>
                        <div class="input-group">
                            <input type="text" class="form-control">
                            <span class="input-group-btn">
                              <button class="btn btn-info" type="button">Testar Acesso</button>
                            </span>
                         </div>
                        <br>
                        <label class="text-right col-sm-6">Pasta dos Logs de Erro: </label><div class="input-group"><input class="form-control" type="text"/></div>
                        <br>
                        <label class="text-right col-sm-6">Prefixo dos Logs de Erro: </label>
                        <div class="input-group">
                            <input type="text" class="form-control">
                            <span class="input-group-btn">
                              <button class="btn btn-info" type="button">Testar Acesso</button>
                            </span>
                         </div>
                        <br>
                    </div>
                </fieldset>
            </div>
            <div class="row">
                <fieldset class="form-group">
                    <legend>Periocidade de Leitura dos Logs</legend>
                    <div class="col-sm-6">
                        <label class="text-right col-sm-6">Data da Primeira Leitura: </label><div class="input-group"><input class="form-control" type="text"/></div> <br>
                        <label class="text-right col-sm-6">Horário da Primeira Leitura: </label><div class="input-group"><input class="form-control" type="text"/><span class="input-group-addon"> (hh:mm)</span></div> <br>
                        <label class="text-right col-sm-6">Novas Leituras a Cada: </label><div class="input-group"><input class="form-control" type="text"/><span class="input-group-addon">(dd hh:mm)</span></div> <br>
                    </div>
                </fieldset>
            </div>
            <div class="row text-center">
            <button type="submit" class="btn btn-primary ">Salvar</button>
            <button class="btn btn-secondary "type="button">Cancelar</button>
            </div>
        </form>
    </body>
</html>
