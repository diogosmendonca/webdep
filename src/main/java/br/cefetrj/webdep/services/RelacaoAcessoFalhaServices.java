package br.cefetrj.webdep.services;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import br.cefetrj.webdep.model.dao.PersistenceManager;
import br.cefetrj.webdep.model.entity.Versao;

public class RelacaoAcessoFalhaServices {
	public void buscaDados(List<Versao> versionList, String[] errorList, String[] okList){
		List<Object> l = null;
		Iterator it = l.iterator();
		
		while(it.hasNext()){
			Versao v = (Versao)it.next();
			for(int j = 0; j < errorList.length; j++){
				Query q = PersistenceManager.getInstance().createQuery("FROM RegistroLogAcesso r WHERE r.codigo = :error and sistema_id == :version_sistema");
				q.setParameter("error", errorList[i]);
				q.setParameter("version_sistema", (Versao)versionList[i].getSistema().getId());
			}
		}
	}
}
