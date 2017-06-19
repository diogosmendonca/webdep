package br.cefetrj.webdep.view.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.entity.EmailNotificacao;
import br.cefetrj.webdep.services.EmailNotificacaoServices;

public class ExcluirEmailNotificacaoCommand implements Command{
	@Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long id = Long.parseLong(request.getParameter("id"));
		System.out.println(id);
		EmailNotificacao emailNotificacao = EmailNotificacaoServices.obterPorId(id);
		
		EmailNotificacaoServices.excluir(emailNotificacao);
		
		
		request.getRequestDispatcher("FrontControllerServlet?action=ListaEmailNotificacao&get=true").forward(request, response);
    }
}
