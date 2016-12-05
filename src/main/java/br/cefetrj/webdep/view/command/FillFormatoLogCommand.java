package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.entity.FormatoLog;
import br.cefetrj.webdep.services.FormatoLogServices;

public class FillFormatoLogCommand implements Command{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filtro = request.getParameter("filtro");
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
			json = "{\"Erro\": \"Não foi encontrado nenhum formato de Log associado ao servidor escolhido\"}";
		}
		pw.write(json);
	}
}
