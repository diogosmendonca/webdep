package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.cefetrj.webdep.model.entity.FormatoLog;
import br.cefetrj.webdep.services.FormatoLogServices;

public class FillFormatoLogCommand implements Command{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filtro = request.getParameter("filtro");
		HttpSession session = request.getSession();
		String lang = (String)session.getAttribute("lang");
		PrintWriter pw = response.getWriter();
		String json = "";
		List<FormatoLog> s = FormatoLogServices.searchFormatoLogByServidor(Long.parseLong(filtro));
		if (s != null){
			json = "{\"formatoLogs\":[";
			for (int i = 0; i < s.size(); i++) {
				json += "{";
				json += "\"nome\":\"" + s.get(i).getNome() + "\",";
				json += "\"id\":" + s.get(i).getId().toString();
				json += "},";
			}
			json += "]}";
			json = json.replace("},]}", "}]}");
		} else {
			switch (lang){
        	case "en_US":
        		json = "{\"Erro\": \"Could not find any log format associated with this server\"}";
        		break;
        		
        	case "pt_BR":
        		json = "{\"Erro\": \"Não foi encontrado nenhum formato de log associado ao servidor escolhido\"}";
        		break;
        	default: 
        		json = "{\"Erro\": \"Não foi encontrado nenhum formato de log associado ao servidor escolhido\"}";
    			break;
    	}
		}
		pw.write(json);
	}
}
