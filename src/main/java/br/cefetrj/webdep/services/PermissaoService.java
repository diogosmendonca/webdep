package br.cefetrj.webdep.services;

import br.cefetrj.webdep.model.dao.GenericDAO;
import br.cefetrj.webdep.model.dao.PersistenceManager;
import br.cefetrj.webdep.model.entity.Permissao;


public class PermissaoService {

public static void insertPermissao(Permissao _permissao){
		
		PersistenceManager pManager = PersistenceManager.getInstance();
		pManager.beginTransaction();
		
		GenericDAO<Permissao> permissaoDAO = pManager.createGenericDAO(Permissao.class);
		permissaoDAO.insert(_permissao);
		
		pManager.commitTransaction();
		
			
	}
	
}
