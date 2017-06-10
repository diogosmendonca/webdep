package br.cefetrj.webdep.model.dao;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import br.cefetrj.webdep.model.entity.ConfiguracaoSistema;

public class PersistenceManager {

	private EntityManagerFactory emFactory;
	private EntityManager manager;

	private static PersistenceManager instance;

	private PersistenceManager() {
		String pathToProperties = this.getClass().getClassLoader().getResource("config.properties").getPath();
		try {
			pathToProperties = URLDecoder.decode(pathToProperties, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			Logger lg = Logger.getLogger(PersistenceManager.class);
			lg.error("Não foi possível decodificar o path do config.properties", e);
		}
		// configuração de produção
		ConfiguracaoSistema cs = ConfigurationDAO.load(pathToProperties);

		String pu = null;
		if (cs != null) {
			String database = cs.getBanco();
			if (database.equals("mysql")) {
				pu = "WebDepMYSQLDBPU";
			} else if (database.equals("postgres")) {
				pu = "WebDepPOSTGRESDBPU";
			} else {
				pu = "WebDepHSQLDBPU";
			}
		} else {
			pu = "WebDepHSQLDBPU";
		}
		this.emFactory = Persistence.createEntityManagerFactory(pu);
		this.manager = emFactory.createEntityManager();
	}

	public static PersistenceManager getInstance() {
		if (instance == null)
			instance = new PersistenceManager();
		return instance;
	}

	public <T> GenericDAO<T> createGenericDAO(Class<T> t) {
		return new GenericDAO<T>(t, manager);
	}

	public void beginTransaction() {
		this.manager.getTransaction().begin();
	}

	public void commitTransaction() {
		this.manager.getTransaction().commit();
	}

	public void rollbackTransaction() {
		this.manager.getTransaction().rollback();
	}

	public Query createQuery(String query) {
		return this.manager.createQuery(query);
	}

	public Query createNativeQuery(String query) {
		return this.manager.createNativeQuery(query);
	}
	
	public Query createNativeQuery(String query, Class classe) {
		return this.manager.createNativeQuery(query, classe);
	}
	
	public static void resetInstance() {
		instance = null;
	}

	@Override
	public void finalize() {
		if (this.manager != null)
			this.manager.close();

		if (this.emFactory != null)
			this.emFactory.close();

		instance = null;
	}

}
