package br.cefetrj.webdep.view.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class TestaLogAcessoCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resposta = "";
        String ptLogs = request.getParameter("ptLogs");
        String pxLogs = request.getParameter("pxLogs");
        String pathLog = ptLogs+pxLogs;

        if(!(pathLog.equals(""))) { //sempre ser� cheio pois a valida��o est� em JS
            File file = new File(pathLog);
            if(file.isFile())
                resposta = "{\"mensagem\":\"O caminho '" + pathLog + "' existe!\" }";
            else
            	resposta = "{\"mensagem\":\"O caminho '" + pathLog + "' não existe!\" }";
        }

        PrintWriter pw = response.getWriter();
        response.setCharacterEncoding("UTF-8");
        pw.write(resposta);
    }
}
