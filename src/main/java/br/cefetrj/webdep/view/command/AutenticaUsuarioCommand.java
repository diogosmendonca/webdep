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
		boolean valido = false;
		boolean loginValido = false;
		boolean senhaValida = false;
		String msg ="";
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		if((login == null) || login.trim().equals("") ||
				(senha == null) || senha.trim().equals("")){
			loginValido = false;
			senhaValida = false;
			msg += "Login ou senha incorretos.<br/>";
		}
		
		if(login != null) {
			if (login.trim().equals("adminGeral")){
				loginValido = true;
			}
		}
		
		if(loginValido) {
			if((senha == null) || senha.trim().equals("")){
				senhaValida = false;
			}
			else if(senha.equals("ZYM681")) {
				senhaValida = true;
			}
		}
		
		if(loginValido && senhaValida) {
			valido = true;
		}
		//request.setAttribute("validou", true);
		//request.setAttribute("loginValido", loginValido);
		//request.setAttribute("senhaValida", senhaValida);
		
		if(valido){
			//System.out.println("validou");
			//request.getRequestDispatcher("home.jsp").forward(request, response);
			request.getSession().setAttribute("login", login);
            response.sendRedirect("home.jsp");
		}else{
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			//response.sendError(HttpServletResponse.SC_FORBIDDEN, "Login failed.");
		}	
	}
}
