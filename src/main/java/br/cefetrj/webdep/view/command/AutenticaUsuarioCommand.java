package br.cefetrj.webdep.view.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.security.MessageDigest;

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
		String msg ="Login ou senha incorretos.<br/>";
		//Login e senha inseridos no index.jsp
		String loginUsuario = request.getParameter("login");
		String senhaUsuario = request.getParameter("senha");
		//Senha inserida criptografada
		String senhaCriptografada = AutenticaUsuarioCommand.sha512(senhaUsuario);
		//Login e senha existentes no banco de dados
		Usuario login = null;
		String senha = null;
		
		try {
			login = UsuarioServices.validarLogin(loginUsuario);
			senha = login != null? login.getSenha(): null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(login != null && senha != null) {
			if(senha.equals(senhaCriptografada)) autenticado = true;
			else autenticado = false;
		} else autenticado = false;

		if(autenticado) {
            response.sendRedirect("home.jsp");
		} else {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("index.jsp").forward(request, response);
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
