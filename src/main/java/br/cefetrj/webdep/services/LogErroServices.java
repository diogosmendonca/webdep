package br.cefetrj.webdep.services;

import br.cefetrj.webdep.model.dao.GenericDAO;
import br.cefetrj.webdep.model.dao.PersistenceManager;
import br.cefetrj.webdep.model.entity.RegistroLogErro;

import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

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
}
