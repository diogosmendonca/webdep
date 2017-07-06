package commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.entity.EmailNotificacao;
import br.cefetrj.webdep.services.EmailNotificacaoServices;
import br.cefetrj.webdep.view.command.Command;

public class GetEmailNotificacaoCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		EmailNotificacao en = EmailNotificacaoServices.obterPorId(id);
		
		request.setAttribute("emailNotificacao", en);
		request.getRequestDispatcher("EditaEmailNotificacao.jsp").forward(request, response);
		
	}

}
