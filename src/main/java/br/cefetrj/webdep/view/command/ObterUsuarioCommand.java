package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.model.entity.Usuario;
import br.cefetrj.webdep.services.SistemaServices;
import br.cefetrj.webdep.services.UsuarioServices;

public class ObterUsuarioCommand implements Command{

	
	//Daqui retorno para  view ou daqui lanço para o JSP ?
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
		
		Long id =Long.parseLong(request.getParameter("id"));

		Usuario uSaida = UsuarioServices.obterPorId(id);
		
		request.setAttribute("usuario", uSaida);
		
		
		List<Sistema> permissaoSistemaLista= SistemaServices.listByPermissaoUsuario(id.intValue());
		
		request.setAttribute("listasistema", permissaoSistemaLista);
		
		
		request.getRequestDispatcher("/home.jsp").forward(request, response);
		
		
	}

}
