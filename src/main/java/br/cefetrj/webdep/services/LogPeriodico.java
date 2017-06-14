package br.cefetrj.webdep.services;

	import java.io.IOException;
	import java.io.PrintWriter;
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
				
				// sempre inicializa a thread quando o banco é modificado ou quando  a pessoa loga.
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
								//System.out.println("Execucao da Thread de Nome : "+nome);
							}
							else
							{
								//System.out.println("Thread - "+aux+" Interrompida");
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
				 			//System.out.println("Esperando sessao para iniciar Thread de Limpeza Periodica de Log");
				 			
				 		}
				 		else
				 		{
				 			//Checa se existe algum sistema valido cadastrado
				 			if (sistemasFiltrados.size() > 0)
				 		    {
				 				// Pega toda a tabela de BD para verificar quem esta rodando
				 				for (Sistema sistema : sistemasFiltrados)
				 				{
				 					// Se nenhuma thread foi inciada
					 				//System.out.println("Iniciando a Thread do "+sistema.getNome());
					 				String nome = sistema.getNome();
					 				long aux = sistema.getId();
					 				long periodicidade = (aux*3)+1;
					 				ScheduledFuture<?> importadorAgendamentoSistema = null;
					 				//System.out.println("Thread de Nome : "+nome+" - Programada para a cada "+periodicidade+" segundos ");
					 				importadorAgendamentoSistema = scheduler.scheduleAtFixedRate(threadSistema(nome, aux, contador, importadorAgendamentoSistema), delayInicial, periodicidade, TimeUnit.SECONDS);
					 				  
				 				}
				 			   	threadon=true;
				 			 }
				 		    else
				 		    {
				 		    	// Nenhum sistema cadastrado
				 		    	//System.out.println("Nenhum sistema cadastrado");
				 		    	threadon=true;
				 		    	
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


