package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.cefetrj.webdep.model.entity.PadraoURL;
import br.cefetrj.webdep.services.PadraoURLServices;

public class FillPadraoURLCommand implements Command{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession();
		String lang = (String)session.getAttribute("lang");
		Long usuarioId = (Long) session.getAttribute("id");
		String json = "";
		List<PadraoURL> padroes = PadraoURLServices.listarTodosPorUsuario(usuarioId);
		if (padroes != null){
			json = "{\"padroes\":[";
			for(PadraoURL p : padroes){
				json += "{";
				json += "\"nome\":\"" + p.getNome() + "\",";
				json += "\"id\":" + p.getId().toString();
				json += "},";
			}
			json += "]}";
			json = json.replace("},]}", "}]}");
		} else {
			switch (lang){
	        	case "en_US":
	        		json = "{\"Erro\": \"Could not find any URL Pattern associated with this user\"}";
	        		break;
	        		
	        	case "pt_BR":
	        		json = "{\"Erro\": \"Não foi encontrado nenhum padrão URL associado ao usuário logado\"}";
	        		break;
	        	default: 
	        		json = "{\"Erro\": \"Não foi encontrado nenhum padrão URL associado ao usuário logado\"}";
	    			break;
	    	}
		}
		pw.write(json);
	}
}
