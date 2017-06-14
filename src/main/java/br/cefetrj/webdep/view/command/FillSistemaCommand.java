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
import javax.servlet.http.HttpSession;

import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.services.SistemaServices;

public class FillSistemaCommand implements Command{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filtro = request.getParameter("filtro");
		HttpSession session = request.getSession();  
        String lang = (String)session.getAttribute("lang");
		PrintWriter pw = response.getWriter();
		String json = "";
		Sistema s = SistemaServices.obterPorId(Long.parseLong(filtro));
		if (s != null){
			json = "{\"sistema\":";
				json += "{";
				json += "\"nome\":\"" + s.getNome() + "\",";
				json += "\"servidor\":\"" + s.getServidor().getId() + "\",";
				json += "\"formatolog\":\"" + s.getServidor().getFormatoLog().getId() + "\",";
				json += "\"ptLogs\":\"" + s.getPastaLogAcesso().replace("\\", "\\\\") + "\",";
				json += "\"pxLogs\":\"" + s.getPrefixoLogAcesso().replace("\\", "\\\\") + "\",";
				json += "\"ptLogs2\":\"" + s.getPastaLogErro().replace("\\", "\\\\") + "\",";
				json += "\"pxLogs2\":\"" + s.getPrefixoLogErro().replace("\\", "\\\\") + "\",";
				json += "\"data\":\"" + s.getPrimeiraLeitura().toLocalDate().toString() + "\",";
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(s.getPeriodicidadeLeitura());
				LocalDateTime ofInstant = LocalDateTime.ofInstant(cal.toInstant(), ZoneId.systemDefault());
				json += "\"time\":\"" + s.getPrimeiraLeitura().toLocalTime().toString() + "\",";
				Date novaLeituraInput = new Date(s.getPeriodicidadeLeitura());
				Calendar calNovaLeitura = new GregorianCalendar();
				calNovaLeitura.setTime(novaLeituraInput);
				String periodicidade = ((calNovaLeitura.get(Calendar.DAY_OF_YEAR) < 10)?("0"+calNovaLeitura.get(Calendar.DAY_OF_YEAR)):((calNovaLeitura.get(Calendar.DAY_OF_YEAR) > 99)? "0":calNovaLeitura.get(Calendar.DAY_OF_YEAR)))
					    +" "+ ((calNovaLeitura.get(Calendar.HOUR_OF_DAY) < 10)?("0"+calNovaLeitura.get(Calendar.HOUR_OF_DAY)):(calNovaLeitura.get(Calendar.HOUR_OF_DAY)))
					    + ":" 
					   + ((calNovaLeitura.get(Calendar.MINUTE) < 10)?("0"+calNovaLeitura.get(Calendar.MINUTE)):(calNovaLeitura.get(Calendar.MINUTE)));
				json += "\"novaData\":\"" + periodicidade + "\"";
				json += "}";
			json += "}";
		} else {
			switch (lang){
	        	case "en_US":
	        		json = "{\"Erro\": \"No results found\"}";
	        		break;
	        		
	        	case "pt_BR":
	        		json = "{\"Erro\": \"Nenhum resultado encontrado\"}";
	        		break;
	        	default: 
	        		json = "{\"Erro\": \"Nenhum resultado encontrado\"}";
	    			break;
			}
		}
		pw.write(json);
	}
}
