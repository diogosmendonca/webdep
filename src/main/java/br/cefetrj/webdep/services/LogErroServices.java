package br.cefetrj.webdep.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

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

		if (buscar.equals("")) {
			query = pm.createQuery("from RegistroLogErro where timestamp " + "between :inicio and :fim");
		} else {
			query = pm.createQuery("from RegistroLogErro where " + "ip like :busca or nivel like :busca or "
					+ "mensagem like :busca " + "and timestamp between :inicio and :fim");
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
	
	public List<Defeito> buscarDefeitos(LocalDateTime dataInicial, LocalDateTime dataFinal, String buscar) {

		List<RegistroLogErro> listaErros = LogErroServices.buscarLog(dataInicial, dataFinal, buscar);
		
		List<Defeito> listaDefeito = new ArrayList<>();

		List<Defeito> retorno = new ArrayList<>();

		Map<String, IntSummaryStatistics> teste = new HashMap<>();
		
		for (RegistroLogErro r : listaErros) {
			String aux[] = r.getMensagem().split("\\w{5}/");
			aux = aux[1].split(" on line ");
			listaDefeito.add(new Defeito(aux[0], aux[0], Integer.parseInt(aux[1]), 1L));
		}
		
		teste = listaDefeito.stream().collect(Collectors.groupingBy(Defeito::getPrimaryKey, Collectors.summarizingInt(Defeito::getNumFalha)));
		
		for(Entry<String, IntSummaryStatistics> aux: teste.entrySet()) {
			String parse[] = aux.getKey().split(" ");
			retorno.add(new Defeito(parse[0], parse[0], Integer.parseInt(parse[1]), aux.getValue().getSum()));
		}
		
		return retorno;
		
//		System.out.println(listaDefeito);
//		Iterator<Defeito> it = listaDefeito.iterator();
//		while(it.hasNext()){
//			Defeito dUm = it.next();
//			for (int i = 1; i < listaDefeito.size(); i++) {
//				Defeito dDois = listaDefeito.get(i);
//				if (dUm.equals(dDois)){
//					dUm.setNumFalha(dUm.getNumFalha() + 1);
//					retorno.add(dUm);
//				}else{
//					
//				}
//			}
//		}
//		
//		for(Defeito d: retorno) {
//			teste.put(d.getArquivo(), d);
//		}
//		retorno = new ArrayList<>();
//		for (Entry<String, Defeito> d: teste.entrySet()) {
//			retorno.add(d.getValue());
//		}
		
		
	}

}
