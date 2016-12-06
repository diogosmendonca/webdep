package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.services.ServidorServices;
import br.cefetrj.webdep.services.SistemaServices;

public class UpdateSistemaCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			PrintWriter pw = response.getWriter();
			HttpSession session = request.getSession();
	        String lang = (String)session.getAttribute("lang");
			Sistema s = new Sistema();
			String id = request.getParameter("id_sistema_update");
			s = SistemaServices.obterPorId(Long.parseLong(id));
			String nome = request.getParameter("nome");
			String server = request.getParameter("servidor");
			String ptLogs = request.getParameter("ptLogs");
			String pxLogs = request.getParameter("pxLogs");
			String ptLogs2 = request.getParameter("ptLogs2");
			String pxLogs2 = request.getParameter("pxLogs2");
			int novaDias = Integer.parseInt(request.getParameter("novaLeituraDia")); // MEXI AQUI
            String novaHora = request.getParameter("novaLeituraHora"); // MEXI AQUI
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm"); // MEXI AQUI
            LocalTime tempoNovaLeitura = LocalTime.parse(novaHora, formatter); // MEXI AQUI
            LocalDate ld = LocalDate.parse(request.getParameter("dataPrimeiraLeitura")); // MEXI AQUI
            LocalTime lt = LocalTime.parse(request.getParameter("horaPrimeiraLeitura")); // MEXI AQUI
			LocalDateTime l = LocalDateTime.of(ld, lt);
			String mensagem = "";
			
			GregorianCalendar c = new GregorianCalendar();
            c.set(Calendar.DAY_OF_YEAR, novaDias); // MEXI AQUI
            c.set(Calendar.HOUR_OF_DAY, tempoNovaLeitura.getHour()); // MEXI AQUI
            c.set(Calendar.MINUTE, tempoNovaLeitura.getMinute()); // MEXI AQUI
           
            String nova = c.get(Calendar.DAY_OF_YEAR) 
		    +" "+ ((c.get(Calendar.HOUR_OF_DAY) < 10)?("0"+c.get(Calendar.HOUR_OF_DAY)):(c.get(Calendar.HOUR_OF_DAY)))
		    + ":" 
		   + ((c.get(Calendar.MINUTE) < 10)?("0"+c.get(Calendar.MINUTE)):(c.get(Calendar.MINUTE)));
		try{	
			
			s.setServidor(ServidorServices.searchServidor(server).get(0));
			s.setPastaLogAcesso(ptLogs);
			s.setPrefixoLogAcesso(pxLogs);
			s.setPrefixoLogErro(pxLogs2);
			s.setPastaLogErro(ptLogs2);
			s.setPrimeiraLeitura(l);
			s.setPeriodicidadeLeitura(new SimpleDateFormat("DD HH:mm").parse(nova).getTime());
			if (s.getNome().equals(nome)){
				SistemaServices.updateSistema(s);
				switch (lang){
		        	case "en_US":
		        		mensagem = "System successfully updated!";
		        		break;
		        		
		        	case "pt_BR":
		        		mensagem = "Sistema atualizado com sucesso!";
		        		break;
		        	default: 
		        		mensagem = "Sistema atualizado com sucesso!";
		    			break;
	        	}
			} else {
				s.setNome(nome);
				if (!SistemaServices.verificaDuplicata(s)){
					SistemaServices.updateSistema(s);
					switch (lang){
			        	case "en_US":
			        		mensagem = "System successfully updated!";
			        		break;
			        		
			        	case "pt_BR":
			        		mensagem = "Sistema atualizado com sucesso!";
			        		break;
			        	default: 
			        		mensagem = "Sistema atualizado com sucesso!";
			    			break;
		        	}
				} else {
					switch (lang){
			        	case "en_US":
			        		mensagem = "A System with same name already exists";
			        		break;
			        		
			        	case "pt_BR":
			        		mensagem = "Sistema com mesmo nome já existe!";
			        		break;
			        	default: 
			        		mensagem = "Sistema com mesmo nome já existe!";
			    			break;
		        	}
				}
			}
		} catch (Exception e) {
			switch (lang){
	        	case "en_US":
	        		mensagem = "Could not update the system!";
	        		break;
	        		
	        	case "pt_BR":
	        		mensagem = "Não foi possível atualizar o sistema!";
	        		break;
	        		
	        	default: 
	        		mensagem = "Não foi possível atualizar o sistema!";
	    			break;
	    	}
			
			e.printStackTrace();
		} finally {
			String json = "{\"mensagem\": \"" + mensagem + "\"}";
			pw.write(json);
		}
	}
}