package br.cefetrj.webdep.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.cefetrj.webdep.DTO.Defeito;
import br.cefetrj.webdep.model.dao.GenericDAO;
import br.cefetrj.webdep.model.dao.PersistenceManager;
import br.cefetrj.webdep.model.entity.RegistroLogErro;

/**
 * Created by renatoor on 11/25/16.
 */
public class LogErroServices {
    public static List<RegistroLogErro> buscarLog(LocalDateTime ldtInicial, LocalDateTime ldtFinal, String buscar) {

        PersistenceManager pm = PersistenceManager.getInstance();
        Query query;

        if(buscar.equals("")) {
            query = pm.createQuery("from RegistroLogErro where timestamp " +
                    "between :inicio and :fim");
        }
        else {
            query = pm.createQuery("from RegistroLogErro where " +
                    "ip like :busca or nivel like :busca or " +
                    "mensagem like :busca " +
                    "and timestamp between :inicio and :fim");
            query.setParameter("busca", "%" + buscar + "%");
        }

        query.setParameter("inicio", ldtInicial);
        query.setParameter("fim", ldtFinal);

        return query.getResultList();
    }

    public static void excluirLog(String[] idLog) {
        RegistroLogErro log;

        if(idLog.length > 0) {
            PersistenceManager pm = PersistenceManager.getInstance();

            pm.beginTransaction();

            GenericDAO<RegistroLogErro> dao = pm.createGenericDAO(RegistroLogErro.class);

            for(int i = 0; i < idLog.length; i++) {
                log = dao.get(Long.parseLong(idLog[i]));
                dao.delete(log);
            }

            pm.commitTransaction();
        }
    }
 
    public static List<Defeito> buscarDefeitos(LocalDateTime ldtInicial, LocalDateTime ldtFinal, String buscar) {
    	try {
    		
    		List<RegistroLogErro> listaErros = LogErroServices.buscarLog(ldtInicial, ldtFinal, buscar);
    		List<Defeito> listaDefeito = new ArrayList<>();
    		List<Defeito> retorno = new ArrayList<>();
    		for (RegistroLogErro linha: listaErros) {
    			String aux[] = linha.getMensagem().split("\\w{4}/");
    			String juntar = aux[1] + aux[2];
    			aux = juntar.split(" na|em linha ");
    			listaDefeito.add(new Defeito(aux[0], aux[0], aux[1]));
    		}
    		
    		for (int i = 0; i < listaDefeito.size(); i++) {
    			Defeito posicaoI = listaDefeito.get(i);
				for (int j = i++; j <= listaDefeito.size(); j++) {
					Defeito posicaoJ = listaDefeito.get(j);
					if (posicaoI.getArquivo().equals(posicaoJ.getArquivo()) && 
							posicaoI.getLinhaCod().equals(posicaoJ.getLinhaCod())){
						Defeito adiciona = posicaoI;
						if (retorno.get(i) == null) {
							adiciona.setNumFalhas(1);
							retorno.add(adiciona);
						}
						else retorno.get(i).setNumFalhas(retorno.get(i).getNumFalhas() + 1);
					}
				}
			}
    		
    		return retorno;
    		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
    }
    
}
