package br.cefetrj.webdep.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

public class GenericDAO<T> {

	private EntityManager em;
	private Class<T> t;
	
	GenericDAO(Class<T> t, EntityManager em){
		this.t = t;
		this.em = em;
	}
	
	public T get(Long id){
		return em.find(t, id);
	}
	
	public void insert(T pessoa){
		em.persist(pessoa);
	}
	
	public void update(T pessoa){
		em.merge(pessoa);
	}
	
	public void delete(T pessoa){
		em.remove(pessoa);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listAll(){
		return em.createQuery("from " + t.getName()).getResultList();
	}
	
	
}
