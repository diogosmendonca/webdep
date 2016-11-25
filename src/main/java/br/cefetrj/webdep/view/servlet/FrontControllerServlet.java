package br.cefetrj.webdep.view.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.view.command.AtualizaUsuarioCommand;
import br.cefetrj.webdep.view.command.AtualizaVersaoCommand;
import br.cefetrj.webdep.view.command.AutenticaUsuarioCommand;
import br.cefetrj.webdep.view.command.CadastraUsuarioCommand;
import br.cefetrj.webdep.view.command.Command;
import br.cefetrj.webdep.view.command.DeletaUsuarioCommand;
import br.cefetrj.webdep.view.command.DeletaVersaoCommand;
import br.cefetrj.webdep.view.command.DeslogaUsuarioCommand;
import br.cefetrj.webdep.view.command.FillSistemaCommand;
import br.cefetrj.webdep.view.command.InserePadraoURLCommand;
import br.cefetrj.webdep.view.command.InsereSistemaCommand;
import br.cefetrj.webdep.view.command.InsereVersaoCommand;
import br.cefetrj.webdep.view.command.ListaPermissaoUsuarioCommand;
import br.cefetrj.webdep.view.command.ListaUsuarioCommand;
import br.cefetrj.webdep.view.command.ObterVersaoCommand;
import br.cefetrj.webdep.view.command.ValidaHttpReportCommand;

/**
 * Servlet implementation class FrontControllerServlet
 */

@WebServlet("/FrontControllerServlet")
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static Map<String, Command> commands = new HashMap<>();
	
	static{
		commands.put("errorParameter", new ValidaHttpReportCommand());
		
		commands.put("autenticaUsuario", new AutenticaUsuarioCommand());
		commands.put("deslogaUsuario", new DeslogaUsuarioCommand());
		commands.put("cadastraUsuario", new CadastraUsuarioCommand());
		commands.put("listaUsuario", new ListaUsuarioCommand());
		commands.put("alteraUsuario", new AtualizaUsuarioCommand());
		commands.put("deletaUsuario", new DeletaUsuarioCommand());
		commands.put("listarPemissaoUsuario", new ListaPermissaoUsuarioCommand());
		
		commands.put("insertSistema", new InsereSistemaCommand());
		commands.put("insertPadraoURL", new InserePadraoURLCommand());
		commands.put("fillSistema", new FillSistemaCommand());
		commands.put("insertVersion", new InsereVersaoCommand());
		commands.put("searchVersion", new ObterVersaoCommand());
		commands.put("deleteVersion", new DeletaVersaoCommand());
		commands.put("changeVersion", new AtualizaVersaoCommand());
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String action = request.getParameter("action");
			commands.get(action).execute(request, response);
		}catch(Exception e){
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
