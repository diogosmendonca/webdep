package br.cefetrj.webdep.view.tag;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.rosuda.REngine.REngineException;

public class ScatterPlotRChartTag extends RChartCommand {
	
	@Override
	public void plot(Object dados) {
		int[] dados2 = {15, 32, 87, 100, 112, 34, 65, 29}; 
		try {
			List<Map<String, Long>> list = (List<Map<String, Long>>) dados;
			Map<String, Long> contagemAcessosUrls = list.get(0);
			Map<String, Long> contagemAcessosUrlsComFalha = list.get(1);
			
			double[] acessosTotais = new double[contagemAcessosUrls.values().size()];
	
			int i = 0;
			for (Long value: contagemAcessosUrls.values()) {
				acessosTotais[i] = value;
				i++;
			}
			c.assign("acessosTotais", acessosTotais);
			
			
			double[] acessosComFalha = new double[contagemAcessosUrlsComFalha.values().size()];
			i = 0;
			for (Long value: contagemAcessosUrlsComFalha.values()) {
				acessosComFalha[i] = value;
				i++;
			}
			c.assign("acessosComFalha", acessosComFalha);
		
			c.eval("plot(acessosTotais, acessosComFalha, "
					+ " xlab = 'Número de Acessos', "
					+ " ylab = 'Número de Falhas')"); 
			
		} catch (REngineException e) {
			Logger lg = Logger.getLogger(BoxPlotRChartTag.class);
			lg.error("NÃ£o foi possÃ­vel gerar o grÃ¡fico Scatter Plot", e);
		}
	}
}
