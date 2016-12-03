package br.cefetrj.webdep.tests;

import static org.junit.Assert.assertEquals;
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
import br.cefetrj.webdep.model.entity.PadraoURL;
import br.cefetrj.webdep.model.entity.Permissao;
import br.cefetrj.webdep.model.entity.RegistroLogAcesso;
import br.cefetrj.webdep.model.entity.RegistroLogErro;
import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.model.entity.Usuario;
import br.cefetrj.webdep.model.entity.Versao;
import br.cefetrj.webdep.tests.support.TestSupport;

public class ConfiabilidadeUrlTabelaTest {

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
	private static TestSupport auxTest;
	
	
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
		
		Versao v1 = new Versao();
		v1.setNome("Versao1.01");
		v1.setSistema(s1);
		v1.setTimestampLiberacao(LocalDateTime.now());
		
		Versao v2 = new Versao();
		v2.setNome("Versao1.02");
		v2.setSistema(s1);
		v2.setTimestampLiberacao(LocalDateTime.now().plusMonths(4L));
		
		RegistroLogAcesso logA1 = new RegistroLogAcesso();
		logA1.setSistema(s1);
		logA1.setIp("222333444");
		logA1.setAgente("usuarioTesteLog");
		logA1.setTimestamp(LocalDateTime.now().minusMonths(1L));
		logA1.setUrl("index.html");
		logA1.setCodigo(200);
		
		RegistroLogAcesso logA2 = new RegistroLogAcesso();
		logA2.setSistema(s1);
		logA2.setIp("222333444");
		logA2.setAgente("usuarioTesteLog");
		logA2.setTimestamp(LocalDateTime.now().minusMonths(1L));
		logA2.setUrl("index.html");
		logA2.setCodigo(302);
		
		RegistroLogAcesso logA3 = new RegistroLogAcesso();
		logA3.setSistema(s1);
		logA3.setIp("222333444");
		logA3.setAgente("usuarioTesteLog");
		logA3.setTimestamp(LocalDateTime.now().minusMonths(1L));
		logA3.setUrl("index.html");
		logA3.setCodigo(200);
		
		RegistroLogAcesso logA4 = new RegistroLogAcesso();
		logA4.setSistema(s1);
		logA4.setIp("222333444");
		logA4.setAgente("usuarioTesteLog");
		logA4.setTimestamp(LocalDateTime.now().minusMonths(1L));
		logA4.setUrl("index.html");
		logA4.setCodigo(200);
		
		RegistroLogAcesso logA5 = new RegistroLogAcesso();
		logA5.setSistema(s1);
		logA5.setIp("222333444");
		logA5.setAgente("usuarioTesteLog");
		logA5.setTimestamp(LocalDateTime.now().minusMonths(1L));
		logA5.setUrl("index.html");
		logA5.setCodigo(302);
		
		RegistroLogAcesso logA6 = new RegistroLogAcesso();
		logA6.setSistema(s1);
		logA6.setIp("222333444");
		logA6.setAgente("usuarioTesteLog");
		logA6.setTimestamp(LocalDateTime.now().minusMonths(5L));
		logA6.setUrl("index.html");
		logA6.setCodigo(302);
		
		RegistroLogAcesso logA7 = new RegistroLogAcesso();
		logA7.setSistema(s1);
		logA7.setIp("222333444");
		logA7.setAgente("usuarioTesteLog");
		logA7.setTimestamp(LocalDateTime.now().minusMonths(5L));
		logA7.setUrl("index.html");
		logA7.setCodigo(200);
		
		RegistroLogAcesso logA8 = new RegistroLogAcesso();
		logA8.setSistema(s1);
		logA8.setIp("222333444");
		logA8.setAgente("usuarioTesteLog");
		logA8.setTimestamp(LocalDateTime.now().minusMonths(5L));
		logA8.setUrl("index.html");
		logA8.setCodigo(200);
		
		RegistroLogErro logE1 = new RegistroLogErro();
		logE1.setSistema(s1);
		logE1.setTimestamp(LocalDateTime.now().plusDays(5L));
		logE1.setNivel("500");
		logE1.setIp("222333444");
		logE1.setMensagem("Erro 500");
		
