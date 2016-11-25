package br.cefetrj.webdep.view.command;

import javax.servlet.ServletException;

import br.cefetrj.webdep.model.dao.ConfigurationDAO;
import br.cefetrj.webdep.model.entity.ConfiguracaoSistema;
import br.cefetrj.webdep.model.entity.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ValidaConfig implements Command	{
	@Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String usuario = req.getParameter("usuarioEmail");
		String senha= req.getParameter("senhaEmail");
		String bd = req.getParameter("banco");
		String url = req.getParameter("urlBanco");
		String user = req.getParameter("usuarioBanco");
		String password = req.getParameter("senhaBanco");
		String usuarioAdmin = req.getParameter("usuarioAdmin");
		String senhaAdmin = req.getParameter("senhaAdmin");
		String confirmSenhaAdmin = req.getParameter("confirmSenhaAdmin");
		String nomeAdmin = req.getParameter("nomeAdmin");
		String emailAdmin = req.getParameter("emailAdmin");
		
		
		boolean validouBanco = true;
		boolean validouEmail = true;
		
		boolean bdInvalido = false;
		
		
		boolean bdValido = true;
		if(bd.equals("hsqldb") || bd.equals("mysql") || bd.equals("postgres") ){
			bdValido = true;
		}else{
			bdValido = false;
		}
		
		
		boolean urlValida = true;
		boolean urlVazia = false;
		boolean urlInvalida = false;
		if(url == null || url.trim().equals("")){
			urlValida = false;
			urlVazia = true;
		}else{
			if(url.length()>100){
				urlValida = false;
				urlInvalida = true;
			}
		}
		
		
		boolean userValido = true;
		boolean userVazio = false;
		boolean userInvalido = false;
		if(user == null || user.trim().equals("")){
			userValido = false;
			userVazio = true;
		}else{
			if(user.length()>50){
				userValido = false;
				userInvalido = true;
			}
		}
		
		
		boolean passwordValido = true;
		boolean passwordVazio = false;
		boolean passwordInvalido = false;
		if(password == null || password.trim().equals("")){
			passwordValido = false;
			passwordVazio = true;
		}else{
			if(password.length()>50){
				passwordValido = false;
				passwordInvalido = true;
			}
		}
		
		if(bdValido == false || urlValida == false || userValido == false || passwordValido == false){
			bdInvalido = true;
			bdValido = false;
		}
		
		boolean obrigatoriosVazios = false;
		if(userVazio && passwordVazio && urlVazia){
			obrigatoriosVazios = true;
			req.setAttribute("obrigatoriosVazios", obrigatoriosVazios);
			req.getRequestDispatcher("config.jsp").forward(req, resp);
			return;
		}
		
		boolean validouAdmin = true;
		boolean usuarioAdminValido = true;
		boolean usuarioAdminVazio = false;
		boolean usuarioAdminInvalido = false;
		
		if(usuarioAdmin == null || usuarioAdmin.trim().equals("")){
			usuarioAdminValido = false;
			usuarioAdminVazio = true;
		}else{
			if(usuarioAdmin.length()>50 || usuarioAdmin.length()<5){
				usuarioAdminValido = false;
				usuarioAdminInvalido = true;
			}
		}
		
		boolean nomeAdminValido = true;
		boolean nomeAdminVazio = false;
		boolean nomeAdminInvalido = false;
		
		if(nomeAdmin == null || nomeAdmin.trim().equals("")){
			nomeAdminValido = false;
			nomeAdminVazio = true;
		}else{
			if(nomeAdmin.length()>100){
				nomeAdminValido = false;
				nomeAdminInvalido = true;
			}
		}
		
		boolean emailAdminValido = true;
		boolean emailAdminVazio = false;
		boolean emailAdminInvalido = false;
		
		if(emailAdmin == null || emailAdmin.trim().equals("")){
			emailAdminValido = false;
			emailAdminVazio = true;
		}else{
			if(emailAdmin.length()>100 || emailAdmin.length()<3){
				emailAdminValido = false;
				emailAdminInvalido = true;
			}
		}
		
		boolean senhaAdminValido = true;
		boolean senhaAdminVazio = false;
		boolean senhaAdminInvalido = false;
		
		if(senhaAdmin == null || senhaAdmin.trim().equals("")){
			senhaAdminValido = false;
			senhaAdminVazio = true;
		}else{
			if(senhaAdmin.length()>50 || senhaAdmin.length()<6){
				senhaAdminValido = false;
				senhaAdminInvalido = true;
			}
		}
		
		boolean confirmSenhaAdminValido = true;
		boolean confirmSenhaAdminVazio = false;
		boolean confirmSenhaAdminInvalido = false;
		
		if(confirmSenhaAdmin == null || confirmSenhaAdmin.trim().equals("")){
			confirmSenhaAdminValido = false;
			confirmSenhaAdminVazio = true;
		}else{
			if(confirmSenhaAdmin.length()>50 || confirmSenhaAdmin.length()<6){
				confirmSenhaAdminValido = false;
				confirmSenhaAdminInvalido = true;
			}
		}
		
		boolean senhaConfirmada = true;
		if(senhaAdminValido && confirmSenhaAdminValido){
			if(senhaAdmin.equals(confirmSenhaAdmin) == false){
				senhaConfirmada = false;
			}
		}
		
		if( usuarioAdminValido == false || nomeAdminValido == false || emailAdminValido == false || senhaConfirmada == false ){
			validouAdmin = false;
		}
		
		
		
		boolean usuarioValido = true;
		boolean usuarioVazio = false;
		boolean usuarioInvalido = false;
		
				
		boolean senhaValida = true;
		boolean senhaVazia = false;
		boolean senhaInvalida = false;
		
		if(usuario.trim().length() > 0 || senha.trim().length() > 0 ){
			
			
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
			
			
			
			if(usuarioValido == false || senhaValida == false){
				validouEmail = false;	
			}
			
		}
		
		
		
		if(validouBanco == true && validouEmail == true && validouAdmin == true){
			ConfiguracaoSistema adm = new ConfiguracaoSistema();
			adm.setBanco(bd);
			adm.setSenhaBanco(password);
			adm.setSenhaEmail(senhaAdmin);
			adm.setUrlBanco(url);
			adm.setUsuarioBanco(user);
			adm.setUsuarioEmail(usuarioAdmin);
			ConfigurationDAO config = new ConfigurationDAO();
			config.store(adm, "C:/Users/Gabriel/git/webdep/src/main/resources/database/config.properties");
			
			
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}else if(validouBanco == true && validouEmail == false){
			if(validouAdmin == true){
				req.setAttribute("senhaValida", senhaValida);
				req.setAttribute("senhalInvalida", senhaInvalida);
				req.setAttribute("senhaVazia", senhaVazia);
				req.setAttribute("usuarioValido", usuarioValido);
				req.setAttribute("usuarioInvalido", usuarioInvalido);
				req.setAttribute("usuarioVazio", usuarioVazio);
				req.setAttribute("validouEmail", validouEmail);
				req.getRequestDispatcher("config.jsp").forward(req, resp);
			}else{
				req.setAttribute("senhaValida", senhaValida);
				req.setAttribute("senhalInvalida", senhaInvalida);
				req.setAttribute("senhaVazia", senhaVazia);
				req.setAttribute("usuarioValido", usuarioValido);
				req.setAttribute("usuarioInvalido", usuarioInvalido);
				req.setAttribute("usuarioVazio", usuarioVazio);
				req.setAttribute("validouEmail", validouEmail);
				req.setAttribute("usuarioAdminValido", usuarioAdminValido);
				req.setAttribute("usuarioAdminInvalido", usuarioAdminInvalido);
				req.setAttribute("usuarioAdminVazio", usuarioAdminVazio);
				req.setAttribute("nomeAdminValido", nomeAdminValido);
				req.setAttribute("nomeAdminInvalido", nomeAdminInvalido);
				req.setAttribute("nomeAdminVazio", nomeAdminVazio);
				req.setAttribute("emailAdminValido", emailAdminValido);
				req.setAttribute("emailAdminInvalido", emailAdminInvalido);
				req.setAttribute("emailAdminVazio", emailAdminVazio);
				req.setAttribute("senhaAdminValido", senhaAdminValido);
				req.setAttribute("senhaAdminInvalido", senhaAdminInvalido);
				req.setAttribute("senhaAdminVazio", senhaAdminVazio);
				req.setAttribute("confirmSenhaAdminValido", confirmSenhaAdminValido);
				req.setAttribute("confirmSenhaAdminInvalido", confirmSenhaAdminInvalido);
				req.setAttribute("confirmSenhaAdminVazio", confirmSenhaAdminVazio);
				req.setAttribute("validouAdmin", validouAdmin);
				req.setAttribute("senhaConfirmada", senhaConfirmada);
				req.getRequestDispatcher("config.jsp").forward(req, resp);
			}
		}else if(validouBanco == false && validouEmail == true){
			if(validouAdmin == true){
				req.setAttribute("bdValido", bdValido);
				req.setAttribute("urlValida", urlValida);
				req.setAttribute("urlInvalida", urlInvalida);
				req.setAttribute("urlVazia", urlVazia);
				req.setAttribute("userValido", userValido);
				req.setAttribute("userInvalido", userInvalido);
				req.setAttribute("userVazio", userVazio);
				req.setAttribute("passwordValido", passwordValido);
				req.setAttribute("passwordInvalido", passwordInvalido);
				req.setAttribute("passwordVazio", passwordVazio);
				req.setAttribute("validouBanco", validouBanco);
				req.getRequestDispatcher("config.jsp").forward(req, resp);
			}else{
				req.setAttribute("bdValido", bdValido);
				req.setAttribute("urlValida", urlValida);
				req.setAttribute("urlInvalida", urlInvalida);
				req.setAttribute("urlVazia", urlVazia);
				req.setAttribute("userValido", userValido);
				req.setAttribute("userInvalido", userInvalido);
				req.setAttribute("userVazio", userVazio);
				req.setAttribute("passwordValido", passwordValido);
				req.setAttribute("passwordInvalido", passwordInvalido);
				req.setAttribute("passwordVazio", passwordVazio);
				req.setAttribute("validouBanco", validouBanco);
				req.setAttribute("usuarioAdminValido", usuarioAdminValido);
				req.setAttribute("usuarioAdminInvalido", usuarioAdminInvalido);
				req.setAttribute("usuarioAdminVazio", usuarioAdminVazio);
				req.setAttribute("nomeAdminValido", nomeAdminValido);
				req.setAttribute("nomeAdminInvalido", nomeAdminInvalido);
				req.setAttribute("nomeAdminVazio", nomeAdminVazio);
				req.setAttribute("emailAdminValido", emailAdminValido);
				req.setAttribute("emailAdminInvalido", emailAdminInvalido);
				req.setAttribute("emailAdminVazio", emailAdminVazio);
				req.setAttribute("senhaAdminValido", senhaAdminValido);
				req.setAttribute("senhaAdminInvalido", senhaAdminInvalido);
				req.setAttribute("senhaAdminVazio", senhaAdminVazio);
				req.setAttribute("confirmSenhaAdminValido", confirmSenhaAdminValido);
				req.setAttribute("confirmSenhaAdminInvalido", confirmSenhaAdminInvalido);
				req.setAttribute("confirmSenhaAdminVazio", confirmSenhaAdminVazio);
				req.setAttribute("validouAdmin", validouAdmin);
				req.setAttribute("senhaConfirmada", senhaConfirmada);
				req.getRequestDispatcher("config.jsp").forward(req, resp);
			}
		}else{
			if(validouAdmin == true){
				req.setAttribute("bdValido", bdValido);
				req.setAttribute("urlValida", urlValida);
				req.setAttribute("urlInvalida", urlInvalida);
				req.setAttribute("urlVazia", urlVazia);
				req.setAttribute("userValido", userValido);
				req.setAttribute("userInvalido", userInvalido);
				req.setAttribute("userVazio", userVazio);
				req.setAttribute("passwordValido", passwordValido);
				req.setAttribute("passwordInvalido", passwordInvalido);
				req.setAttribute("passwordVazio", passwordVazio);
				req.setAttribute("senhaValida", senhaValida);
				req.setAttribute("senhalInvalida", senhaInvalida);
				req.setAttribute("senhaVazia", senhaVazia);
				req.setAttribute("usuarioValido", usuarioValido);
				req.setAttribute("usuarioInvalido", usuarioInvalido);
				req.setAttribute("usuarioVazio", usuarioVazio);
				req.setAttribute("validouEmail", validouEmail);
				req.setAttribute("validouBanco", validouBanco);
				req.getRequestDispatcher("config.jsp").forward(req, resp);
			}else{
				req.setAttribute("bdValido", bdValido);
				req.setAttribute("urlValida", urlValida);
				req.setAttribute("urlInvalida", urlInvalida);
				req.setAttribute("urlVazia", urlVazia);
				req.setAttribute("userValido", userValido);
				req.setAttribute("userInvalido", userInvalido);
				req.setAttribute("userVazio", userVazio);
				req.setAttribute("passwordValido", passwordValido);
				req.setAttribute("passwordInvalido", passwordInvalido);
				req.setAttribute("passwordVazio", passwordVazio);
				req.setAttribute("senhaValida", senhaValida);
				req.setAttribute("senhalInvalida", senhaInvalida);
				req.setAttribute("senhaVazia", senhaVazia);
				req.setAttribute("usuarioValido", usuarioValido);
				req.setAttribute("usuarioInvalido", usuarioInvalido);
				req.setAttribute("usuarioVazio", usuarioVazio);
				req.setAttribute("validouEmail", validouEmail);
				req.setAttribute("validouBanco", validouBanco);
				req.setAttribute("usuarioAdminValido", usuarioAdminValido);
				req.setAttribute("usuarioAdminInvalido", usuarioAdminInvalido);
				req.setAttribute("usuarioAdminVazio", usuarioAdminVazio);
				req.setAttribute("nomeAdminValido", nomeAdminValido);
				req.setAttribute("nomeAdminInvalido", nomeAdminInvalido);
				req.setAttribute("nomeAdminVazio", nomeAdminVazio);
				req.setAttribute("emailAdminValido", emailAdminValido);
				req.setAttribute("emailAdminInvalido", emailAdminInvalido);
				req.setAttribute("emailAdminVazio", emailAdminVazio);
				req.setAttribute("senhaAdminValido", senhaAdminValido);
				req.setAttribute("senhaAdminInvalido", senhaAdminInvalido);
				req.setAttribute("senhaAdminVazio", senhaAdminVazio);
				req.setAttribute("confirmSenhaAdminValido", confirmSenhaAdminValido);
				req.setAttribute("confirmSenhaAdminInvalido", confirmSenhaAdminInvalido);
				req.setAttribute("confirmSenhaAdminVazio", confirmSenhaAdminVazio);
				req.setAttribute("validouAdmin", validouAdmin);
				req.setAttribute("senhaConfirmada", senhaConfirmada);
				req.getRequestDispatcher("config.jsp").forward(req, resp);
			}
		}
		
	}
		
		
}
