package br.cefetrj.webdep.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.junit.Before;
import org.junit.Test;

import br.cefetrj.webdep.model.dao.ConfigurationDAO;
import br.cefetrj.webdep.model.dao.ConfigurationDAO.DatabaseType;

public class PersistenceConfigurationTest {

	@Before
	public void createCleanFile(){
		Path source = Paths.get("src/test/resources/persistence-clean-template.xml");
	    Path destination = Paths.get("src/test/resources/persistence.xml");
	 
	    try {
			Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void configHSQLTest() {
		
		String filePath = "src/test/resources/persistence.xml";
		String url = "url1";
		String username = "name1";
		String password = "pass1";
		
		ConfigurationDAO.changeDatabaseConfig(filePath, DatabaseType.HSQL, url, username, password);
		
		String urlFileValue = ConfigurationDAO.getPropertyValue(filePath, DatabaseType.HSQL, "javax.persistence.jdbc.url");
		String userNameFileValue = ConfigurationDAO.getPropertyValue(filePath, DatabaseType.HSQL, "javax.persistence.jdbc.user");
		String passwordFileValue = ConfigurationDAO.getPropertyValue(filePath, DatabaseType.HSQL, "javax.persistence.jdbc.password");
		
		assertEquals(url, urlFileValue);
		assertEquals(username, userNameFileValue);
		assertEquals(password, passwordFileValue);
	}
	

	@Test
	public void configMySQLTest() {
		
		String filePath = "src/test/resources/persistence.xml";
		String url = "url2";
		String username = "name2";
		String password = "pass2";
		
		ConfigurationDAO.changeDatabaseConfig(filePath, DatabaseType.MySQL, url, username, password);
		
		String urlFileValue = ConfigurationDAO.getPropertyValue(filePath, DatabaseType.MySQL, "javax.persistence.jdbc.url");
		String userNameFileValue = ConfigurationDAO.getPropertyValue(filePath, DatabaseType.MySQL, "javax.persistence.jdbc.user");
		String passwordFileValue = ConfigurationDAO.getPropertyValue(filePath, DatabaseType.MySQL, "javax.persistence.jdbc.password");
		
		assertEquals(url, urlFileValue);
		assertEquals(username, userNameFileValue);
		assertEquals(password, passwordFileValue);
	}
	
	@Test
	public void configPostgresTest() {
		
		String filePath = "src/test/resources/persistence.xml";
		String url = "url3";
		String username = "name3";
		String password = "pass3";
		
		ConfigurationDAO.changeDatabaseConfig(filePath, DatabaseType.Postgres, url, username, password);
		
		String urlFileValue = ConfigurationDAO.getPropertyValue(filePath, DatabaseType.Postgres, "javax.persistence.jdbc.url");
		String userNameFileValue = ConfigurationDAO.getPropertyValue(filePath, DatabaseType.Postgres, "javax.persistence.jdbc.user");
		String passwordFileValue = ConfigurationDAO.getPropertyValue(filePath, DatabaseType.Postgres, "javax.persistence.jdbc.password");
		
		assertEquals(url, urlFileValue);
		assertEquals(username, userNameFileValue);
		assertEquals(password, passwordFileValue);
	}

}