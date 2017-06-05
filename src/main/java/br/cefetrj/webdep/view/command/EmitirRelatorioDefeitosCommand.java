package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.DTO.Defeito;
import br.cefetrj.webdep.services.LogErroServices;

/**
 * Esta e a classe de commando que ira realizar o intermedio com a camada de controle e a camada de View.
 * @author Gabriel Lima
 * @version 0.2
 * @since 05/30/2017
 */
public class EmitirRelatorioDefeitosCommand implements Command {

		
	/**
	 * @category Metodo de execusao 
	 * @param Recebe Parametros do proprio .jsp 
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			LocalDateTime dtInicial = LocalDateTime.parse(request.getParameter(""));
			LocalDateTime dtFinal = LocalDateTime.parse(request.getParameter(""));
			String url = request.getParameter("url");
			
			List<Defeito> listaDefeitos = LogErroServices.buscarDefeitos(dtInicial, dtFinal, url);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
       

