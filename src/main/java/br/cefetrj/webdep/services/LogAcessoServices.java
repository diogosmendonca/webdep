package br.cefetrj.webdep.services;

import br.cefetrj.webdep.model.dao.GenericDAO;
import br.cefetrj.webdep.model.dao.PersistenceManager;
import br.cefetrj.webdep.model.entity.RegistroLogAcesso;

import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by renatoor on 11/25/16.
 */
public class LogAcessoServices {

    public static List<RegistroLogAcesso> buscarLog(LocalDateTime ldtInicial, LocalDateTime ldtFinal, String buscar) {
        PersistenceManager pm = PersistenceManager.getInstance();
        Query query;

        if(buscar.equals("")) {
            query = pm.createQuery("from RegistroLogAcesso where timestamp " +
                    "between :inicio and :fim");
        }
        else {
            try {
                int buscaNum = Integer.parseInt(buscar);
                query = pm.createQuery("from RegistroLogAcesso where " +
                        "codigo=:busca or bytes=:busca " +
                        "and timestamp between :inicio and :fim");
                query.setParameter("busca", buscaNum);
            } catch (Exception e) {
                query = pm.createQuery("from RegistroLogAcesso where " +
                        "ip like :busca or usuario like :busca or " +
                        "url like :busca or origem like :busca or " +
                        "agente like :busca " +
                        "and timestamp between :inicio and :fim");
                query.setParameter("busca", "%" + buscar + "%");
            }
        }

        query.setParameter("inicio", ldtInicial);
        query.setParameter("fim", ldtFinal);

        return query.getResultList();
    }

    public static void excluirLog(String[] idLog) {
        RegistroLogAcesso log;

        if(idLog.length > 0) {
            PersistenceManager pm = PersistenceManager.getInstance();

            pm.beginTransaction();

            GenericDAO<RegistroLogAcesso> dao = pm.createGenericDAO(RegistroLogAcesso.class);

            for(int i = 0; i < idLog.length; i++) {
                log = dao.get(Long.parseLong(idLog[i]));
                dao.delete(log);
            }

            pm.commitTransaction();
        }
    }
}