		RegistroLogErro logE2 = new RegistroLogErro();
		logE2.setSistema(s1);
		logE2.setTimestamp(LocalDateTime.now().plusMonths(5L));
		logE2.setNivel("404");
		logE2.setIp("222333444");
		logE2.setMensagem("Erro 404");
		
		PadraoURL pUrl = new PadraoURL();
		pUrl.setUsuario(u2);
		pUrl.setExpressaoRegular("[index.*]");
		pUrl.setNome("index_Sistema1");
		
		GenericDAO<Usuario> usuarioDao = pm.createGenericDAO(Usuario.class);
		GenericDAO<Sistema> sistemaDao = pm.createGenericDAO(Sistema.class);
		GenericDAO<Permissao> permissaoDao = pm.createGenericDAO(Permissao.class);
		GenericDAO<Versao> versaoDao = pm.createGenericDAO(Versao.class);
		GenericDAO<RegistroLogAcesso> logAcessoDao = pm.createGenericDAO(RegistroLogAcesso.class);
		GenericDAO<RegistroLogErro> logErroDao = pm.createGenericDAO(RegistroLogErro.class);
		GenericDAO<PadraoURL> pUrlDao = pm.createGenericDAO(PadraoURL.class);
		
		sistemaDao.insert(s1);
		sistemaDao.insert(s2);
		
		usuarioDao.insert(u1);
		usuarioDao.insert(u2);
		usuarioDao.insert(u3);
		
		permissaoDao.insert(p1);
		permissaoDao.insert(p2);
		
		versaoDao.insert(v1);
		versaoDao.insert(v2);
		
		logAcessoDao.insert(logA1);
		logAcessoDao.insert(logA2);
		logAcessoDao.insert(logA3);
		logAcessoDao.insert(logA4);
		logAcessoDao.insert(logA5);
		logAcessoDao.insert(logA6);
		logAcessoDao.insert(logA7);
		logAcessoDao.insert(logA8);
		
		logErroDao.insert(logE1);
		logErroDao.insert(logE2);
		
