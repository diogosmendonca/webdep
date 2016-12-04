package br.cefetrj.webdep.services;

import java.util.List;

import javax.persistence.Query;

import br.cefetrj.webdep.model.dao.GenericDAO;
import br.cefetrj.webdep.model.dao.PersistenceManager;
import br.cefetrj.webdep.model.entity.FormatoLog;

public class FormatoLogServices {
	public static void insertFormatoLog(FormatoLog s) {
		PersistenceManager pm = PersistenceManager.getInstance();

		pm.beginTransaction();

		GenericDAO<FormatoLog> dao = pm.createGenericDAO(FormatoLog.class);
		dao.insert(s);

		pm.commitTransaction();

	}
	
	public static List<FormatoLog> listAllFormatoLog() {
		PersistenceManager pm = PersistenceManager.getInstance();
		GenericDAO<FormatoLog> dao = pm.createGenericDAO(FormatoLog.class);
		return dao.listAll();
	}
	
	public static List<FormatoLog> searchFormatoLogByServidor(Long servidorId) {
		PersistenceManager pm = PersistenceManager.getInstance();
		try {
			Query q = pm.createQuery("SELECT fmt FROM FormatoLog fmt, Servidor ser WHERE ser.id = :param AND ser.formatoLog.nome LIKE fmt.nome");
			q.setParameter("param", servidorId);
			return q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<FormatoLog> searchFormatoLog(String s) {
		PersistenceManager pm = PersistenceManager.getInstance();
		try {
			Query q = pm.createQuery("FROM FormatoLog WHERE nome LIKE :param ");
			
			q.setParameter("param", "%"+s+"%");

			return q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
