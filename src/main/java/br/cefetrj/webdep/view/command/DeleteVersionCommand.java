package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.util.ArrayList;

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
		ArrayList <Versao> l = (ArrayList) request.getSession().getAttribute("list");
		int index = Integer.parseInt(request.getParameter("index"));
		
		
		Versao v = l.get(index);
		VersionServices.deleteVersion(v);
		
		request.getRequestDispatcher("versionSearch.jsp").forward(request, response);
		
	}
	
	
}
