package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.entity.RegistroLogAcesso;
import br.cefetrj.webdep.services.LogAcessoServices;

@WebServlet("/RelatorioFalhaTempo")
public class RelatorioFalhaTempoCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recebe parâmetros
		String padraoUrl = request.getParameter("padraoUrl");
		LocalDate idate, fdate;
		DateTimeFormatter fmtMonth = DateTimeFormatter.ofPattern("MM-yyyy");
		DateTimeFormatter fmtDay = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		DateTimeFormatter fmtYear = DateTimeFormatter.ofPattern("yyyy");
		Map<Integer, Integer> yearList = new HashMap<Integer, Integer>();
		Map<Integer, Integer> yearListCount = new HashMap<Integer, Integer>();
		Map<String, String> monthList = new HashMap<String, String>();
		Map<String, Integer> monthListCount = new HashMap<String, Integer>();
		Map<String, String> dayList = new HashMap<String, String>();
		Map<String, Integer> dayListCount = new HashMap<String, Integer>();

		// Inicializa as variáveis de validação de parâmetros
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

		// Valida padrão de URL
		if (!(padraoUrl != null && padraoUrl.trim().length() > 0)) {
			formValido = false;
			request.setAttribute("padraoUrlStatus", "has-error");
			request.setAttribute("formValido", formValido);
			msgKeys += "br.cefetrj.webdep.jsp.acessofalha.patternField";
		}

		//request.getRequestDispatcher("relatorioFalhaTempo.jsp").forward(request, response);
		
		//try {
		idate = LocalDate.parse(request.getParameter("initialDate"));
		fdate = LocalDate.parse(request.getParameter("finalDate"));
		if (idate == null || fdate == null){
			formValido = false;
			msgKeys += "data inválida";
		}
		/*} catch (DateTimeParseException e) {
			e.printStackTrace();
			// Set error attribute
			dataIn = false;
			request.setAttribute("dataIn", dataIn);
			request.getRequestDispatcher("relatorioFalhaTempo.jsp").forward(request, response);
			return;

		//}
		//int group = Integer.parseInt(request.getParameter("groupApr"));
*/
		request.setAttribute("msgKeys", msgKeys);
		request.getRequestDispatcher("relatorioFalhaTempo.jsp").forward(request, response);

	}
}
