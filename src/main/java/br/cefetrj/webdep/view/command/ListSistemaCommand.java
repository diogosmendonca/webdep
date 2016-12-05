package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.services.SistemaServices;

public class ListSistemaCommand implements Command{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filtro = request.getParameter("filtro");
		PrintWriter pw = response.getWriter();
		String json = "";
		List<Sistema> sistemasFiltrados = null;
		boolean buscaPorData = false;
		Date filtroData = null;
		if (filtro.equals("all")) {
			sistemasFiltrados = SistemaServices.listarTodos();
		} else {
			if(filtro.contains("/") || filtro.contains(":")){
				try {
					if (!filtro.contains("/")) {
						SimpleDateFormat periodicidadeParse = new SimpleDateFormat("dd HH:mm");
						filtroData = periodicidadeParse.parse(filtro);
					} else {
						SimpleDateFormat dataHoraParse = new SimpleDateFormat("dd/mm/yyyy HH:mm");
						filtroData = dataHoraParse.parse(filtro);
					}
					sistemasFiltrados = SistemaServices.listarTodos();
					buscaPorData = true;
				} catch (ParseException e) {
					buscaPorData = false;
					sistemasFiltrados = SistemaServices.searchSistema(filtro);
				}
			} else {
				sistemasFiltrados = SistemaServices.searchSistema(filtro);
			}
		}
		
		if (sistemasFiltrados.size() > 0) {
			json = "{\"sistemas\": [";
			for (Sistema s : sistemasFiltrados) {
				//PEGANDO HORA DO SISTEMA
				Calendar now = new GregorianCalendar().getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat();
				Date novaLeituraInput = new Date(s.getPeriodicidadeLeitura());
				Calendar calNovaLeitura = new GregorianCalendar();
				calNovaLeitura.setTime(novaLeituraInput);
				LocalDate ld = s.getPrimeiraLeitura().toLocalDate();
				LocalTime lt = s.getPrimeiraLeitura().toLocalTime();
				LocalDateTime l = LocalDateTime.of(ld, lt);
				LocalDate dataPrimeiraLeitura = l.toLocalDate();
				Date d = java.sql.Date.valueOf(dataPrimeiraLeitura);
				Calendar calPrimeiraLeitura = new GregorianCalendar();
				calPrimeiraLeitura.setTime(d);
				calPrimeiraLeitura.set(Calendar.HOUR_OF_DAY, lt.getHour());
				calPrimeiraLeitura.set(Calendar.MINUTE, lt.getMinute());

				Calendar x = calPrimeiraLeitura;

				while (!(x.getTime().after(now.getTime()))) {
					x.add(Calendar.DATE, calNovaLeitura.get(Calendar.DAY_OF_YEAR));
					x.add(Calendar.HOUR, calNovaLeitura.get(Calendar.HOUR_OF_DAY));
					x.add(Calendar.MINUTE, calNovaLeitura.get(Calendar.MINUTE));
				}
				
				String formato = "dd/MM/yyyy HH:mm";
			    SimpleDateFormat sdf2 = new SimpleDateFormat(formato);
				String novaLeitura = sdf2.format(x.getTime());
				    String periodicidade = calNovaLeitura.get(Calendar.DAY_OF_YEAR) 
				    +" "+ ((calNovaLeitura.get(Calendar.HOUR_OF_DAY) < 10)?("0"+calNovaLeitura.get(Calendar.HOUR_OF_DAY)):(calNovaLeitura.get(Calendar.HOUR_OF_DAY)))
				    + ":" 
				   + ((calNovaLeitura.get(Calendar.MINUTE) < 10)?("0"+calNovaLeitura.get(Calendar.MINUTE)):(calNovaLeitura.get(Calendar.MINUTE)));
				if (buscaPorData) {
					if (novaLeitura.equals(filtro) || periodicidade.equals(filtro)) {
						json += "{";
						json += "\"id\":\"" + s.getId() + "\",";
						json += "\"nome\":\"" + s.getNome() + "\",";
						json += "\"servidor\":\"" + s.getServidor().getNome() + "\",";
						json += "\"formatolog\":\"" + s.getServidor().getFormatoLog().getNome() + "\",";
						json += "\"periodicidade\":\"" + periodicidade + "\",";
						json += "\"proximaleitura\":\"" + novaLeitura + "\"";
						json += "},";
					}
				} else {
					json += "{";
					json += "\"id\":\"" + s.getId() + "\",";
					json += "\"nome\":\"" + s.getNome() + "\",";
					json += "\"servidor\":\"" + s.getServidor().getNome() + "\",";
					json += "\"formatolog\":\"" + s.getServidor().getFormatoLog().getNome() + "\",";
					json += "\"periodicidade\":\"" + periodicidade + "\",";
					json += "\"proximaleitura\":\"" + novaLeitura + "\"";
					json += "},";
				}
			}
			json += "]}";
			json = json.replace("},]}", "}]}");
		} else {
			json = "{\"Erro\": \"Nenhum resultado encontrado\"}";
		}
		pw.write(json);
	}
}
