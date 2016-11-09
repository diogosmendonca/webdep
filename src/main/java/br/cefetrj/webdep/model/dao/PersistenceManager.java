package br.cefetrj.webdep.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManager {
		
	private EntityManagerFactory emFactory;
	private EntityManager manager;
	
	private static PersistenceManager instance;
	
	private PersistenceManager(){
		this.emFactory = Persistence.createEntityManagerFactory("WebDepHSQLDBPU");
		this.manager = emFactory.createEntityManager();
	}
	
	public static PersistenceManager getInstance(){
		if(instance == null)
			instance = new PersistenceManager();
		return instance;
	}
	
	public <T> GenericDAO<T> createGenericDAO(Class<T> t){
		return new GenericDAO<T>(t, manager);
	}
	
	public void beginTransaction(){
		this.manager.getTransaction().begin();
	}
	
	public void commitTransaction(){
		this.manager.getTransaction().commit();
	}
	
	public void rollbackTransaction(){
		this.manager.getTransaction().rollback();
	}
	
	@Override
	public void finalize(){
		this.manager.close();
		this.emFactory.close();
	}

}
