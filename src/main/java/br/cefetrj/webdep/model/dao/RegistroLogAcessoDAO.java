package br.cefetrj.webdep.model.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Query;

import br.cefetrj.webdep.model.entity.RegistroLogAcesso;
import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.model.entity.Versao;

/**
 * Classe de abstração de acesso aos dados de registros de log de acesso.
 * 		
 * @author diogo
 * @since 0.2
 */
public class RegistroLogAcessoDAO {
	
	/**
	 * Busca os códigos http distintos de um sistema em seu registro de log
	 * de aesso.
	 * 
	 * @param s Sistema
	 * @return Lista de registro de log acesso contendo somente o campo código 
	 * preechido com os códigos HTTP distintos
	 * 
	 * @author diogo
	 * @since 0.2
	 */
	@SuppressWarnings("unchecked")
	public static List<Integer> listCodigosBySistema(Sistema s){
		PersistenceManager pm = PersistenceManager.getInstance();
		
		Query query = pm.createQuery(
				 "SELECT DISTINCT r.codigo "
				+ "	FROM RegistroLogAcesso r "
				+ " WHERE r.sistema = :sistema ");
		
		query.setParameter("sistema", s);
		
		return query.getResultList();
	}
	
	/**
	 * Recupera uma lista de registros de log de acesso de um determinado sistema nas 
	 * versões solicitadas e que tiveram os códigos HTTP de retorno especificados.
	 * 
	 * @param sistema Sistema a serem buscados os registros de log de acesso
	 * @param versoes Versões a serem filtradas
	 * @param codigos códigos de retorno HTTP a serem considerados na busca. Parâmetro opcional, pode
	 * ser passado null.
	 * 
	 * @return lista de registro de logs de acesso filtrados pelos parâmetros passados
	 * 
	 * @author diogo
	 * @since 0.2
	 */
	@SuppressWarnings("unchecked")
	public static List<RegistroLogAcesso> listAcessosBySistemaVersoesCodigos(Sistema sistema, 
																		   List<Versao> versoes, 
																		   List<Integer> codigos){
		
		if (sistema == null || sistema.getId() == null)
			throw new IllegalArgumentException("sistemaId argument cannot be null");
		
		if (versoes == null || versoes.size() == 0)
			throw new IllegalArgumentException("versoesId argument cannot be null or empty");
		
		//Monta a string de parâmetro das versões
		StringBuilder versoesBuilder = new StringBuilder();
		for (Versao versao : versoes) {
			versoesBuilder.append(versao.getId());
			versoesBuilder.append(",");
		}
		versoesBuilder.deleteCharAt(versoesBuilder.length() -1);
		String versoesStr = versoesBuilder.toString();
		
		//Monta a string de parâmetros dos códigos
		String codigosStr = null;
		if(codigos != null && codigos.size() > 0){
			StringBuilder codigosBuilder = new StringBuilder();
			for (Integer codigo : codigos) {
				codigosBuilder.append(codigo);
				codigosBuilder.append(",");
			}
			codigosBuilder.deleteCharAt(codigosBuilder.length() -1);
			codigosStr = codigosBuilder.toString();
		}
		
		String sqlTemplate = 
				"SELECT  LA.ID, LA.AGENTE, LA.BYTES, LA.CODIGO, LA.IP, LA.ORIGEM, LA.TIMESTAMP, LA.URL, LA.USUARIO, "
				+ "		 LA.SISTEMA_ID "
				+ " FROM REGISTROLOGACESSO LA, VERSAO V "
				+ "WHERE V.SISTEMA_ID = LA.SISTEMA_ID "
				+ "  AND (LA.TIMESTAMP < (SELECT MIN(V1.TIMESTAMPLIBERACAO) "
				+ "							FROM VERSAO V1 "
				+ " 					   WHERE V1.TIMESTAMPLIBERACAO > V.TIMESTAMPLIBERACAO) "
				+ " 	  OR "
				+ "		  NOT EXISTS( SELECT * FROM VERSAO V2 WHERE V2.TIMESTAMPLIBERACAO > V.TIMESTAMPLIBERACAO) "
				+ "      ) "
				+ "  AND LA.TIMESTAMP >= V.TIMESTAMPLIBERACAO "
				+ "  AND V.ID IN (%s) "
				+ "  AND LA.SISTEMA_ID = %s ";
		
		if(codigosStr != null){
			sqlTemplate += "  AND LA.CODIGO IN (%s) ";
		}
		sqlTemplate += "ORDER BY LA.TIMESTAMP ASC ";
		
		String sql = null;
		
		if(codigosStr != null){
			sql = String.format(sqlTemplate, versoesStr, sistema.getId().toString(), codigosStr);
		}else{
			sql = String.format(sqlTemplate, versoesStr, sistema.getId().toString());
		}
		
		PersistenceManager pManager = PersistenceManager.getInstance();
		return pManager.createNativeQuery(sql, RegistroLogAcesso.class).getResultList();
		
	}
	
	/**
	 * Busca ordenada por código dos registros de log acesso de um sistema  
	 * dentro de um intervalo de tempo específico.
	 * 
	 * @param ldtInicial LocalDateTime
	 * @param ldtFinal LocalDateTime
	 * @param s Sistema
	 * @return Lista de registro de log acesso ordenada por código, filtrada por Sistema
	 * e dentro do intervalo de tempo entre ldtInicial e ldtFinal
	 * 
	 * @author GabrielPoyares
	 * @since 0.2
	 */
	@SuppressWarnings("unchecked")
	public static List<RegistroLogAcesso> searchLogCodeOrdered(LocalDateTime ldtInicial, LocalDateTime ldtFinal, Long idSistema) {
        PersistenceManager pm = PersistenceManager.getInstance();
        Query query = pm.createQuery("from RegistroLogAcesso where (timestamp " +
                "between :inicio and :fim) AND (sistema_id = :sistemaId) " +
    			"ORDER BY codigo ASC");     

        query.setParameter("inicio", ldtInicial);
        query.setParameter("fim", ldtFinal);
        query.setParameter("sistemaId", idSistema);

        return query.getResultList();
    }

}
