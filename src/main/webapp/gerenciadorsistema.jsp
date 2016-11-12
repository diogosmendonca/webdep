<%-- 
    Document   : gerenciadorsistema
    Created on : Nov 5, 2016, 9:26:21 PM
    Author     : Luan
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="head.jspf"/>
    </head>
    <body>
        <jsp:include page="scripts.jspf"/>
        <div class="form-group container">
            <br></br>
            <div class="row">
                <input type="text">  <button type="submit" class="btn btn-primary">Buscar</button><br></br>
            </div>
            <div class="row">
                <table class="table table-bordered table-condensed">
                    <thead>
                        <tr>
                            <th>Sistema</th>
                            <th>Servidor</th>
                            <th>Formato do Log</th>
                            <th>Periodicidade</th>
                            <th>Próxima Leitura</th>
                            <th>Alterar</th>
                            <th>Excluir</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row">XPTO</th>
                            <td>Apache Web Server</td>
                            <td>Common</td>
                            <td>1d</td>
                            <td>30/09/2016 00:00</td>
                            <td><a class="">Alterar</a></td>
                            <td><a>Excluir</a></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <br></br>
            <div class="row">
                <button type="button" class="btn btn-secondary btn-lg center-block">Voltar</button>
            </div>
            <br></br>
            <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
                Criar Novo padrão URL
            </button>
        </div>

        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel">Novo Padrão de URL</h4>
                    </div>
                    <div class="modal-body">
                        <div class="container">
                            <form id="form-padrao-url" class="form-horizontal col-sm-11">
                                <div class="row">
                                    <div id="div-nome" class="form-group">
                                        <label class="col-sm-2 text-right control-label" for="padrao-url-nome">Nome: </label>
                                        <div class="col-sm-3">
                                            <input name="padrao-url-nome" id="padrao-url-nome" type="text" class="form-control"/>
                                        </div>
                                    </div>
                                    <div id="div-regex" class="form-group">
                                        <label class="col-sm-2 text-right control-label" for="regex">Expressão Regular:</label>
                                        <div class="col-sm-3 input-group">
                                            <input id="regex" name="regex" type="text" class="form-control" />
                                            <span class="input-group-btn text-right">
                                                <button id="submit-regex" name="action" class="btn btn-primary" value="buscaRegex" type="submit">Buscar</button>
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
                                    <button id="submit-padrao-url" name="action" type="submit" class="btn btn-primary" value="salvarPadrao">Salvar</button>
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
    </body>
</html>
