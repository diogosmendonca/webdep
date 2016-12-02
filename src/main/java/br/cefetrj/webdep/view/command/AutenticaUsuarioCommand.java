package br.cefetrj.webdep.view.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.security.MessageDigest;
import java.util.Locale;

import br.cefetrj.webdep.model.entity.Usuario;
import br.cefetrj.webdep.services.UsuarioServices;

/**
 * Classe responsavel por checar o login e senha do usuario 
 * no momento de acesso ao sistema.
 * 
 * @author Lawrence Fernandes
 * @since   12-11-2016 
 */

public class AutenticaUsuarioCommand implements Command {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean autenticado = false;
		//Login e senha inseridos no index.jsp
		String loginUsuario = request.getParameter("login");
		String senhaUsuario = request.getParameter("senha");
		//Senha inserida criptografada
		String senhaCriptografada = AutenticaUsuarioCommand.sha512(senhaUsuario);
		//Login e senha existentes no banco de dados
		Usuario login = null;
		String senha = null;
		Long id = null;
		
		//User Locale - i18n
		Locale currentLocale = request.getLocale();
		String msg = "";
		if(currentLocale.getDisplayCountry().equals("Brazil")) {
			msg += "Login ou senha incorretos!";
		} else msg += "Incorrect username or password!";
		
		//Validando usuario
		try {
			login = UsuarioServices.validarLogin(loginUsuario);
			senha = login != null? login.getSenha(): null;
			id = login != null? login.getId(): null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(login != null && senha != null) {
			if(senha.equals(senhaCriptografada)) autenticado = true;
			else autenticado = false;
		} else autenticado = false;
		
		if(autenticado) {
			request.getSession().setAttribute("id", id);
			login.setSenha(null);
			request.getSession().setAttribute("usuario", login);
			response.sendRedirect(request.getContextPath() + "/home.jsp");
			return;
		} else {
			request.getSession().setAttribute("msg", msg);
			request.getSession().setAttribute("usuario", loginUsuario);
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
	}
	
	public static String sha512(String passwordToHash) {
		try {
	        MessageDigest digest = MessageDigest.getInstance("SHA-512");
	        byte[] hash = digest.digest(passwordToHash.getBytes("UTF-8"));
	        StringBuffer hexString = new StringBuffer();

	        for (int i = 0; i < hash.length; i++) {
	            String hex = Integer.toHexString(0xff & hash[i]);
	            if(hex.length() == 1) hexString.append('0');
	            hexString.append(hex);
	        }
	        return hexString.toString();
	        
	    } catch(Exception ex) {
	       throw new RuntimeException(ex);
	    }
	}
}
