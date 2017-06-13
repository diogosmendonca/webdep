package br.cefetrj.webdep.view.tag;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public abstract class RChartCommand implements ChartCommand{

	protected RConnection c;
	
	protected void createTempFile() throws RserveException{
		c.eval("png(tf1 <- tempfile(fileext = '.png'))");
	}
	
	protected String writeTempFile(Integer largura, Integer altura) throws RserveException, REXPMismatchException{
		c.eval("dev.off()");
        c.eval("library(RCurl)");
		c.eval("txt <- base64Encode(readBin(tf1, 'raw', file.info(tf1)[1, 'size']), 'txt')");
		
		String larguraStr = "";
		if (largura != null ){
			larguraStr = " width='" + largura + "' ";
		}
		
		String alturaStr = "";
		if (altura != null ){
			alturaStr = " height='" + altura + "' ";
		}
		
		return c.eval("sprintf('<img " + larguraStr + 
										 alturaStr + 
				" src=\"data:image/png;base64,%s\">', txt)").asString();
	}
	
	public abstract void plot(Object dados);
	
	
	@Override
	public String gerarGrafico(Integer largura, Integer altura, Object dados) throws Exception {
		c = new RConnection();
		createTempFile();
		plot(dados);
		String grafico = writeTempFile(largura, altura);
		c.close();
		return grafico;
	}

	
	
}
