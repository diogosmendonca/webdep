package br.cefetrj.webdep.view.command;

import br.cefetrj.webdep.services.LogAcessoServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by renatoor on 11/25/16.
 */
public class ExcluirLogAcessoCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] idLog = request.getParameterValues("idLog");

        LogAcessoServices.excluirLog(idLog);

        request.setAttribute("excluido", Integer.toString(idLog.length));
        request.getRequestDispatcher("/excluirLogAcesso.jsp").forward(request, response);
    }
}
