package br.cefetrj.webdep.view.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertSelectIdSistemaCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			Long id = null;
			try{
				id = Long.parseLong(request.getParameter("idSistema"));
			}catch(Exception e){
				//não faz nada, pois o usuário não selecionou nenhum id.
			}
			request.getSession().setAttribute("idsistema", id);
			
			//request.getRequestDispatcher("/home.jsp").forward(request, response);
		
	}

	
	
	
}
