package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.services.SistemaServices;

public class FillSistemaCommand implements Command{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filtro = request.getParameter("filtro");
		PrintWriter pw = response.getWriter();
		String json = "";
		Sistema s = SistemaServices.obterPorId(Long.parseLong(filtro));
		if (s != null){
			json = "{\"sistema\":";
				json += "{";
				json += "\"nome\":\"" + s.getNome() + "\",";
				json += "\"servidor\":" + s.getServidor().getId() + ",";
				json += "\"formatolog\":" + s.getServidor().getFormatoLog().getId() + ",";
				json += "\"ptLogs\":\"" + s.getPastaLogAcesso() + "\",";
				json += "\"pxLogs\":\"" + s.getPrefixoLogAcesso() + "\",";
				json += "\"ptLogs2\":\"" + s.getPastaLogErro() + "\",";
				json += "\"pxLogs2\":\"" + s.getPrefixoLogErro() + "\",";
				json += "\"data\":\"" + s.getPrimeiraLeitura().toLocalDate().toString() + "\",";
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(s.getPeriodicidadeLeitura());
				LocalDateTime ofInstant = LocalDateTime.ofInstant(cal.toInstant(), ZoneId.systemDefault());
				json += "\"time\":\"" + s.getPrimeiraLeitura().toLocalTime().toString() + "\",";
				json += "\"novaData\":\"" + ofInstant.toLocalTime().toString() + "\"";
				json += "}";
			json += "}";
		} else {
			json = "{\"Erro\": \"Nenhum resultado encontrado\"}";
		}
		pw.write(json);
	}
}
