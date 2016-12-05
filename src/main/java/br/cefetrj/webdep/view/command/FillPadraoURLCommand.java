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
			json = "{\"Erro\": \"Não foi encontrado nenhum padrão URL associado ao usuário logado\"}";
		}
		pw.write(json);
	}
}
