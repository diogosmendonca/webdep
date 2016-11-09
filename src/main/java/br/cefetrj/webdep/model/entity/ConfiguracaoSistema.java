package br.cefetrj.webdep.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * Entidade de banco de dados correspondente a tabela Configuração Sistema.
 * 
 * @author diogo
 * @since 0.1
 */
@Entity
public class ConfiguracaoSistema implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false, length = 50)
	private String banco;
	
	@Column(nullable = false, length = 100)
	private String urlBanco;
	
	@Column(nullable = false, length = 50)
	private String usuarioBanco;
	
	@Column(nullable = false, length = 50)
	private String senhaBanco;
	
	@Column(nullable = true, length = 100)
	private String provedorSmtp;
	
	@Column(nullable = true, length = 50)
	private String usuarioEmail;
	
	@Column(nullable = true, length = 50)
	private String senhaEmail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getUrlBanco() {
		return urlBanco;
	}

	public void setUrlBanco(String urlBanco) {
		this.urlBanco = urlBanco;
	}

	public String getUsuarioBanco() {
		return usuarioBanco;
	}

	public void setUsuarioBanco(String usuarioBanco) {
		this.usuarioBanco = usuarioBanco;
	}

	public String getSenhaBanco() {
		return senhaBanco;
	}

	public void setSenhaBanco(String senhaBanco) {
		this.senhaBanco = senhaBanco;
	}

	public String getProvedorSmtp() {
		return provedorSmtp;
	}

	public void setProvedorSmtp(String provedorSmtp) {
		this.provedorSmtp = provedorSmtp;
	}

	public String getUsuarioEmail() {
		return usuarioEmail;
	}

	public void setUsuarioEmail(String usuarioEmail) {
		this.usuarioEmail = usuarioEmail;
	}

	public String getSenhaEmail() {
		return senhaEmail;
	}

	public void setSenhaEmail(String senhaEmail) {
		this.senhaEmail = senhaEmail;
	}
	
	

}
