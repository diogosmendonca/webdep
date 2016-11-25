package br.cefetrj.webdep.view.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import br.cefetrj.webdep.model.entity.FormatoLog;
import br.cefetrj.webdep.model.entity.Servidor;
import br.cefetrj.webdep.services.FormatoLogServices;
import br.cefetrj.webdep.services.ServidorServices;

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
public class CadastroSistemaFormatoLogComboTag extends SimpleTagSupport {

	private Integer formatoLogId;
	
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

	public Integer getFormatoLogId() {
		return formatoLogId;
	}

	public void setFormatoLogId(Integer formatoLogId) {
		this.formatoLogId = formatoLogId;
	}

	public void doTag() throws JspException, IOException {
		
		
		JspWriter out = getJspContext().getOut();
		List<FormatoLog> formatos = FormatoLogServices.listAllFormatoLog();

		out.print("<select ");
		if(getClassCss() != null){
			out.print(" class=\"" + getClassCss() + "\" ");
		}
		if(getStyle() != null){
			out.print(" style=\"" + getStyle() + "\" ");
		}
		out.println(" name=\"formatoLog\" id=\"formatoLog\">");
		if(formatos != null)
			for (FormatoLog formato : formatos) {
				out.print("<option value=\"" + formato.getId() + "\">");
				out.print(formato.getNome());
				out.println("</option>");
			}
		out.println("</select>");
	}
	
}
