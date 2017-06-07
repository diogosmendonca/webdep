package br.cefetrj.webdep.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entidade de banco de dados correspondente a tabela Registro Log Acesso.
 * 
 * @author diogo
 * @since 0.1
 */
@Entity
public class RegistroLogAcesso implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false, length = 50)
	private String ip;
	
	@Column(nullable = false, length = 100)
	private String usuario;
	
	@Column(nullable = true)
	private LocalDateTime timestamp;
	
	@Column(nullable = false, length = 4096)
	private String url;
	
	@Column(nullable = false)
	private Integer codigo;
	
	@Column(nullable = false)
	private Integer bytes;
	
	@Column(nullable = false, length = 4096)
	private String origem;
	
	@Column(nullable = false, length = 1024)
	private String agente;
	
	@ManyToOne
	@JoinColumn(name="sistema_id")
	private Sistema sistema;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getBytes() {
		return bytes;
	}

	public void setBytes(Integer bytes) {
		this.bytes = bytes;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getAgente() {
		return agente;
	}

	public void setAgente(String agente) {
		this.agente = agente;
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
	    	oldSistema.removeAcesso(this);
	    //set myself into new owner
	    if (sistema!=null)
	    	sistema.addAcesso(this);
	  }

	  private boolean checarSeExiste(Sistema newSistema) {
	    return sistema==null? newSistema == null : sistema.equals(newSistema);
	  }
}
