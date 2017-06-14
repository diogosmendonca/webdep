package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.entity.PadraoURL;
import br.cefetrj.webdep.model.entity.RegistroLogAcesso;
import br.cefetrj.webdep.services.LogAcessoServices;
import br.cefetrj.webdep.services.PadraoURLServices;
import br.cefetrj.webdep.services.RegistroLogAcessoService;

public class AccessProfileReportCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LocalDate idate, fdate;
		LocalTime itime, ftime;
		LocalDateTime ildt, fldt;
		String padraoUrl = request.getParameter("padraoUrl");
		PadraoURL padrao = null;
		DateTimeFormatter fmtMonth = DateTimeFormatter.ofPattern("MM-yyyy");
		DateTimeFormatter fmtDay = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		DateTimeFormatter fmtYear = DateTimeFormatter.ofPattern("yyyy");
		Map<Integer, Integer> yearList = new HashMap<Integer, Integer>();
		Map<Integer, Integer> yearListCount = new HashMap<Integer, Integer>();
		Map<String, String> monthList = new HashMap<String, String>();
		Map<String, Integer> monthListCount = new HashMap<String, Integer>();
		Map<String, String> dayList = new HashMap<String, String>();
		Map<String, Integer> dayListCount = new HashMap<String, Integer>();
		
		boolean dataIn = true;
		/*
		 * Valida��o dos campos
		 */
		if(padraoUrl != null && padraoUrl.trim().length() > 0){
			Long padraoIdLong = null; 
			try{
				padraoIdLong = Long.parseLong(padraoUrl);
				padrao = PadraoURLServices.obterPorId(padraoIdLong);
				if(padrao == null){
					dataIn = false;
					request.setAttribute("dataIn", dataIn);
					request.getRequestDispatcher("accessProfileReport.jsp").forward(request, response);
					return;
				}
			}catch(Exception e){
				dataIn = false;
				request.setAttribute("dataIn", dataIn);
				request.getRequestDispatcher("accessProfileReport.jsp").forward(request, response);
				return;
			}
		}
		else{
			dataIn = false;
			request.setAttribute("dataIn", dataIn);
			request.getRequestDispatcher("accessProfileReport.jsp").forward(request, response);
			return;
		}
		try {
			idate = LocalDate.parse(request.getParameter("initialDate"));
			itime = LocalTime.parse(request.getParameter("initialTime"));
			fdate = LocalDate.parse(request.getParameter("finalDate"));
			ftime = LocalTime.parse(request.getParameter("finalTime"));
			ildt = LocalDateTime.of(idate, itime);
			fldt = LocalDateTime.of(fdate, ftime);
		} catch (DateTimeParseException e){
			e.printStackTrace();
			//Set error attribute
			dataIn = false;
			request.setAttribute("dataIn", dataIn);
			request.getRequestDispatcher("accessProfileReport.jsp").forward(request, response);
			return;
		}
		
		//Pega por qual agrupamento o gráfico deve ser feito
		int group = Integer.parseInt(request.getParameter("groupApr"));
		
		//Pega os logs de entre a data inicial e a data final
		List<RegistroLogAcesso> logs = RegistroLogAcessoService.filterByTimestamp(ildt, fldt);
		//Filtra os logs do pradrão url selecionado
		logs = RegistroLogAcessoService.filterByPadraoURL(logs, padrao);
		
		Map<Integer, Integer> logsAgrupado = new HashMap<Integer, Integer>();
		if(group == 2){
			Iterator<RegistroLogAcesso> it = logs.iterator();
			while(it.hasNext()){
				RegistroLogAcesso log = it.next();
				int curYear = log.getTimestamp().getYear();
				if(logsAgrupado.containsKey(curYear)) logsAgrupado.put(curYear, logsAgrupado.get(curYear)+1);
				else logsAgrupado.put(curYear,1);
			}
		}else{
			if(group == 1){
				Iterator<RegistroLogAcesso> it = logs.iterator();
				while(it.hasNext()){
					RegistroLogAcesso log = it.next();
					int curMonth = log.getTimestamp().getMonthValue();
					if(logsAgrupado.containsKey(curMonth)) logsAgrupado.put(curMonth, logsAgrupado.get(curMonth)+1);
					else logsAgrupado.put(curMonth,1);
				}
			} else{
				Iterator<RegistroLogAcesso> it = logs.iterator();
				while(it.hasNext()){
					RegistroLogAcesso log = it.next();
					int curDay = log.getTimestamp().getYear();
					if(logsAgrupado.containsKey(curDay)) logsAgrupado.put(curDay, logsAgrupado.get(curDay)+1);
					else logsAgrupado.put(curDay,1);
				}
			}
		}
		
		request.setAttribute("dados", logsAgrupado);
		request.setAttribute("dataIn", dataIn);
		request.getRequestDispatcher("accessProfileReport.jsp").forward(request, response);
	}
}