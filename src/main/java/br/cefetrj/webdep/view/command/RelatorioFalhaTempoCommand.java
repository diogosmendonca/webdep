package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.entity.PadraoURL;
import br.cefetrj.webdep.model.entity.RegistroLogAcesso;
import br.cefetrj.webdep.services.PadraoURLServices;
import br.cefetrj.webdep.services.RegistroLogAcessoService;

@WebServlet("/RelatorioFalhaTempo")
public class RelatorioFalhaTempoCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recebe parÃ¢metros
		String padraoUrl = request.getParameter("padraoUrl");
		PadraoURL padrao = null;
		LocalDate idate, fdate;

		// Inicializa as variÃ¡veis de validaÃ§Ã£o de parÃ¢metros
		boolean formValido = true;
		String msgKeys = "";

		// Valida id do sistema
		Long idSistema = null;
		if (request.getSession().getAttribute("idsistema") != null) {
			idSistema = (Long) request.getSession().getAttribute("idsistema");
		} else {
			formValido = false;
			request.setAttribute("idSistemaEMsg", "br.cefetrj.webdep.jsp.acessofalha.sistemMes");
		}

		// Valida padrÃ£o de URL
		if (!(padraoUrl != null && padraoUrl.trim().length() > 0)) {
			formValido = false;
			request.setAttribute("padraoUrlStatus", "has-error");
			msgKeys += "br.cefetrj.webdep.jsp.acessofalha.patternField";
		}
		
		try {
			idate = LocalDate.parse(request.getParameter("initialDate"));
			fdate = LocalDate.parse(request.getParameter("finalDate"));
			if (idate.isAfter(fdate)){
				formValido = false;
				msgKeys += "data inicial depois que a data final";
			}
		}
			catch (DateTimeParseException e) {
			e.printStackTrace();
			// Set error attribute
			formValido = false;
			msgKeys += "data invalida";
			}
		request.setAttribute("formValido", formValido);
		request.setAttribute("msgKeys", msgKeys);
		
		
		
		try{
			Long padraoIdLong = null; 
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
		
		List<RegistroLogAcesso> acessos = RegistroLogAcessoService.listAllRegisters();
		List<Integer> codigosErro = new ArrayList<Integer>();
		codigosErro.add(400);
		
		//Filtra pelo padrÃ£o de url passado
		acessos = RegistroLogAcessoService.filterByPadraoURL(acessos, padrao);
		
		//Conta a quantidade de acessos nas URLs
		Map<String, Long> contagemAcessosUrls = RegistroLogAcessoService.countByURL(acessos);
		
		//Separa as contagem em dois grupos, URLs que apresentaram erro e que nÃ£o apresentaram
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
		
		List<Map<String, Long>> dados = new ArrayList<Map<String, Long>>();
		dados.add(contagemAcessosUrlsComFalha);
		request.setAttribute("dadosGrafico", dados);
		
		request.getRequestDispatcher("relatorioFalhaTempo.jsp").forward(request, response);

	}
}