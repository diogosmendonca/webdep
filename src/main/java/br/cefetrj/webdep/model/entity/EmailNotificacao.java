package br.cefetrj.webdep.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Entidade correspondente a tabela EmailNotificação.
 * 
 * @author Danilo Moura
 * @since 0.2
 */
@Entity
public class EmailNotificacao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="sistema_id")
	private Sistema sistema;
	
	@ManyToOne
	@JoinColumn(name="URL_id")
	private PadraoURL URL;
		
	@ManyToMany
	@JoinTable(name="EMAILNOTIFICACAO_REGISTROLOGACESSO", joinColumns = @JoinColumn(name = "emailnotificacao_id"), inverseJoinColumns = @JoinColumn(name = "registrologacesso_id"))
	private List<RegistroLogAcesso> codigosFalha;
	
	@Column(nullable = false)
	private String email;

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public PadraoURL getURL() {
		return URL;
	}

	public void setURL(PadraoURL uRL) {
		URL = uRL;
	}

	public List<RegistroLogAcesso> getCodigosFalha() {
		return codigosFalha;
	}

	public void setCodigosFalha(List<RegistroLogAcesso> codigosFalha) {
		this.codigosFalha = codigosFalha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
