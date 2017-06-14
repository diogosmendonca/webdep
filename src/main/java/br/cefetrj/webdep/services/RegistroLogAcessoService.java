package br.cefetrj.webdep.services;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

import javax.persistence.Query;

import br.cefetrj.webdep.model.dao.GenericDAO;
import br.cefetrj.webdep.model.dao.PersistenceManager;
import br.cefetrj.webdep.model.dao.RegistroLogAcessoDAO;
import br.cefetrj.webdep.model.entity.PadraoURL;
import br.cefetrj.webdep.model.entity.RegistroLogAcesso;
import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.model.entity.Usuario;
import br.cefetrj.webdep.model.entity.Versao;

public class RegistroLogAcessoService {
	
	public static void insertRegistroLogAcesso(RegistroLogAcesso s) {
		PersistenceManager pm = PersistenceManager.getInstance();

		pm.beginTransaction();

		GenericDAO<RegistroLogAcesso> dao = pm.createGenericDAO(RegistroLogAcesso.class);
		dao.insert(s);

		pm.commitTransaction();

	}
	
	public static void updateRegistroLogAcesso(RegistroLogAcesso s) {
		PersistenceManager pm = PersistenceManager.getInstance();

		pm.beginTransaction();

		GenericDAO<RegistroLogAcesso> dao = pm.createGenericDAO(RegistroLogAcesso.class);
		dao.update(s);

		pm.commitTransaction();

	}
	
