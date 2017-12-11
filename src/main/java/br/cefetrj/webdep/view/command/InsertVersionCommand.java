package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.model.entity.Versao;
import br.cefetrj.webdep.services.SistemaServices;
import br.cefetrj.webdep.services.VersionServices;

public class InsertVersionCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * Valida��o dos campos
		 * */
		Versao v = new Versao();
		LocalDateTime l = null;
		String name = null;
		
		LocalTime lt = null;
		LocalDate ld = null;
		
		
		Long id = null;
		Sistema s= null;
		
		boolean dateIn = true;
		boolean timeIn = true;
		boolean systemIn = true;
		boolean nameIn = true;
		
		String msg = "";
		
		try{
			ld = LocalDate.parse(request.getParameter("date"));
		}catch(DateTimeParseException de){
			dateIn = false;
		}
		try{
			lt = LocalTime.parse(request.getParameter("time"));
		}catch(DateTimeParseException te){
			timeIn = false;
		}
		
		if(lt != null && ld != null)
			l = LocalDateTime.of(ld, lt);
		
		try{
			id = Long.parseLong(request.getParameter("sistema"));
		}catch(NumberFormatException ide){
			systemIn = false;
		}
		
		if(systemIn)
			s = SistemaServices.obterPorId(id);
		
		try{
		name = request.getParameter("nome").trim();
		if(name.isEmpty() || name.length()>100)
			throw new IllegalArgumentException();
		else
			System.out.println(name.length());
			v.setNome(name);
		}catch(IllegalArgumentException ne){
			nameIn = false;
		}
		
		if(s != null)
			v.setSistema(s);
		if(l != null)
			v.setTimestampLiberacao(l);
		if(dateIn && timeIn && nameIn && systemIn) {
			VersionServices.insertVersion(v);
		msg = "Versão cadastrada com sucesso";
		}
		else {
			request.setAttribute("version", v);
		}
		request.setAttribute("nameIn", nameIn);
		request.setAttribute("dateIn", dateIn);
		request.setAttribute("timeIn", timeIn);
		request.setAttribute("systemIn", systemIn);
		request.setAttribute("msg", msg);
		
		request.getRequestDispatcher("versionRegistration.jsp").forward(request, response);
	}

}