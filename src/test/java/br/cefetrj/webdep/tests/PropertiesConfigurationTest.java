package br.cefetrj.webdep.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import br.cefetrj.webdep.model.dao.ConfigurationDAO;
import br.cefetrj.webdep.model.entity.ConfiguracaoSistema;

public class PropertiesConfigurationTest {

	@Test
	public void storeLoadConfigTest() {
		
		String pathConfigFile = "src/test/resources/config.properties";
		String banco = "banco";
		String urlBanco = "urlBanco";
		String usuarioBanco = "usuarioBanco";
		String senhaBanco = "senhaBanco";
		String provedorSmtp = "provedorSmtp";
		String usuarioEmail = "usuarioEmail";
		String senhaEmail = "senhaEmail";
		
		ConfiguracaoSistema cs = new ConfiguracaoSistema();
		cs.setBanco(banco);
		cs.setUrlBanco(urlBanco);
		cs.setUsuarioBanco(usuarioBanco);
		cs.setSenhaBanco(senhaBanco);
		cs.setProvedorSmtp(provedorSmtp);
		cs.setUsuarioEmail(usuarioEmail);
		cs.setSenhaEmail(senhaEmail);
		
		ConfigurationDAO.store(cs, pathConfigFile);
		
		ConfiguracaoSistema csLoad = ConfigurationDAO.load(pathConfigFile);
		assertEquals(banco, csLoad.getBanco());
		assertEquals(urlBanco, csLoad.getUrlBanco());
		assertEquals(usuarioBanco, csLoad.getUsuarioBanco());
		assertEquals(senhaBanco, csLoad.getSenhaBanco());
		assertEquals(provedorSmtp, csLoad.getProvedorSmtp());
		assertEquals(usuarioEmail, csLoad.getUsuarioEmail());
		assertEquals(senhaEmail, csLoad.getSenhaEmail());
		
	}

}
