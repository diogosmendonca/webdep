package br.cefetrj.webdep.view.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Lawrence Fernandes
 * @version 0.1
 * @since   12-11-2016 
 */

public class AutenticaUsuarioCommand implements Command {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean autenticado = false;
		String msg ="Login ou senha incorretos.<br/>";
		String loginUsuario = request.getParameter("login");
		String senhaUsuario = request.getParameter("senha");
		String senhaCriptografada = this.sha512(senhaUsuario);
		System.out.println(senhaCriptografada);
		
		Usuario login = null;
		String senha = null;
		try {
			login = UsuarioServices.validarLogin(loginUsuario);
			senha = login != null? login.getSenha(): null;
			System.out.println(senha);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(login != null && senha != null) {
			//O banco esta salvando a senha "cortada", mesmo apos atualizar o tamanho dela em Usuario
			//if(senha.equals(senhaCriptografada)) autenticado = true;
			if(senha.equals(senhaUsuario)) autenticado = true;
			else autenticado = false;
		} else autenticado = false;

		if(autenticado) {
            response.sendRedirect("home.jsp");
		} else {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}	
	}
	
	private String sha512(String passwordToHash) {
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
