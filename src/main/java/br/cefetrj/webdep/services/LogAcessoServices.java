package br.cefetrj.webdep.services;

import br.cefetrj.webdep.model.dao.GenericDAO;
import br.cefetrj.webdep.model.dao.PersistenceManager;
import br.cefetrj.webdep.model.entity.RegistroLogAcesso;
import br.cefetrj.webdep.model.entity.Sistema;

import javax.persistence.Query;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by renatoor on 11/25/16.
 */
public class LogAcessoServices {

	

	public static List<RegistroLogAcesso> buscarLog(LocalDateTime ldtInicial, LocalDateTime ldtFinal, String buscar) {
		PersistenceManager pm = PersistenceManager.getInstance();
		Query query;

		if (buscar.equals("")) {
			query = pm.createQuery("from RegistroLogAcesso where timestamp " + "between :inicio and :fim");
		} else {
			try {
				int buscaNum = Integer.parseInt(buscar);
				query = pm.createQuery("from RegistroLogAcesso where " + "codigo=:busca or bytes=:busca "
						+ "and timestamp between :inicio and :fim");
				query.setParameter("busca", buscaNum);
			} catch (Exception e) {
				query = pm.createQuery("from RegistroLogAcesso where " + "ip like :busca or usuario like :busca or "
						+ "url like :busca or origem like :busca or " + "agente like :busca "
						+ "and timestamp between :inicio and :fim");
				query.setParameter("busca", "%" + buscar + "%");
			}
		}

		query.setParameter("inicio", ldtInicial);
		query.setParameter("fim", ldtFinal);

		return query.getResultList();
	}

	public static void excluirLog(String[] idLog) {
		RegistroLogAcesso log;

		if (idLog.length > 0) {
			PersistenceManager pm = PersistenceManager.getInstance();

			pm.beginTransaction();

			GenericDAO<RegistroLogAcesso> dao = pm.createGenericDAO(RegistroLogAcesso.class);

			for (int i = 0; i < idLog.length; i++) {
				log = dao.get(Long.parseLong(idLog[i]));
				dao.delete(log);
			}

			pm.commitTransaction();
		}
	}

	private static void comprimirArquivo(String caminho) {
		
	}

	private static void extrairArquivo(String caminho) {
		// byte[] buffer = new byte[1024];
		try {
			BufferedReader in = null;

			if (verificarExtensao(caminho, ".zip")) {
				// é pra descompactar com ZIP

				ZipInputStream zinstream = new ZipInputStream(new FileInputStream(caminho));
				ZipEntry zentry = zinstream.getNextEntry();

				try {
					in = new BufferedReader(new FileReader(zentry.getName()));
					for (String linha = in.readLine(); linha != null; linha = in.readLine()) {
						
						//aqui salva no banco . Ao percorrer cada linha deve usar as Expressoes regulares e salvar no banco
						RegistroLogAcesso r = new RegistroLogAcesso();
						salvarLog(r);

					}
				} finally {
					if (in != null)
						try {
							in.close();
							zinstream.close();
						} catch (IOException ex) {
						}
				}
			} else {
				// é um texto, lê direto
				try {
					in = new BufferedReader(new FileReader(new File(caminho)));
					for (String linha = in.readLine(); linha != null; linha = in.readLine()) {
						// aqui salva no banco . Ao percorrer cada linha deve usar as Expressoes regulares e salvar no banco
						
						RegistroLogAcesso r = new RegistroLogAcesso();
						salvarLog(r);					
						}
				} finally {
					if (in != null)
						try {
							in.close();
						} catch (IOException ex) {
						}
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void lerArquivoTexto(File arquivo) {

	}

	private static void salvarLog(RegistroLogAcesso log) {

		PersistenceManager pm = PersistenceManager.getInstance();

		pm.beginTransaction();

		GenericDAO<RegistroLogAcesso> dao = pm.createGenericDAO(RegistroLogAcesso.class);
		dao.insert(log);

		pm.commitTransaction();

	}

	/**
	 * @see View.LogProcessor#processa(java.lang.String, Sistema)
	 */
	public void processa(String caminho, Sistema sistema) {

		RegistroLogAcesso r = new RegistroLogAcesso();

		
		extrairArquivo(caminho);

	}

	private static boolean verificarExtensao(String caminho, String extensao) {
		File arquivo = new File(caminho);

		return arquivo.getName().endsWith(extensao);
	}
}
