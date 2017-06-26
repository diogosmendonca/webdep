package br.cefetrj.webdep.services;

	import java.io.IOException;
	import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
	import java.util.List;
	import java.util.concurrent.Executors;
	import java.util.concurrent.ScheduledExecutorService;
	import java.util.concurrent.ScheduledFuture;
	import java.util.concurrent.TimeUnit;

	import javax.servlet.ServletException;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import javax.servlet.http.HttpSession;

	import br.cefetrj.webdep.model.entity.Sistema;
	import br.cefetrj.webdep.view.command.Command;

	/**
	 * 
	 * @author Thiago Ferreira
	 * @since 04/06/2017
	 * @version 0.2
	 *
	 */
	public class LogPeriodico{
		private static boolean threadon = false;
		static ScheduledFuture<?> importadorAgendamento ;
		static int contador = 0;
		
		static int getcontador()
		{
			int conta =contador;
			return conta;
		}
		
		/**
		 * Metodo que vai ser responsavel por realizar os agendamentos periodicos
		 */
		public static void agendarTarefaPeriodica(){
			
			
			List<Sistema> listaThreadsAtivas = null;
			Sistema aux = null;
			threadon = false;
			//ScheduledFuture<?> thread = new ScheduledFuture();
			long delayInicial =0L;
			long periodicidade = 5L;
			int id ='0'; //get ID atual set value
			final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
			ScheduledFuture<?> criathread ; 
			 // CHAMA A VERIFICACAO DO LOG PARA MANUTENCAO
			
			
			
			
			final Runnable importador = new Runnable() {
				
				// sempre inicializa a thread quando o banco Ã© modificado ou quando  a pessoa loga.
				private Runnable threadSistema(String nome,long aux, int contador, ScheduledFuture<?> threade) {
					
					// TODO Auto-generated method stub
					//System.out.println("Chamou Thread"+ aux);
					final Runnable importador2 = new Runnable() {
						
						@Override
						public void run() {
							int conta = LogPeriodico.getcontador();
							// Compara o valor da Thread atual e cancela se for da geracao passada
							if(conta == contador)
							{
								// Se for da atual faz a acao de ler;
								System.out.println("Executando Thread de Nome : "+nome);
								// Importa log
								Sistema sis = SistemaServices.SearchById(aux);
								LogAcessoServices.ImportarLogAcesso(sis);
								
								//Excluir Log - Funcao nao encontrada.
								//LogAcessoServices.ExcluirLogAcesso(sis);
							}
							else
							{
								//Cancela a Thread se for modificado o Banco de Dados.
								threade.cancel(false);
							}
						}
					};
						
					return importador2;
				}

				
			     public void run() {
			    	
			    	// Checa se o usuario esta logado para comecar as threads
			    	List<Sistema> sistemasFiltrados =  SistemaServices.listarTodos();
			    	if(threadon ==false)
			    	{
			    		
				    	if(sistemasFiltrados == null || sistemasFiltrados.isEmpty() )
				 		{
				 			// Usuario nao esta em sessao
				 			System.out.println("Esperando sessao para iniciar Thread de Limpeza Periodica de Log");
				 			
				 		}
				 		else
				 		{
				 			// Mosta que as threads estao rodando
				 			threadon=true;
				 			//Checa se existe algum sistema valido cadastrado
				 			if (sistemasFiltrados.size() > 0)
				 		    {
				 				// Pega toda a tabela de BD para verificar quem esta rodando
				 				for (Sistema sistema : sistemasFiltrados)
				 				{
				 					// Se nenhuma thread foi inciada
					 				System.out.println("Iniciando a Thread do "+sistema.getNome());
					 				String nome = sistema.getNome();
					 				long aux = sistema.getId();
					 				LocalDateTime primeiraLeitura = sistema.getPrimeiraLeitura();
					 				LocalDateTime agora = LocalDateTime.now();
					 				long periodicidadereal = sistema.getPeriodicidadeLeitura();
					 				
					 				
					 				// Pega os valores do BD e coloca no formato correto
					 				Calendar now = new GregorianCalendar().getInstance();
									SimpleDateFormat sdf = new SimpleDateFormat();
									Date novaLeituraInput = new Date(sistema.getPeriodicidadeLeitura());
									Calendar calNovaLeitura = new GregorianCalendar();
									calNovaLeitura.setTime(novaLeituraInput);
									LocalDate ld = sistema.getPrimeiraLeitura().toLocalDate();
									LocalTime lt = sistema.getPrimeiraLeitura().toLocalTime();
									LocalDateTime l = LocalDateTime.of(ld, lt);
									LocalDate dataPrimeiraLeitura = l.toLocalDate();
									Date d = java.sql.Date.valueOf(dataPrimeiraLeitura);
									Calendar calPrimeiraLeitura = new GregorianCalendar();
									calPrimeiraLeitura.setTime(d);
									calPrimeiraLeitura.set(Calendar.HOUR_OF_DAY, lt.getHour());
									calPrimeiraLeitura.set(Calendar.MINUTE, lt.getMinute());

									Calendar x = calPrimeiraLeitura;
									//Verifica se ja passou
									while (!(x.getTime().after(now.getTime()))) {
										if (calNovaLeitura.get(Calendar.DAY_OF_YEAR) > 99){
											x.add(Calendar.HOUR, calNovaLeitura.get(Calendar.HOUR_OF_DAY));
											x.add(Calendar.MINUTE, calNovaLeitura.get(Calendar.MINUTE));
										} else {
											x.add(Calendar.DATE, calNovaLeitura.get(Calendar.DAY_OF_YEAR));
											x.add(Calendar.HOUR, calNovaLeitura.get(Calendar.HOUR_OF_DAY));
											x.add(Calendar.MINUTE, calNovaLeitura.get(Calendar.MINUTE));
										}
									}
									
									//Verifica o tempo
									String formato = "yyyy-MM-dd HH:mm";
								    SimpleDateFormat sdf2 = new SimpleDateFormat(formato);
									String novaLeitura = sdf2.format(x.getTime());
									String periodicidadedia = ((calNovaLeitura.get(Calendar.DAY_OF_YEAR) < 10)?("0"+calNovaLeitura.get(Calendar.DAY_OF_YEAR)):((calNovaLeitura.get(Calendar.DAY_OF_YEAR) > 99)? "0":calNovaLeitura.get(Calendar.DAY_OF_YEAR)))+"";
									long dias = Long.parseLong(periodicidadedia);
									    
									String periodicidadehoras =((calNovaLeitura.get(Calendar.HOUR_OF_DAY) < 10)?("0"+calNovaLeitura.get(Calendar.HOUR_OF_DAY)):(calNovaLeitura.get(Calendar.HOUR_OF_DAY)))+"";
									long horas = Long.parseLong(periodicidadehoras);
									
									
									String periodicidademinutos =((calNovaLeitura.get(Calendar.MINUTE) < 10)?("0"+calNovaLeitura.get(Calendar.MINUTE)):(calNovaLeitura.get(Calendar.MINUTE)))+"";
									long minutos = Long.parseLong(periodicidademinutos);
									long total = minutos+(horas*60)+(dias*1440);
					 				
					 				// verifica a data de primeira leitura e compara para setar a nova.
					 				//passando para minutos
					 				
					 				
					 				//Converte para DateTime
					 				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
					 				LocalDateTime dateTime = LocalDateTime.parse(novaLeitura, formatter);
					 				
					 				long minutes = Duration.between(agora, dateTime).toMinutes();
					 				//passando para minutos
					 				periodicidadereal = periodicidadereal/60;
					 				//agendando a Thread
					 				ScheduledFuture<?> importadorAgendamentoSistema = null;
					 				System.out.println("Thread de Nome : "+nome+" - Comeca em "+ minutes +" minutos e roda a cada "+total+" minutos ");
					 				importadorAgendamentoSistema = scheduler.scheduleAtFixedRate(threadSistema(nome, aux, contador, importadorAgendamentoSistema), minutes, total, TimeUnit.MINUTES);
					 				  
				 				}
				 				
				 			 }
				 		    else
				 		    {
				 		    	System.out.println("Nenhum sistema cadastrado");
				 		    	
				 		    	
				 		    }
				 		}
				    	
				    	
			    	}
			    	else
			    	{
			    		// Threads ja foram inicializada parar o inicializador
			    		//System.out.println("Threads Rodando - Contador "+ contador );
			    		importadorAgendamento.cancel(false);
			    		
			    		
			    	}
			        	
			     }

				
				
			};
			
		   //SETA A THREAD PARA RODAR PERIODICAMENTE
			contador++;
		    importadorAgendamento = scheduler.scheduleAtFixedRate(importador, delayInicial, periodicidade, TimeUnit.SECONDS);
		    
		   // ADICIONA A THREAD NA LISTA
		}
		

		
	}


