package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.entity.Permissao;
import br.cefetrj.webdep.model.entity.Usuario;
import br.cefetrj.webdep.services.UsuarioServices;

/**
 * Classe responsavel por remover usuarios do sistema.
 * 
 * @author Lawrence Fernandes
 * @author Iury
 * @author Leonardo
 * @version 0.1
 * @since   22-11-2016 
 */

public class DeletaUsuarioCommand implements Command {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = null;
		if(request.getParameter("id") != null){
			try{
				id = Long.valueOf(request.getParameter("id"));
			}catch (Exception e) {
				e.printStackTrace();
			}			
		}
		
		if(id != null){
			Usuario usu = UsuarioServices.obterPorId(id);			
			if(usu != null){
				request.setAttribute("usuario", usu);
				request.getRequestDispatcher("deletaUsuario.jsp").forward(request, response);	
			}else{
				request.setAttribute("AlterErroUsu", true);
				request.getRequestDispatcher("alteraUsuario.jsp").forward(request, response);
			}
			
		}else{
			request.setAttribute("AlterErroUsu", true);
			request.getRequestDispatcher("alteraUsuario.jsp").forward(request, response);	
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = null;
		if(request.getParameter("id") != null){
			try{
				id = Long.valueOf(request.getParameter("id"));
			}catch (Exception e) {
				e.printStackTrace();
			}			
		}
		
		if(id != null){
			Usuario usu = null;
			usu =UsuarioServices.obterPorId(id);	
			
			if(usu != null){
				new UsuarioServices();
				List<Permissao> lp = UsuarioServices.getPermissao(usu);
				for (Permissao permissao : lp) {
					UsuarioServices usuSePe = new UsuarioServices();
					usuSePe.removerPermissao(permissao);
				}
				UsuarioServices.remover(usu);
				request.getRequestDispatcher("FrontControllerServlet?action=listaUsuario&get=true").forward(request, response);	
			}else{
				request.setAttribute("AlterErroUsu", true);
				request.getRequestDispatcher("alteraUsuario.jsp").forward(request, response);
			}
			
		}else{
			request.setAttribute("AlterErroUsu", true);
			request.getRequestDispatcher("alteraUsuario.jsp").forward(request, response);	
		}
	}
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final Boolean get;
		if(request.getParameter("get") != null){
			get = Boolean.valueOf(request.getParameter("get"));
		}else{
			get = false;
		}
		
		if(get == null || get == true){
			doGet(request, response);
		}else{
			doPost(request, response);
		}	
	}
}
