<%-- 
    Document   : cadastrodesistema
    Created on : Nov 5, 2016, 8:24:05 PM
    Author     : Luan
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <body>
        <form>
            <fieldset>
                <legend>Sistema</legend>
                Nome do Sistema: <input type="text"/><br>
                Servidor: <select><option>Apache</option></select>
            </fieldset>
            <fieldset>
                <legend>Logs</legend>
                Formato Logs de Acesso: <select><option>Common</option></select><br>
                Pasta dos Logs de Acesso: <input type="text"/><br>
                Prefixo dos Logs de Acesso: <input type="text"/><button type="button">Testar Acesso</button><br>
                Pasta dos Logs de Erro: <input type="text"/><br>
                Prefixo dos Logs de Erro: <input type="text"/><button type="button">Testar Acesso</button><br>
            </fieldset>
            <fieldset>
                <legend>Periocidade de Leitura dos Logs</legend>
                Data da Primeira Leitura: <input type="text"/>
                Horário da Primeira Leitura: <input type="text"/> (hh:mm) <br>
                Novas Leituras a Cada: <input type="text"/> (dd hh:mm) <br>
            </fieldset>
            <button type="submit" class="btn btn-primary">Salvar</button><button type="button">Cancelar</button>
        </form>
    </body>
</html>