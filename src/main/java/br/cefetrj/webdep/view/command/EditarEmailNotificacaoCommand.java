package br.cefetrj.webdep.view.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.entity.EmailNotificacao;
import br.cefetrj.webdep.services.EmailNotificacaoServices;

public class EditarEmailNotificacaoCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		System.out.println(id);
		EmailNotificacao emailNotificacao = EmailNotificacaoServices.obterPorId(id);
		
		/*request.setAttribute("usuario", usu);	
		request.setAttribute("sis", valor);				
		request.getRequestDispatcher("alteraUsuario.jsp").forward(request, response);
		
		request.setAttribute("usuario", usu);
		request.getRequestDispatcher("FrontControllerServlet?action=ListaEmailNotificacao&get=true").forward(request, response);
		FrontControllerServlet?action=alteraUsuario&get=true&id=${ usu.id }*/
		
	}

}
