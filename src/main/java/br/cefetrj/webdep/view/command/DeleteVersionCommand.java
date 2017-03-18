package br.cefetrj.webdep.view.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.entity.Versao;
import br.cefetrj.webdep.services.VersionServices;

public class DeleteVersionCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* Tratamento de exceções
		*/
		
		Long id = Long.parseLong(request.getParameter("id"));
		Versao v = VersionServices.obterPorId(id);
		
		VersionServices.deleteVersion(v);
		
		request.getRequestDispatcher("versionSearch.jsp").forward(request, response);
		
	}
	
	
}
