package br.cefetrj.webdep.view.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.entity.Usuario;
import br.cefetrj.webdep.services.UsuarioService;

public class ObterUsuarioCommand implements Command{

	
	//Daqui retorno para  view ou daqui lanço para o JSP ?
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Usuario u = new Usuario();
		
		u.setId(Long.parseLong(request.getParameter("id")));

		Usuario uSaida = UsuarioService.getUsuario(u);
		
		request.setAttribute("usuario", uSaida);
		
		request.getRequestDispatcher("/testehome.jsp").forward(request, response);
		
		
	}

}
