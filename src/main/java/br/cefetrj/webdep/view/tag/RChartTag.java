/**
 * 
 */
package br.cefetrj.webdep.view.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.rosuda.REngine.Rserve.RConnection;

/**
 * Tag utilizada ao gerar um gráfico de dispersão.
 * 
 * <%@ taglib prefix="cmp" uri="WEB-INF/components.tld"%>
 * <cmp:RChartTag/>
 * 
 * @author Lyago
 *
 */
public class RChartTag extends SimpleTagSupport{

	/**
	 * 
	 */
	@Override
	public void doTag() throws JspException, IOException {
		
		RConnection c;
		try {
			c = new RConnection();
			int[] dados = {15, 32, 87, 100, 112, 34, 65, 29}; 
			c.assign("dados", dados);
			c.eval("png(tf1 <- tempfile(fileext = '.png'))");
			c.eval("plot(dados)"); 
			c.eval("dev.off()");
			            
			c.eval("library(RCurl)");
			c.eval("txt <- base64Encode(readBin(tf1, 'raw', file.info(tf1)[1, 'size']), 'txt')");
			String img = c.eval("sprintf('<img src=\"data:image/png;base64,%s\">', txt)").asString();
			
			getJspContext().getOut().append(img);
			c.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 

}
