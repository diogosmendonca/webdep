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

import br.cefetrj.webdep.model.entity.FormatoLog;
import br.cefetrj.webdep.model.entity.Servidor;
import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.services.LogPeriodico;
import br.cefetrj.webdep.services.ServidorServices;
import br.cefetrj.webdep.services.SistemaServices;

public class InsertSistemaCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter pw = response.getWriter();
			Sistema s = new Sistema();
			HttpSession session = request.getSession();
			String lang = (String)session.getAttribute("lang");
			String nome = request.getParameter("nome");
			String server = request.getParameter("servidor");
			String formatoLog = request.getParameter("formatoLog");
			FormatoLog fmt = new FormatoLog();
			fmt.setId(Long.parseLong(formatoLog));
			Servidor serv = new Servidor();
			serv.setFormatoLog(fmt);
			serv.setId(Long.parseLong(server));
			String ptLogs = request.getParameter("ptLogs");
			String pxLogs = request.getParameter("pxLogs");
			String ptLogs2 = request.getParameter("ptLogs2");
			String pxLogs2 = request.getParameter("pxLogs2");
			/*String nova = request.getParameter("novaData");
			LocalDate ld = LocalDate.parse(request.getParameter("data"));
			LocalTime lt = LocalTime.parse(request.getParameter("time"));*/
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
           
            String nova = ((c.get(Calendar.DAY_OF_YEAR) > 99)? "0":c.get(Calendar.DAY_OF_YEAR))
        		    +" "+ ((c.get(Calendar.HOUR_OF_DAY) < 10)?("0"+c.get(Calendar.HOUR_OF_DAY)):(c.get(Calendar.HOUR_OF_DAY)))
        		    + ":" 
        		   + ((c.get(Calendar.MINUTE) < 10)?("0"+c.get(Calendar.MINUTE)):(c.get(Calendar.MINUTE)));
            
            Integer periodicidade = 0;
            String strPeriodicidade = request.getParameter("periodicidade");
			try{
				System.out.println(strPeriodicidade);
				if( !strPeriodicidade.isEmpty() && strPeriodicidade != null ){
					periodicidade = Integer.parseInt(strPeriodicidade);
					
					if(!( periodicidade >= 1 && periodicidade <= 999)){
						throw new Exception("Periodicidade fora do tamanho permitido");
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}            
			
		try{
			if(periodicidade > 0 ){
				s.setPeriodicidade(periodicidade);
			}
			
			s.setNome(nome);
			s.setServidor(ServidorServices.searchServidor(serv).get(0));
			s.setPastaLogAcesso(ptLogs);
			s.setPrefixoLogAcesso(pxLogs);
			s.setPrefixoLogErro(pxLogs2);
			s.setPastaLogErro(ptLogs2);
			s.setPrimeiraLeitura(l);
			s.setPeriodicidadeLeitura(new SimpleDateFormat("DD HH:mm").parse(nova).getTime()); // MEXI AQUI
			//s.setPeriodicidadeLeitura(new SimpleDateFormat("hh:mm").parse(nova).getTime());
			if (!SistemaServices.verificaDuplicata(s)){
				SistemaServices.insertSistema(s);
				LogPeriodico.agendarTarefaPeriodica();
				switch (lang){
		        	case "en_US":
		        		mensagem = "System successfully inserted!";
		        		break;
		        		
		        	case "pt_BR":
		        		mensagem = "Sistema cadastrado com sucesso!";
		        		break;
		        	default: 
		    			mensagem = "Sistema cadastrado com sucesso!";
		    			break;
				}
			} else {
				switch (lang){
		        	case "en_US":
		        		mensagem = "A system with the same name already exists!";
		        		break;
		        		
		        	case "pt_BR":
		        		mensagem = "Sistema com mesmo nome já existe!";
		        		break;
		        	default: 
		    			mensagem = "Sistema com mesmo nome já existe!";
		    			break;
				}
				
			}
			
		} catch (Exception e) {
			switch (lang){
	        	case "en_US":
	        		mensagem = "Insertion error: " + e.getMessage();
	        		break;
	        		
	        	case "pt_BR":
	        		mensagem = "Erro no cadastro: " + e.getMessage();
	        		break;
	        	default: 
	    			mensagem = "Erro no cadastro: " + e.getMessage();
	    			break;
			}
			//javax.persistence.EntityExistsException j� existe
			e.printStackTrace();
		} finally {
			String json = "{\"mensagem\": \"" + mensagem + "\"}";
			pw.write(json);
		}
	}
}