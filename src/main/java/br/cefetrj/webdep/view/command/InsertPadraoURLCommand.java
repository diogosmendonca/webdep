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
        	PadraoURLServices.insertPadraoURL(padraoURL);
        	mensagem = "Padrão URL inserido com sucesso";
		} catch (Exception e) {
			mensagem = "Erro na inserção!";
			e.printStackTrace();
		} finally {
			String json = "{\"mensagem\": \"" + mensagem + "\"}";
			pw.write(json);
		}
        
	}
}
