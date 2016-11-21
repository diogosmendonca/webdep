package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.entity.Permissao;
import br.cefetrj.webdep.services.PermissaoService;

public class ListarPermissaoUsuarioCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long id = Long.parseLong(request.getParameter("id"));
		List<Permissao> permissaoLista= PermissaoService.PermissaoUsuario(id);
		
		request.setAttribute("lista", permissaoLista);
		
		request.getRequestDispatcher("/testehome.jsp").forward(request, response);
		
	}

}
