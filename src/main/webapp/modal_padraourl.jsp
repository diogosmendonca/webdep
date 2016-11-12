<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <html>
    <head>
        <jsp:include page="head.jspf"/>
    </head>
    <body>
        <jsp:include page="scripts.jspf"/>
        
    <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
                Criar Novo padrão URL
            </button>
    <br></br>
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
         </div>
         </body>
         </html>