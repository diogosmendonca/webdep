package br.cefetrj.webdep.view.tag;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.rosuda.REngine.REngineException;

public class LinePlotRChartTag extends RChartCommand{
	public void plot(Object dados) {
		try{
			Map<String, Integer> logsAgrupados = (Map<String, Integer>)dados;
			Set<String> keys = logsAgrupados.keySet();
			Collection<Integer> values = logsAgrupados.values();
			String[] x = new String[keys.size()];
			int[] y = new int[values.size()];
			
			int i = 0;
			for(String k : keys){
				x[i] = k;
				i++;
			}
			
			i = 0;
			for(Integer v: values){
				y[i] = v;
				i++;
			}
			
			c.assign("x", x);
			c.assign("y", y);
			
			c.eval("plot(x, y, type='l')");
		} catch (REngineException e) {
			Logger lg = Logger.getLogger(BoxPlotRChartTag.class);
			lg.error("Não foi possível gerar o gráfico Box Plot", e);
		}
	}
}
