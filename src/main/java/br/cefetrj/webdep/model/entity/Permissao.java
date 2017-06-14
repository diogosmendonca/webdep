package br.cefetrj.webdep.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entidade de banco de dados correspondente a tabela Permissão.
 * 
 * @author diogo
 * @since 0.1
 */
@Entity
public class Permissao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name="sistema_id")
	private Sistema sistema;
	
	@ManyToOne
	private Usuario usuario;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
	    //prevent endless loop
	    if (checarSeExiste(sistema))
	      return ;
	    //set new owner
	    Sistema oldSistema = this.sistema;
	    this.sistema = sistema;
	    //remove from the old owner
	    if (oldSistema!=null)
	    	oldSistema.removePermissao(this);
	    //set myself into new owner
	    if (sistema!=null)
	    	sistema.addPermissao(this);
	  }

	  private boolean checarSeExiste(Sistema newSistema) {
	    return sistema==null? newSistema == null : sistema.equals(newSistema);
	  }

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	
}
