package br.cefetrj.webdep.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Entidade de banco de dados correspondente a tabela Servidor.
 * 
 * @author diogo
 * @since 0.1
 */
@Entity
public class Servidor implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false, length = 100)
	private String nome;
	
	@OneToMany(mappedBy="servidor")
	private List<Sistema> sistemas;
	
	@ManyToOne
	private FormatoLog formatoLog;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Sistema> getSistemas() {
		return sistemas;
	}

	public void setSistemas(List<Sistema> sistemas) {
		this.sistemas = sistemas;
	}
	

	public FormatoLog getFormatoLog() {
		return formatoLog;
	}

	public void setFormatoLog(FormatoLog formatoLog) {
		this.formatoLog = formatoLog;
	}
	
}
