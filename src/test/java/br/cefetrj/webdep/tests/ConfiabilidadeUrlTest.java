package br.cefetrj.webdep.tests;

public class ConfiabilidadeUrlTest {

	/*
	private WebDriver driver;
	private TestSupport auxTest;
	
	//@Before
	public void inicializa(){
		this.driver = new FirefoxDriver();
		this.auxTest = new TestSupport(driver);
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
	
	//@After
	public void encerra(){
		this.driver.close();
	}
	*/
}
