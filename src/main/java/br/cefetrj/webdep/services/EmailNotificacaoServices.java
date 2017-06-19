package br.cefetrj.webdep.services;

import java.util.List;

import br.cefetrj.webdep.model.dao.GenericDAO;
import br.cefetrj.webdep.model.dao.PersistenceManager;
import br.cefetrj.webdep.model.entity.EmailNotificacao;
import br.cefetrj.webdep.model.entity.RegistroLogAcesso;
import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.model.entity.Versao;

public class EmailNotificacaoServices {
	
	public static void create(EmailNotificacao _emailNotificacao){
		PersistenceManager pManager = PersistenceManager.getInstance();
		pManager.beginTransaction();
		
		GenericDAO<EmailNotificacao> emailNotificacaoDAO = pManager.createGenericDAO(EmailNotificacao.class);
		emailNotificacaoDAO.insert(_emailNotificacao);
		
		pManager.commitTransaction();
	}
	
	public static List<EmailNotificacao> listarTodos(){
		List<EmailNotificacao> emailNotificacao = null;
		PersistenceManager pManager = PersistenceManager.getInstance();
		try {
			pManager.beginTransaction();
			
			GenericDAO<EmailNotificacao> emailNotificacaoDAO = pManager.createGenericDAO(EmailNotificacao.class);
			emailNotificacao = emailNotificacaoDAO.listAll();
			
			pManager.commitTransaction();
			
		} catch (Exception e) {
			pManager.rollbackTransaction();
			System.out.println("Erro ao tentar listar todos. Msg erro: " +e.getMessage());
		}
		
		return 	emailNotificacao;			
	}
	
	public static void excluir(EmailNotificacao emailNotificacao) {
		PersistenceManager pManager = PersistenceManager.getInstance();
		
		pManager.beginTransaction();

		GenericDAO<EmailNotificacao> emailNotificacaoDAO = pManager.createGenericDAO(EmailNotificacao.class);
		emailNotificacaoDAO.delete(emailNotificacao);

		pManager.commitTransaction();
	}
	
	public static EmailNotificacao obterPorId(Long id){
		EmailNotificacao en = null;
		PersistenceManager pManager = PersistenceManager.getInstance();
		try {
			pManager.beginTransaction();
			
			GenericDAO<EmailNotificacao> emailNotificacaoDAO = pManager.createGenericDAO(EmailNotificacao.class);
			en = emailNotificacaoDAO.get(id);
			
			pManager.commitTransaction();
		} catch (Exception e) {
			pManager.rollbackTransaction();
		}
		
		return 	en;
	}
}
