package br.cefetrj.webdep.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.cefetrj.webdep.model.dao.GenericDAO;
import br.cefetrj.webdep.model.dao.PersistenceManager;
import br.cefetrj.webdep.model.entity.Permissao;
import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.model.entity.Usuario;

public class UsuarioServices {

	private static Usuario usuario;
	
	public static Usuario getUsuario(Usuario _usuario) {
		PersistenceManager pManager = PersistenceManager.getInstance();
		pManager.beginTransaction();
		
		GenericDAO<Usuario> usuarioDAO = pManager.createGenericDAO(Usuario.class);
		usuario = usuarioDAO.get(_usuario.getId());
		
		pManager.commitTransaction();
		
		return usuario;
	}
	
	
	public static Usuario obterPorId(Long id) {
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
		return usuario;
	}
	
	public static void salvar(Usuario usuario) {
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
	
	public static void salvarPermissao(Permissao permissao) {
		PersistenceManager pManager = PersistenceManager.getInstance();
		try {
			pManager.beginTransaction();
			
			GenericDAO<Permissao> permissaoDAO = pManager.createGenericDAO(Permissao.class);
			permissaoDAO.insert(permissao);
			
			pManager.commitTransaction();
		} catch (Exception e) {
			pManager.rollbackTransaction();
		}
	}
	
	public void ForceSavePermission(Long id ,Usuario usu, Sistema sis) {
		PersistenceManager pManager = PersistenceManager.getInstance();
		try {
			pManager.beginTransaction();
			
			Query query = pManager.createNativeQuery("INSERT INTO permissao (id, sistema_id, usuario_id)VALUES(?,?,?)");
			query.setParameter(1, id);
			query.setParameter(2, sis.getId());
	        query.setParameter(3, usu.getId());
	        query.executeUpdate();
			
			pManager.commitTransaction();
		} catch (Exception e) {
			pManager.rollbackTransaction();
		}
	}
	
	public Permissao getLastPermission() {
		List<Permissao> permissao = null;
		PersistenceManager pManager = PersistenceManager.getInstance();
		try {
			pManager.beginTransaction();
			TypedQuery	<Permissao> query = (TypedQuery<Permissao>) pManager.createQuery("select p from Permissao p ORDER BY p.id DESC");
			permissao = query.getResultList();
			
			pManager.commitTransaction();
		} catch (Exception e) {
			pManager.rollbackTransaction();
		}
		
		return permissao.get(0);
	}
	
	public static List<Permissao> getPermissao(Usuario usu){
		PersistenceManager pManager = PersistenceManager.getInstance();
		List<Permissao> list = new ArrayList<Permissao>(); 
		
		try {
			pManager.beginTransaction();
			
			TypedQuery	<Permissao> query = (TypedQuery<Permissao>) pManager.createQuery("SELECT p FROM Permissao p where p.usuario = :usu");
			list = query.setParameter("usu", usu).getResultList();
			
			pManager.commitTransaction();
		} catch (Exception e) {
			pManager.rollbackTransaction();
		}
		return list;
	}
	
	public void removerPermissao(Permissao per) {
		PersistenceManager pManager = PersistenceManager.getInstance();
		
		try {
			pManager.beginTransaction();
						
			GenericDAO<Permissao> permissaoDAO = pManager.createGenericDAO(Permissao.class);
			permissaoDAO.delete(per);
			
			pManager.commitTransaction();
		} catch (Exception e) {
			pManager.rollbackTransaction();
		}
	}
	
	public static void update(Usuario usuario) {
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
	
	public static void remover(Usuario usuario) {
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
	
	public static List<Usuario> listarTodos() {
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
		return usuarios;			
	}
	
	public static List<Usuario> buscaTodos(String busca) {
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
		return usuarios;			
	}
	
	public static Usuario validarLogin(String Login) {
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
		return usuario;
	}
	
	public static Usuario validarEmail(String Email) {
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
		return usuario;
	}
	
}
