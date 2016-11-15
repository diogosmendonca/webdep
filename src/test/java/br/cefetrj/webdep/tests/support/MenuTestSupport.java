package br.cefetrj.webdep.tests.support;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MenuTestSupport {
	
	private WebDriver driver;
	
	public MenuTestSupport(WebDriver driver) {
		this.driver = driver;
	}

	public void visita() {
		driver.get("localhost:8080/index.jsp");
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

}
