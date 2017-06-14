package br.cefetrj.webdep.teste;

import java.util.List;

import br.cefetrj.webdep.model.entity.Permissao;
import br.cefetrj.webdep.services.PermissaoService;

public class TestePermissaoUsuario {

	public static void main (String [] args){
		
		
		List<Permissao> pList = null;
		pList = PermissaoService.PermissaoUsuario(1L);
		System.out.print("acabou");
		
	}
	
}