		pUrlDao.insert(pUrl);
		
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
        auxTest = new TestSupport(driver);
	}
	
	//TC61
	//@Test(expected=IllegalArgumentException.class)
	public void tabelaBuscarSemSelecoes(){
		auxTest.logarIrAtePagina("usuario2", "123456", "Sistema Teste", "Relatórios", "Confiabilidade das URLs");
		WebElement botaoBuscar = driver.findElement(By.id("buscar"));
		botaoBuscar.click();
		//List<WebElement> linhas = driver.findElements(By.tagName("tr"));
		//assertEquals(0, linhas.size());
	}
	//TC62
	//@Test
	public void tabelaSeletorVersoes(){
		auxTest.logarIrAtePagina("usuario2", "123456", "Sistema Teste", "Relatórios", "Confiabilidade das URLs");
		Select versoes = new Select(driver.findElement(By.id("versoes")));
		List<WebElement> valores = versoes.getOptions();
		assertEquals(0, valores.size());
	}
	//TC63
	//@Test
	public void tabelaVersoesComPadraoURL(){
		auxTest.logarIrAtePagina("usuario2", "123456", "Sistema Teste", "Relatórios", "Confiabilidade das URLs");
		Select padroesURL = new Select(driver.findElement(By.id("padrao-url")));
		padroesURL.selectByVisibleText("index_Sistema1");
		Select versoes = new Select(driver.findElement(By.id("versoes")));
		List<WebElement> valores = versoes.getOptions();
		boolean versao101 = valores.contains("Versao1.01");
		boolean versao102 = valores.contains("Versao1.02");
		assertTrue(versao101);
		assertTrue(versao102);
		assertEquals(2, valores.size());
	}
	//TC64
	//@Test
	public void tabelaCodigosHTTPErro(){
		auxTest.logarIrAtePagina("usuario2", "123456", "Sistema Teste", "Relatórios", "Confiabilidade das URLs");
		Select http = new Select(driver.findElement(By.id("codigo-erro")));
		List<WebElement> valores = http.getOptions();
		assertEquals(0, valores.size());
	}
	//TC65
	//@Test
	public void tabelaCodigosHTTPErroComPadraoUrl(){
		auxTest.logarIrAtePagina("usuario2", "123456", "Sistema Teste", "Relatórios", "Confiabilidade das URLs");
		Select padroesURL = new Select(driver.findElement(By.id("padrao-url")));
		padroesURL.selectByVisibleText("index_Sistema1");
		Select http = new Select(driver.findElement(By.id("codigo-erro")));
		List<WebElement> valores = http.getOptions();
		assertEquals(0, valores.size());
	}
	//TC66
	//@Test
	public void tabelaCodigosHTTPErroComPadraoUrlUmaVersao(){
		auxTest.logarIrAtePagina("usuario2", "123456", "Sistema Teste", "Relatórios", "Confiabilidade das URLs");
		Select padroesURL = new Select(driver.findElement(By.id("padrao-url")));
		padroesURL.selectByVisibleText("index_Sistema1");
		Select versoes = new Select(driver.findElement(By.id("versoes")));
		versoes.selectByVisibleText("Versao1.01");
		Select http = new Select(driver.findElement(By.id("codigo-erro")));
		List<WebElement> valores = http.getOptions();
		boolean codigo = valores.contains("500");
		assertTrue(codigo);
		assertEquals(1, valores.size());
	}
	//TC67
	//@Test
	public void tabelaCodigosHTTPErroComPadraoUrlMaisDeUmaVersao(){
		auxTest.logarIrAtePagina("usuario2", "123456", "Sistema Teste", "Relatórios", "Confiabilidade das URLs");
		Select padroesURL = new Select(driver.findElement(By.id("padrao-url")));
		padroesURL.selectByVisibleText("index_Sistema1");
		Select versoes = new Select(driver.findElement(By.id("versoes")));
		versoes.selectByVisibleText("Versao1.01");
		versoes.selectByVisibleText("Versao1.02");
		Select http = new Select(driver.findElement(By.id("codigo-erro")));
		List<WebElement> valores = http.getOptions();
		boolean codigo500 = valores.contains("500");
		boolean codigo400 = valores.contains("400");
		assertTrue(codigo500);
		assertTrue(codigo400);
		assertEquals(2, valores.size());
	}
	//TC68
	//@Test
	public void tabelaCodigosHTTPOk() {
		auxTest.logarIrAtePagina("usuario2", "123456", "Sistema Teste", "Relatórios", "Confiabilidade das URLs");
		Select http = new Select(driver.findElement(By.id("codigo-ok")));
		List<WebElement> valores = http.getOptions();
		assertEquals(0, valores.size());
	}
	//TC69
	//@Test
	public void tabelaCodigosHTTPOkComPadraoUrl(){
		auxTest.logarIrAtePagina("usuario2", "123456", "Sistema Teste", "Relatórios", "Confiabilidade das URLs");
		Select padroesURL = new Select(driver.findElement(By.id("padrao-url")));
		padroesURL.selectByVisibleText("index_Sistema1");
		Select http = new Select(driver.findElement(By.id("codigo-ok")));
		List<WebElement> valores = http.getOptions();
		assertEquals(0, valores.size());
	}
	//TC70
	//@Test
	public void tabelaCodigosHTTPOkComPadraoUrlUmaVersao(){
		auxTest.logarIrAtePagina("usuario2", "123456", "Sistema Teste", "Relatórios", "Confiabilidade das URLs");
		Select padroesURL = new Select(driver.findElement(By.id("padrao-url")));
		padroesURL.selectByVisibleText("index_Sistema1");
		Select versoes = new Select(driver.findElement(By.id("versoes")));
		versoes.selectByVisibleText("Versao1.01");
		Select http = new Select(driver.findElement(By.id("codigo-ok")));
		List<WebElement> valores = http.getOptions();
		boolean codigo = valores.contains("200");
		assertTrue(codigo);
		assertEquals(1, valores.size());
	}
	//TC71
	//@Test
	public void tabelaCodigosHTTPOkComPadraoUrlMaisDeUmaVersao(){
		auxTest.logarIrAtePagina("usuario2", "123456", "Sistema Teste", "Relatórios", "Confiabilidade das URLs");
		Select padroesURL = new Select(driver.findElement(By.id("padrao-url")));
		padroesURL.selectByVisibleText("index_Sistema1");
		Select versoes = new Select(driver.findElement(By.id("versoes")));
		versoes.selectByVisibleText("Versao1.01");
		versoes.selectByVisibleText("Versao1.02");
		Select http = new Select(driver.findElement(By.id("codigo-ok")));
		List<WebElement> valores = http.getOptions();
		boolean codigo = valores.contains("200");
		assertTrue(codigo);
		assertEquals(1, valores.size());
	}
	//TC72
	//@Test
	public void tabelaCodigosHTTPOkComPadraoUrlUmaVersaoCodigoErro(){
		auxTest.logarIrAtePagina("usuario2", "123456", "Sistema Teste", "Relatórios", "Confiabilidade das URLs");
		Select padroesURL = new Select(driver.findElement(By.id("padrao-url")));
		padroesURL.selectByVisibleText("index_Sistema1");
		Select versoes = new Select(driver.findElement(By.id("versoes")));
		versoes.selectByVisibleText("Versao1.01");
		Select httpErro = new Select(driver.findElement(By.id("codigo-erro")));
		httpErro.selectByVisibleText("500");
		Select httpOk = new Select(driver.findElement(By.id("codigo-ok")));
		List<WebElement> valores = httpOk.getOptions();
		boolean codigo = valores.contains("200");
		assertTrue(codigo);
		assertEquals(1, valores.size());
	}
	//TC73
	//@Test
	public void tabelaCodigosHTTPOkComPadraoUrlUmaVersaoMaisDeUmCodigoErro(){
		auxTest.logarIrAtePagina("usuario2", "123456", "Sistema Teste", "Relatórios", "Confiabilidade das URLs");
		Select padroesURL = new Select(driver.findElement(By.id("padrao-url")));
		padroesURL.selectByVisibleText("index_Sistema1");
		Select versoes = new Select(driver.findElement(By.id("versoes")));
		versoes.selectByVisibleText("Versao1.01");
		Select httpErro = new Select(driver.findElement(By.id("codigo-erro")));
		httpErro.selectByVisibleText("500");
		httpErro.selectByVisibleText("404");
		Select httpOk = new Select(driver.findElement(By.id("codigo-ok")));
		List<WebElement> valores = httpOk.getOptions();
		boolean codigo = valores.contains("200");
		assertTrue(codigo);
		assertEquals(1, valores.size());
	}
	//TC74 -- ERRO
	//@Test(expected=IllegalArgumentException.class)
	public void tabelaBuscarComPadraoUrl() {
		auxTest.logarIrAtePagina("usuario2", "123456", "Sistema Teste", "Relatórios", "Confiabilidade das URLs");
		Select padroesURL = new Select(driver.findElement(By.id("padrao-url")));
		padroesURL.selectByVisibleText("index_Sistema1");
		WebElement botaoBuscar = driver.findElement(By.id("buscar"));
		botaoBuscar.click();
	}
	//TC75 -- ERRO
	//@Test(expected=IllegalArgumentException.class)
	public void tabelaBuscarComVersoes(){
		auxTest.logarIrAtePagina("usuario2", "123456", "Sistema Teste", "Relatórios", "Confiabilidade das URLs");
		Select padroesURL = new Select(driver.findElement(By.id("padrao-url")));
		padroesURL.selectByVisibleText("index_Sistema1");
		Select versoes = new Select(driver.findElement(By.id("versoes")));
		versoes.selectByVisibleText("Versao1.01");
		WebElement botaoBuscar = driver.findElement(By.id("buscar"));
		botaoBuscar.click();
	}
	//TC76 -- ERRO
	//@Test(expected=IllegalArgumentException.class)
	public void tabelaBuscarComCodigoErro(){
		auxTest.logarIrAtePagina("usuario2", "123456", "Sistema Teste", "Relatórios", "Confiabilidade das URLs");
		Select padroesURL = new Select(driver.findElement(By.id("padrao-url")));
		padroesURL.selectByVisibleText("index_Sistema1");
		Select versoes = new Select(driver.findElement(By.id("versoes")));
		versoes.selectByVisibleText("Versao1.01");
		Select httpErro = new Select(driver.findElement(By.id("codigo-erro")));
		httpErro.selectByVisibleText("500");
		WebElement botaoBuscar = driver.findElement(By.id("buscar"));
		botaoBuscar.click();
	}
	//TC77 -- ERRO
	//@Test(expected=IllegalArgumentException.class)
	public void tabelaBuscarComCodigoOk(){
		auxTest.logarIrAtePagina("usuario2", "123456", "Sistema Teste", "Relatórios", "Confiabilidade das URLs");
		Select padroesURL = new Select(driver.findElement(By.id("padrao-url")));
		padroesURL.selectByVisibleText("index_Sistema1");
		Select versoes = new Select(driver.findElement(By.id("versoes")));
		versoes.selectByVisibleText("Versao1.01");
		Select httpOk = new Select(driver.findElement(By.id("codigo-ok")));
		httpOk.selectByVisibleText("200");
		WebElement botaoBuscar = driver.findElement(By.id("buscar"));
		botaoBuscar.click();
	}
	//TC78
	//@Test
	public void tabelaBuscarComTodosOsSeletores(){
		auxTest.logarIrAtePagina("usuario2", "123456", "Sistema Teste", "Relatórios", "Confiabilidade das URLs");
		Select padroesURL = new Select(driver.findElement(By.id("padrao-url")));
		padroesURL.selectByVisibleText("index_Sistema1");
		Select versoes = new Select(driver.findElement(By.id("versoes")));
		versoes.selectByVisibleText("Versao1.01");
		Select httpErro = new Select(driver.findElement(By.id("codigo-erro")));
		httpErro.selectByVisibleText("500");
		Select httpOk = new Select(driver.findElement(By.id("codigo-ok")));
		httpOk.selectByVisibleText("200");
		WebElement botaoBuscar = driver.findElement(By.id("buscar"));
		botaoBuscar.click();
		List<WebElement> linhas = driver.findElements(By.tagName("tr"));
		boolean probabilidade = driver.getPageSource().contains("50%");
		assertTrue(probabilidade);
		assertEquals(1, linhas.size());
	}
	//TC79
	//@Test
	public void tabelaBuscarComTodosOsSeletoresMaisDeUmaVersao(){
		auxTest.logarIrAtePagina("usuario2", "123456", "Sistema Teste", "Relatórios", "Confiabilidade das URLs");
		Select padroesURL = new Select(driver.findElement(By.id("padrao-url")));
		padroesURL.selectByVisibleText("index_Sistema1");
		Select versoes = new Select(driver.findElement(By.id("versoes")));
		versoes.selectByVisibleText("Versao1.01");
		versoes.selectByVisibleText("Versao1.02");
		Select httpErro = new Select(driver.findElement(By.id("codigo-erro")));
		httpErro.selectByVisibleText("500");
		Select httpOk = new Select(driver.findElement(By.id("codigo-ok")));
		httpOk.selectByVisibleText("200");
		WebElement botaoBuscar = driver.findElement(By.id("buscar"));
		botaoBuscar.click();
		List<WebElement> linhas = driver.findElements(By.tagName("tr"));
		boolean probabilidade = driver.getPageSource().contains("50%");
		assertTrue(probabilidade);
		assertEquals(1, linhas.size());
	}
	//TC80
	//@Test
	public void tabelaBuscarComTodosOsSeletoresMaisDeUmCodigoErro(){
		auxTest.logarIrAtePagina("usuario2", "123456", "Sistema Teste", "Relatórios", "Confiabilidade das URLs");
		Select padroesURL = new Select(driver.findElement(By.id("padrao-url")));
		padroesURL.selectByVisibleText("index_Sistema1");
		Select versoes = new Select(driver.findElement(By.id("versoes")));
		versoes.selectByVisibleText("Versao1.01");
		versoes.selectByVisibleText("Versao1.02");
		Select httpErro = new Select(driver.findElement(By.id("codigo-erro")));
		httpErro.selectByVisibleText("500");
		httpErro.selectByVisibleText("404");
		Select httpOk = new Select(driver.findElement(By.id("codigo-ok")));
		httpOk.selectByVisibleText("200");
		WebElement botaoBuscar = driver.findElement(By.id("buscar"));
		botaoBuscar.click();
		List<WebElement> linhas = driver.findElements(By.tagName("tr"));
		boolean probabilidade = driver.getPageSource().contains("66%");
		assertTrue(probabilidade);
		assertEquals(1, linhas.size());
	}
	//TC81
	//@Test
	public void tabelaBuscarComTodosOsSeletoresMaisDeUmCodigoOk(){
		auxTest.logarIrAtePagina("usuario2", "123456", "Sistema Teste", "Relatórios", "Confiabilidade das URLs");
		Select padroesURL = new Select(driver.findElement(By.id("padrao-url")));
		padroesURL.selectByVisibleText("index_Sistema1");
		Select versoes = new Select(driver.findElement(By.id("versoes")));
		versoes.selectByVisibleText("Versao1.01");
		versoes.selectByVisibleText("Versao1.02");
		Select httpErro = new Select(driver.findElement(By.id("codigo-erro")));
		httpErro.selectByVisibleText("500");
		httpErro.selectByVisibleText("404");
		Select httpOk = new Select(driver.findElement(By.id("codigo-ok")));
		httpOk.selectByVisibleText("200");
		httpOk.selectByVisibleText("302");
		WebElement botaoBuscar = driver.findElement(By.id("buscar"));
		botaoBuscar.click();
		List<WebElement> linhas = driver.findElements(By.tagName("tr"));
		boolean probabilidade = driver.getPageSource().contains("50%");
		assertTrue(probabilidade);
		assertEquals(1, linhas.size());
	}
	//TC82
	//@Test
	public void conferenciaDadosDaTabela(){
		auxTest.logarIrAtePagina("usuario2", "123456", "Sistema Teste", "Relatórios", "Confiabilidade das URLs");
		Select padroesURL = new Select(driver.findElement(By.id("padrao-url")));
		padroesURL.selectByVisibleText("index_Sistema1");
		Select versoes = new Select(driver.findElement(By.id("versoes")));
		versoes.selectByVisibleText("Versao1.01");
		Select httpErro = new Select(driver.findElement(By.id("codigo-erro")));
		httpErro.selectByVisibleText("500");
		Select httpOk = new Select(driver.findElement(By.id("codigo-ok")));
		httpOk.selectByVisibleText("200");
		WebElement botaoBuscar = driver.findElement(By.id("buscar"));
		botaoBuscar.click();
		List<WebElement> linhas = driver.findElements(By.tagName("tr"));
		boolean acessos = driver.getPageSource().contains("5");
		boolean falhas = driver.getPageSource().contains("1");
		boolean probabilidade = driver.getPageSource().contains("17%");
		assertTrue(acessos);
		assertTrue(falhas);
		assertTrue(probabilidade);
		assertEquals(1, linhas.size());
	}
	//TC83
	//@Test
	public void tabelaVoltar(){
		auxTest.logarIrAtePagina("usuario2", "123456", "Sistema Teste", "Relatórios", "Confiabilidade das URLs");
		WebElement botaoVoltar = driver.findElement(By.id("voltar"));
		botaoVoltar.click();
		boolean pagina = driver.getTitle().contains("WebDep");
		assertTrue(pagina);
	}
	//TC84 -- GRAFICO
	//@Test
	public void conferenciaDadosGrafico(){
		auxTest.logarIrAtePagina("usuario2", "123456", "Sistema Teste", "Relatórios", "Confiabilidade das URLs");
		Select padroesURL = new Select(driver.findElement(By.id("padrao-url")));
		padroesURL.selectByVisibleText("index_Sistema1");
		Select versoes = new Select(driver.findElement(By.id("versoes")));
		versoes.selectByVisibleText("Versao1.01");
		Select httpErro = new Select(driver.findElement(By.id("codigo-erro")));
		httpErro.selectByVisibleText("500");
		Select httpOk = new Select(driver.findElement(By.id("codigo-ok")));
		httpOk.selectByVisibleText("200");
		auxTest.clicaLink("Gráfico");
		WebElement botaoBuscar = driver.findElement(By.id("buscar"));
		botaoBuscar.click();
		boolean acessos = driver.getPageSource().contains("5");
		boolean falhas = driver.getPageSource().contains("1");
		boolean probabilidade = driver.getPageSource().contains("17%");
		assertTrue(acessos);
		assertTrue(falhas);
		assertTrue(probabilidade);
	}
	//TC85 -- GRAFICO
	//@Test
	public void graficoBuscarComTodosOsSeletoresMaisDeUmaVersao(){
		auxTest.logarIrAtePagina("usuario2", "123456", "Sistema Teste", "Relatórios", "Confiabilidade das URLs");
		Select padroesURL = new Select(driver.findElement(By.id("padrao-url")));
		padroesURL.selectByVisibleText("index_Sistema1");
		Select versoes = new Select(driver.findElement(By.id("versoes")));
		versoes.selectByVisibleText("Versao1.01");
		versoes.selectByVisibleText("Versao1.02");
		Select httpErro = new Select(driver.findElement(By.id("codigo-erro")));
		httpErro.selectByVisibleText("500");
		Select httpOk = new Select(driver.findElement(By.id("codigo-ok")));
		httpOk.selectByVisibleText("200");
		auxTest.clicaLink("Gráfico");
		WebElement botaoBuscar = driver.findElement(By.id("buscar"));
		botaoBuscar.click();
		boolean acessos = driver.getPageSource().contains("8");
		boolean falhas = driver.getPageSource().contains("2");
		boolean probabilidade = driver.getPageSource().contains("20%");
		assertTrue(acessos);
		assertTrue(falhas);
		assertTrue(probabilidade);
	}
	//TC86 -- GRAFICO
	//@Test
	public void graficoBuscarComTodosOsSeletoresMaisDeUmCodigoErro(){
		auxTest.logarIrAtePagina("usuario2", "123456", "Sistema Teste", "Relatórios", "Confiabilidade das URLs");
		Select padroesURL = new Select(driver.findElement(By.id("padrao-url")));
		padroesURL.selectByVisibleText("index_Sistema1");
		Select versoes = new Select(driver.findElement(By.id("versoes")));
		versoes.selectByVisibleText("Versao1.01");
		versoes.selectByVisibleText("Versao1.02");
		Select httpErro = new Select(driver.findElement(By.id("codigo-erro")));
		httpErro.selectByVisibleText("500");
		httpErro.selectByVisibleText("404");
		Select httpOk = new Select(driver.findElement(By.id("codigo-ok")));
		httpOk.selectByVisibleText("200");
		auxTest.clicaLink("Gráfico");
		WebElement botaoBuscar = driver.findElement(By.id("buscar"));
		botaoBuscar.click();
		boolean acessos = driver.getPageSource().contains("8");
		boolean falhas = driver.getPageSource().contains("2");
		boolean probabilidade = driver.getPageSource().contains("20%");
		assertTrue(acessos);
		assertTrue(falhas);
		assertTrue(probabilidade);
	}
	//TC87 -- GRAFICO
	//@Test
	public void graficoBuscarComTodosOsSeletoresMaisDeUmCodigoOk(){
		auxTest.logarIrAtePagina("usuario2", "123456", "Sistema Teste", "Relatórios", "Confiabilidade das URLs");
		Select padroesURL = new Select(driver.findElement(By.id("padrao-url")));
		padroesURL.selectByVisibleText("index_Sistema1");
		Select versoes = new Select(driver.findElement(By.id("versoes")));
		versoes.selectByVisibleText("Versao1.01");
		versoes.selectByVisibleText("Versao1.02");
		Select httpErro = new Select(driver.findElement(By.id("codigo-erro")));
		httpErro.selectByVisibleText("500");
		httpErro.selectByVisibleText("404");
		Select httpOk = new Select(driver.findElement(By.id("codigo-ok")));
		httpOk.selectByVisibleText("200");
		httpOk.selectByVisibleText("302");
		auxTest.clicaLink("Gráfico");
		WebElement botaoBuscar = driver.findElement(By.id("buscar"));
		botaoBuscar.click();
		boolean acessos = driver.getPageSource().contains("8");
		boolean falhas = driver.getPageSource().contains("2");
		boolean probabilidade = driver.getPageSource().contains("20%");
		assertTrue(acessos);
		assertTrue(falhas);
		assertTrue(probabilidade);
	}
	//TC88
	//@Test
	public void graficoVoltar(){
		auxTest.logarIrAtePagina("usuario2", "123456", "Sistema Teste", "Relatórios", "Confiabilidade das URLs");
		auxTest.clicaLink("Gráfico");
		WebElement botaoVoltar = driver.findElement(By.id("voltar"));
		botaoVoltar.click();
		boolean pagina = driver.getTitle().contains("WebDep");
		assertTrue(pagina);
	}
}
