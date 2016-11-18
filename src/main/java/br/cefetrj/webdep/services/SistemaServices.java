package br.cefetrj.webdep.services;

import java.util.List;

import javax.persistence.Query;

import br.cefetrj.webdep.model.dao.GenericDAO;
import br.cefetrj.webdep.model.dao.PersistenceManager;
import br.cefetrj.webdep.model.entity.Servidor;
import br.cefetrj.webdep.model.entity.Sistema;

public class SistemaServices {
	public static void insertSistema(Sistema s) {
		PersistenceManager pm = PersistenceManager.getInstance();

		pm.beginTransaction();

		GenericDAO<Sistema> dao = pm.createGenericDAO(Sistema.class);
		dao.insert(s);

		pm.commitTransaction();

	}
	
	public static List<Servidor> searchServidor(String s) {
		PersistenceManager pm = PersistenceManager.getInstance();
		try {
			Query q = pm.createQuery("FROM Servidor WHERE nome LIKE :param ");
			
			q.setParameter("param", "%"+s+"%");

			return q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Sistema> searchSistema(String s) {
		PersistenceManager pm = PersistenceManager.getInstance();
		try {
			Query q = pm.createQuery("FROM Sistema WHERE nome LIKE :param "
					+ " OR servidor LIKE :param ");
			
			q.setParameter("param", "%"+s+"%");

			return q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
