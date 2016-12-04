package br.cefetrj.webdep.services;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Query;

import br.cefetrj.webdep.model.dao.GenericDAO;
import br.cefetrj.webdep.model.dao.PersistenceManager;
import br.cefetrj.webdep.model.dao.SistemaDAO;
import br.cefetrj.webdep.model.entity.RegistroLogAcesso;
import br.cefetrj.webdep.model.entity.Servidor;
import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.model.entity.Usuario;

public class SistemaServices {
	public static void insertSistema(Sistema s) {
		PersistenceManager pm = PersistenceManager.getInstance();

		pm.beginTransaction();

		GenericDAO<Sistema> dao = pm.createGenericDAO(Sistema.class);
		dao.insert(s);

		pm.commitTransaction();

	}
	
	public static void updateSistema(Sistema s) {
		PersistenceManager pm = PersistenceManager.getInstance();

		pm.beginTransaction();

		GenericDAO<Sistema> dao = pm.createGenericDAO(Sistema.class);
		dao.update(s);

		pm.commitTransaction();

	}
	
	public static void deleteSistema(Sistema s){
		PersistenceManager pm = PersistenceManager.getInstance();
		pm.beginTransaction();
		GenericDAO<Sistema> dao = pm.createGenericDAO(Sistema.class);
		dao.delete(s);
		pm.commitTransaction();
	}
	
	public static List<Sistema> searchSistema(String s) {
		PersistenceManager pm = PersistenceManager.getInstance();
		try {
			Query q = pm.createQuery("FROM Sistema sis WHERE sis.nome LIKE :param "
					+ " OR (sis.servidor.nome LIKE :param )"
					+ " OR (sis.servidor.formatoLog.nome LIKE :param )");
			
			q.setParameter("param", "%"+s+"%");

			return q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Lista os sistemas que um determinado usuário tem permissão.
	 * 
	 * @param idUsuario
	 * @return lista de sistemas que o usuário tem permissão.
	 */
	public static List<Sistema> listByPermissaoUsuario(Integer idUsuario){
		return SistemaDAO.listByPermissaoUsuario(idUsuario);
	}
	
	
	
	public static Sistema obterPorId(Long id){
		Sistema sistema = null;
		PersistenceManager pManager = PersistenceManager.getInstance();
		try {
			pManager.beginTransaction();
			
			GenericDAO<Sistema> permissaoDAO = pManager.createGenericDAO(Sistema.class);
			sistema = permissaoDAO.get(id);
			
			pManager.commitTransaction();
		} catch (Exception e) {
			pManager.rollbackTransaction();
		}
		
		return 	sistema;
	}
	
	public static List<Sistema> listarTodos(){
		List<Sistema> sistemas = null;
		PersistenceManager pManager = PersistenceManager.getInstance();
		try {
			pManager.beginTransaction();
			
			GenericDAO<Sistema> permissaoDAO = pManager.createGenericDAO(Sistema.class);
			sistemas = permissaoDAO.listAll();
			
			pManager.commitTransaction();
		} catch (Exception e) {
			pManager.rollbackTransaction();
		}
		
		return 	sistemas;			
	}
	
}
