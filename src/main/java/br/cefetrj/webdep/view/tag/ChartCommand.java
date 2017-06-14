package br.cefetrj.webdep.view.tag;

/**
 * Interface de um comando que gera um gráfico
 * 
 * @author diogo
 * @since 0.2
 */
public interface ChartCommand {
	
	/**
	 * Gera um gráfico retornando ele em uma String. 
	 * A string gerada será impressa no JSP.
	 * 
	 * @param largura largura da imagem do gráfico
	 * @param altura altura da imagem do gráfico
	 * @param dados dados a serem passados para o comando de geração do gráfico
	 * @return string com o gráfico
	 * @throws Exception qualquer exceção que ocorra durante a geração dos gráficos
	 */
	public String gerarGrafico(Integer largura, Integer altura, Object dados) throws Exception;

}
