package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
		 * Validação dos campos
		 * */
		Versao v = new Versao();
		LocalDate ld = LocalDate.parse(request.getParameter("date"));
		LocalTime lt = LocalTime.parse(request.getParameter("time"));
		LocalDateTime l = LocalDateTime.of(ld, lt);
		Long id = Long.parseLong(request.getParameter("sistema"));
		
		Sistema s = SistemaServices.obterPorId(id);
		
		v.setNome(request.getParameter("nome"));
		v.setSistema(s);
		v.setTimestampLiberacao(l);
		VersionServices.insertVersion(v);
		
		request.getRequestDispatcher("versionRegistration.jsp").forward(request, response);
	}

}