package br.cefetrj.webdep.view.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.view.command.ChangeVersionCommand;
import br.cefetrj.webdep.view.command.Command;
import br.cefetrj.webdep.view.command.DeleteSistemaCommand;
import br.cefetrj.webdep.view.command.DeleteVersionCommand;
import br.cefetrj.webdep.view.command.InsertPadraoURLCommand;
import br.cefetrj.webdep.view.command.InsertSistemaCommand;
import br.cefetrj.webdep.view.command.InsertVersionCommand;
import br.cefetrj.webdep.view.command.ListSistemaCommand;
import br.cefetrj.webdep.view.command.SearchVersionCommand;
import br.cefetrj.webdep.view.command.UpdateSistemaCommand;
import br.cefetrj.webdep.view.command.ValidaHttpReportCommand;
import br.cefetrj.webdep.view.command.ObterUsuarioCommand;
import br.cefetrj.webdep.view.command.RegexPadraoURLCommand;
import br.cefetrj.webdep.view.command.AutenticaUsuarioCommand;
import br.cefetrj.webdep.view.command.DeslogaUsuarioCommand;

/**
 * Servlet implementation class FrontControllerServlet
 */
@WebServlet("/FrontControllerServlet")
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static Map<String, Command> commands = new HashMap<>();
	
	static{
		commands.put("insertVersion", new InsertVersionCommand());
		commands.put("searchVersion", new SearchVersionCommand());
		commands.put("deleteVersion", new DeleteVersionCommand());
		commands.put("changeVersion", new ChangeVersionCommand());
		commands.put("getUsuario", new ObterUsuarioCommand());
		commands.put("errorParameter", new ValidaHttpReportCommand());
		commands.put("insertSistema", new InsertSistemaCommand());
		commands.put("updateSistema", new UpdateSistemaCommand());
		commands.put("listSistema", new ListSistemaCommand());
		commands.put("deleteSistema", new DeleteSistemaCommand());
		commands.put("insertPadraoURL", new InsertPadraoURLCommand());
		commands.put("regexPadraoURL", new RegexPadraoURLCommand());
		commands.put("autenticaUsuario", new AutenticaUsuarioCommand());
		commands.put("deslogaUsuario", new DeslogaUsuarioCommand());

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
