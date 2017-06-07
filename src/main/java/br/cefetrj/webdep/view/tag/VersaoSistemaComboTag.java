package br.cefetrj.webdep.view.tag;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.model.entity.Versao;
import br.cefetrj.webdep.services.SistemaServices;
import br.cefetrj.webdep.services.VersionServices;

/**
 * Tag que exibe a combo box com as versões de um sistema.
 * Forma de utilização:
 * 
 * <cmp:VersaoSistemaCombo nome="nomdeDaCombo" sistemaId="${ idsistema }" versoesSelecionadas="${ versoesSelecionadas }"/>
 * 
 * @author diogo
 * @since 0.2
 */
public class VersaoSistemaComboTag extends SimpleTagSupport{
	
	/**
	 * Nome e id do elemento html que será inserido.
	 */
	private String nome;
	
	/**
	 * Id do sistema que será utilizado para buscar as versões.
	 */
	private Long sistemaId;
	
	/**
	 * Lista de versões que aparecerão selecionadas na combo
	 */
	private String[] versoesSelecionadas;
	
	
	/**
	 * Imprime o código HTML correspondente a combo box.
	 * Chamado quando é inserida a tag no JSP.
	 */
	public void doTag() throws JspException, IOException {
		
		if(sistemaId != null){
			
			List<String> versoesSelecionadasList = null;
			if(versoesSelecionadas != null)
				versoesSelecionadasList = Arrays.asList(versoesSelecionadas);
			
			//Busca o sistema
			Sistema s = SistemaServices.obterPorId(sistemaId);
			
			//Recupera as versões de um sistema para montar o select de versão
			List<Versao> versionList = VersionServices.searchVersaoBySistema(s);
			
			//Imprime a combo com os códigos HTTP
			JspWriter out = getJspContext().getOut();
			out.append("<select name='" + nome + "' aria-describedby='helpBlock-" + nome + "' class='selectpicker' multiple title='' id='" + nome  + "'>");
			for (Versao versao : versionList) {
				String selected = "";
				if(versoesSelecionadasList != null && versoesSelecionadasList.contains(versao.getId().toString())){
					selected = " selected ";
				}
				out.append("<option " + selected + " value='" + versao.getId() + "'>" + versao.getNome() + "</option>");
			}
			out.append("</select>");
			
		}
		
	}
	
	/**
	 * Recupera a lista de versões que aparecerão selecionadas na combo
	 * 
	 * @return ids das versões que aparecerão selecionadas na combo
	 */
	public String[] getVersoesSelecionadas() {
		return versoesSelecionadas;
	}

	/**
	 * Atribui a lista de versões que aparecerão selecionadas na combo
	 * 
	 * @param versoesSelecionadas lista de versões que aparecerão selecionadas na combo
	 */
	public void setVersoesSelecionadas(String[] versoesSelecionadas) {
		this.versoesSelecionadas = versoesSelecionadas;
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
	 * Recupera o id do sistema que as versões serão buscadas 
	 * 
	 * @return id do sistema 
	 */
	public Long getSistemaId() {
		return sistemaId;
	}

	/**
	 * Atribui um id de sistema para buscar as versões
	 * 
	 * @param sistemaId id do sistema
	 */
	public void setSistemaId(Long sistemaId) {
		this.sistemaId = sistemaId;
	}

}
