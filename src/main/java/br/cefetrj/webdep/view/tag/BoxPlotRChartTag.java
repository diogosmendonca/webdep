package br.cefetrj.webdep.view.tag;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.rosuda.REngine.REngineException;

/**
 * Gráfico de boxplot
 * 
 * @author diogo
 *
 */
public class BoxPlotRChartTag extends RChartCommand {

	@Override
	public void plot(Object dados) {
		int[] dados2 = {15, 32, 87, 100, 112, 34, 65, 29}; 
		try {
			List<Map<String, Long>> list = (List<Map<String, Long>>) dados;
			Map<String, Long> contagemAcessosUrlsSemFalha = list.get(0);
			Map<String, Long> contagemAcessosUrlsComFalha = list.get(1);
			
			double[] acessosSemFalha = new double[contagemAcessosUrlsSemFalha.values().size()];
			int i = 0;
			for (Long value: contagemAcessosUrlsSemFalha.values()) {
				acessosSemFalha[i] = value;
				i++;
			}
			c.assign("acessosSemFalha", acessosSemFalha);
			
			double[] acessosComFalha = new double[contagemAcessosUrlsComFalha.values().size()];
			i = 0;
			for (Long value: contagemAcessosUrlsComFalha.values()) {
				acessosComFalha[i] = value;
				i++;
			}
			c.assign("acessosComFalha", acessosComFalha);
			
			c.eval("boxplot(acessosSemFalha, acessosComFalha, "
					+ " names = c('URLs sem Falha', 'URL com Falha'), "
					+ " ylab = 'número de acessos')"); 
			
		} catch (REngineException e) {
			Logger lg = Logger.getLogger(BoxPlotRChartTag.class);
			lg.error("Não foi possível gerar o gráfico Box Plot", e);
		}
	}
	
}
