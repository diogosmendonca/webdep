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
	
	@Test
	public void selecionarSistemaLinkDaPrincipalComAdministradorGeral(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario1", "123456");
		auxMenu.clicaLink("Selecione um sistema");
		boolean sistema = driver.findElements(By.id("sistema")).contains("Sistema Teste");
		assertTrue(sistema);
	}
	
	@Test
	public void selecionarSistemaLinkDaPrincipalComAdministrador(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Selecione um sistema");
		boolean sistema = driver.findElements(By.id("sistema")).contains("Sistema Teste");
		assertTrue(sistema);
	}
	
	@Test
	public void selecionarSistemaLinkDaPrincipalComAnalista(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.clicaLink("Selecione um sistema");
		boolean sistema = driver.findElements(By.id("sistema")).contains("Sistema Teste");
		assertTrue(sistema);
	}
	
	@Test
	public void registrarVersaoLinkDaPrincipalComAdministradorGeral(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario1", "123456");
		auxMenu.clicaLink("Registre uma versão");
		boolean paginaCerta = driver.getTitle().contains("Cadastrar Nova Versão");
		assertTrue(paginaCerta);
	}
	
	@Test
	public void registrarVersaoLinkDaPrincipalComAdministrador(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Registre uma versão");
		boolean paginaCerta = driver.getTitle().contains("Cadastrar Nova Versão");
		assertTrue(paginaCerta);
	}
	
	@Test
	public void registrarVersaoLinkDaPrincipalComAnalista(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		WebElement semLink = driver.findElement(By.linkText("Registre uma versão"));
		assertNull(semLink);
	}
	
	@Test
	public void importarLogsLinkDaPrincipalComAdministradorGeral() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario1", "123456");
		auxMenu.clicaLink("Importe manualmente");
		boolean paginaCerta = driver.getTitle().contains("Importar Logs");
		assertTrue(paginaCerta);
	}
	
	@Test
	public void importarLogsLinkDaPrincipalComAdministrador() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Importe manualmente");
		boolean paginaCerta = driver.getTitle().contains("Importar Logs");
		assertTrue(paginaCerta);
	}
	
	@Test
	public void importarLogsLinkDaPrincipalComAnalista(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		WebElement semLink = driver.findElement(By.linkText("Importar Logs"));
		assertNull(semLink);
	}
	
	@Test
	public void relatoriosPerfilLinkDaPrincipalComAdministradorGeral() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario1", "123456");
		auxMenu.clicaLink("Perfil de Acesso");
		boolean paginaCerta = driver.getTitle().contains("Perfil de Acesso");
		assertTrue(paginaCerta);
	}
	
	public void relatoriosPerfilLinkDaPrincipalComAdministrador() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Perfil de Acesso");
		boolean paginaCerta = driver.getTitle().contains("Perfil de Acesso");
		assertTrue(paginaCerta);
	}
	
	public void relatoriosPerfilLinkDaPrincipalComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.clicaLink("Perfil de Acesso");
		boolean paginaCerta = driver.getTitle().contains("Perfil de Acesso");
		assertTrue(paginaCerta);
	}
	
	@Test
	public void relatoriosErrosLinkDaPrincipalComAdministradorGeral() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario1", "123456");
		auxMenu.clicaLink("Erros no Sistema");
		boolean paginaCerta = driver.getTitle().contains("Confiabilidade das URLs");
		assertTrue(paginaCerta);
	}
	
	@Test
	public void relatoriosErrosLinkDaPrincipalComAdministrador() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Erros no Sistema");
		boolean paginaCerta = driver.getTitle().contains("Confiabilidade das URLs");
		assertTrue(paginaCerta);
	}
	
	@Test
	public void relatoriosErrosLinkDaPrincipalComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.clicaLink("Erros no Sistema");
		boolean paginaCerta = driver.getTitle().contains("Confiabilidade das URLs");
		assertTrue(paginaCerta);
	}
	
	
	
	
	@After
	public void encerra(){
		this.driver.close();
	}

}
