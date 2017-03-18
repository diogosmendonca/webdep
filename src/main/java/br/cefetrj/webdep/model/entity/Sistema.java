package br.cefetrj.webdep.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
	
	@OneToMany(mappedBy="sistema", cascade=CascadeType.REMOVE, targetEntity=Versao.class)
	private List<Versao> versoes = new ArrayList<>(); 
	
	@OneToMany(mappedBy="sistema", cascade=CascadeType.REMOVE, targetEntity=Permissao.class)
	private List<Permissao> permissoes = new ArrayList<>();
	
	@OneToMany(mappedBy="sistema", cascade=CascadeType.REMOVE, targetEntity=RegistroLogAcesso.class)
	private List<RegistroLogAcesso> acessos = new ArrayList<>(); 
	
	@OneToMany(mappedBy="sistema", cascade=CascadeType.REMOVE, targetEntity=RegistroLogErro.class)
	private List<RegistroLogErro> erros = new ArrayList<>(); 
	
	@ManyToOne
	private Servidor servidor;
	
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

	public void addVersao(Versao versao) {
	    if (versoes.contains(versao))
	      return ;
	    versoes.add(versao);
	    versao.setSistema(this);
	  }
	
	  public void removeVersao(Versao versao) {
	    if (!versoes.contains(versao))
	      return ;
	    versoes.remove(versao);
	    versao.setSistema(null);
	  }

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void addPermissao(Permissao permissao) {
	    if (permissoes.contains(permissao))
	      return ;
	    permissoes.add(permissao);
	    permissao.setSistema(this);
	  }
	
	  public void removePermissao(Permissao permissao) {
	    if (!permissoes.contains(permissao))
	      return ;
	    permissoes.remove(permissao);
	    permissao.setSistema(null);
	  }

	public List<RegistroLogAcesso> getAcessos() {
		return acessos;
	}

	public void addAcesso(RegistroLogAcesso acesso) {
	    if (acessos.contains(acesso))
	      return ;
	    acessos.add(acesso);
	    acesso.setSistema(this);
	  }
	
	  public void removeAcesso(RegistroLogAcesso acesso) {
	    if (!acessos.contains(acesso))
	      return ;
	    acessos.remove(acesso);
	    acesso.setSistema(null);
	  }

	public List<RegistroLogErro> getErros() {
		return erros;
	}

	public void addErro(RegistroLogErro erro) {
	    if (erros.contains(erro))
	      return ;
	    erros.add(erro);
	    erro.setSistema(this);
	  }
	
	  public void removeErro(RegistroLogErro erro) {
	    if (!acessos.contains(erro))
	      return ;
	    erros.remove(erro);
	    erro.setSistema(null);
	  }

	public Servidor getServidor() {
		return servidor;
	}

	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}

	
}
