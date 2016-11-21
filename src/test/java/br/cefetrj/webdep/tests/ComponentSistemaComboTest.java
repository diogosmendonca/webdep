package br.cefetrj.webdep.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import br.cefetrj.webdep.model.dao.GenericDAO;
import br.cefetrj.webdep.model.dao.PersistenceManager;
import br.cefetrj.webdep.model.entity.Permissao;
import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.model.entity.Usuario;

public class ComponentSistemaComboTest {

	private static Long adminGeralId;
	private static Long usuarioNormalId;
	private static WebDriver driver;
		
	@AfterClass
	public static void restoreDbConfig() throws IOException {
		driver.quit();
	}
	
	@BeforeClass
	public static void configureDb() throws IOException {
		
		PersistenceManager pm = PersistenceManager.getInstance();
		pm.beginTransaction();
		
		Sistema s1 = new Sistema();
		s1.setNome("Sistema 1");
		s1.setPastaLogAcesso("/teste/");
		s1.setPrefixoLogAcesso("log");
		s1.setPastaLogErro("/teste/");
		s1.setPrefixoLogErro("log");
		s1.setPrimeiraLeitura(LocalDateTime.now());
		s1.setPeriodicidadeLeitura(1L);
		
		Sistema s2 = new Sistema();
		s2.setNome("Sistema 2");
		s2.setPastaLogAcesso("/teste/");
		s2.setPrefixoLogAcesso("log");
		s2.setPastaLogErro("/teste/");
		s2.setPrefixoLogErro("log");
		s2.setPrimeiraLeitura(LocalDateTime.now());
		s2.setPeriodicidadeLeitura(1L);
		
		Usuario u1 = new Usuario();
		u1.setNome("Admin Geral");
		u1.setLogin("admgeral");
		u1.setSenha("admgeral");
		u1.setPerfil("admin");
		u1.setAdmGeral(true);
		u1.setEmail("admin@admin.com");
		
		Usuario u2 = new Usuario();
		u2.setNome("Admin");
		u2.setLogin("adm");
		u2.setSenha("adm");
		u2.setPerfil("admin");
		u2.setAdmGeral(false);
		u2.setEmail("admin1@admin.com");
		
		Permissao p1 = new Permissao();
		p1.setSistema(s1);
		p1.setUsuario(u2);
		
		u2.setPermissoes(Arrays.asList(new Permissao[]{p1}));
		
		GenericDAO<Usuario> usuarioDao = pm.createGenericDAO(Usuario.class);
		GenericDAO<Sistema> sistemaDao = pm.createGenericDAO(Sistema.class);
		GenericDAO<Permissao> permissaoDao = pm.createGenericDAO(Permissao.class);
		
		sistemaDao.insert(s1);
		sistemaDao.insert(s2);
		
		usuarioDao.insert(u1);
		usuarioDao.insert(u2);
		
		permissaoDao.insert(p1);
		
		pm.commitTransaction();
		
		adminGeralId = u1.getId();
		usuarioNormalId = u2.getId();
		
        if (System.getProperty("os.name").contains("Windows")){
        	System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\geckodriver.exe");
        }else{
        	System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
        }
        
        driver = new FirefoxDriver();
		
	}
	
	@Test
	public void adminGeralTest(){
		
        driver.get("http://localhost:8080/webdep/comboSistemaTestDriver.jsp?id=" + adminGeralId);
        
        boolean sistema1Encontrado = false;
        boolean sistema2Encontrado = false;
        
        Select seletor = new Select(driver.findElement(By.id("sistema")));
        List<WebElement> opcoes = seletor.getOptions();
		for (WebElement webElement : opcoes) {
			if(webElement.getText().contains("Sistema 1")){
				sistema1Encontrado = true;
			}
			if(webElement.getText().contains("Sistema 2")){
				sistema2Encontrado = true;
			}
		}
		
		assertEquals("Sistema não encontrado em perfil com acesso a ele", true, sistema1Encontrado);
		assertEquals("Sistema não encontrado em perfil com acesso a ele", true, sistema2Encontrado);
        
	}
	
	@Test
	public void usuarioComumTest(){
		
        driver.get("http://localhost:8080/webdep/comboSistemaTestDriver.jsp?id=" + usuarioNormalId);
        
        boolean sistema1Encontrado = false;
        boolean sistema2Encontrado = false;
        
        Select seletor = new Select(driver.findElement(By.id("sistema")));
        List<WebElement> opcoes = seletor.getOptions();
		for (WebElement webElement : opcoes) {
			if(webElement.getText().contains("Sistema 1")){
				sistema1Encontrado = true;
			}
			if(webElement.getText().contains("Sistema 2")){
				sistema2Encontrado = true;
			}
		}
		
		assertEquals("Sistema não encontrado em perfil com acesso a ele", true, sistema1Encontrado);
		assertEquals("Sistema encontrado em perfil sem acesso a ele", false, sistema2Encontrado);
        
	}

}
