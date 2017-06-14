package br.cefetrj.webdep.view.tag;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.rosuda.REngine.REngineException;

public class LinePlotRChartTag extends RChartCommand{
	public void plot(Object dados) {
		try{
			Map<Integer, Integer> logsAgrupados = (Map<Integer, Integer>)dados;
			int[] x = new int[logsAgrupados.keySet().size()];
			int[] y = new int[logsAgrupados.values().size()];
			
			int i = 0;
			for(int k : logsAgrupados.keySet()){
				x[i] = k;
				i++;
			}
			
			i = 0;
			for(Integer v: logsAgrupados.values()){
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
