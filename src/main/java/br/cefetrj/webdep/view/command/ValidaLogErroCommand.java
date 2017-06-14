package br.cefetrj.webdep.view.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by renatoor on 11/25/16.
 */
public class ValidaLogErroCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resposta;
        String sistema = request.getParameter("sistema");
        String servidor = request.getParameter("servidor");
        String log = request.getParameter("log");
        String logAcesso = request.getParameter("logAcesso");
        String logErro = request.getParameter("logErro");

        request.setAttribute("sistema", sistema);
        request.setAttribute("servidor", servidor);
        request.setAttribute("log", log);
        request.setAttribute("logAcesso", logAcesso);
        request.setAttribute("logErro", logErro);

        if(!(logErro.equals(""))) {
            File file = new File(logErro);
            if(file.isDirectory())
                request.setAttribute("logErroValido", "1");
            else
                request.setAttribute("logErroInvalido", "1");
        }
        else
            request.setAttribute("logErroVazio", "1");

        if(!(logAcesso.equals(""))) {
            File file = new File(logAcesso);
            if(file.isDirectory())
                request.setAttribute("logAcessoValido", "1");
            else
                request.setAttribute("logAcessoInvalido", "1");
        }

        request.getRequestDispatcher("/importarLog.jsp").forward(request, response);
    }
}
