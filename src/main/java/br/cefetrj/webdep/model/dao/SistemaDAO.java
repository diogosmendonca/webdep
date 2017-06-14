package br.cefetrj.webdep.model.dao;

import java.util.List;

import javax.persistence.Query;

import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.model.entity.Usuario;

/**
 * Classe responsável pela acesso aos dados da entidade Sistema. 
 * 
 * @author diogo
 * @since 0.1
 *
 */
public class SistemaDAO {

	/**
	 * Lista os sistemas que um determinado usuário tem permissão.
	 * 
	 * @param idUsuario
	 * @return lista de sistemas que o usuário tem permissão.
	 */
	public static List<Sistema> listByPermissaoUsuario(Integer idUsuario){
		List<Sistema> sistemas = null;
		PersistenceManager pm = PersistenceManager.getInstance();
		GenericDAO<Usuario> usuarioDAO = pm.createGenericDAO(Usuario.class);
		Usuario u = usuarioDAO.get(Long.valueOf(idUsuario));
		if(u != null){
			if(u.isAdmGeral()){
				GenericDAO<Sistema> sistemaDAO = pm.createGenericDAO(Sistema.class);
				sistemas = sistemaDAO.listAll();
			}else{
				String query = "SELECT s "
						+ "	FROM Sistema s "
						+ " JOIN FETCH s.permissoes p "
						+ " JOIN FETCH p.usuario u "
						+ " WHERE u.id = " + idUsuario;
				Query q = PersistenceManager.getInstance().createQuery(query);
				sistemas = q.getResultList();
			}
		}
		return sistemas;
	}
	
	
}
