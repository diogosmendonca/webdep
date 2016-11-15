package br.cefetrj.webdep.tests;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.cefetrj.webdep.tests.support.MenuTestSupport;

public class MenuTest {
	
	private WebDriver driver;
	private MenuTestSupport auxMenu;
	
	@Before
	public void inicializa(){
		this.driver = new FirefoxDriver();
		this.auxMenu = new MenuTestSupport(driver);
	}
	
	@Test
	public void cadastrarSistemaLinkDaPrincipalComAdministradorGeral() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario1", "123456");
		auxMenu.clicaLink("Clique aqui para cadastrar");
		boolean paginaCerta = driver.getTitle().contains("Cadastrar Sistema");
		assertTrue(paginaCerta);
	}
	
	@Test
	public void cadastrarSistemaLinkDaPrincipalComAdministrador() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Clique aqui para cadastrar");
		boolean paginaCerta = driver.getTitle().contains("Cadastrar Sistema");
		assertTrue(paginaCerta);
	}
	
	@Test
	public void cadastrarSistemaLinkDaPrincipalComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		WebElement semLink = driver.findElement(By.linkText("Clique aqui para cadastrar"));
		assertNull(semLink);
	}
	
	@After
	public void encerra(){
		this.driver.close();
	}

}
