package br.cefetrj.webdep.view.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.services.SistemaServices;

/**
 * Combo de sistema. Para utilizar importe o tld no JSP conforme abaixo e
 * passe como parâmetro o id do usuário.
 * 
 * <%@ taglib prefix="cmp" uri="WEB-INF/components.tld"%>
 * <cmp:ComboSistema userId="${ id }"/>
 * 
 * @author diogo
 * @since 0.1
 */
public class SistemaComboTag extends SimpleTagSupport {

	private Integer userId;
	
	private String classCss;
	
	private String style;
	
	private String attributes;
	
	private String selectedList;
	
	/**
	 * Define se será exibido antes da primeira 
	 * opção uma vazia solicitando que o usuário selecione
	 * um sistema. 
	 */
	private boolean opcaoVazia;
	
	/**
	 * Se insere um alerta visual indicando que a combo
	 * precisa ser selecionada.
	 * 
	 */
	private boolean alertaSelecao;
	
	
	public String getClassCss() {
		return classCss;
	}

	public void setClassCss(String classe) {
		this.classCss = classe;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public boolean isOpcaoVazia() {
		return opcaoVazia;
	}

	public void setOpcaoVazia(boolean opcaoVazia) {
		this.opcaoVazia = opcaoVazia;
	}

	public boolean isAlertaSelecao() {
		return alertaSelecao;
	}

	public void setAlertaSelecao(boolean alertaSelecao) {
		this.alertaSelecao = alertaSelecao;
	}

	public String getSelectedList() {
		return selectedList;
	}

	public void setSelectedList(String selectedList) {
		this.selectedList = selectedList;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public void doTag() throws JspException, IOException {
		
		
		JspWriter out = getJspContext().getOut();
		List<Sistema> sistemas = SistemaServices.listByPermissaoUsuario(getUserId());
		List<Long> selecionados = new ArrayList<Long>();
				
		if(getSelectedList() != null && getSelectedList().trim().length() > 0){
			for(String id: getSelectedList().split(",")){
				selecionados.add(Long.parseLong(id));
			}
		}
		
		out.print("<select ");
		if(getClassCss() != null){
			out.print(" class=\"" + getClassCss() + "\" ");
		}
		if(getStyle() != null){
			out.print(" style=\"" + getStyle() + "\" ");
		}
		
		if(getAttributes() != null){
			out.print(" " + getAttributes() + " ");
		}
		
		out.print(" name=\"sistema\" id=\"sistema\" ");
		
		if(alertaSelecao == true){
			out.print(" title='' data-content='Selecione o sistema que deseja "
					+ "trabalhar para que todas as telas sejam exibidas com dados específicos para este sistema."
					+ " Esta seleção é necessária para a exibição correta de diversas funcionalidades do WebDep.' data-placement='bottom' "
					+ " data-toggle='' ");
		}
		
		out.println(">");
		if(sistemas != null){
			if(opcaoVazia == true){
				out.print("<option value=''>Escolha um Sistema</option>");
			}
			for (Sistema sistema : sistemas) {
				out.print("<option value=\"" + sistema.getId() + "\" ");
				if(selecionados.contains(sistema.getId())){
					out.print(" selected ");
				}
				out.print(">");
				out.print(sistema.getNome());
				out.println("</option>");
			}
			out.println("</select>");
			
			if((alertaSelecao == true) && (selecionados.size() == 0) && (sistemas.size() > 0)){
				out.println(""
						+ "<script> "
						+ "window.onload = "
						+ "	function() {"
						+ "		$('#sistema').trigger('manual');"
						+ "		$('#sistema').popover('show');"
						+ "	};"
						+ "document.getElementById('sistema').onclick = "
						+ "	function() {"
						+ "		$('#sistema').popover('destroy');"
						+ " };"
						+ "</script>");
				
			}
		}
	}
	
}
