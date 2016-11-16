package br.cefetrj.webdep.services;

import br.cefetrj.webdep.model.dao.GenericDAO;
import br.cefetrj.webdep.model.dao.PersistenceManager;
import br.cefetrj.webdep.model.entity.Usuario;

public class UsuarioService {

	private static Usuario usuario;
	
	public static Usuario getUsuario(Usuario _usuario){
		
		PersistenceManager pManager = PersistenceManager.getInstance();
		pManager.beginTransaction();
		
		GenericDAO<Usuario> usuarioDAO = pManager.createGenericDAO(Usuario.class);
		usuario = usuarioDAO.get(_usuario.getId());
		
		pManager.commitTransaction();
		
		return usuario;
	}
	
	
}
