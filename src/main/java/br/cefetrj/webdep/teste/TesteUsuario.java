package br.cefetrj.webdep.teste;

import br.cefetrj.webdep.model.entity.Permissao;
import br.cefetrj.webdep.model.entity.Usuario;
import br.cefetrj.webdep.services.UsuarioService;

//Class de teste para chegar a nivel do servico

public class TesteUsuario {

	public static void main(String [] args){
		
		Usuario u = new Usuario();
		
		u.setId(1L);

		Usuario uSaida = UsuarioService.getUsuario(u);
		
		System.out.println(uSaida.getNome());
		
		for (Permissao permissao : uSaida.getPermissoes()) {
			
			System.out.println(permissao.getSistema().getNome());
			
		}
		
		
	}
	
	
}
