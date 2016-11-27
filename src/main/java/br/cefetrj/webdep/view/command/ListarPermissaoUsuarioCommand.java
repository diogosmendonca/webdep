package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.services.SistemaServices;

public class ListarPermissaoUsuarioCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		List<Sistema> permissaoSistemaLista= SistemaServices.listByPermissaoUsuario(id);
		
		request.setAttribute("lista", permissaoSistemaLista);
		
		request.getRequestDispatcher("/home.jsp").forward(request, response);
		
	}

}
