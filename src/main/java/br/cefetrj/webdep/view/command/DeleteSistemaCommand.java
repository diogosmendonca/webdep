package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.cefetrj.webdep.model.entity.RegistroLogAcesso;
import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.services.RegistroLogAcessoService;
import br.cefetrj.webdep.services.SistemaServices;
import br.cefetrj.webdep.services.UsuarioServices;

public class DeleteSistemaCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("filtro");
		HttpSession session = request.getSession();
	    String lang = (String)session.getAttribute("lang");
		Sistema sistemaFiltrado = SistemaServices.obterPorId(Long.parseLong(id));
		PrintWriter pw = response.getWriter();
		String mensagem = "";
		try {
			/*List<RegistroLogAcesso> rLAs = RegistroLogAcessoService.searchRegistroLogAcessoBySistema(sistemaFiltrado);
			if (rLAs.size() > 0){
				for (RegistroLogAcesso r : rLAs) {
					RegistroLogAcessoService.deleteRegistroLogAcesso(r);
				}
			}*/
			SistemaServices.deleteSistema(sistemaFiltrado);
			switch (lang){
	        	case "en_US":
	        		mensagem = "System successfully removed!";
	        		break;
	        		
	        	case "pt_BR":
	        		mensagem = "Sistema excluído com sucesso!";
	        		break;
	        	default: 
	        		mensagem = "Sistema excluído com sucesso!";
	    			break;
	    	}
		} catch (Exception e) {
			switch (lang){
	        	case "en_US":
	        		mensagem = "Deletion error: " + e.getMessage();
	        		break;
	        		
	        	case "pt_BR":
	        		mensagem = "Erro na exclusão: " + e.getMessage();
	        		break;
	        	default: 
	        		mensagem = "Erro na exclusão: " + e.getMessage();
	    			break;
	    	}
		} finally {
			String json = "{\"mensagem\": \"" + mensagem + "\"}";
			pw.write(json);
		}
	}
}
