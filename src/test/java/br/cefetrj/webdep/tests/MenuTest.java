package br.cefetrj.webdep.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Query;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import br.cefetrj.webdep.model.dao.GenericDAO;
import br.cefetrj.webdep.model.dao.PersistenceManager;
import br.cefetrj.webdep.model.entity.Permissao;
import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.model.entity.Usuario;
import br.cefetrj.webdep.tests.support.TestSupport;

public class MenuTest {
	/*
	@SuppressWarnings("unused")
	private static Long adminGeralId;
	
	@SuppressWarnings("unused")
	private static Long adminId;
	
	@SuppressWarnings("unused")
	private static Long analistaId;
	
	@SuppressWarnings("unused")
	private static Long sistemaId;
	
	private static WebDriver driver;
	private static PersistenceManager pm;
	private static TestSupport auxMenu;
	
	
	@AfterClass
	public static void restoreDbConfig() throws IOException {
		pm.beginTransaction();
		Query q = pm.createNativeQuery("TRUNCATE SCHEMA public AND COMMIT");
		q.executeUpdate();
		pm.commitTransaction();
		driver.quit();
	}
	
	
	@BeforeClass
	public static void configureDb() throws IOException {
		
		pm = PersistenceManager.getInstance();
		pm.beginTransaction();
		
		Sistema s1 = new Sistema();
		s1.setNome("Sistema Teste");
		s1.setPastaLogAcesso("/PastaLog/Acesso");
		s1.setPrefixoLogAcesso("sistemaTesteAcesso");
		s1.setPastaLogErro("/PastaLog/Erro");
		s1.setPrefixoLogErro("sistemaTesteErro");
		s1.setPrimeiraLeitura(LocalDateTime.now());
		s1.setPeriodicidadeLeitura(1L);
		
		Sistema s2 = new Sistema();
		s2.setNome("Sistema Teste 2");
		s2.setPastaLogAcesso("/PastaLog/Acesso");
		s2.setPrefixoLogAcesso("sistemaTeste2Acesso");
		s2.setPastaLogErro("/PastaLog/Erro");
		s2.setPrefixoLogErro("sistemaTeste2Erro");
		s2.setPrimeiraLeitura(LocalDateTime.now());
		s2.setPeriodicidadeLeitura(1L);
		
		Usuario u1 = new Usuario();
		u1.setNome("Usuario Um");
		u1.setLogin("usuario1");
		u1.setSenha("123456");
		u1.setPerfil("admin");
		u1.setAdmGeral(true);
		u1.setEmail("usuario_um@usuario.com");
		
		Usuario u2 = new Usuario();
		u2.setNome("Usuario Dois");
		u2.setLogin("usuario2");
		u2.setSenha("123456");
		u2.setPerfil("admin");
		u2.setAdmGeral(false);
		u2.setEmail("usuario_dois@outrousuario.com.br");
		
		Usuario u3 = new Usuario();
		u3.setNome("Usuario Tres");
		u3.setLogin("usuario3");
		u3.setSenha("123456");
		u3.setPerfil("analista");
		u3.setAdmGeral(false);
		u3.setEmail("usuario_tres@usuario.com");
		
		Permissao p1 = new Permissao();
		p1.setSistema(s1);
		p1.setUsuario(u2);
		
		Permissao p2 = new Permissao();
		p2.setSistema(s1);
		p2.setUsuario(u3);
		
		u2.setPermissoes(Arrays.asList(new Permissao[]{p1}));
		u3.setPermissoes(Arrays.asList(new Permissao[]{p2}));
		
		GenericDAO<Usuario> usuarioDao = pm.createGenericDAO(Usuario.class);
		GenericDAO<Sistema> sistemaDao = pm.createGenericDAO(Sistema.class);
		GenericDAO<Permissao> permissaoDao = pm.createGenericDAO(Permissao.class);
		
		sistemaDao.insert(s1);
		sistemaDao.insert(s2);
		
		usuarioDao.insert(u1);
		usuarioDao.insert(u2);
		usuarioDao.insert(u3);
		
		permissaoDao.insert(p1);
		permissaoDao.insert(p2);
		
		pm.commitTransaction();
		
		adminGeralId = u1.getId();
		adminId = u2.getId();
		analistaId = u3.getId();
		sistemaId = s1.getId();
		
		
        //if (System.getProperty("os.name").contains("Windows")){
        //	System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\geckodriver.exe");
        //}else{
        //	System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
        //}
        //
        //driver = new FirefoxDriver();
		
		if (System.getProperty("os.name").contains("Windows")){
        	System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
        }else{
        	System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        }
        
        driver = new ChromeDriver();
        auxMenu = new TestSupport(driver);
	}
	
	//TC01 -- PAGINA SEM TITULO
	@Test
	public void cadastrarSistemaLinkDaPrincipalComAdministradorGeral() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario1", "123456");
		auxMenu.clicaLink("Clique aqui para cadastrar");
		
		boolean legendaSistema = driver.getPageSource().contains("Sistema");
		boolean legendaLog = driver.getPageSource().contains("Log");
		
		assertTrue(legendaSistema);
		assertTrue(legendaLog);
	}
	
	//TC02
	@Test
	public void cadastrarSistemaLinkDaPrincipalComAdministrador() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Clique aqui para cadastrar");

		boolean legendaSistema = driver.getPageSource().contains("Sistema");
		boolean legendaLog = driver.getPageSource().contains("Log");
		
		assertTrue(legendaSistema);
		assertTrue(legendaLog);
	}
	
	//TC03
	@Test
	public void cadastrarSistemaLinkDaPrincipalComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		
		boolean semLink = driver.getPageSource().contains("Clique aqui para cadastrar");
		assertFalse(semLink);
	}
	
	//TC04
	@Test
	public void selecionarSistemaLinkDaPrincipalComAdministradorGeral(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario1", "123456");
		auxMenu.clicaLink("Selecione um sistema para trabalhar");
		Select seletor = new Select(driver.findElement(By.id("sistema")));
		List<WebElement> opcoes = seletor.getOptions();
		
		boolean sistema1 = false;
		boolean sistema2 = false;
		
		for (WebElement webElement : opcoes) {
				if(webElement.getText().contains("Sistema Teste")){
				sistema1 = true;
			}
			if(webElement.getText().contains("Sistema Teste 2")){
				sistema2 = true;
			}
		}
		
		assertTrue(sistema1);
		assertTrue(sistema2);
		assertEquals(2, opcoes.size());
	}
	
	//TC05
	@Test
	public void selecionarSistemaLinkDaPrincipalComAdministrador(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Selecione um sistema para trabalhar");
		Select seletor = new Select(driver.findElement(By.id("sistema")));
		List<WebElement> opcoes = seletor.getOptions();

		boolean sistema1 = false;
		boolean sistema2 = false;
		
		for (WebElement webElement : opcoes) {
				if(webElement.getText().contains("Sistema Teste")){
				sistema1 = true;
			}
			if(webElement.getText().contains("Sistema Teste 2")){
				sistema2 = true;
			}
		}
		
		assertTrue(sistema1);
		assertFalse(sistema2);
		assertEquals(1, opcoes.size());
	}
	
	//TC06
	@Test
	public void selecionarSistemaLinkDaPrincipalComAnalista(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.clicaLink("Selecione um sistema para trabalhar");
		Select seletor = new Select(driver.findElement(By.id("sistema")));
		List<WebElement> opcoes = seletor.getOptions();

		boolean sistema1 = false;
		boolean sistema2 = false;
		
		for (WebElement webElement : opcoes) {
				if(webElement.getText().contains("Sistema Teste")){
				sistema1 = true;
			}
			if(webElement.getText().contains("Sistema Teste 2")){
				sistema2 = true;
			}
		}
		
		assertTrue(sistema1);
		assertFalse(sistema2);
		assertEquals(1, opcoes.size());
	}
	
	//TC07
	@Test
	public void registrarVersaoLinkDaPrincipalComAdministradorGeral(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario1", "123456");
		auxMenu.clicaLink("Registre uma versão do sistema");
		boolean paginaCerta = driver.getTitle().contains("Registro de Versão");
		assertTrue(paginaCerta);
	}
	
	//TC08
	@Test
	public void registrarVersaoLinkDaPrincipalComAdministrador(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Registre uma versão do sistema");
		boolean paginaCerta = driver.getTitle().contains("Registro de Versão");
		assertTrue(paginaCerta);
	}
	
	//TC09
	@Test
	public void registrarVersaoLinkDaPrincipalComAnalista(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		
		boolean semLink = driver.getPageSource().contains("Registre uma versão do sistema");
		assertFalse(semLink);
	}
	
	//TC10 -- PAGINA SEM TITULO
	@Test
	public void importarLogsLinkDaPrincipalComAdministradorGeral() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario1", "123456");
		auxMenu.clicaLink("Importe manualmente os dados de logs históricos");
		
		boolean legendaSistema = driver.getPageSource().contains("Sistema");
		boolean legendaLog = driver.getPageSource().contains("Log");
		boolean textFormato = driver.getPageSource().contains("Formato logs de acesso:");
		
		assertTrue(legendaSistema);
		assertTrue(legendaLog);
		assertTrue(textFormato);
	}
	
	//TC11 -- PAGINA SEM TITULO
	@Test
	public void importarLogsLinkDaPrincipalComAdministrador() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Importe manualmente os dados de logs históricos");

		boolean legendaSistema = driver.getPageSource().contains("Sistema");
		boolean legendaLog = driver.getPageSource().contains("Log");
		boolean textFormato = driver.getPageSource().contains("Formato logs de acesso:");
		
		assertTrue(legendaSistema);
		assertTrue(legendaLog);
		assertTrue(textFormato);
	}
	
	//TC12
	@Test
	public void importarLogsLinkDaPrincipalComAnalista(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		
		boolean semLink = driver.getPageSource().contains("Importe manualmente os dados de logs históricos");
		assertFalse(semLink);
	}
	
	//TC13
	@Test
	public void relatoriosPerfilLinkDaPrincipalComAdministradorGeral() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario1", "123456");
		auxMenu.clicaLink("Emita Relatórios de Perfil de Acesso");
		boolean paginaCerta = driver.getTitle().contains("Relatório de perfil de acesso");
		assertTrue(paginaCerta);
	}
	
	//TC14
	@Test
	public void relatoriosPerfilLinkDaPrincipalComAdministrador() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Emita Relatórios de Perfil de Acesso");
		boolean paginaCerta = driver.getTitle().contains("Relatório de perfil de acesso");
		assertTrue(paginaCerta);
	}
	
	//TC15
	@Test
	public void relatoriosPerfilLinkDaPrincipalComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.clicaLink("Emita Relatórios de Perfil de Acesso");
		boolean paginaCerta = driver.getTitle().contains("Relatório de perfil de acesso");
		assertTrue(paginaCerta);
	}
	
	//TC16
	@Test
	public void relatoriosErrosLinkDaPrincipalComAdministradorGeral() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario1", "123456");
		auxMenu.clicaLink("Analise os Erros no Sistema (Código HTTP)");
		boolean paginaCerta = driver.getTitle().contains("Relatório Códigos HTTP");
		assertTrue(paginaCerta);
	}
	//TC17
	@Test
	public void relatoriosErrosLinkDaPrincipalComAdministrador() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Analise os Erros no Sistema (Código HTTP)");
		boolean paginaCerta = driver.getTitle().contains("Relatório Códigos HTTP");
		assertTrue(paginaCerta);
	}
	//TC18
	@Test
	public void relatoriosErrosLinkDaPrincipalComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.clicaLink("Analise os Erros no Sistema (Código HTTP)");
		boolean paginaCerta = driver.getTitle().contains("Relatório Códigos HTTP");
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
	
	//TC20 -- VERIFICAR SE RETORNA NULL
	@Test
	public void menuWebdepComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		WebElement semLink = driver.findElement(By.linkText("WebDep"));
		assertNull(semLink);
	}
	
	//TC21
	@Test
	public void menuWebdepCadastrarUsuario(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("WebDep");
		auxMenu.clicaLink("Cadastrar Usuário");
		boolean paginaCerta = driver.getTitle().contains("Usuário");
		assertTrue(paginaCerta);
	}
	
	//TC22 -- PÁGINA ESTÁ COM TITULO USUÁRIO
	@Test
	public void menuWebDepListarUsuarios() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("WebDep");
		auxMenu.clicaLink("Listar/Alterar/Excluir Usuários");
		boolean tabelaUsuario = driver.getPageSource().contains("Usuário");
		boolean tabelaEmail = driver.getPageSource().contains("E-mail");
		boolean tabelaPermissao = driver.getPageSource().contains("Alterar/Permissões");
		
		assertTrue(tabelaUsuario);
		assertTrue(tabelaEmail);
		assertTrue(tabelaPermissao);
	}
	
	//TC23 -- PAGINA SEM TITULO
	@Test
	public void menuWebdepConfiguracoes() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario1", "123456");
		auxMenu.clicaLink("WebDep");
		auxMenu.clicaLink("Configurações");
		boolean paginaCerta = driver.getPageSource().contains("Configuração Inicial do Web Dep");
		
		assertTrue(paginaCerta);
	}
	
	//TC24
	@Test
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
	
	//TC25 -- VERIFICAR SE RETORNA NULL
	@Test
	public void menuSistemasComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		WebElement semLink = driver.findElement(By.linkText("Sistemas"));
		assertNull(semLink);
	}
	
	//TC26 -- PAGINA SEM TITULO
	@Test
	public void menuSistemasCadastrar() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Sistemas");
		auxMenu.clicaLink("Cadastrar Sistema");
		
		boolean paginaSistema = driver.getPageSource().contains("Sistema");
		boolean paginaLogs = driver.getPageSource().contains("Logs");
		boolean paginaPeriodicidade = driver.getPageSource().contains("Periodicidade de Leitura dos Logs");
		
		assertTrue(paginaSistema);
		assertTrue(paginaLogs);
		assertTrue(paginaPeriodicidade);
	}
	
	//TC27 -- PAGINA SEM TITULO
	@Test
	public void menuSistemasListar(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Sistemas");
		auxMenu.clicaLink("Listar/Atualizar/Excluir Sistema");
		
		boolean paginaServidor = driver.getPageSource().contains("Servidor");
		boolean paginaLeitura = driver.getPageSource().contains("Próxima Leitura");
		boolean paginaLog = driver.getPageSource().contains("Formato do Log");
		
		assertTrue(paginaServidor);
		assertTrue(paginaLeitura);
		assertTrue(paginaLog);
	}
	
	//TC28
	@Test
	public void menuSistemasVersao(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Sistemas");
		auxMenu.clicaLink("Cadastrar Nova Versão");
		boolean paginaCerta = driver.getTitle().contains("Registro de Versão");
		assertTrue(paginaCerta);
	}
	
	//TC29
	@Test
	public void menuSistemasListarVersao(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Sistemas");
		auxMenu.clicaLink("Listar/Atualizar/Excluir Versões");
		boolean paginaCerta = driver.getTitle().contains("Buscar Versão");
		assertTrue(paginaCerta);
	}
	
	//TC30
	@Test
	public void menuLogsComAdministrador() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Logs");
		boolean menuCorreto = driver.getPageSource().contains("Importar Logs") &&	
				driver.getPageSource().contains("Consultar/Excluir Registros de Acesso") && 
				driver.getPageSource().contains("Consultar/Excluir Registros de Erro");
		assertTrue(menuCorreto);
	}
	
	//TC31 -- VERIFICAR SE RETORNA NULL
	@Test
	public void menuLogsComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		WebElement semLink = driver.findElement(By.linkText("Logs"));
		assertNull(semLink);
	}
	
	//TC32
	@Test
	public void menuLogsImportar(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Logs");
		auxMenu.clicaLink("Importar Logs");
		
		boolean paginaServidor = driver.getPageSource().contains("Servidor");
		boolean paginaSistema = driver.getPageSource().contains("Nome do Sistema");
		boolean paginaLog = driver.getPageSource().contains("Caminho log de acesso");
		
		assertTrue(paginaServidor);
		assertTrue(paginaSistema);
		assertTrue(paginaLog);
	}
	
	//TC33 -- PÁGINA SEM TITULO
	@Test
	public void menuLogsConsultarAcesso(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.acionaSelectSistema("Sistema Teste");
		auxMenu.clicaLink("Logs");
		auxMenu.clicaLink("Consultar/Excluir Registros de Acesso");

		boolean paginaIp = driver.getPageSource().contains("IP");
		boolean paginaUsuario = driver.getPageSource().contains("Usuário");
		boolean paginaBytes = driver.getPageSource().contains("Bytes transferidos");
		
		assertTrue(paginaIp);
		assertTrue(paginaUsuario);
		assertTrue(paginaBytes);
	}
	
	//TC36
	@Test
	public void menuLogsConsultarErro(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.acionaSelectSistema("Sistema Teste");
		auxMenu.clicaLink("Logs");
		auxMenu.clicaLink("Consultar/Excluir Registros de Erro");

		boolean paginaIp = driver.getPageSource().contains("IP");
		boolean paginaData = driver.getPageSource().contains("Data e Hora");
		boolean paginaMsg = driver.getPageSource().contains("Mensagem");
		
		assertTrue(paginaIp);
		assertTrue(paginaData);
		assertTrue(paginaMsg);
	}
	
	//TC39
	@Test
	public void menuRelatorioComAdministrador() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Relatórios");
		boolean menuCorreto = driver.getPageSource().contains("Perfil de Acesso") &&	
				driver.getPageSource().contains("Confiabilidade das URLs");
		assertTrue(menuCorreto);
	}
	
	//TC40
	@Test
	public void menuRelatorioComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.clicaLink("Relatórios");
		boolean menuCorreto = driver.getPageSource().contains("Perfil de Acesso") &&	
				driver.getPageSource().contains("Confiabilidade das URLs");
		assertTrue(menuCorreto);
	}
	
	//TC41
	@Test
	public void menuRelatoriosPerfilAcessoComAdministrador(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.acionaSelectSistema("Sistema Teste");
		auxMenu.clicaLink("Relatórios");
		auxMenu.clicaLink("Perfil de Acesso");
		boolean paginaCerta = driver.getTitle().contains("Relatório de perfil de acesso");
		assertTrue(paginaCerta);
	}
	
	//TC44
	@Test
	public void menuRelatoriosConfiabilidadeURLComAdministrador(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.acionaSelectSistema("Sistema Teste");
		auxMenu.clicaLink("Relatórios");
		auxMenu.clicaLink("Confiabilidade das URLs");
		boolean paginaCerta = driver.getTitle().contains("Relatório Códigos HTTP");
		assertTrue(paginaCerta);
	}
	
	//TC45
	@Test
	public void menuRelatoriosPerfilAcessoComAnalista(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.acionaSelectSistema("Sistema Teste");
		auxMenu.clicaLink("Relatórios");
		auxMenu.clicaLink("Perfil de Acesso");
		boolean paginaCerta = driver.getTitle().contains("Relatório de perfil de acesso");
		assertTrue(paginaCerta);
	}
	
	//TC46
	@Test
	public void menuRelatoriosConfiabilidadeURLComAnalista(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.acionaSelectSistema("Sistema Teste");
		auxMenu.clicaLink("Relatórios");
		auxMenu.clicaLink("Confiabilidade das URLs");
		boolean paginaCerta = driver.getTitle().contains("Relatório Códigos HTTP");
		assertTrue(paginaCerta);
	}
	
	//TC49
	@Test
	public void menuIdiomaComAdministrador() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Idioma");
		boolean menuCorreto = driver.getPageSource().contains("Inglês") &&	
				driver.getPageSource().contains("Português");
		assertTrue(menuCorreto);
	}
	
	//TC50
	@Test
	public void menuIdiomaComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.clicaLink("Idioma");
		boolean menuCorreto = driver.getPageSource().contains("Inglês") &&	
				driver.getPageSource().contains("Português");
		assertTrue(menuCorreto);
	}
	
	//TC51
	@Test
	public void menuIdiomaInglesComAdministrador() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Idioma");
		auxMenu.clicaLink("Inglês");
		boolean traduzido = driver.getPageSource().contains("Language");
		assertTrue(traduzido);
	}
	
	//TC52
	@Test
	public void menuIdiomaPortuguesComAdministrador() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Idioma");
		auxMenu.clicaLink("Inglês");
		boolean translate = driver.getPageSource().contains("Language");
		assertTrue(translate);
		auxMenu.clicaLink("Idioma");
		auxMenu.clicaLink("Português");
		boolean traduzido = driver.getPageSource().contains("Idioma");
		assertTrue(traduzido);
	}
	
	//TC53
	@Test
	public void menuIdiomaInglesComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.clicaLink("Idioma");
		auxMenu.clicaLink("Inglês");
		boolean traduzido = driver.getPageSource().contains("Language");
		assertTrue(traduzido);
	}
	
	//TC54
	@Test
	public void menuIdiomaPortuguesComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.clicaLink("Idioma");
		auxMenu.clicaLink("Inglês");
		boolean translate = driver.getPageSource().contains("Language");
		assertTrue(translate);
		auxMenu.clicaLink("Idioma");
		auxMenu.clicaLink("Português");
		boolean traduzido = driver.getPageSource().contains("Idioma");
		assertTrue(traduzido);
	}
	
	//TC55
	@Test
	public void menuSair() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.clicaLink("Sair");
		boolean paginaCerta = driver.getPageSource().contains("Confirma sair do sistema?");
		assertTrue(paginaCerta);
	}
	
	//TC56 -- BOTAO CANCELAR
	@Test
	public void menuSairBotaoCancelar(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Sair");
		WebElement botaoNao = driver.findElement(By.className("btn-danger"));
		botaoNao.click();
		boolean paginaCerta = driver.getPageSource().contains("Idioma");
		assertTrue(paginaCerta);
	}
	
	//TC57 -- BOTAO CONFIRMAR
	@Test
	public void menuSairBotaoSim(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.clicaLink("Sair");
		WebElement botaoSim = driver.findElement(By.id("submit-padrao-url"));
		botaoSim.click();
		boolean paginaCerta = driver.getPageSource().contains("Autenticação");
		assertTrue(paginaCerta);
	}
	
	//TC58 -- SELETOR DE SISTEMA
	@Test
	public void componenteSeletorSistemaComAdministrador(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		Select seletor = new Select(driver.findElement(By.id("sistema")));
		List<WebElement> opcoes = seletor.getOptions();
		
		boolean sistema1 = false;
		boolean sistema2 = false;
		
		for (WebElement webElement : opcoes) {
				if(webElement.getText().contains("Sistema Teste")){
				sistema1 = true;
			}
			if(webElement.getText().contains("Sistema Teste 2")){
				sistema2 = true;
			}
		}
		
		assertTrue(sistema1);
		assertFalse(sistema2);
		assertEquals(1, opcoes.size());
	}
	
	//TC59 -- SELETOR DE SISTEMA
	@Test
	public void componenteSeletorSistemaComAnalista(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		Select seletor = new Select(driver.findElement(By.id("sistema")));
		List<WebElement> opcoes = seletor.getOptions();
		
		boolean sistema1 = false;
		boolean sistema2 = false;
		
		for (WebElement webElement : opcoes) {
				if(webElement.getText().contains("Sistema Teste")){
				sistema1 = true;
			}
			if(webElement.getText().contains("Sistema Teste 2")){
				sistema2 = true;
			}
		}
		
		assertTrue(sistema1);
		assertFalse(sistema2);
		assertEquals(1, opcoes.size());
	}
	
	//TCextra01
	@Test
	public void cadastreUsuariosLinkDaPrincipalComAdministradorGeral() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario1", "123456");
		auxMenu.clicaLink("Cadastre usuários e atribua permissões a eles");
		boolean paginaCerta = driver.getTitle().contains("Usuário");
		assertTrue(paginaCerta);
	}
	
	//TCextra02
	@Test
	public void cadastreUsuariosLinkDaPrincipalComAdministrador() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Cadastre usuários e atribua permissões a eles");
		boolean paginaCerta = driver.getTitle().contains("Usuário");
		assertTrue(paginaCerta);
	}
	
	//TCextra03
	@Test
	public void cadastreUsuariosLinkDaPrincipalComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		boolean semLink = driver.getPageSource().contains("Cadastre usuários e atribua permissões a eles");
		assertFalse(semLink);
	}
	
	//TCextra04
	@Test
	public void consultarExcluirLogLinkDaPrincipalComAdministradorGeral() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario1", "123456");
		auxMenu.clicaLink("Selecione exclua dados históricos de logs");
		
		boolean paginaCerta = driver.getPageSource().contains("Sistema");
		boolean tabelaData = driver.getPageSource().contains("Data e Hora");
		boolean tabelaNivel = driver.getPageSource().contains("Nível");
		
		assertTrue(paginaCerta);
		assertTrue(tabelaData);
		assertTrue(tabelaNivel);
	}
	
	//TCextra05
	@Test
	public void consultarExcluirLogLinkDaPrincipalComAdministrador() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Selecione exclua dados históricos de logs");

		boolean paginaCerta = driver.getPageSource().contains("Sistema");
		boolean tabelaData = driver.getPageSource().contains("Data e Hora");
		boolean tabelaNivel = driver.getPageSource().contains("Nível");
		
		assertTrue(paginaCerta);
		assertTrue(tabelaData);
		assertTrue(tabelaNivel);
	}
	
	//TCextra06
	@Test
	public void consultarExcluirLogLinkDaPrincipalComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		boolean semLink = driver.getPageSource().contains("Selecione exclua dados históricos de logs");
		assertFalse(semLink);
	}
	*/
	
	/* TESTE SEM SISTEMA SELECIONADO E SEM SISTEMA NENHUM
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
	
	//TC60 -- SELETOR DE SISTEMA ---- VERIFICAR SE RETORNA NULL OU VAZIO
	@Test
	public void componenteSeletorSistemaSemNenhumSistema(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario1", "123456");
		Select seletor = new Select(driver.findElement(By.id("sistema")));
		List<WebElement> opcoes = seletor.getOptions();
		boolean sistema = opcoes.isEmpty();
		assertTrue(sistema);
	}
	*/
}
