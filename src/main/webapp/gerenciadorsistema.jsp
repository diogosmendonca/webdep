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
        <div id="div-buscar-sistemas" class="form-group container">
            <br></br>
            <div class="row form-inline">
            	<div class="col-sm-5">
                	<input id="filtro-busca-sistemas" class="form-control" type="text">
                	<button id="buscar-sistemas" type="submit" class="btn btn-primary">Buscar</button>
            	</div>
            </div>
            <br>
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
                        
                    </tbody>
                </table>
            </div>
            <br></br>
            <div class="row">
                <button type="button" class="btn btn-secondary btn-lg center-block">Voltar</button>
            </div>
        </div>
    </body>
</html>
