package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.entity.EmailNotificacao;
import br.cefetrj.webdep.services.EmailNotificacaoServices;
import br.cefetrj.webdep.services.VersionServices;

public class ListaEmailNotificacaoCommand implements Command{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<EmailNotificacao> l = EmailNotificacaoServices.listarTodos();
		System.out.println(7);
		
		request.setAttribute("list", l);
		request.getRequestDispatcher("listaEmailNotificacao.jsp").forward(request, response);		
	}
	

}
