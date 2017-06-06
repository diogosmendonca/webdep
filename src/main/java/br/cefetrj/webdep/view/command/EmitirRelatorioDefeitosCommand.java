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

import br.cefetrj.webdep.DTO.Defeito;
import br.cefetrj.webdep.services.LogErroServices;

public class EmitirRelatorioDefeitosCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {

			String dtInicial = new String(request.getParameter("initialDate"));
			String dtFinal = new String(request.getParameter("finalDate"));
			String url = new String(request.getParameter("url"));

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
			// TODO: handle exception
			Logger lg = Logger.getLogger(EmitirRelatorioDefeitosCommand.class);
			lg.error(e.getMessage());
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
