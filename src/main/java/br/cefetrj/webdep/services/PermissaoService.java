package br.cefetrj.webdep.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.cefetrj.webdep.model.dao.GenericDAO;
import br.cefetrj.webdep.model.dao.PersistenceManager;
import br.cefetrj.webdep.model.entity.Permissao;
import br.cefetrj.webdep.view.command.ListarPermissaoUsuarioCommand;


public class PermissaoService {

public static void insertPermissao(Permissao _permissao){
		
		PersistenceManager pManager = PersistenceManager.getInstance();
		pManager.beginTransaction();
		
		GenericDAO<Permissao> permissaoDAO = pManager.createGenericDAO(Permissao.class);
		permissaoDAO.insert(_permissao);
		
		pManager.commitTransaction();
		
			
	}

	public static List<Permissao> PermissaoUsuario (Long id){
		PersistenceManager pManager = PersistenceManager.getInstance();
		pManager.beginTransaction();
		
		GenericDAO<Permissao> permissaoDAO = pManager.createGenericDAO(Permissao.class);
		
		List<Permissao> list = permissaoDAO.listAll();
		List<Permissao> listUsuarioPermissao = new ArrayList<Permissao>(); 
		for (Permissao permissao : list) {
			
			if(permissao.getUsuario().getId()==id){
				listUsuarioPermissao.add(permissao);
				
			}
		}
		
		pManager.commitTransaction();
		
		return listUsuarioPermissao;
		
		
	}
	
}
