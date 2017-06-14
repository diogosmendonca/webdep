package br.cefetrj.webdep.model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.cefetrj.webdep.model.entity.ConfiguracaoSistema;

/**
 * Classe que gerencia o acesso aos dados do persistence.xml e config.properties.
 * 
 * @author diogo
 * @since 0.1
 */
public class ConfigurationDAO {
	
	/**
	 * Enum que define os tipos de banco de dados possíveis.
	 * @author diogo
	 * @since 0.1
	 */
	public static enum DatabaseType {HSQL, MySQL, Postgres};
	
	/**
	 * Carrega as configurações do config.properties para a entidade ConfiguracaoSistema
	 * 
	 * @param pathConfigFile caminho para o arquivo de configuração config.properties
	 * @return configurações do sistema
	 * @author diogo
	 * @since 0.1
	 */
	public static ConfiguracaoSistema load(String pathConfigFile){
		Properties p = new Properties();
		ConfiguracaoSistema config = null;
		try (FileInputStream fis = new FileInputStream(new File(pathConfigFile))){
			p.load(fis);
			
			config = new ConfiguracaoSistema();
			config.setBanco(p.getProperty("banco"));
			config.setUrlBanco(p.getProperty("urlBanco"));
			config.setUsuarioBanco(p.getProperty("usuarioBanco"));
			config.setSenhaBanco(p.getProperty("senhaBanco"));
			config.setProvedorSmtp(p.getProperty("provedorSmtp"));
			config.setUsuarioEmail(p.getProperty("usuarioEmail"));
			config.setSenhaEmail(p.getProperty("senhaEmail"));
			
		} catch (IOException e) {
			Logger lg = Logger.getLogger(ConfigurationDAO.class);
        	lg.error("Impossible to load config.propertes", e);
		}
		
		return config;
	}
	
	/**
	 * Armazena as os valores da entidade ConfiguracaoSistema no arquivo config.properties
	 * 
	 * @param config configurações do sistema
	 * @param pathConfigFile caminho para o arquivo de configuração config.properties
	 * @return se o armazenamento foi bem sucedido
	 * 
	 * @author diogo
	 * @since 0.1
	 */
	public static boolean store(ConfiguracaoSistema config, String pathConfigFile){
		
		if (config == null)
			throw new IllegalArgumentException("config parameter cannot be null");
		
		Properties p = new Properties();
		boolean status = false;
		
		try (FileOutputStream fos = new FileOutputStream(new File(pathConfigFile))){
			
			p.setProperty("banco", config.getBanco());
			p.setProperty("urlBanco", config.getUrlBanco());
			p.setProperty("usuarioBanco", config.getUsuarioBanco());
			p.setProperty("senhaBanco", config.getSenhaBanco());
			p.setProperty("provedorSmtp", config.getProvedorSmtp());
			p.setProperty("usuarioEmail", config.getUsuarioEmail());
			p.setProperty("senhaEmail", config.getSenhaEmail());
			
			p.store(fos, "");
			status = true;
		} catch (IOException e) {
			Logger lg = Logger.getLogger(ConfigurationDAO.class);
        	lg.error("Impossible to store config.propertes", e);
		}
		return status;
	}
	
