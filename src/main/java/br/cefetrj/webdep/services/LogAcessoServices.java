package br.cefetrj.webdep.services;

import br.cefetrj.webdep.model.dao.GenericDAO;
import br.cefetrj.webdep.model.dao.PersistenceManager;
import br.cefetrj.webdep.model.entity.RegistroLogAcesso;
import br.cefetrj.webdep.model.entity.RegistroLogErro;

import javax.persistence.Query;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

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
    
	private static void salvarLog(RegistroLogAcesso log) {
	    	
	    	PersistenceManager pm = PersistenceManager.getInstance();
	
			pm.beginTransaction();
	
			GenericDAO<RegistroLogAcesso> dao = pm.createGenericDAO(RegistroLogAcesso.class);
			dao.insert(log);
	
			pm.commitTransaction();
	
	    }
	    
	    private static void comprimirArquivo(String caminho) {
	    	byte[] buffer = new byte[1024];
			try {
				// Stream de saida
				FileOutputStream fos = new FileOutputStream(caminho);

				// Zip de saida
				ZipOutputStream zos = new ZipOutputStream(fos);

				// Arquivo a ser zipdo
				ZipEntry ze = new ZipEntry("nwmonitor.sqlite");

				// Adciona arquivo no Zip de saida
				zos.putNextEntry(ze);

				// Ler o Arquivo que sera Zipado
				FileInputStream in = new FileInputStream("nwmonitor.sqlite");

				int len;
				while ((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}

				// Fecha Arquivos
				in.close();

				// Fecha Zip e entrada
				zos.closeEntry();
				zos.close();

				System.out.println("Done");

			} catch (IOException ex) {
				ex.printStackTrace();
			}
	    }


		private static void extrairArquivo(String caminho) {
			byte[] buffer = new byte[1024];
			try {
				// Cria o input do arquivo ZIP
				ZipInputStream zinstream = new ZipInputStream(new FileInputStream(caminho));

				// Pega a proxima entrada do arquivo
				ZipEntry zentry = zinstream.getNextEntry();

				// Enquanto existir entradas no ZIP
				while (zentry != null) {
					// Pega o nome da entrada
					String entryName = zentry.getName();

					// Cria o output do arquivo , Sera extraido onde esta rodando a classe
					FileOutputStream outstream = new FileOutputStream(entryName);
					int n;

					// Escreve no arquivo
					while ((n = zinstream.read(buffer)) > -1) {
						outstream.write(buffer, 0, n);

					}

					// Fecha arquivo
					outstream.close();

					// Fecha entrada e tenta pegar a proxima
					zinstream.closeEntry();
					zentry = zinstream.getNextEntry();
				}

				// Fecha o zip como um todo
				zinstream.close();

				System.out.println("Done");
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	
		private void lerArquivoTexto(File arquivo) {
			
		}
}
		

