package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.services.SistemaServices;

/**
 * Esta e a classe de commando que ira realizar o intermedio com a camada de controle e a camada de View.
 * @author Gabriel Lima
 * @version 0.2
 * @since 05/30/2017
 */
public class ListarDefeitosCommand implements Command {

		
	/**
	 * @category Metodo de execusao 
	 * @param Recebe Parametros do proprio .jsp 
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		
	}

}
       