	/**
	 * Grava as configurações de banco no pesistence unit do banco selecionado.
	 * 
	 * @param persistencePath caminho para o arquivo persistence.xml
	 * @param database o tipo do banco de dados que se quer gravar a configuração
	 * @param url url de acesso ao banco
	 * @param username nome de usuário de acesso ao banco
	 * @param password senha de acesso ao banco
	 * @return se a gravação ocorreu com sucesso
	 * 
	 * @author diogo
	 * @since 0.1
	 */
	public static boolean changeDatabaseConfig(String persistencePath, DatabaseType database, String url, String username, String password){
		
		if (persistencePath == null)
			throw new IllegalArgumentException("Persistence path parameter cannot be null");
		
		if (database == null)
			throw new IllegalArgumentException("Database parameter cannot be null");
		
		if (url == null)
			throw new IllegalArgumentException("Url parameter cannot be null");
		
		if (username == null)
			throw new IllegalArgumentException("Username parameter cannot be null");
		
		if (password == null)
			throw new IllegalArgumentException("Password parameter cannot be null");
		
		String pu = null;
		
		if(database == DatabaseType.HSQL){
			pu = "WebDepHSQLDBPU";
		}else if (database == DatabaseType.MySQL){
			pu = "WebDepMYSQLDBPU";
		}else if (database == DatabaseType.Postgres){
			pu = "WebDepPOSTGRESDBPU";
		}else{
			throw new IllegalArgumentException("No option available for database parameter " + database);
		}
		
		File inputFile = new File(persistencePath);
        DocumentBuilderFactory dbFactory 
           = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        
        try{
	        dBuilder = dbFactory.newDocumentBuilder();
	
	        Document doc = dBuilder.parse(inputFile);
	        doc.getDocumentElement().normalize();
	
	        XPath xPath =  XPathFactory.newInstance().newXPath();
	
	        String XPathQuery = "//persistence-unit[@name=\"" + pu + "\"]//property";	        
	        NodeList nodeList = (NodeList) xPath.compile(XPathQuery).evaluate(doc, XPathConstants.NODESET);
	        for (int i = 0; i < nodeList.getLength(); i++) {
	           Node nNode = nodeList.item(i);
	           if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               if(eElement.getAttribute("name").equals("javax.persistence.jdbc.url")){
	            	   eElement.setAttribute("value", url);
	               }else if (eElement.getAttribute("name").equals("javax.persistence.jdbc.user")){
	            	   eElement.setAttribute("value", username);
	               }else if (eElement.getAttribute("name").equals("javax.persistence.jdbc.password")){
	            	   eElement.setAttribute("value", password);
	               }
	           }
	        }
	        
	        // Use a Transformer for output
	        TransformerFactory tFactory =
	        TransformerFactory.newInstance();
	        Transformer transformer = tFactory.newTransformer();
	
	        DOMSource source = new DOMSource(doc);
	        StreamResult result = new StreamResult(inputFile);
	        transformer.transform(source, result);
	        
	        
        }catch(Exception e){
        	Logger lg = Logger.getLogger(ConfigurationDAO.class);
        	lg.error("Impossible to configure the persistence.xml", e);
        	return false;
        }
		
		return true;
	}
	
	/**
	 * Recupera o valor de uma propriedade da configuração de um banco de dados.
	 * 
	 * @param persistencePath caminho para o perisistence.xml
	 * @param database o tipo do banco de dados de configuração
	 * @param propertyName nome da propriedade que se quer recuperar 
	 * @return valor da propriedade no xml
	 */
	public static String getPropertyValue(String persistencePath, DatabaseType database, String propertyName){
		
		if (persistencePath == null)
			throw new IllegalArgumentException("Persistence path parameter cannot be null");
		
		if (database == null)
			throw new IllegalArgumentException("Database parameter cannot be null");
		
		String pu = null;
		
		if(database == DatabaseType.HSQL){
			pu = "WebDepHSQLDBPU";
		}else if (database == DatabaseType.MySQL){
			pu = "WebDepMYSQLDBPU";
		}else if (database == DatabaseType.Postgres){
			pu = "WebDepPOSTGRESDBPU";
		}else{
			throw new IllegalArgumentException("No option available for database parameter " + database);
		}
		
		File inputFile = new File(persistencePath);
        DocumentBuilderFactory dbFactory 
           = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        
        String value = null;
        
        try{
	        dBuilder = dbFactory.newDocumentBuilder();
	
	        Document doc = dBuilder.parse(inputFile);
	        doc.getDocumentElement().normalize();
	
	        XPath xPath =  XPathFactory.newInstance().newXPath();
	
	        String XPathQuery = "string(//persistence-unit[@name=\"" + pu + 
					"\"]//property[@name=\"" + propertyName + "\"]/@value)";	        
	        value = (String) xPath.compile(XPathQuery).evaluate(doc, XPathConstants.STRING);
        }catch(Exception e){
        	Logger lg = Logger.getLogger(ConfigurationDAO.class);
        	lg.error("Impossible to get property value form persistence.xml", e);
        }
		
		return value;
	}

}
