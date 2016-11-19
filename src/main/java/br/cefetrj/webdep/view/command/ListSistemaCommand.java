package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.services.SistemaServices;

public class ListSistemaCommand implements Command{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filtro = request.getParameter("filtro");
		PrintWriter pw = response.getWriter();
		String json = "";
		List<Sistema> sistemasFiltrados = SistemaServices.searchSistema(filtro);
		if (sistemasFiltrados.size() > 0) {
			json = "{\"sistemas\": [";
			for (Sistema s : sistemasFiltrados) {
				json += "{";
				json += "\"id\":\"" + s.getId() + "\",";
				json += "\"nome\":\"" + s.getNome() + "\",";
				json += "\"servidor\":\"" + s.getServidor().getNome() + "\",";
				json += "\"formatolog\":\"" + s.getServidor().getFormatoLog().getNome() + "\",";
				json += "\"periodicidade\":\"" + s.getPeriodicidadeLeitura().toString() + "\",";
				json += "\"proximaleitura\":\"" + "Ultima leitura + periodicidade" + "\"";
				json += "},";
			}
			json += "]}";
			json = json.replace("},]}", "}]}");
		} else {
			json = "{\"Erro\": \"Nenhum resultado encontrado\"}";
		}
		response.setContentType("application/json");
		pw.write(json);
	}
}
