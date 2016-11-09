package br.cefetrj.webdep.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entidade de banco de dados correspondente a tabela Usuário.
 * 
 * @author diogo
 * @since 0.1
 */
@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false, length = 50)
	private String login;
	
	@Column(nullable = false, length = 50)
	private String senha;
	
	@Column(nullable = false, length = 100)
	private String nome;
	
	@Column(nullable = false, length = 100)
	private String email;
	
	@Column(nullable = false)
	private boolean admGeral;
	
	@OneToMany(mappedBy="usuario")
	private List<PadraoURL> padraoURL;
	
	@OneToMany(mappedBy="usuario")
	private List<Permissao> permissoes;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmGeral() {
		return admGeral;
	}

	public void setAdmGeral(boolean admGeral) {
		this.admGeral = admGeral;
	}

	public List<PadraoURL> getPadraoURL() {
		return padraoURL;
	}

	public void setPadraoURL(List<PadraoURL> padraoURL) {
		this.padraoURL = padraoURL;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
	
}
