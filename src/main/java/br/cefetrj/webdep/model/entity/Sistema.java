package br.cefetrj.webdep.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entidade de banco de dados correspondente a tabela Sistema.
 * 
 * @author diogo
 * @since 0.1
 */
@Entity
public class Sistema implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false, length = 100)
	private String nome;
	
	@Column(nullable = false, length = 255)
	private String pastaLogAcesso;
	
	@Column(nullable = false, length = 100)
	private String prefixoLogAcesso;
	
	@Column(nullable = false, length = 255)
	private String pastaLogErro;
	
	@Column(nullable = false, length = 100)
	private String prefixoLogErro;
	
	@Column(nullable = false)
	private LocalDateTime primeiraLeitura;
	
	@Column(nullable = false)
	private Long periodicidadeLeitura;
	
	@OneToMany(mappedBy="sistema")
	private List<Versao> versoes; 
	
	@OneToMany(mappedBy="sistema")
	private List<Permissao> permissoes; 
	
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

	public String getPastaLogAcesso() {
		return pastaLogAcesso;
	}

	public void setPastaLogAcesso(String pastaLogAcesso) {
		this.pastaLogAcesso = pastaLogAcesso;
	}

	public String getPrefixoLogAcesso() {
		return prefixoLogAcesso;
	}

	public void setPrefixoLogAcesso(String prefixoLogAcesso) {
		this.prefixoLogAcesso = prefixoLogAcesso;
	}

	public String getPastaLogErro() {
		return pastaLogErro;
	}

	public void setPastaLogErro(String pastaLogErro) {
		this.pastaLogErro = pastaLogErro;
	}

	public String getPrefixoLogErro() {
		return prefixoLogErro;
	}

	public void setPrefixoLogErro(String prefixoLogErro) {
		this.prefixoLogErro = prefixoLogErro;
	}

	public LocalDateTime getPrimeiraLeitura() {
		return primeiraLeitura;
	}

	public void setPrimeiraLeitura(LocalDateTime primeiraLeitura) {
		this.primeiraLeitura = primeiraLeitura;
	}

	public Long getPeriodicidadeLeitura() {
		return periodicidadeLeitura;
	}

	public void setPeriodicidadeLeitura(Long periodicidadeLeitura) {
		this.periodicidadeLeitura = periodicidadeLeitura;
	}
	

	public List<Versao> getVersoes() {
		return versoes;
	}

	public void setVersoes(List<Versao> versoes) {
		this.versoes = versoes;
	}

	
}
