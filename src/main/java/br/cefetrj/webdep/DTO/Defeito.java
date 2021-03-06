package br.cefetrj.webdep.DTO;

import java.io.Serializable;

/**
 * 
 * @author Rafael
 * @since 30/05/2017
 * 
 * Classe onde serao armazenadas os defeitos que serao mostrados no listaDefeito.jsp, porem classe que nao sera armazena no Banco de dados
 *
 */
public class Defeito implements Serializable {

	private static final long serialVersionUID = 1L;

	private String arquivo;
	private String nomArquivo;
	private Integer linhaCod;
	private Integer numFalha;
	public Defeito(String arquivo, String nomArquivo, Integer linhaCod, Integer numFalha) {
		super();
		this.arquivo = arquivo;
		this.nomArquivo = nomArquivo;
		this.linhaCod = linhaCod;
		this.numFalha = numFalha;
	}

	public Defeito(String arquivo, String nomArquivo, Integer linhaCod, long numFalha) {
		super();
		this.arquivo = arquivo;
		this.nomArquivo = nomArquivo;
		this.linhaCod = linhaCod;
		this.numFalha = (int) numFalha;
	}
	
	public Defeito() {
		super();
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public String getNomArquivo() {
		return nomArquivo;
	}

	public void setNomArquivo(String nomArquivo) {
		this.nomArquivo = nomArquivo;
	}

	public Integer getLinhaCod() {
		return linhaCod;
	}

	public void setLinhaCod(Integer linhaCod) {
		this.linhaCod = linhaCod;
	}

	public Integer getNumFalha() {
		return numFalha;
	}

	public void setNumFalha(Integer numFalha) {
		this.numFalha = numFalha;
	}

	@Override
	public String toString() {
		return "Defeito [arquivo=" + arquivo + ", nomArquivo=" + nomArquivo + ", linhaCod=" + linhaCod + ", numFalha="
				+ numFalha + "]";
	}
	
	public String getPrimaryKey(){
		return this.arquivo + " " + this.linhaCod;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		Defeito d = (Defeito) obj;
		if(this.getArquivo().equals(d.getArquivo()) && this.getLinhaCod()==(d.getLinhaCod())) return true;
		else return false;
	}

}
