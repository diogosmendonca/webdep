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

public class ListSistemaCommand implements Command{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filtro = request.getParameter("filtro");
		PrintWriter pw = response.getWriter();
		String json = "";
		List<Sistema> sistemasFiltrados = null;
		if (filtro.equals("all")) {
			sistemasFiltrados = SistemaServices.listarTodos();
		} else {
			sistemasFiltrados = SistemaServices.searchSistema(filtro);
		}
		
		if (sistemasFiltrados.size() > 0) {
			json = "{\"sistemas\": [";
			for (Sistema s : sistemasFiltrados) {
				//PEGANDO HORA DO SISTEMA
				LocalTime now = LocalTime.now();
				Date novaLeituraInput = new Date(s.getPeriodicidadeLeitura()); //MEXI AQUI
                Calendar cal = new GregorianCalendar(); //MEXI AQUI
                cal.setTime(novaLeituraInput);//MEXI AQUI
				LocalTime PrimeiraLeitura = s.getPrimeiraLeitura().toLocalTime();
				
				while(PrimeiraLeitura.getHour() < now.getHour()){
					
					PrimeiraLeitura = PrimeiraLeitura.plusMinutes(cal.get(Calendar.MINUTE));//MEXI AQUI
                    PrimeiraLeitura = PrimeiraLeitura.plusHours(cal.get(Calendar.HOUR_OF_DAY));//MEXI AQUI
                }
                    PrimeiraLeitura = PrimeiraLeitura.plusMinutes(cal.get(Calendar.MINUTE));//MEXI AQUI
                    PrimeiraLeitura = PrimeiraLeitura.plusHours(cal.get(Calendar.HOUR_OF_DAY));//MEXI AQUI
					
					GregorianCalendar gc = new GregorianCalendar();  
				    gc.setTime(new Date());  
				    
				    String formato = "dd/MM/yyyy HH:mm";
				    SimpleDateFormat sdf2 = new SimpleDateFormat(formato);
				    ///AQUI EU SETO A HORA QUE QUERO SOMAR E MAIS OS MINUTOS, SE QUISER SOMAR OS SEGUNDO É SÓ COLOCAR O SEGUNDOS PARA SOMAR
				    gc.add(Calendar.HOUR,PrimeiraLeitura.getHour());  
				    gc.add(Calendar.MINUTE,PrimeiraLeitura.getMinute());
				   
				    String novaLeitura = sdf2.format(gc.getTime());
				    String periodicidade = cal.get(Calendar.DAY_OF_MONTH)+" dias "
				    + cal.get(Calendar.HOUR_OF_DAY)+":"
				    		+cal.get(Calendar.MINUTE);
				    
				json += "{";
				json += "\"id\":\"" + s.getId() + "\",";
				json += "\"nome\":\"" + s.getNome() + "\",";
				json += "\"servidor\":\"" + s.getServidor().getNome() + "\",";
				json += "\"formatolog\":\"" + s.getServidor().getFormatoLog().getNome() + "\",";
				json += "\"periodicidade\":\"" + periodicidade + "\",";
				//json += "\"periodicidade\":\"" + Periodicidade.toString() + "\",";
				json += "\"proximaleitura\":\"" + novaLeitura + "\"";
				json += "},";
			}
			json += "]}";
			json = json.replace("},]}", "}]}");
		} else {
			json = "{\"Erro\": \"Nenhum resultado encontrado\"}";
		}
		pw.write(json);
	}
}
