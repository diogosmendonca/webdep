package br.cefetrj.webdep.view.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
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
		
		out.println(" name=\"sistema\" id=\"sistema\">");
		if(sistemas != null)
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
	}
	
}
