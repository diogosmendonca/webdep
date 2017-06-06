package br.cefetrj.webdep.model.dao;

import java.util.List;

import javax.persistence.Query;

import br.cefetrj.webdep.model.entity.RegistroLogAcesso;
import br.cefetrj.webdep.model.entity.Sistema;

public class RegistroLogAcessoDAO {
	
	/**
	 * Busca os códigos http distintos de um sistema em seu registro de log
	 * de aesso.
	 * 
	 * @param s Sistema
	 * @return Lista de registro de log acesso contendo somente o campo código 
	 * preechido com os códigos HTTP distintos
	 */
	public static List<Integer> listCodigosBySistema(Sistema s){
		PersistenceManager pm = PersistenceManager.getInstance();
		
		Query query = pm.createQuery(
				 "SELECT DISTINCT r.codigo "
				+ "	FROM RegistroLogAcesso r "
				+ " WHERE r.sistema = :sistema ");
		
		query.setParameter("sistema", s);
		
		return query.getResultList();
	}

}
