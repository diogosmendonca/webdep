package br.cefetrj.webdep.view.tag;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import br.cefetrj.webdep.model.entity.Servidor;
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
public class CadastroSistemaServidorComboTag extends SimpleTagSupport {

	private Integer serverId;
	
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

	public Integer getServerId() {
		return serverId;
	}

	public void setServerId(Integer userId) {
		this.serverId = serverId;
	}

	public void doTag() throws JspException, IOException {
		
		
		JspWriter out = getJspContext().getOut();
		List<Servidor> servidores = ServidorServices.listAllServidor();

		out.print("<select ");
		if(getClassCss() != null){
			out.print(" class=\"" + getClassCss() + "\" ");
		}
		if(getStyle() != null){
			out.print(" style=\"" + getStyle() + "\" ");
		}
		out.println(" name=\"servidor\" id=\"servidor\">");
		HttpServletRequest request = ((HttpServletRequest)((PageContext)getJspContext()).getRequest());
		HttpSession session = request.getSession();
		
		String mensagem = "";
		String lang = (String)session.getAttribute("lang");
		if (lang.equals("en_US")){
			mensagem = "Select a Server";
		} else if(lang.equals("pt_BR")){
			mensagem = "Selecione um Servidor";
		}
		if(servidores != null){
			out.print("<option value='' selected disabled>"+ mensagem +"</option>");
			for (Servidor servidor : servidores) {
				out.print("<option value=\"" + servidor.getId() + "\">");
				out.print(servidor.getNome());
				out.println("</option>");
			}
		} else {
			
		}
		out.println("</select>");
	}
	
}
