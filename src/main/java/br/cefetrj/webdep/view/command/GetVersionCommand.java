package br.cefetrj.webdep.view.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.entity.Versao;
import br.cefetrj.webdep.services.VersionServices;

public class GetVersionCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		Long id = Long.parseLong(request.getParameter("id"));
		Versao v = VersionServices.obterPorId(id);
		
		request.setAttribute("version", v);
		request.getRequestDispatcher("versionModify.jsp").forward(request, response);
	}
	
	

}
