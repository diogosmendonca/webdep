package br.cefetrj.webdep.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * Entidade correspondente a Configuração do Sistema.
 * Não é armazenada no banco de dados, mas sim no config.properties
 * 
 * @author diogo
 * @since 0.1
 */
public class ConfiguracaoSistema implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String banco;
	
	private String urlBanco;
	
	private String usuarioBanco;
	
	private String senhaBanco;
	
	private String provedorSmtp;
	
	private String usuarioEmail;
	
	private String senhaEmail;

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
