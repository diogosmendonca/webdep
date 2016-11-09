package br.cefetrj.webdep.tests;

import static org.junit.Assert.fail;

import org.junit.Test;

import br.cefetrj.webdep.model.dao.PersistenceManager;
import br.cefetrj.webdep.model.entity.ConfiguracaoSistema;
import br.cefetrj.webdep.model.entity.PadraoURL;
import br.cefetrj.webdep.model.entity.Permissao;
import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.model.entity.Usuario;
import br.cefetrj.webdep.model.entity.Versao;

public class JPASmokeTest {

	private PersistenceManager pm = PersistenceManager.getInstance();
		
	@Test
	public void configuracaoSistemaSmokeTest() {
		try{
			pm.createGenericDAO(ConfiguracaoSistema.class).listAll();
		}catch(Exception e){
			fail("Não foi possível buscar dados de Configuração Sistema no banco");
		}
	}
	
	@Test
	public void padraoURLSmokeTest() {
		try{
			pm.createGenericDAO(PadraoURL.class).listAll();
		}catch(Exception e){
			fail("Não foi possível buscar dados de Padrão URL no banco");
		}
	}
	
	@Test
	public void permissaoSmokeTest() {
		try{
			pm.createGenericDAO(Permissao.class).listAll();
		}catch(Exception e){
			fail("Não foi possível buscar dados de Permissão no banco");
		}
	}
	
	@Test
	public void sistemaSmokeTest() {
		try{
			pm.createGenericDAO(Sistema.class).listAll();
		}catch(Exception e){
			fail("Não foi possível buscar dados de Sistema no banco");
		}
	}
	
	@Test
	public void usuarioSmokeTest() {
		try{
			pm.createGenericDAO(Usuario.class).listAll();
		}catch(Exception e){
			fail("Não foi possível buscar dados de Usuario no banco");
		}
	}
	
	@Test
	public void versaoSmokeTest() {
		try{
			pm.createGenericDAO(Versao.class).listAll();
		}catch(Exception e){
			fail("Não foi possível buscar dados de Usuario no banco");
		}
	}

}
