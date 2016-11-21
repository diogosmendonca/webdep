package br.cefetrj.webdep.view.tag;

import java.io.IOException;
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

	public void doTag() throws JspException, IOException {
		
		
		JspWriter out = getJspContext().getOut();
		List<Sistema> sistemas = SistemaServices.listByPermissaoUsuario(getUserId());

		out.print("<select ");
		if(getClassCss() != null){
			out.print(" class=\"" + getClassCss() + "\" ");
		}
		if(getStyle() != null){
			out.print(" style=\"" + getStyle() + "\" ");
		}
		out.println(" name=\"sistema\" id=\"sistema\">");
		if(sistemas != null)
			for (Sistema sistema : sistemas) {
				out.print("<option value=\"" + sistema.getId() + "\">");
				out.print(sistema.getNome());
				out.println("</option>");
			}
		out.println("</select>");
	}
	
}
