package br.cefetrj.webdep.teste;

import br.cefetrj.webdep.model.dao.PersistenceManager;

public class TesteConexao {

	public static void main (String [] args){
				
		PersistenceManager pManager = PersistenceManager.getInstance();
		pManager.beginTransaction();
		
		pManager.commitTransaction();
		pManager.finalize();
	}
	
	
}
