package br.cefetrj.webdep.view.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeslogaUsuarioCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Invalida sessão e redireciona para login
		//System.out.println("aqui");
        request.getSession().invalidate();
        response.sendRedirect("index.jsp");
	}
 
}
