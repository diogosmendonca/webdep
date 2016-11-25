package br.cefetrj.webdep.services;

import java.util.List;

import javax.persistence.TypedQuery;

import br.cefetrj.webdep.model.dao.GenericDAO;
import br.cefetrj.webdep.model.dao.PersistenceManager;
import br.cefetrj.webdep.model.entity.Usuario;

public class UsuarioServices {

	private static Usuario usuario;
	
	public static Usuario getUsuario(Usuario _usuario){
		
		PersistenceManager pManager = PersistenceManager.getInstance();
		pManager.beginTransaction();
		
		GenericDAO<Usuario> usuarioDAO = pManager.createGenericDAO(Usuario.class);
		usuario = usuarioDAO.get(_usuario.getId());
		
		pManager.commitTransaction();
		
		return usuario;
	}
	
	
	public static Usuario obterPorId(Long id){
		Usuario usuario = null;
		PersistenceManager pManager = PersistenceManager.getInstance();
		try {
			pManager.beginTransaction();
			
			GenericDAO<Usuario> permissaoDAO = pManager.createGenericDAO(Usuario.class);
			usuario = permissaoDAO.get(id);
			
			pManager.commitTransaction();
		} catch (Exception e) {
			pManager.rollbackTransaction();
		}
		
		return 	usuario;
	}
	
	public static void salvar(Usuario usuario){
		PersistenceManager pManager = PersistenceManager.getInstance();
		try {
			pManager.beginTransaction();
			
			GenericDAO<Usuario> permissaoDAO = pManager.createGenericDAO(Usuario.class);
			permissaoDAO.insert(usuario);
			
			pManager.commitTransaction();
		} catch (Exception e) {
			pManager.rollbackTransaction();
		}
	}
	
	public static void update(Usuario usuario){
		PersistenceManager pManager = PersistenceManager.getInstance();
		try {
			pManager.beginTransaction();
			
			GenericDAO<Usuario> permissaoDAO = pManager.createGenericDAO(Usuario.class);
			permissaoDAO.update(usuario);
			
			pManager.commitTransaction();
		} catch (Exception e) {
			pManager.rollbackTransaction();
		}
	}
	
	public static void remover(Usuario usuario){
		PersistenceManager pManager = PersistenceManager.getInstance();
		try {
			pManager.beginTransaction();
			
			GenericDAO<Usuario> permissaoDAO = pManager.createGenericDAO(Usuario.class);
			permissaoDAO.delete(usuario);
			
			pManager.commitTransaction();
		} catch (Exception e) {
			pManager.rollbackTransaction();
		}
	}
	
	public static List<Usuario> listarTodos(){
		List<Usuario> usuarios = null;
		PersistenceManager pManager = PersistenceManager.getInstance();
		try {
			pManager.beginTransaction();
			
			GenericDAO<Usuario> permissaoDAO = pManager.createGenericDAO(Usuario.class);
			usuarios = permissaoDAO.listAll();
			
			pManager.commitTransaction();
		} catch (Exception e) {
			pManager.rollbackTransaction();
		}
		
		return 	usuarios;			
	}
	
	public static List<Usuario> buscaTodos(String busca){
		List<Usuario> usuarios = null;
		PersistenceManager pManager = PersistenceManager.getInstance();
		try {
			pManager.beginTransaction();

			TypedQuery	<Usuario> query = (TypedQuery<Usuario>) pManager.createQuery("select u from Usuario u WHERE u.nome LIKE :busca OR u.login LIKE :busca OR u.email LIKE :busca");
			usuarios = query.setParameter("busca", "%"+busca+"%").getResultList();
			
			pManager.commitTransaction();
		} catch (Exception e) {
			pManager.rollbackTransaction();
		}
		
		return 	usuarios;			
	}
	
	public static Usuario validarLogin(String Login){
		Usuario usuario = null;
		PersistenceManager pManager = PersistenceManager.getInstance();
		try {
			pManager.beginTransaction();
			TypedQuery	<Usuario> query = (TypedQuery<Usuario>) pManager.createQuery("select u from Usuario u WHERE u.login = :login");
			usuario = query.setParameter("login", Login).getSingleResult();
			
			pManager.commitTransaction();
		} catch (Exception e) {
			pManager.rollbackTransaction();
		}
		
		return 	usuario;
	}
	
	public static Usuario validarEmail(String Email){
		Usuario usuario = null;
		PersistenceManager pManager = PersistenceManager.getInstance();
		try {
			pManager.beginTransaction();
			TypedQuery	<Usuario> query = (TypedQuery<Usuario>) pManager.createQuery("select u from Usuario u WHERE u.email = :email");
			usuario = query.setParameter("email", Email).getSingleResult();	
			
			pManager.commitTransaction();
		} catch (Exception e) {
			pManager.rollbackTransaction();
		}
		
		return 	usuario;
	}
	
}
