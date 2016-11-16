package br.cefetrj.webdep.tests.support;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class TestSupport {
	
	private WebDriver driver;
	
	public TestSupport(WebDriver driver) {
		this.driver = driver;
	}

	public void visita() {
		driver.get("http://localhost:8080/webdep/index.jsp");
	}

	public void efetuaLogin(String usuario, String senha) {
		WebElement login = driver.findElement(By.id("login"));
		WebElement password = driver.findElement(By.id("senha"));
		login.sendKeys(usuario);
		password.sendKeys(senha);
		login.submit();
	}

	public void clicaLink(String url) {
		driver.findElement(By.linkText(url)).click();
	}

	public void acionaSelectSistema(String nomeSistema) {
		Select sistema = new Select(driver.findElement(By.id("sistema")));
		sistema.selectByVisibleText(nomeSistema);
	}
	
	public void logarIrAtePagina(String usuario,
								  String senha,
								  String sistema,
								  String menu,
								  String subMenu) {
		visita();
		efetuaLogin(usuario, senha);
		acionaSelectSistema(sistema);
		clicaLink(menu);
		clicaLink(subMenu);
	}
}
