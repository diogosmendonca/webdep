package br.cefetrj.webdep.tests.smoke;

import static org.junit.Assert.*;

import org.junit.Test;

import br.cefetrj.webdep.services.EMailServices;

public class EmailSmokeTest {

	@Test
	public void emailSmokeTest() {
		
		//coloque seus dados para testar
		//ative aplicativos menos seguros para funcionar. 
		//Configuração em https://www.google.com/settings/security/lesssecureapps.
		String from = "usuario@gmail.com";
		String password = "";//lembre-se de não dar commit na sua senha ;)
		String to[] = {"usuario@gmail.com"};
		String subject = "Teste";
		String body = "Teste";
		
		assertTrue(EMailServices.sendFromGMail(from, password, to, subject, body));
		
	}

}
