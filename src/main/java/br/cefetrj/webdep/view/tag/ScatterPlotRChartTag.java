package br.cefetrj.webdep.view.tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.rosuda.REngine.REngineException;

/**
 * Gráfico de scatterplot
 * 
 * @author caiopotter
 *
 */
public class ScatterPlotRChartTag extends RChartCommand {

	@Override
	public void plot(Object dados) {
		int[] dados2 = {15, 32, 87, 100, 112, 34, 65, 29}; 
		try {
			List<Map<String, Long>> list = (List<Map<String, Long>>) dados;
			Map<String, Long> contagemAcessosUrlsSemFalha = list.get(0);
			Map<String, Long> contagemAcessosUrlsComFalha = list.get(1);
			List<String> acessoList = new ArrayList<String>(contagemAcessosUrlsSemFalha.keySet());
			String acessoConcat = "";
			int i;
			double[] acessosComFalha = new double[contagemAcessosUrlsComFalha.values().size()];
			i = 0;
			for (Long value: contagemAcessosUrlsComFalha.values()) {
				acessosComFalha[i] = value;
				i++;
			}
			
			for (int j=0; j < acessoList.size(); j++){
				acessoConcat += acessoList.get(j) + ", ";
			}
			
			c.assign("acessosComFalha", acessosComFalha);
			c.assign("acessoConcat", acessoConcat);
			c.eval("acessoList = as.list(strsplit(acessoConcat, ',')[[1]])");
			c.eval("plot(acessosSemFalha, yaxt = 'n', xlab ='numero de acessos', ylab = c('URLs com Falha'))");
			c.eval("axis(2, at=1:acessosSemFalha, labels=acessoList[1:acessosSemFalha])");
			
		} catch (REngineException e) {
			Logger lg = Logger.getLogger(ScatterPlotRChartTag.class);
			lg.error("Não foi possível gerar o gráfico Plot", e);
		}
	}
	
}
