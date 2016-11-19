package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.entity.RegistroLogAcesso;
import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.services.RegistroLogAcessoService;
import br.cefetrj.webdep.services.SistemaServices;

public class DeleteSistemaCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("filtro");
		Sistema sistemaFiltrado = SistemaServices.searchSistema(id).get(0);
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json");
		String mensagem = "";
		try {
			List<RegistroLogAcesso> rLAs = RegistroLogAcessoService.searchRegistroLogAcessoBySistema(sistemaFiltrado);
			if (rLAs.size() > 0){
				for (RegistroLogAcesso r : rLAs) {
					RegistroLogAcessoService.deleteRegistroLogAcesso(r);
				}
			}
			SistemaServices.deleteSistema(sistemaFiltrado);
			
			mensagem = "Sistema excluído com sucesso";
		} catch (Exception e) {
			mensagem = "Erro na exclusão!";
			e.printStackTrace();
		} finally {
			String json = "{\"mensagem\": \"" + mensagem + "\"]}";
			response.setContentType("application/json");
			pw.write(json);
		}
	}
}
