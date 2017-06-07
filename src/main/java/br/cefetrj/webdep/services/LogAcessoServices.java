package br.cefetrj.webdep.services;

import br.cefetrj.webdep.model.dao.GenericDAO;
import br.cefetrj.webdep.model.dao.PersistenceManager;
import br.cefetrj.webdep.model.entity.RegistroLogAcesso;
import br.cefetrj.webdep.model.entity.Sistema;

import javax.persistence.Query;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by renatoor on 11/25/16.
 */
public class LogAcessoServices {

    public static List<RegistroLogAcesso> buscarLog(LocalDateTime ldtInicial, LocalDateTime ldtFinal, String buscar) {
        PersistenceManager pm = PersistenceManager.getInstance();
        Query query;

        if(buscar.equals("")) {
            query = pm.createQuery("from RegistroLogAcesso where timestamp " +
                    "between :inicio and :fim");
        }
        else {
            try {
                int buscaNum = Integer.parseInt(buscar);
                query = pm.createQuery("from RegistroLogAcesso where " +
                        "codigo=:busca or bytes=:busca " +
                        "and timestamp between :inicio and :fim");
                query.setParameter("busca", buscaNum);
            } catch (Exception e) {
                query = pm.createQuery("from RegistroLogAcesso where " +
                        "ip like :busca or usuario like :busca or " +
                        "url like :busca or origem like :busca or " +
                        "agente like :busca " +
                        "and timestamp between :inicio and :fim");
                query.setParameter("busca", "%" + buscar + "%");
            }
        }

        query.setParameter("inicio", ldtInicial);
        query.setParameter("fim", ldtFinal);

        return query.getResultList();
    }

    public static void excluirLog(String[] idLog) {
        RegistroLogAcesso log;

        if(idLog.length > 0) {
            PersistenceManager pm = PersistenceManager.getInstance();

            pm.beginTransaction();

            GenericDAO<RegistroLogAcesso> dao = pm.createGenericDAO(RegistroLogAcesso.class);

            for(int i = 0; i < idLog.length; i++) {
                log = dao.get(Long.parseLong(idLog[i]));
                dao.delete(log);
            }

            pm.commitTransaction();
        }
    }
    
    
public static void ImportarLogAcesso(Sistema s) {
		
		File file = new File(s.getPastaLogAcesso());		
		File afile[] = file.listFiles();
		int i = 0;				
		
		String diretorio = s.getPastaLogAcesso();
		RegistroLogAcesso logAcesso;		

		if(!(Character.toString(diretorio.charAt(diretorio.length() -1))).equals("\\")){
			diretorio = diretorio + "\\";
		}
		
//		System.out.println("Diretorio convertido: " +diretorio);
		String strPrefixo = s.getPrefixoLogAcesso();
//		String strFormatoLog = s.getFomatoLog();
		String strFormatoLog = "common";
		
		for (int j = afile.length; i < j; i++) {
			File arquivo = afile[i];			
			if (arquivo.getName().substring(arquivo.getName().lastIndexOf("."), arquivo.getName().length()).equals(".txt")
					&& arquivo.getName().substring(0, strPrefixo.length()).equals(strPrefixo)){
				
				System.out.println(arquivo.getName());
				
				try{
					   FileInputStream fstream = new FileInputStream(diretorio +arquivo.getName());
					   BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
					   String linhaLog;
					   /* read log line by line */
					   while ((linhaLog = br.readLine()) != null)   {
						   System.out.println (linhaLog);
						   Pattern pLog = null;
					     	if(strFormatoLog.equals("common")) {
					     		pLog = Pattern.compile("\\b(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\b - (.*?) (.*? .*?) (.*? .*?) (\\d+?) (\\d+?)");
					     	}else if(strFormatoLog.equals("combined")){
					     		pLog = Pattern.compile("\\b(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\b - (.*?) (.*? .*?) (.*? .*?) (\\d+?) (\\d+?) (\\d+?) (\\d+?) (\\d+?)");
					     	}
					     	
							Matcher mLog = pLog.matcher(linhaLog);
									
							System.out.println("matches = " + mLog.matches());

							if(mLog.matches()){
								String ip1 = mLog.group(1);
								String ip2 = mLog.group(2);
								String ip3 = mLog.group(3);
								String ip4 = mLog.group(4);
								String usuario = mLog.group(5);
								String dataHora = mLog.group(6);
								dataHora = dataHora.substring(1, dataHora.length()-1);
								String url = mLog.group(7);
								String codigo = mLog.group(8);
								String bytes = mLog.group(9);
								String agente = "";
								String origem = "";
								if(strFormatoLog.equals("combined")){
									agente = mLog.group(10);
									origem = mLog.group(11);
								}
								
								String ip = ip1 + "." + ip2 + "." + ip3 + "." + ip4;

								System.out.println("Linha Log: " +linhaLog);
								
								//Define Registro Log Acesso
								logAcesso  = new RegistroLogAcesso();
								logAcesso.setSistema(s);
								
								logAcesso.setIp(ip);
								logAcesso.setUsuario(usuario);
															
								dataHora.replace('/', '-');
								System.out.println(dataHora);
								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy:HH:mm:ss Z", new Locale("en", "US"));
								LocalDateTime dateTime = LocalDateTime.parse(dataHora, formatter);						
								logAcesso.setTimestamp(dateTime);								
								logAcesso.setUrl(url);
								logAcesso.setCodigo(Integer.parseInt(codigo));
								logAcesso.setBytes(Integer.parseInt(bytes));
								//somente para formato combined
								logAcesso.setAgente(agente);
								logAcesso.setOrigem(origem);															

								RegistroLogAcessoService.insertRegistroLogAcesso(logAcesso);
								
								System.out.println("IP: " + ip);
								System.out.println("Usuário: " + usuario);
								System.out.println("Data Hora: " + dataHora);
								System.out.println("URL: " + url);
								System.out.println("Codigo: " + codigo);
								System.out.println("Bytes: " + bytes);
								System.out.println("");		
								
								
								
							}else{
								System.out.println("NÃ£o bate!");
							}														    					     
					   }
					   br.close();	
				}catch(Exception e){
					System.out.println(e.getStackTrace().toString());
				}
					
			}
		}

	}	
}
