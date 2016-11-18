package br.cefetrj.webdep.teste;

import br.cefetrj.webdep.model.entity.Permissao;
import br.cefetrj.webdep.model.entity.Servidor;
import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.model.entity.Usuario;
import br.cefetrj.webdep.services.PermissaoService;
import br.cefetrj.webdep.services.UsuarioService;

public class TestePermissao {

public static void main(String [] args){
		
		Usuario usu = new Usuario();
		Sistema sis = new Sistema();
		Permissao per = new Permissao();
		
		usu.setNome("felipe");
		usu.setAdmGeral(true);
		//alterado devido ao perfil ser do usuário e não da permissão
		usu.setPerfil("Administartor");
				
		sis.setNome("LINUX");
		sis.setPastaLogErro("logAcesso");
		
		per.setSistema(sis);
		per.setUsuario(usu);
		
		PermissaoService.insertPermissao(per);
		
		
	}
	
	
}
