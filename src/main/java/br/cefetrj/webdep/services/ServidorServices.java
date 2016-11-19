package br.cefetrj.webdep.services;

import java.util.List;

import javax.persistence.Query;

import br.cefetrj.webdep.model.dao.GenericDAO;
import br.cefetrj.webdep.model.dao.PersistenceManager;
import br.cefetrj.webdep.model.entity.Servidor;
import br.cefetrj.webdep.model.entity.Sistema;

public class ServidorServices {
	public static void insertServidor(Servidor s) {
		PersistenceManager pm = PersistenceManager.getInstance();

		pm.beginTransaction();

		GenericDAO<Servidor> dao = pm.createGenericDAO(Servidor.class);
		dao.insert(s);

		pm.commitTransaction();

	}
	
	public static List<Servidor> listAllServidor() {
		PersistenceManager pm = PersistenceManager.getInstance();
		GenericDAO<Servidor> dao = pm.createGenericDAO(Servidor.class);
		return dao.listAll();
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
}
