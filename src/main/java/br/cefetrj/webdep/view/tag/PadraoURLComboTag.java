package br.cefetrj.webdep.view.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import br.cefetrj.webdep.model.entity.PadraoURL;
import br.cefetrj.webdep.services.PadraoURLServices;

/**
 * Tag que exibe a combo de padrão de URL.
 * 
 * @author diogo
 * @since 0.2
 */
public class PadraoURLComboTag extends SimpleTagSupport{
	
	/**
	 * Nome e id do elemento html que será inserido.
	 */
	private String nome;
	
	/**
	 * Id do usuário que será utilizado para buscar os padrões de URL.
	 */
	private Long usuarioId;
	
	/**
	 * Id do padrão de URL que aparecerá selecionado na combo
	 */
	private String padraoSelecionado;

	/**
	 * Imprime o código HTML correspondente a combo box.
	 * Chamado quando é inserida a tag no JSP.
	 */
	public void doTag() throws JspException, IOException {
		
		if(usuarioId != null){
			
			//Recupera os padrões de url do usuário montar a combo
			List<PadraoURL> padraoList = PadraoURLServices.listarTodosPorUsuario(usuarioId);
			
			//Imprime a combo com os códigos HTTP
			JspWriter out = getJspContext().getOut();
			out.append("<select name='" + nome + "' aria-describedby='helpBlock-" + nome + "' class='selectpicker' title='' id='" + nome  + "'>");
			for (PadraoURL padrao : padraoList) {
				String selected = "";
				if(padraoSelecionado != null && padraoSelecionado.equals(padrao.getId().toString())){
					selected = " selected ";
				}
				out.append("<option " + selected + " value='" + padrao.getId() + "'>" + padrao.getNome() + "</option>");
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
	 * Recupera o id do usuário que será utilizado 
	 * para recuperar os padrões de URL
	 * 
	 * @return id do usuário
	 */
	public Long getUsuarioId() {
		return usuarioId;
	}

	/**
	 * Atribui o id do usuário que será utilizado 
	 * para recuperar os padrões de URL
	 * 
	 * @param usuarioId
	 */
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	/**
	 * Recupera o id do padrão de URL que será exibido como selecionado.
	 * 
	 * @return padrão de url selecionado
	 */
	public String getPadraoSelecionado() {
		return padraoSelecionado;
	}

	/**
	 * Atribui o id do padrão de URL que será exibido como selecionado.
	 * 
	 * @param padraoSelecionado
	 */
	public void setPadraoSelecionado(String padraoSelecionado) {
		this.padraoSelecionado = padraoSelecionado;
	}
	
	
	
	
}
