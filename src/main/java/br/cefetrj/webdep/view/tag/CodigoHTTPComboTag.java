package br.cefetrj.webdep.view.tag;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.services.LogAcessoServices;
import br.cefetrj.webdep.services.SistemaServices;

/**
 * Combo de código HTTP. Exibe os códigos HTTP encontrados no
 * registro de log de acesso do sistema passado como parâmetro.
 * 
 * Forma de utilização:
 * <cmp:CodigoHTTPCombo nome="nomeDaCombo" sistemaId="${ idsistema }" codigosSelecionados="${ listaDeSelecionados}"/>
 * 
 * @author diogo
 * @since 0.2
 * 
 */
public class CodigoHTTPComboTag extends SimpleTagSupport{
	
	/**
	 * Nome e id que serão atribuídos a combo box.
	 */
	private String nome;
	
	/**
	 * Id do sistema que será utilizado para buscar os códigos HTTP.
	 */
	private Long sistemaId;
	
	/**
	 * Lista de códigos que aparecerão selecionados na combo
	 */
	private String[] codigosSelecionados;
	
	/**
	 * Imprime o código HTML correspondente a combo box.
	 * Chamado quando é inserida a tag no JSP.
	 */
	public void doTag() throws JspException, IOException {
		
		if(sistemaId != null){
			
			List<String> codigosSelecionadosList = null;
			if(codigosSelecionados != null)
				codigosSelecionadosList = Arrays.asList(codigosSelecionados);
			
			//Busca o sistema
			Sistema s = SistemaServices.obterPorId(sistemaId);
			
			//Recupera os códigos http distintos para montar o select de código http
			List<Integer> httpCodes = 
					LogAcessoServices.buscarCodigosRegistroLogAcesso(s);
			
			//Imprime a combo com os códigos HTTP
			JspWriter out = getJspContext().getOut();
			out.append("<select name='" + nome + "' aria-describedby='helpBlock-" + nome + "' class='selectpicker' multiple title='' id='" + nome  + "'>");
			for (Integer httpCode : httpCodes) {
				String selected = "";
				if(codigosSelecionadosList != null && codigosSelecionadosList.contains(httpCode.toString())){
					selected = " selected ";
				}
				out.append("<option " + selected + " value='" + httpCode + "'>" + httpCode + "</option>");
			}
			out.append("</select>");
			
		}
		
	}
	
	/**
	 * Recupera o nome atribuído a combo
	 *  
	 * @return nome atribuído a combo
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Atribui um nome a combo
	 * @param nome nome da combo
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Recupera o id do sistema que os códigos http serão buscados 
	 * 
	 * @return id do sistema 
	 */
	public Long getSistemaId() {
		return sistemaId;
	}

	/**
	 * Atribui um id de sistema para buscar os códigos http
	 * 
	 * @param sistemaId id do sistema
	 */
	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}

	/**
	 * Recupera a lista de códigos http que serão mostrados como
	 * selecionados na combo
	 * 
	 * @return lista de códigos
	 */
	public String[] getCodigosSelecionados() {
		return codigosSelecionados;
	}
	
	/**
	 * Atribui uma lista de códigos http que serão exibidos como
	 * selecionados na combo
	 * 
	 * @param codigosSelecionados lista de códigos http
	 */
	public void setCodigosSelecionados(String[] codigosSelecionados) {
		this.codigosSelecionados = codigosSelecionados;
	}

	
}
