package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.cefetrj.webdep.model.entity.PadraoURL;
import br.cefetrj.webdep.model.entity.Usuario;
import br.cefetrj.webdep.services.PadraoURLServices;
import br.cefetrj.webdep.services.UsuarioServices;

public class InsertPadraoURLCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
        HttpSession session = request.getSession();  
        String lang = (String)session.getAttribute("lang");
        Long usuario_id;
        Usuario u = new Usuario();
        Usuario usuarioLogado = u;
        String mensagem = "";
        if (session != null) {
        	usuario_id = (Long)session.getAttribute("id");
        	u.setId(usuario_id);
        	usuarioLogado = UsuarioServices.getUsuario(u);
        }
        //retrieving URL Pattern form fields
        String nome = request.getParameter("nome");
        String regex = request.getParameter("regex");
        
        PadraoURL padraoURL = new PadraoURL();
        padraoURL.setNome(nome);
        padraoURL.setExpressaoRegular(regex);
        padraoURL.setUsuario(usuarioLogado);
        try{
        	if (!PadraoURLServices.verificaDuplicata(padraoURL)) { //retorna true se possui duplicata
        		PadraoURLServices.insertPadraoURL(padraoURL);
        		switch (lang){
		        	case "en_US":
		        		mensagem = "URL Pattern successfully inserted!";
		        		break;
		        		
		        	case "pt_BR":
		        		mensagem = "Padrão URL inserido com sucesso";
		        		break;
		        	default: 
		    			mensagem = "Padrão URL inserido com sucesso";
		    			break;
				}
        	} else {
        		switch (lang){
		        	case "en_US":
		        		mensagem = "A URL Pattern with this name already exists";
		        		break;
		        		
		        	case "pt_BR":
		        		mensagem = "Padrão URL já existe";
		        		break;
		        	default: 
		    			mensagem = "Padrão URL já existe";
		    			break;
				}
        	}
        	
		} catch (Exception e) {
			switch (lang){
	        	case "en_US":
	        		mensagem = "Insertion error";
	        		break;
	        		
	        	case "pt_BR":
	        		mensagem = "Erro na inserção!";
	        		break;
	        	default: 
	    			mensagem = "Erro na inserção!";
	    			break;
			}
			e.printStackTrace();
		} finally {
			String json = "{\"mensagem\": \"" + mensagem + "\"}";
			pw.write(json);
		}
        
	}
}
