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

import br.cefetrj.webdep.model.entity.RegistroLogAcesso;
import br.cefetrj.webdep.services.LogAcessoServices;

public class AccessProfileReportCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LocalDate idate, fdate;
		LocalTime itime, ftime;
		LocalDateTime ildt, fldt;
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
		
		int group = Integer.parseInt(request.getParameter("groupApr"));
		List<RegistroLogAcesso> logs = LogAcessoServices.buscarLog(ildt, fldt, "");
		Map<Integer, Integer> logsAgrupado = new HashMap<Integer, Integer>();
		if(group == 2){
			int lastYear = 0;
			Iterator<RegistroLogAcesso> it = logs.iterator();
			int quant = 0;
			while(it.hasNext()){
				RegistroLogAcesso log = it.next();
				int curYear = log.getTimestamp().getYear();
				if(lastYear == 0 || curYear == lastYear){
					quant++;
					lastYear = curYear;
				}
				else{
					logsAgrupado.put(lastYear, quant);
					quant = 1;
					lastYear = curYear;
				}
			}
			logsAgrupado.put(lastYear, quant);
		}else{
			if(group == 1){
				int lastMonth = 0;
				Iterator<RegistroLogAcesso> it = logs.iterator();
				int quant = 0;
				while(it.hasNext()){
					RegistroLogAcesso log = it.next();
					int curMonth = log.getTimestamp().getMonthValue();
					if(lastMonth == 0 || curMonth == lastMonth){
						quant++;
						lastMonth = curMonth;
					}
					else{
						logsAgrupado.put(lastMonth, quant);
						quant = 1;
						lastMonth= curMonth;
					}
				}
				logsAgrupado.put(lastMonth, quant);
			} else{
				if(group == 0){
					int lastDay = 0;
					Iterator<RegistroLogAcesso> it = logs.iterator();
					int quant = 0;
					while(it.hasNext()){
						RegistroLogAcesso log = it.next();
						int curDay = log.getTimestamp().getDayOfYear();
						if(lastDay == 0 || curDay == lastDay){
							quant++;
							lastDay = curDay;
						}
						else{
							logsAgrupado.put(lastDay, quant);
							quant = 1;
							lastDay= curDay;
						}
					}
					logsAgrupado.put(lastDay, quant);
				}
			}
		}
		
		request.setAttribute("dados", logsAgrupado);
		request.setAttribute("dataIn", dataIn);
		request.getRequestDispatcher("accessProfileReport.jsp").forward(request, response);
	}
}