	public static List<RegistroLogAcesso> searchRegistroLogAcessoByUsuario(Usuario u) {
		PersistenceManager pm = PersistenceManager.getInstance();
		try {
			Query q = pm.createQuery("FROM RegistroLogAcesso WHERE usuario = :param ");
			
			q.setParameter("param", u);

			return q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<RegistroLogAcesso> searchRegistroLogAcessoBySistema(Sistema s) {
		PersistenceManager pm = PersistenceManager.getInstance();
		try {
			Query q = pm.createQuery("FROM RegistroLogAcesso r WHERE r.sistema = :param");
			
			q.setParameter("param", s);

			return q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void deleteRegistroLogAcesso (RegistroLogAcesso r) {
		PersistenceManager pm = PersistenceManager.getInstance();

		pm.beginTransaction();

		GenericDAO<RegistroLogAcesso> dao = pm.createGenericDAO(RegistroLogAcesso.class);
		dao.delete(r);

		pm.commitTransaction();
	}

	public static List<RegistroLogAcesso> listAllRegisters() {
		List<RegistroLogAcesso> regsAcesso = null;
		PersistenceManager pManager = PersistenceManager.getInstance();
		try {
			pManager.beginTransaction();
			
			GenericDAO<RegistroLogAcesso> regAcessoDAO = pManager.createGenericDAO(RegistroLogAcesso.class);
			regsAcesso = regAcessoDAO.listAll();
			
			pManager.commitTransaction();
		} catch (Exception e) {
			pManager.rollbackTransaction();
		}
		
		return 	regsAcesso;	
	}
	
	/**
	 * Conta a quantidade de acessos em cada URL de uma lista de logs de acesso.
	 * 
	 * @param acessos Lista de registro de logs de acesso a ser contada.
	 * @return mapa onde a chave é a URL e o valor a quantidade de acessos.
	 * 
	 * @author diogo
	 * @since 0.2
	 */
	public static Map<String, Long> countByURL(List<RegistroLogAcesso> acessos){
		if (acessos == null)
			throw new IllegalArgumentException("acessos argument cannot be null");
		
		return acessos
				.parallelStream()
				.collect(Collectors.groupingBy(RegistroLogAcesso::getUrl, Collectors.counting()));
	}
	
	
	
	/**
	 * Filtra uma lista de registro de logs de acesso por um padrão de URL. 
	 * 
	 * @param acessos Lista de registros de log de acesso a serem filtrados
	 * @param padrao Padrão de URL que será utilizado na filtragem
	 * @return Lista de registro de logs de acesso filtrados
	 * 
	 * @author diogo
	 * @since 0.2
	 */
	public static List<RegistroLogAcesso> filterByPadraoURL(List<RegistroLogAcesso> acessos, PadraoURL padrao){
		if (acessos == null)
			throw new IllegalArgumentException("acessos argument cannot be null");
		
		if (padrao == null)
			throw new IllegalArgumentException("padrao argument cannot be null");
		
		if (padrao.getExpressaoRegular() == null)
			throw new IllegalArgumentException("field expressaoRegular in padrao argument cannot be null");
		
		try{
			Pattern.compile(padrao.getExpressaoRegular());
		}catch(PatternSyntaxException  e){
			throw new IllegalArgumentException("field expressaoRegular in padrao argument is not a valid regular expression");
		}
		
		return acessos
				.parallelStream()
				.filter(r -> r.getUrl().matches(padrao.getExpressaoRegular()))
				.collect(Collectors.toList());
	}
	
	/**
	 * Recupera uma lista de registros de log de acesso de um determinado sistema nas 
	 * versões solicitadas e que tiveram os códigos HTTP de retorno especificados.
	 * 
	 * @param sistema sistema a serem buscados os registros de log de acesso
	 * @param versoes versões a serem filtradas
	 * @param codigo códigos de retorno HTTP a serem considerados na busca. Parâmetro opcional, pode
	 * ser passado null.
	 * 
	 * @return lista de registro de logs de acesso filtrados pelos parâmetros passados
	 * 
	 * @author diogo
	 * @since 0.2
	 */
	public static List<RegistroLogAcesso> listAcessosBySistemaVersoesCodigos(Sistema sistema, 
																		     List<Versao> versoes, 
																		     List<Integer> codigos){
		
		return RegistroLogAcessoDAO.listAcessosBySistemaVersoesCodigos(sistema, versoes, codigos);
	}
	
	
	/**
	 * Verifica se uma determinada URL apresenta pelo menos um registro de log de acesso que retorne 
	 * um dos códigos especificados como parâmentro.
	 * 
	 * @param url url que se quer saber se o código está presente
	 * @param codigos os códigos que se quer verificar
	 * @param acessos o conjunto de registro de logs de acesso a ser pesquisado
	 * 
	 * @return se a url apresenta um dos códigos de retorno passados nos registros de log de acesso
	 */
	public static boolean urlsHasCodes(String url, List<Integer> codigos, List<RegistroLogAcesso> acessos){
		if (url == null)
			throw new IllegalArgumentException("url argument cannot be null");
		
		if (codigos == null)
			throw new IllegalArgumentException("codigos argument cannot be null");
		
		
		if (acessos == null)
			throw new IllegalArgumentException("acessos argument cannot be null");
		
		return acessos
				.parallelStream()
				.filter(r -> r.getUrl().equals(url) && codigos.contains(r.getCodigo()))
				.findAny()
				.isPresent();
	}
	
	/**
	 * Retorna a quantidade de erros numa determinada URL no Log de Acesso atrav�s do par�metro c�digo passado
	 * 
	 * @param url url que se quer saber se o código está presente
	 * @param codigos os códigos que se quer verificar
	 * @param acessos o conjunto de registro de logs de acesso a ser pesquisado
	 * @return Um Long que representa o n�mero total de erros.
	 * 
	 * @author Lyago
	 * @since 0.2
	 */
	public static Long getErros(String url, List<Integer> codigos, List<RegistroLogAcesso> acessos){
		if (url == null)
			throw new IllegalArgumentException("url argument cannot be null");
		
		if (codigos == null)
			throw new IllegalArgumentException("codigos argument cannot be null");
		
		
		if (acessos == null)
			throw new IllegalArgumentException("acessos argument cannot be null");
		
		return acessos
				.parallelStream()
				.filter(r -> r.getUrl().equals(url) && codigos.contains(r.getCodigo()))
				.collect(Collectors.counting());
	}
	
}
