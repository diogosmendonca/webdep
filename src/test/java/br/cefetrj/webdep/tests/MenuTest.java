package br.cefetrj.webdep.tests;

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
		
		/*
        if (System.getProperty("os.name").contains("Windows")){
        	System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\geckodriver.exe");
        }else{
        	System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
        }
        
        driver = new FirefoxDriver();
		*/
		if (System.getProperty("os.name").contains("Windows")){
        	System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
        }else{
        	System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        }
        
        driver = new ChromeDriver();
        auxMenu = new TestSupport(driver);
	}
	
	//TC01
	//@Test
	public void cadastrarSistemaLinkDaPrincipalComAdministradorGeral() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario1", "123456");
		auxMenu.clicaLink("Clique aqui para cadastrar");
		boolean paginaCerta = driver.getTitle().contains("Cadastrar Sistema");
		assertTrue(paginaCerta);
	}
	//TC02
	//@Test
	public void cadastrarSistemaLinkDaPrincipalComAdministrador() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Clique aqui para cadastrar");
		boolean paginaCerta = driver.getTitle().contains("Cadastrar Sistema");
		assertTrue(paginaCerta);
	}
	//TC03
	//@Test
	public void cadastrarSistemaLinkDaPrincipalComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		WebElement semLink = driver.findElement(By.linkText("Clique aqui para cadastrar"));
		assertNull(semLink);
	}
	//TC04
	//@Test
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
	//@Test
	public void selecionarSistemaLinkDaPrincipalComAdministrador(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Selecione um sistema");
		boolean sistema = driver.findElements(By.id("sistema")).contains("Sistema Teste");
		assertTrue(sistema);
	}
	//TC06
	//@Test
	public void selecionarSistemaLinkDaPrincipalComAnalista(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.clicaLink("Selecione um sistema");
		boolean sistema = driver.findElements(By.id("sistema")).contains("Sistema Teste");
		assertTrue(sistema);
	}
	//TC07
	//@Test
	public void registrarVersaoLinkDaPrincipalComAdministradorGeral(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario1", "123456");
		auxMenu.clicaLink("Registre uma versão");
		boolean paginaCerta = driver.getTitle().contains("Cadastrar Nova Versão");
		assertTrue(paginaCerta);
	}
	//TC08
	//@Test
	public void registrarVersaoLinkDaPrincipalComAdministrador(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Registre uma versão");
		boolean paginaCerta = driver.getTitle().contains("Cadastrar Nova Versão");
		assertTrue(paginaCerta);
	}
	//TC09
	//@Test
	public void registrarVersaoLinkDaPrincipalComAnalista(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		WebElement semLink = driver.findElement(By.linkText("Registre uma versão"));
		assertNull(semLink);
	}
	//TC10
	//@Test
	public void importarLogsLinkDaPrincipalComAdministradorGeral() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario1", "123456");
		auxMenu.clicaLink("Importe manualmente");
		boolean paginaCerta = driver.getTitle().contains("Importar Logs");
		assertTrue(paginaCerta);
	}
	//TC11
	//@Test
	public void importarLogsLinkDaPrincipalComAdministrador() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Importe manualmente");
		boolean paginaCerta = driver.getTitle().contains("Importar Logs");
		assertTrue(paginaCerta);
	}
	//TC12
	//@Test
	public void importarLogsLinkDaPrincipalComAnalista(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		WebElement semLink = driver.findElement(By.linkText("Importar Logs"));
		assertNull(semLink);
	}
	//TC13
	//@Test
	public void relatoriosPerfilLinkDaPrincipalComAdministradorGeral() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario1", "123456");
		auxMenu.clicaLink("Perfil de Acesso");
		boolean paginaCerta = driver.getTitle().contains("Perfil de Acesso");
		assertTrue(paginaCerta);
	}
	//TC14
	//@Teste
	public void relatoriosPerfilLinkDaPrincipalComAdministrador() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Perfil de Acesso");
		boolean paginaCerta = driver.getTitle().contains("Perfil de Acesso");
		assertTrue(paginaCerta);
	}
	//TC15
	//@Teste
	public void relatoriosPerfilLinkDaPrincipalComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.clicaLink("Perfil de Acesso");
		boolean paginaCerta = driver.getTitle().contains("Perfil de Acesso");
		assertTrue(paginaCerta);
	}
	//TC16
	//@Test
	public void relatoriosErrosLinkDaPrincipalComAdministradorGeral() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario1", "123456");
		auxMenu.clicaLink("Erros no Sistema");
		boolean paginaCerta = driver.getTitle().contains("Confiabilidade das URLs");
		assertTrue(paginaCerta);
	}
	//TC17
	//@Test
	public void relatoriosErrosLinkDaPrincipalComAdministrador() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Erros no Sistema");
		boolean paginaCerta = driver.getTitle().contains("Confiabilidade das URLs");
		assertTrue(paginaCerta);
	}
	//TC18
	//@Test
	public void relatoriosErrosLinkDaPrincipalComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.clicaLink("Erros no Sistema");
		boolean paginaCerta = driver.getTitle().contains("Confiabilidade das URLs");
		assertTrue(paginaCerta);
	}
	//TC19
	//@Test
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
	//@Teste
	public void menuWebdepComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		WebElement semLink = driver.findElement(By.linkText("WebDep"));
		assertNull(semLink);
	}
	//TC21
	//@Teste
	public void menuWebdepCadastrarUsuario(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("WebDep");
		auxMenu.clicaLink("Cadastrar Usuário");
		boolean paginaCerta = driver.getTitle().contains("Cadastrar Usuário");
		assertTrue(paginaCerta);
	}
	//TC22
	//@Teste
	public void menuWebDepListarUsuarios() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("WebDep");
		auxMenu.clicaLink("Listar/Alterar/Excluir Usuários");
		boolean paginaCerta = driver.getTitle().contains("Listar/Alterar/Excluir Usuários");
		assertTrue(paginaCerta);
	}
	//TC23
	//@Teste
	public void menuWebdepConfiguracoes() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario1", "123456");
		auxMenu.clicaLink("WebDep");
		auxMenu.clicaLink("Configurações");
		boolean paginaCerta = driver.getTitle().contains("Configurações");
		assertTrue(paginaCerta);
	}
	//TC24
	//@Teste
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
	//@Teste
	public void menuSistemasComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		WebElement semLink = driver.findElement(By.linkText("Sistemas"));
		assertNull(semLink);
	}
	//TC26
	//@Teste
	public void menuSistemasCadastrar() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Sistemas");
		auxMenu.clicaLink("Cadastrar Sistema");
		boolean paginaCerta = driver.getTitle().contains("Cadastrar Sistema");
		assertTrue(paginaCerta);
	}
	//TC27
	//@Teste
	public void menuSistemasListar(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Sistemas");
		auxMenu.clicaLink("Listar/Atualizar/Excluir Sistema");
		boolean paginaCerta = driver.getTitle().contains("Listar/Atualizar/Excluir Sistema");
		assertTrue(paginaCerta);
	}
	//TC28
	//@Teste
	public void menuSistemasVersao(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Sistemas");
		auxMenu.clicaLink("Cadastrar Nova Versão");
		boolean paginaCerta = driver.getTitle().contains("Cadastrar Nova Versão");
		assertTrue(paginaCerta);
	}
	//TC29
	//@Teste
	public void menuSistemasListarVersao(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Sistemas");
		auxMenu.clicaLink("Listar/Atualizar/Excluir Versões");
		boolean paginaCerta = driver.getTitle().contains("Listar/Atualizar/Excluir Versões");
		assertTrue(paginaCerta);
	}
	//TC30
	//@Teste
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
	//@Teste
	public void menuLogsComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		WebElement semLink = driver.findElement(By.linkText("Logs"));
		assertNull(semLink);
	}
	//TC32
	//@Teste
	public void menuLogsImportar(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Logs");
		auxMenu.clicaLink("Importar Logs");
		boolean paginaCerta = driver.getTitle().contains("Importar Logs");
		assertTrue(paginaCerta);
	}
	//TC33
	//@Teste
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
	//@Test(expected=IllegalArgumentException.class)
	public void menuLogsConsultarAcessoSemSistemaSelecionado(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Logs");
		auxMenu.clicaLink("Consultar/Excluir Registros de Acesso");
	}
	//TC35 -- ERRO
	//@Test(expected=IllegalArgumentException.class)
	public void menuLogsConsultarAcessoSemNenhumSistema(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Logs");
		auxMenu.clicaLink("Consultar/Excluir Registros de Acesso");
	}
	//TC36
	//@Teste
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
	//@Test(expected=IllegalArgumentException.class)
	public void menuLogsConsultarErroSemSistemaSelecionado(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Logs");
		auxMenu.clicaLink("Consultar/Excluir Registros de Erro");
	}
	//TC38 -- ERRO
	//@Test(expected=IllegalArgumentException.class)
	public void menuLogsConsultarErroSemNenhumSistema(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Logs");
		auxMenu.clicaLink("Consultar/Excluir Registros de Erro");
	}
	//TC39
	//@Teste
	public void menuRelatorioComAdministrador() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Relatórios");
		boolean menuCorreto = driver.getPageSource().contains("Perfil de Acesso") &&	
				driver.getPageSource().contains("Confiabilidade das URLs");
		assertTrue(menuCorreto);
	}
	//TC40
	//@Teste
	public void menuRelatorioComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.clicaLink("Relatórios");
		boolean menuCorreto = driver.getPageSource().contains("Perfil de Acesso") &&	
				driver.getPageSource().contains("Confiabilidade das URLs");
		assertTrue(menuCorreto);
	}
	//TC41
	//@Teste
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
	//@Test(expected=IllegalArgumentException.class)
	public void menuRelatoriosPerfilAcessoSemSistemaSelecionado(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Relatórios");
		auxMenu.clicaLink("Perfil de Acesso");
	}
	//TC43 -- ERRO
	//@Test(expected=IllegalArgumentException.class)
	public void menuRelatoriosPerfilAcessoSemNenhumSistema(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Relatórios");
		auxMenu.clicaLink("Perfil de Acesso");
	}
	//TC44
	//@Teste
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
	//@Teste
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
	//@Teste
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
	//@Test(expected=IllegalArgumentException.class)
	public void menuRelatoriosConfiabilidadeURLSemSistemaSelecionado(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.clicaLink("Relatórios");
		auxMenu.clicaLink("Confiabilidade das URLs");
	}
	//TC48 -- ERRO
	//@Test(expected=IllegalArgumentException.class)
	public void menuRelatoriosConfiabilidadeURLSemNenhumSistema(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.clicaLink("Relatórios");
		auxMenu.clicaLink("Confiabilidade das URLs");
	}
	//TC49
	//@Teste
	public void menuIdiomaComAdministrador() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Idioma");
		boolean menuCorreto = driver.getPageSource().contains("Inglês") &&	
				driver.getPageSource().contains("Português");
		assertTrue(menuCorreto);
	}
	//TC50
	//@Teste
	public void menuIdiomaComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.clicaLink("Idioma");
		boolean menuCorreto = driver.getPageSource().contains("Inglês") &&	
				driver.getPageSource().contains("Português");
		assertTrue(menuCorreto);
	}
	//TC51
	//@Teste
	public void menuIdiomaInglesComAdministrador() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario2", "123456");
		auxMenu.clicaLink("Idioma");
		auxMenu.clicaLink("Inglês");
		boolean traduziu = driver.getPageSource().contains("Language");
		assertTrue(traduziu);
	}
	//TC52
	//@Teste
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
	//@Teste
	public void menuIdiomaInglesComAnalista() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.clicaLink("Idioma");
		auxMenu.clicaLink("Inglês");
		boolean traduziu = driver.getPageSource().contains("Language");
		assertTrue(traduziu);
	}
	//TC54
	//@Teste
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
	//@Teste
	public void menuSair() {
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario3", "123456");
		auxMenu.clicaLink("Sair");
		boolean paginaCerta = driver.getPageSource().contains("Tem certeza que deseja sair?");
		assertTrue(paginaCerta);
	}
	//TC56 -- BOTAO
	//@Teste
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
	//@Teste
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
	//@Teste
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
	//@Teste
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
	//@Teste
	public void componenteSeletorSistemaSemNenhumSistema(){
		auxMenu.visita();
		auxMenu.efetuaLogin("usuario1", "123456");
		Select seletor = new Select(driver.findElement(By.id("sistema")));
		List<WebElement> opcoes = seletor.getOptions();
		boolean sistema = opcoes.isEmpty();
		assertTrue(sistema);
	}
}
