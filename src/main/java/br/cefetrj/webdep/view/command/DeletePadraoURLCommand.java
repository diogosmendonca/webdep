package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.cefetrj.webdep.model.entity.PadraoURL;
import br.cefetrj.webdep.services.PadraoURLServices;

public class DeletePadraoURLCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		String mensagem = "";
		HttpSession session = request.getSession();
	    String lang = (String)session.getAttribute("lang");
        //retrieving URL Pattern form fields
        String id = request.getParameter("id");
        System.out.println(id);
        
        PadraoURL padraoURL = PadraoURLServices.obterPorId(Long.parseLong(id));
        
        try{
        	PadraoURLServices.deletePadraoURL(padraoURL);
        	switch (lang){
	        	case "en_US":
	        		mensagem = "URL pattern successfully removed!";
	        		break;
	        	case "pt_BR":
	        		mensagem = "Padrão URL removido com sucesso!";
	        		break;
	        	default: 
	        		mensagem = "Padrão URL removido com sucesso!";
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
			e.printStackTrace();
		} finally {
			String json = "{\"mensagem\": \"" + mensagem + "\"}";
			pw.write(json);
		}
        
	}
}
