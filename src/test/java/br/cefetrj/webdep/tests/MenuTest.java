package br.cefetrj.webdep.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import br.cefetrj.webdep.tests.support.TestSupport;

public class MenuTest {
	
	private WebDriver driver;
	private TestSupport auxMenu;
	
	@Before
	public void inicializa(){
		this.driver = new FirefoxDriver();
		this.auxMenu = new TestSupport(driver);
	}
	//TC01
	@Test
	public void cadastrarSistemaLinkDaPrincipalComAdministradorGeral() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario1", "123456");
		auxMenu.clicaLink("Clique aqui para cadastrar");
		boolean paginaCerta = driver.getTitle().contains("Cadastrar Sistema");
		assertTrue(paginaCerta);
	}
	//TC02
	@Test
	public void cadastrarSistemaLinkDaPrincipalComAdministrador() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Clique aqui para cadastrar");
		boolean paginaCerta = driver.getTitle().contains("Cadastrar Sistema");
		assertTrue(paginaCerta);
	}
	//TC03
	@Test
	public void cadastrarSistemaLinkDaPrincipalComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		WebElement semLink = driver.findElement(By.linkText("Clique aqui para cadastrar"));
		assertNull(semLink);
	}
	//TC04
	@Test
	public void selecionarSistemaLinkDaPrincipalComAdministradorGeral(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario1", "123456");
		auxMenu.clicaLink("Selecione um sistema");
		Select seletor = new Select(driver.findElement(By.id("sistema")));
		List<WebElement> opcoes = seletor.getOptions();
		boolean sistema = opcoes.contains("Sistema Teste");
		assertTrue(sistema);
	}
	//TC05
	@Test
	public void selecionarSistemaLinkDaPrincipalComAdministrador(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Selecione um sistema");
		boolean sistema = driver.findElements(By.id("sistema")).contains("Sistema Teste");
		assertTrue(sistema);
	}
	//TC06
	@Test
	public void selecionarSistemaLinkDaPrincipalComAnalista(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.clicaLink("Selecione um sistema");
		boolean sistema = driver.findElements(By.id("sistema")).contains("Sistema Teste");
		assertTrue(sistema);
	}
	//TC07
	@Test
	public void registrarVersaoLinkDaPrincipalComAdministradorGeral(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario1", "123456");
		auxMenu.clicaLink("Registre uma versão");
		boolean paginaCerta = driver.getTitle().contains("Cadastrar Nova Versão");
		assertTrue(paginaCerta);
	}
	//TC08
	@Test
	public void registrarVersaoLinkDaPrincipalComAdministrador(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Registre uma versão");
		boolean paginaCerta = driver.getTitle().contains("Cadastrar Nova Versão");
		assertTrue(paginaCerta);
	}
	//TC09
	@Test
	public void registrarVersaoLinkDaPrincipalComAnalista(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		WebElement semLink = driver.findElement(By.linkText("Registre uma versão"));
		assertNull(semLink);
	}
	//TC10
	@Test
	public void importarLogsLinkDaPrincipalComAdministradorGeral() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario1", "123456");
		auxMenu.clicaLink("Importe manualmente");
		boolean paginaCerta = driver.getTitle().contains("Importar Logs");
		assertTrue(paginaCerta);
	}
	//TC11
	@Test
	public void importarLogsLinkDaPrincipalComAdministrador() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Importe manualmente");
		boolean paginaCerta = driver.getTitle().contains("Importar Logs");
		assertTrue(paginaCerta);
	}
	//TC12
	@Test
	public void importarLogsLinkDaPrincipalComAnalista(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		WebElement semLink = driver.findElement(By.linkText("Importar Logs"));
		assertNull(semLink);
	}
	//TC13
	@Test
	public void relatoriosPerfilLinkDaPrincipalComAdministradorGeral() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario1", "123456");
		auxMenu.clicaLink("Perfil de Acesso");
		boolean paginaCerta = driver.getTitle().contains("Perfil de Acesso");
		assertTrue(paginaCerta);
	}
	//TC14
	public void relatoriosPerfilLinkDaPrincipalComAdministrador() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Perfil de Acesso");
		boolean paginaCerta = driver.getTitle().contains("Perfil de Acesso");
		assertTrue(paginaCerta);
	}
	//TC15
	public void relatoriosPerfilLinkDaPrincipalComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.clicaLink("Perfil de Acesso");
		boolean paginaCerta = driver.getTitle().contains("Perfil de Acesso");
		assertTrue(paginaCerta);
	}
	//TC16
	@Test
	public void relatoriosErrosLinkDaPrincipalComAdministradorGeral() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario1", "123456");
		auxMenu.clicaLink("Erros no Sistema");
		boolean paginaCerta = driver.getTitle().contains("Confiabilidade das URLs");
		assertTrue(paginaCerta);
	}
	//TC17
	@Test
	public void relatoriosErrosLinkDaPrincipalComAdministrador() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Erros no Sistema");
		boolean paginaCerta = driver.getTitle().contains("Confiabilidade das URLs");
		assertTrue(paginaCerta);
	}
	//TC18
	@Test
	public void relatoriosErrosLinkDaPrincipalComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.clicaLink("Erros no Sistema");
		boolean paginaCerta = driver.getTitle().contains("Confiabilidade das URLs");
		assertTrue(paginaCerta);
	}
	//TC19
	@Test
	public void menuWebdepComAdministradorGeral() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario1", "123456");
		auxMenu.clicaLink("WebDep");
		boolean menuCorreto = driver.getPageSource().contains("Cadastrar Usuário") &&	
				driver.getPageSource().contains("Listar/Alterar/Excluir Usuários") && 
				driver.getPageSource().contains("Configurações");
		assertTrue(menuCorreto);
	}
	//TC20
	public void menuWebdepComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		WebElement semLink = driver.findElement(By.linkText("WebDep"));
		assertNull(semLink);
	}
	//TC21
	public void menuWebdepCadastrarUsuario(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("WebDep");
		auxMenu.clicaLink("Cadastrar Usuário");
		boolean paginaCerta = driver.getTitle().contains("Cadastrar Usuário");
		assertTrue(paginaCerta);
	}
	//TC22
	public void menuWebDepListarUsuarios() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("WebDep");
		auxMenu.clicaLink("Listar/Alterar/Excluir Usuários");
		boolean paginaCerta = driver.getTitle().contains("Listar/Alterar/Excluir Usuários");
		assertTrue(paginaCerta);
	}
	//TC23
	public void menuWebdepConfiguracoes() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario1", "123456");
		auxMenu.clicaLink("WebDep");
		auxMenu.clicaLink("Configurações");
		boolean paginaCerta = driver.getTitle().contains("Configurações");
		assertTrue(paginaCerta);
	}
	//TC24
	public void menuSistemasComAdministrador() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Sistemas");
		boolean menuCorreto = driver.getPageSource().contains("Cadastrar Sistema") &&	
				driver.getPageSource().contains("Listar/Atualizar/Excluir Sistema") && 
				driver.getPageSource().contains("Cadastrar Nova Versão") &&
				driver.getPageSource().contains("Listar/Atualizar/Excluir Versões");
		assertTrue(menuCorreto);
	}
	//TC25
	public void menuSistemasComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		WebElement semLink = driver.findElement(By.linkText("Sistemas"));
		assertNull(semLink);
	}
	//TC26
	public void menuSistemasCadastrar() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Sistemas");
		auxMenu.clicaLink("Cadastrar Sistema");
		boolean paginaCerta = driver.getTitle().contains("Cadastrar Sistema");
		assertTrue(paginaCerta);
	}
	//TC27
	public void menuSistemasListar(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Sistemas");
		auxMenu.clicaLink("Listar/Atualizar/Excluir Sistema");
		boolean paginaCerta = driver.getTitle().contains("Listar/Atualizar/Excluir Sistema");
		assertTrue(paginaCerta);
	}
	//TC28
	public void menuSistemasVersao(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Sistemas");
		auxMenu.clicaLink("Cadastrar Nova Versão");
		boolean paginaCerta = driver.getTitle().contains("Cadastrar Nova Versão");
		assertTrue(paginaCerta);
	}
	//TC29
	public void menuSistemasListarVersao(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Sistemas");
		auxMenu.clicaLink("Listar/Atualizar/Excluir Versões");
		boolean paginaCerta = driver.getTitle().contains("Listar/Atualizar/Excluir Versões");
		assertTrue(paginaCerta);
	}
	//TC30
	public void menuLogsComAdministrador() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Logs");
		boolean menuCorreto = driver.getPageSource().contains("Importar Logs") &&	
				driver.getPageSource().contains("Consultar/Excluir Registros de Acesso") && 
				driver.getPageSource().contains("Consultar/Excluir Registros de Erro");
		assertTrue(menuCorreto);
	}
	//TC31
	public void menuLogsComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		WebElement semLink = driver.findElement(By.linkText("Logs"));
		assertNull(semLink);
	}
	//TC32
	public void menuLogsImportar(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Logs");
		auxMenu.clicaLink("Importar Logs");
		boolean paginaCerta = driver.getTitle().contains("Importar Logs");
		assertTrue(paginaCerta);
	}
	//TC33
	public void menuLogsConsultarAcesso(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.acionaSelectSistema("Sistema Teste");
		auxMenu.clicaLink("Logs");
		auxMenu.clicaLink("Consultar/Excluir Registros de Acesso");
		boolean paginaCerta = driver.getTitle().contains("Consultar/Excluir Registros de Acesso");
		assertTrue(paginaCerta);
	}
	//TC34 -- ERRO
	@Test(expected=IllegalArgumentException.class)
	public void menuLogsConsultarAcessoSemSistemaSelecionado(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Logs");
		auxMenu.clicaLink("Consultar/Excluir Registros de Acesso");
	}
	//TC35 -- ERRO
	@Test(expected=IllegalArgumentException.class)
	public void menuLogsConsultarAcessoSemNenhumSistema(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Logs");
		auxMenu.clicaLink("Consultar/Excluir Registros de Acesso");
	}
	//TC36
	public void menuLogsConsultarErro(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.acionaSelectSistema("Sistema Teste");
		auxMenu.clicaLink("Logs");
		auxMenu.clicaLink("Consultar/Excluir Registros de Erro");
		boolean paginaCerta = driver.getTitle().contains("Consultar/Excluir Registros de Erro");
		assertTrue(paginaCerta);
	}
	//TC37 -- ERRO
	@Test(expected=IllegalArgumentException.class)
	public void menuLogsConsultarErroSemSistemaSelecionado(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Logs");
		auxMenu.clicaLink("Consultar/Excluir Registros de Erro");
	}
	//TC38 -- ERRO
	@Test(expected=IllegalArgumentException.class)
	public void menuLogsConsultarErroSemNenhumSistema(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Logs");
		auxMenu.clicaLink("Consultar/Excluir Registros de Erro");
	}
	//TC39
	public void menuRelatorioComAdministrador() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Relatórios");
		boolean menuCorreto = driver.getPageSource().contains("Perfil de Acesso") &&	
				driver.getPageSource().contains("Confiabilidade das URLs");
		assertTrue(menuCorreto);
	}
	//TC40
	public void menuRelatorioComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.clicaLink("Relatórios");
		boolean menuCorreto = driver.getPageSource().contains("Perfil de Acesso") &&	
				driver.getPageSource().contains("Confiabilidade das URLs");
		assertTrue(menuCorreto);
	}
	//TC41
	public void menuRelatoriosPerfilAcessoComAdministrador(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.acionaSelectSistema("Sistema Teste");
		auxMenu.clicaLink("Relatórios");
		auxMenu.clicaLink("Perfil de Acesso");
		boolean paginaCerta = driver.getTitle().contains("Perfil de Acesso");
		assertTrue(paginaCerta);
	}
	//TC42 -- ERRO
	@Test(expected=IllegalArgumentException.class)
	public void menuRelatoriosPerfilAcessoSemSistemaSelecionado(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Relatórios");
		auxMenu.clicaLink("Perfil de Acesso");
	}
	//TC43 -- ERRO
	@Test(expected=IllegalArgumentException.class)
	public void menuRelatoriosPerfilAcessoSemNenhumSistema(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Relatórios");
		auxMenu.clicaLink("Perfil de Acesso");
	}
	//TC44
	public void menuRelatoriosConfiabilidadeURLComAdministrador(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.acionaSelectSistema("Sistema Teste");
		auxMenu.clicaLink("Relatórios");
		auxMenu.clicaLink("Confiabilidade das URLs");
		boolean paginaCerta = driver.getTitle().contains("Confiabilidade das URLs");
		assertTrue(paginaCerta);
	}
	//TC45
	public void menuRelatoriosPerfilAcessoComAnalista(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.acionaSelectSistema("Sistema Teste");
		auxMenu.clicaLink("Relatórios");
		auxMenu.clicaLink("Perfil de Acesso");
		boolean paginaCerta = driver.getTitle().contains("Perfil de Acesso");
		assertTrue(paginaCerta);
	}
	//TC46
	public void menuRelatoriosConfiabilidadeURLComAnalista(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.acionaSelectSistema("Sistema Teste");
		auxMenu.clicaLink("Relatórios");
		auxMenu.clicaLink("Confiabilidade das URLs");
		boolean paginaCerta = driver.getTitle().contains("Confiabilidade das URLs");
		assertTrue(paginaCerta);
	}
	//TC47 -- ERRO
	@Test(expected=IllegalArgumentException.class)
	public void menuRelatoriosConfiabilidadeURLSemSistemaSelecionado(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.clicaLink("Relatórios");
		auxMenu.clicaLink("Confiabilidade das URLs");
	}
	//TC48 -- ERRO
	@Test(expected=IllegalArgumentException.class)
	public void menuRelatoriosConfiabilidadeURLSemNenhumSistema(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.clicaLink("Relatórios");
		auxMenu.clicaLink("Confiabilidade das URLs");
	}
	//TC49
	public void menuIdiomaComAdministrador() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Idioma");
		boolean menuCorreto = driver.getPageSource().contains("Inglês") &&	
				driver.getPageSource().contains("Português");
		assertTrue(menuCorreto);
	}
	//TC50
	public void menuIdiomaComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.clicaLink("Idioma");
		boolean menuCorreto = driver.getPageSource().contains("Inglês") &&	
				driver.getPageSource().contains("Português");
		assertTrue(menuCorreto);
	}
	//TC51
	public void menuIdiomaInglesComAdministrador() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Idioma");
		auxMenu.clicaLink("Inglês");
		boolean traduziu = driver.getPageSource().contains("Language");
		assertTrue(traduziu);
	}
	//TC52
	public void menuIdiomaPortuguesComAdministrador() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Idioma");
		auxMenu.clicaLink("Inglês");
		auxMenu.clicaLink("Idioma");
		auxMenu.clicaLink("Português");
		boolean traduziu = driver.getPageSource().contains("Idioma");
		assertTrue(traduziu);
	}
	//TC53
	public void menuIdiomaInglesComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.clicaLink("Idioma");
		auxMenu.clicaLink("Inglês");
		boolean traduziu = driver.getPageSource().contains("Language");
		assertTrue(traduziu);
	}
	//TC54
	public void menuIdiomaPortuguesComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.clicaLink("Idioma");
		auxMenu.clicaLink("Inglês");
		auxMenu.clicaLink("Idioma");
		auxMenu.clicaLink("Português");
		boolean traduziu = driver.getPageSource().contains("Idioma");
		assertTrue(traduziu);
	}
	//TC55
	public void menuSair() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.clicaLink("Sair");
		boolean paginaCerta = driver.getPageSource().contains("Tem certeza que deseja sair?");
		assertTrue(paginaCerta);
	}
	//TC56 -- BOTAO
	public void menuSairBotaoNao(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Sair");
		WebElement botaoNao = driver.findElement(By.id("botao-nao"));
		botaoNao.click();
		boolean paginaCerta = driver.getTitle().contains("WebDep");
		assertTrue(paginaCerta);
	}
	//TC57 -- BOTAO
	public void menuSairBotaoSim(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.clicaLink("Sair");
		WebElement botaoSim = driver.findElement(By.id("botao-sim"));
		botaoSim.click();
		boolean paginaCerta = driver.getPageSource().contains("Autenticação");
		assertTrue(paginaCerta);
	}
	//TC58
	public void componenteSeletorSistemaComAdministrador(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		Select seletor = new Select(driver.findElement(By.id("sistema")));
		List<WebElement> opcoes = seletor.getOptions();
		boolean sistemaCerto = opcoes.contains("Sistema Teste");
		boolean sistemaErro = opcoes.contains("Sistema Teste 2");
		assertTrue(sistemaCerto);
		assertFalse(sistemaErro);
	}
	//TC59
	public void componenteSeletorSistemaComAnalista(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		Select seletor = new Select(driver.findElement(By.id("sistema")));
		List<WebElement> opcoes = seletor.getOptions();
		boolean sistemaCerto = opcoes.contains("Sistema Teste");
		boolean sistemaErro = opcoes.contains("Sistema Teste 2");
		assertTrue(sistemaCerto);
		assertFalse(sistemaErro);
	}
	//TC60
	public void componenteSeletorSistemaSemNenhumSistema(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario1", "123456");
		Select seletor = new Select(driver.findElement(By.id("sistema")));
		List<WebElement> opcoes = seletor.getOptions();
		boolean sistema = opcoes.isEmpty();
		assertTrue(sistema);
	}
	@After
	public void encerra(){
		this.driver.close();
	}

}
