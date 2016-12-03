package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.entity.PadraoURL;
import br.cefetrj.webdep.services.PadraoURLServices;

public class DeletePadraoURLCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		String mensagem = "";
        //retrieving URL Pattern form fields
        String id = request.getParameter("id");
        
        PadraoURL padraoURL = PadraoURLServices.obterPorId(Long.parseLong(id));
        
        try{
        	PadraoURLServices.deletePadraoURL(padraoURL);
        	mensagem = "Padrão URL removido com sucesso";
		} catch (Exception e) {
			mensagem = "Erro na exclusão: " + e.getMessage();
			e.printStackTrace();
		} finally {
			String json = "{\"mensagem\": \"" + mensagem + "\"}";
			pw.write(json);
		}
        
	}
}
