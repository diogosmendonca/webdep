package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.entity.Versao;
import br.cefetrj.webdep.services.VersionServices;

public class InsertVersionCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * Valida��o dos campos e inser��o da combo de sistemas do professor
		 * */
		Versao v = new Versao();
		LocalDate ld = LocalDate.parse(request.getParameter("date"));
		LocalTime lt = LocalTime.parse(request.getParameter("time"));
		LocalDateTime l = LocalDateTime.of(ld, lt);
		System.out.println(request.getParameter("sistema"));
		v.setNome(request.getParameter("nome"));
		//v.setSistema();
		v.setTimestampLiberacao(l);
		VersionServices.insertVersion(v);
		
		request.getRequestDispatcher("versionRegistration.jsp").forward(request, response);
	}

}
