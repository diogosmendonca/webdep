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
import br.cefetrj.webdep.view.command.ListarPermissaoUsuarioCommand;
import br.cefetrj.webdep.view.command.SearchVersionCommand;
import br.cefetrj.webdep.view.command.UpdateSistemaCommand;
import br.cefetrj.webdep.view.command.ValidaHttpReportCommand;
import br.cefetrj.webdep.view.command.ObterUsuarioCommand;
import br.cefetrj.webdep.view.command.RegexPadraoURLCommand;

import br.cefetrj.webdep.view.command.AtualizaUsuarioCommand;
import br.cefetrj.webdep.view.command.AutenticaUsuarioCommand;
import br.cefetrj.webdep.view.command.CadastraUsuarioCommand;
import br.cefetrj.webdep.view.command.DeletaUsuarioCommand;
import br.cefetrj.webdep.view.command.DeslogaUsuarioCommand;
import br.cefetrj.webdep.view.command.ListaUsuarioCommand;

import br.cefetrj.webdep.view.command.ValidaBanco;
import br.cefetrj.webdep.view.command.ValidaConfig;
import br.cefetrj.webdep.view.command.ValidaEmail;

import br.cefetrj.webdep.view.command.ValidaLogErroCommand;
import br.cefetrj.webdep.view.command.ValidaLogAcessoCommand;
import br.cefetrj.webdep.view.command.ImportarLogCommand;
import br.cefetrj.webdep.view.command.ExcluirLogErroCommand;
import br.cefetrj.webdep.view.command.FillSistemaCommand;
import br.cefetrj.webdep.view.command.ExcluirLogAcessoCommand;
import br.cefetrj.webdep.view.command.BuscarLogAcessoCommand;
import br.cefetrj.webdep.view.command.BuscarLogErroCommand;
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
		commands.put("listarPemissaoUsuario", new ListarPermissaoUsuarioCommand());
		
		commands.put("insertVersion", new InsertVersionCommand());
 		commands.put("searchVersion", new SearchVersionCommand());
 		commands.put("deleteVersion", new DeleteVersionCommand());
 		commands.put("changeVersion", new ChangeVersionCommand());
 		commands.put("getUsuario", new ObterUsuarioCommand());
 		
 		commands.put("insertSistema", new InsertSistemaCommand());
 		commands.put("updateSistema", new UpdateSistemaCommand());
 		commands.put("listSistema", new ListSistemaCommand());
 		commands.put("deleteSistema", new DeleteSistemaCommand());
 		commands.put("fillSistema", new FillSistemaCommand());
 		
 		commands.put("insertPadraoURL", new InsertPadraoURLCommand());
 		commands.put("regexPadraoURL", new RegexPadraoURLCommand());
		
		commands.put("buscarLogErro", new BuscarLogErroCommand());
		commands.put("buscarLogAcesso", new BuscarLogAcessoCommand());
		commands.put("excluirLogAcesso", new ExcluirLogAcessoCommand());
		commands.put("excluirLogErro", new ExcluirLogErroCommand());
		commands.put("importaLog", new ImportarLogCommand());
		commands.put("validaLogAcesso", new ValidaLogAcessoCommand());
		commands.put("validaLogErro", new ValidaLogErroCommand());
		
		commands.put("ValidaEmail", new ValidaEmail());
        	commands.put("ValidaBanco", new ValidaBanco());
        	commands.put("ValidaConfig", new ValidaConfig());
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String action = request.getParameter("action");
			commands.get(action).execute(request, response);
		}catch(Exception e){
			request.getRequestDispatcher("error.jsp").forward(request, response);
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
