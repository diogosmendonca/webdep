package br.cefetrj.webdep.view.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Comando que trata a solicitação de emissão do relatório de Relação de Acesso x Falhas. 
 * 
 * @author diogo
 * @since 0.2
 * 
 */
public class AcessoFalhaCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//recebe os parâmetros enviados
		String padraoUrl 			= request.getParameter("padraoUrl");
		String[] codigosHttpOk 		= request.getParameterValues("codigosHttpOk");
		String[] versoes 			= request.getParameterValues("versoes");
		String[] codigosHttpErro 	= request.getParameterValues("codigosHttpErro");
		
		//inicializa variáveis para validação dos parâmetros
		boolean formValido 		= true;
		String msgKeys 			= "";

		//Valida o id do sistema na sessão
		Long idSistema = null; 
		if(request.getSession().getAttribute("idsistema") != null){
			idSistema = (Long)request.getSession().getAttribute("idsistema");
		}else{
			formValido = false;
			request.setAttribute("idSistemaEMsg", "br.cefetrj.webdep.jsp.acessofalha.sistemMes");
		}
		
		//Valida padrão de URL
		if(!(padraoUrl != null && padraoUrl.trim().length() > 0)){
			formValido = false;
			request.setAttribute("padraoUrlStatus", "has-error");
			msgKeys += "br.cefetrj.webdep.jsp.acessofalha.patternField,";
		}
		
		//Valida Versões
		if(!(versoes != null && versoes.length > 0)){
			formValido = false;
			request.setAttribute("versoesStatus", "has-error");
			msgKeys += "br.cefetrj.webdep.jsp.acessofalha.versionField,";
		}
		
		//Valida Código HTTP OK
		if(!(codigosHttpOk != null && codigosHttpOk.length > 0)){
			formValido = false;
			request.setAttribute("codigosHttpOkStatus", "has-error");
			msgKeys += "br.cefetrj.webdep.jsp.acessofalha.okCode,";
		}
		
		//Valida Código HTTP Erro
		if(!(codigosHttpErro != null && codigosHttpErro.length > 0)){
			formValido = false;
			request.setAttribute("codigosHttpErroStatus", "has-error");
			msgKeys += "br.cefetrj.webdep.jsp.acessofalha.errorCode,";
		}
		
		if(!formValido){
			request.setAttribute("msgKeys", msgKeys);
			request.getRequestDispatcher("acessoFalha.jsp").forward(request, response);
			return;
		}
		
		
		
		
	}

}
