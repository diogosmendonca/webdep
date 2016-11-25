package br.cefetrj.webdep.view.command;

import javax.servlet.ServletException;
import br.cefetrj.webdep.model.dao.PersistenceManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.cefetrj.webdep.model.entity.ConfiguracaoSistema;
import br.cefetrj.webdep.model.dao.ConfigurationDAO;
import br.cefetrj.webdep.model.dao.ConfigurationDAO.DatabaseType;

import java.io.File;
import java.io.IOException;


public class ValidaBanco implements Command	{
	@Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean bdInvalido = false;
		
		String bd = req.getParameter("banco");
		boolean bdValido = true;
		if(bd.equals("HSQL") || bd.equals("MySQL") || bd.equals("Postgres") ){
			bdValido = true;
		}else{
			bdValido = false;
		}
		
		String url = req.getParameter("urlBanco");
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
		
		String user = req.getParameter("usuarioBanco");
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
		
		String password = req.getParameter("senhaBanco");
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
		}
		
		if(bdInvalido){
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
			req.setAttribute("bdInvalido", bdInvalido);			
			req.getRequestDispatcher("config.jsp").forward(req, resp);
		}else{
			ConfigurationDAO config = new ConfigurationDAO();
			boolean validouBanco = true;			
			if(bd.equals("HSQL")){
				validouBanco = config.changeDatabaseConfig("C:/Users/Gabriel/git/webdep/src/main/resources/META-INF/persistence.xml", DatabaseType.HSQL, url, user, password);
			}else if(bd.equals("MySQL")){
				validouBanco = config.changeDatabaseConfig("C:/Users/Gabriel/git/webdep/src/main/resources/META-INF/persistence.xml", DatabaseType.MySQL, url, user, password);
			}else if(bd.equals("Postegres")){
				validouBanco = config.changeDatabaseConfig("C:/Users/Gabriel/git/webdep/src/main/resources/META-INF/persistence.xml", DatabaseType.Postgres, url, user, password);
			}
			
						
			
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
			req.setAttribute("bdInvalido", bdInvalido);
			req.setAttribute("validouBanco", validouBanco);
			req.getRequestDispatcher("config.jsp").forward(req, resp);
		}
	}

}