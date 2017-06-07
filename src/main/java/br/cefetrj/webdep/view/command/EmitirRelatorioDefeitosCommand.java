package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.type.LocalDateType;

import br.cefetrj.webdep.DTO.Defeito;
import br.cefetrj.webdep.services.LogErroServices;

/**
 * 
 * @author Rafael Souza
 * @version 0.2
 * @since 30/05/2017
 *
 */
public class EmitirRelatorioDefeitosCommand implements Command {

	/**
	 * @param Todo o conteudo enviado pelo ListaDefeitos.jsp
	 * @return Retorna uma Lista do tipo Defeito para o ListaDefeitos.jsp
	 * @exception Envia as excecoes para um arquivo de Log de sistema
	 *
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {

			String dtInicial = new String(request.getParameter("initialDate"));
			String dtFinal = new String(request.getParameter("finalDate"));
			String url = new String(request.getParameter("url"));
			String msg ="";

			if (dtInicial.length() == 0){
				dtInicial = "01/01/2000";
			}
			
			if (dtFinal.length() == 0){
				dtFinal = "01/01/2018";
			}
			

			String formatarData[] = dtInicial.split("/");
			
			LocalDateTime dataInicial = LocalDateTime.of(LocalDate.of(Integer.parseInt(formatarData[2]),
					Integer.parseInt(formatarData[1]), Integer.parseInt(formatarData[0])), LocalTime.of(0, 0));
			
			formatarData = dtFinal.split("/");
			
			LocalDateTime dataFinal = LocalDateTime.of(LocalDate.of(Integer.parseInt(formatarData[2]),
					Integer.parseInt(formatarData[1]), Integer.parseInt(formatarData[0])), LocalTime.of(0, 0));
			
			List<Defeito> lista = new LogErroServices().buscarDefeitos(dataInicial, dataFinal, "");
			
			
			request.getServletContext().setAttribute("listaDefeitos", lista);
			request.getRequestDispatcher("ListaDefeito.jsp").forward(request, response);

		} catch (Exception e) {
			Logger lg = Logger.getLogger(EmitirRelatorioDefeitosCommand.class);
			lg.error(e.getMessage());
			String msg = "Data inputada invalida, Favor inserir uma data Valida!";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("ListaDefeito.jsp").forward(request, response);
		}
	}

}
