package br.cefetrj.webdep.view.command;


import javax.servlet.ServletException;
import br.cefetrj.webdep.services.EMailServices;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ValidaEmail implements Command{
	@Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		boolean emailInvalido = false ;
		
		
		String usuario = req.getParameter("usuarioEmail");
		String senha= req.getParameter("senhaEmail");
		
		
		boolean usuarioValido = true;
		boolean usuarioVazio = false;
		boolean usuarioInvalido = false;
		
				
		boolean senhaValida = true;
		boolean senhaVazia = false;
		boolean senhaInvalida = false;
		
		if(usuario.trim().length() > 0 || senha.trim().length() > 0){
			
			
			if(usuario == null || usuario.trim().equals("")){
				usuarioValido = false;
				usuarioVazio = true;
			}else{
				if(usuario.length()>50){
					usuarioValido = false;
					usuarioInvalido = true;
				}
			}
			
			if(senha == null || senha.trim().equals("")){
				senhaValida = false;
				senhaVazia = true;
			}else{
				if(senha.length()>50){
					senhaValida = false;
					senhaInvalida = true;
				}
			}
			
						
			if(usuarioValido == false || senhaValida == false ){
				emailInvalido = true;	
			}
			
		}
		
				
		
		
		if(emailInvalido){
			req.setAttribute("senhaValida", senhaValida);
			req.setAttribute("senhalInvalida", senhaInvalida);
			req.setAttribute("senhaVazia", senhaVazia);
			req.setAttribute("usuarioValido", usuarioValido);
			req.setAttribute("usuarioInvalido", usuarioInvalido);
			req.setAttribute("usuarioVazio", usuarioVazio);
			req.setAttribute("emailInvalido", emailInvalido);			
			req.getRequestDispatcher("config.jsp").forward(req, resp);
		}else{
			EMailServices teste = new EMailServices();
			String [] email = new String[1];
			email[0] = usuario;
			boolean enviou = teste.sendFromGMail(usuario, senha, email, "Teste do WebDep", "Teste de configuração do WebDep");
			if(enviou){
				req.setAttribute("senhaValida", senhaValida);
				req.setAttribute("senhalInvalida", senhaInvalida);
				req.setAttribute("senhaVazia", senhaVazia);
				req.setAttribute("usuarioValido", usuarioValido);
				req.setAttribute("usuarioInvalido", usuarioInvalido);
				req.setAttribute("usuarioVazio", usuarioVazio);
				req.setAttribute("emailInvalido", emailInvalido);			
				req.getRequestDispatcher("config.jsp").forward(req, resp);
			}else{
				emailInvalido = true;
				req.setAttribute("senhaValida", senhaValida);
				req.setAttribute("senhalInvalida", senhaInvalida);
				req.setAttribute("senhaVazia", senhaVazia);
				req.setAttribute("usuarioValido", usuarioValido);
				req.setAttribute("usuarioInvalido", usuarioInvalido);
				req.setAttribute("usuarioVazio", usuarioVazio);
				req.setAttribute("emailInvalido", emailInvalido);			
				req.getRequestDispatcher("config.jsp").forward(req, resp);
			}
			
		}
	}

}