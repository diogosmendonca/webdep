package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.entity.PadraoURL;
import br.cefetrj.webdep.model.entity.RegistroLogAcesso;
import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.model.entity.Versao;
import br.cefetrj.webdep.services.PadraoURLServices;
import br.cefetrj.webdep.services.RegistroLogAcessoService;
import br.cefetrj.webdep.services.SistemaServices;
import br.cefetrj.webdep.services.VersionServices;

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
		Sistema sistema = null;
		if(request.getSession().getAttribute("idsistema") != null){
			Long idSistema = (Long)request.getSession().getAttribute("idsistema");
			sistema = SistemaServices.obterPorId(idSistema);
			if(sistema == null){
				formValido = false;
				request.setAttribute("idSistemaEMsg", "br.cefetrj.webdep.jsp.acessofalha.sistemMes");
			}
		}else{
			formValido = false;
			request.setAttribute("idSistemaEMsg", "br.cefetrj.webdep.jsp.acessofalha.sistemMes");
		}
		
		//Valida padrão de URL
		PadraoURL padrao = null;
		if(padraoUrl != null && padraoUrl.trim().length() > 0){
			Long padraoIdLong = null; 
			try{
				padraoIdLong = Long.parseLong(padraoUrl);
				padrao = PadraoURLServices.obterPorId(padraoIdLong);
				if(padrao == null){
					formValido = false;
					request.setAttribute("padraoUrlStatus", "has-error");
					msgKeys += "br.cefetrj.webdep.jsp.acessofalha.patternField,";
				}
			}catch(Exception e){
				formValido = false;
				request.setAttribute("padraoUrlStatus", "has-error");
				msgKeys += "br.cefetrj.webdep.jsp.acessofalha.patternField,";
			}
		}else{
			formValido = false;
			request.setAttribute("padraoUrlStatus", "has-error");
			msgKeys += "br.cefetrj.webdep.jsp.acessofalha.patternField,";
		}
		
		//Valida Versões
		List<Versao> versoesList = new ArrayList<Versao>();
		if(versoes != null && versoes.length > 0){
			try{
				for (String v : versoes) {
					Versao vInst = VersionServices.obterPorId(Long.parseLong(v));
					if(vInst != null){
						versoesList.add(vInst);
					}else{
						formValido = false;
						request.setAttribute("versoesStatus", "has-error");
						msgKeys += "br.cefetrj.webdep.jsp.acessofalha.versionField,";
						break;
					}
				}
			}catch(Exception e){
				formValido = false;
				request.setAttribute("versoesStatus", "has-error");
				msgKeys += "br.cefetrj.webdep.jsp.acessofalha.versionField,";
			}
		}else{
			formValido = false;
			request.setAttribute("versoesStatus", "has-error");
			msgKeys += "br.cefetrj.webdep.jsp.acessofalha.versionField,";
		}
		
		//Valida Código HTTP OK
		List<Integer> codigosOk = new ArrayList<Integer>();
		if(codigosHttpOk != null && codigosHttpOk.length > 0){
			try{
				for (String c: codigosHttpOk) {
					codigosOk.add(Integer.parseInt(c));
				}
			}catch(Exception e){
				formValido = false;
				request.setAttribute("codigosHttpOkStatus", "has-error");
				msgKeys += "br.cefetrj.webdep.jsp.acessofalha.okCode,";
			}
		}else{
			formValido = false;
			request.setAttribute("codigosHttpOkStatus", "has-error");
			msgKeys += "br.cefetrj.webdep.jsp.acessofalha.okCode,";
		}
		
		//Valida Código HTTP Erro
		List<Integer> codigosErro = new ArrayList<Integer>();
		if(codigosHttpErro != null && codigosHttpErro.length > 0){
			try{
				for (String c: codigosHttpErro) {
					codigosErro.add(Integer.parseInt(c));
				}
			}catch(Exception e){
				formValido = false;
				request.setAttribute("codigosHttpErroStatus", "has-error");
				msgKeys += "br.cefetrj.webdep.jsp.acessofalha.errorCode,";
			}
		}else{
			formValido = false;
			request.setAttribute("codigosHttpErroStatus", "has-error");
			msgKeys += "br.cefetrj.webdep.jsp.acessofalha.errorCode,";
		}
		
		//Se o formulário não é váldo retorna.
		if(!formValido){
			request.setAttribute("msgKeys", msgKeys);
			request.setAttribute("formValido", formValido);
			request.getRequestDispatcher("acessoFalha.jsp").forward(request, response);
			return;
		}
		
		//Junta os códigos de ok e erro para buscar tudo junto
		List<Integer> codigosTodos = new ArrayList<Integer>();
		codigosTodos.addAll(codigosOk);
		codigosTodos.addAll(codigosErro);
		
		//Lista os registros de log de acesso no sistema e versões selecionados
		List<RegistroLogAcesso> acessos = 
				RegistroLogAcessoService.listAcessosBySistemaVersoesCodigos(sistema, versoesList, codigosTodos);
		
		//Filtra pelo padrão de url passado
		acessos = RegistroLogAcessoService.filterByPadraoURL(acessos, padrao);
		
		//Conta a quantidade de acessos nas URLs
		Map<String, Long> contagemAcessosUrls = RegistroLogAcessoService.countByURL(acessos);
		
		//Separa as contagem em dois grupos, URLs que apresentaram erro e que não apresentaram
		Map<String, Long> contagemAcessosUrlsSemFalha = new HashMap<>(); 
		Map<String, Long> contagemAcessosUrlsComFalha = new HashMap<>();
		
		for (String url: contagemAcessosUrls.keySet()) {
			if(RegistroLogAcessoService.urlsHasCodes(url, codigosErro, acessos)){
				contagemAcessosUrlsComFalha.put(url, contagemAcessosUrls.get(url));
			}else{
				contagemAcessosUrlsSemFalha.put(url, contagemAcessosUrls.get(url));
			}
		}
		
		//redireciona para a tela apresentar os dados
		request.setAttribute("formValido", formValido);
		request.setAttribute("contagemAcessosUrlsComFalha", contagemAcessosUrlsComFalha);
		request.setAttribute("contagemAcessosUrlsSemFalha", contagemAcessosUrlsSemFalha);
		
		List<Map<String, Long>> dados = new ArrayList<Map<String, Long>>();
		dados.add(contagemAcessosUrlsSemFalha);
		dados.add(contagemAcessosUrlsComFalha);
		request.setAttribute("dadosGrafico", dados);
		
		request.getRequestDispatcher("acessoFalha.jsp").forward(request, response);
	}

}
