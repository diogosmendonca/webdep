package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Enumeration;
import java.util.HashMap;
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
		Map<Integer, Integer> yearList = new HashMap<Integer, Integer>();
		Map<Integer, Integer> yearListCount = new HashMap<Integer, Integer>();
		Map<String, String> monthList = new HashMap<String, String>();
		Map<String, Integer> monthListCount = new HashMap<String, Integer>();
		Map<String, String> dayList = new HashMap<String, String>();
		Map<String, Integer> dayListCount = new HashMap<String, Integer>();
		
		/*
		 * Validação dos campos
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
			request.getRequestDispatcher("accessProfileReport.jsp").forward(request, response);
			return;
			
		}
		int group = Integer.parseInt(request.getParameter("groupApr"));
//		System.out.println(
//				"idate: " + idate +
//				"\nfdate: " + fdate +
//				"\nitime: " + itime +
//				"\nftime: " + ftime +
//				"\ngroup: " + group
//				);
		List<RegistroLogAcesso> logs = LogAcessoServices.buscarLog(ildt, fldt, "");
		for(RegistroLogAcesso log : logs){
			LocalDateTime cur = log.getTimestamp();
			if(group == 2){
				int year = cur.getYear();
				if(yearList.containsValue(year)){
					yearListCount.put(yearList.get(year), yearListCount.get(year) + 1);
				} else {
					yearList.put(year, year);
					yearListCount.put(year, 1);
				}
				
			} else if(group == 1){
				int year = cur.getYear();
				int month = cur.getMonthValue();
				String key = cur.format(fmtMonth);
				if(monthList.containsValue(key)){
					monthListCount.put(monthList.get(key), monthListCount.get(key) + 1);
				} else {
					monthList.put(key, key);
					monthListCount.put(key, 1);
				}
			} else if(group == 0){
				int year = cur.getYear();
				int month = cur.getMonthValue();
				int day = cur.getDayOfMonth();
				String key = cur.format(fmtDay);
				if(dayList.containsValue(key)){
					dayListCount.put(dayList.get(key), dayListCount.get(key) + 1);
				} else {
					dayList.put(key, key);
					dayListCount.put(key, 1);
				}
				
			}
		}
//		for(Integer i : yearList.values()){
//			System.out.println(
//					"Year: " + i +
//					"\nAccesses: " + yearListCount.get(i)
//					);
//		}
		if(group == 0){
					
		} else if (group == 1){
			
		}else if (group == 2){
			
		}
		request.getRequestDispatcher("accessProfileReport.jsp").forward(request, response);

	}

}
