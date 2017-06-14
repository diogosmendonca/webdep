<<<<<<< HEAD
/**
 * 
 */
package br.cefetrj.webdep.view.tag;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.log4j.Logger;

/**
 * Tag utilizada ao gerar um gráfico de dispersão.
 * 
 * <%@ taglib prefix="cmp" uri="WEB-INF/components.tld"%>
 * <cmp:RChartTag/>
 * 
 * @author Lyago
 *
 */
public class ChartTag extends SimpleTagSupport{

	
	private Integer largura;
	
	private Integer altura;
	
	private String tipoGrafico;
	
	private Object dados;

	private static Map<String, ChartCommand> comandos = new HashMap<>();
	
	static{
		comandos.put("boxplot", new BoxPlotRChartTag());
		comandos.put("scatterplot", new ScatterPlotRChartTag());
	}
	
	/**
	 * 
	 */
	@Override
	public void doTag() throws JspException, IOException {
		
		String grafico;
		try {
			grafico = comandos.get(tipoGrafico).gerarGrafico(largura, altura, dados);
			getJspContext().getOut().append(grafico);
		} catch (Exception e) {
			Logger lg = Logger.getLogger(ChartTag.class);
			lg.error("Não foi possível gerar o gráfico", e);
		}
		
	}



	public Integer getLargura() {
		return largura;
	}



	public void setLargura(Integer largura) {
		this.largura = largura;
	}



	public Integer getAltura() {
		return altura;
	}



	public void setAltura(Integer altura) {
		this.altura = altura;
	}



	public String getTipoGrafico() {
		return tipoGrafico;
	}



	public void setTipoGrafico(String tipoGrafico) {
		this.tipoGrafico = tipoGrafico;
	}



	public Object getDados() {
		return dados;
	}



	public void setDados(Object dados) {
		this.dados = dados;
	} 

}
=======
/**
 * 
 */
package br.cefetrj.webdep.view.tag;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.log4j.Logger;

/**
 * Tag utilizada ao gerar um gráfico de dispersão.
 * 
 * <%@ taglib prefix="cmp" uri="WEB-INF/components.tld"%>
 * <cmp:RChartTag/>
 * 
 * @author Lyago
 *
 */
public class ChartTag extends SimpleTagSupport{

	
	private Integer largura;
	
	private Integer altura;
	
	private String tipoGrafico;
	
	private Object dados;

	private static Map<String, ChartCommand> comandos = new HashMap<>();
	
	static{
		comandos.put("boxplot", new BoxPlotRChartTag());
		comandos.put("scatterplotHttpReport", new ScatterPlotHttpReportRChartTag());
		comandos.put("lineplot", new LinePlotRChartTag());
	}
	
	/**
	 * 
	 */
	@Override
	public void doTag() throws JspException, IOException {
		
		String grafico;
		try {
			grafico = comandos.get(tipoGrafico).gerarGrafico(largura, altura, dados);
			getJspContext().getOut().append(grafico);
		} catch (Exception e) {
			Logger lg = Logger.getLogger(ChartTag.class);
			lg.error("Não foi possível gerar o gráfico", e);
		}
		
	}



	public Integer getLargura() {
		return largura;
	}



	public void setLargura(Integer largura) {
		this.largura = largura;
	}



	public Integer getAltura() {
		return altura;
	}



	public void setAltura(Integer altura) {
		this.altura = altura;
	}



	public String getTipoGrafico() {
		return tipoGrafico;
	}



	public void setTipoGrafico(String tipoGrafico) {
		this.tipoGrafico = tipoGrafico;
	}



	public Object getDados() {
		return dados;
	}



	public void setDados(Object dados) {
		this.dados = dados;
	} 

}
>>>>>>> grafRelatorioAcesso
