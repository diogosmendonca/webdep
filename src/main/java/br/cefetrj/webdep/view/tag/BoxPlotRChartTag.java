package br.cefetrj.webdep.view.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * Gráfico de boxplot
 * 
 * @author diogo
 *
 */
public class BoxPlotRChartTag extends SimpleTagSupport {
	
	private List<List<Double>> dados;
	
	private List<String> labels;
	
	@Override
	public void doTag() throws JspException, IOException {
		
	}

}
