package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.model.entity.Versao;
import br.cefetrj.webdep.services.SistemaServices;
import br.cefetrj.webdep.services.VersionServices;

public class ChangeVersionCommand implements Command {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		/*
		 *Validação dos campos
		 * 
		 */
		

		Long idv = Long.parseLong(request.getParameter("id"));
		Versao v = VersionServices.obterPorId(idv);
		
		LocalDate ld = LocalDate.parse(request.getParameter("date"));
		LocalTime lt = LocalTime.parse(request.getParameter("time"));
		LocalDateTime ldt = LocalDateTime.of(ld, lt);
		
		String nome = request.getParameter("nome");
		if(nome.trim().length()>0 && nome.trim().length()<101)
			v.setNome(nome);
		
		Long ids = Long.parseLong(request.getParameter("sistema"));
		Sistema s = SistemaServices.obterPorId(ids);
		v.setSistema(s);
		
		v.setTimestampLiberacao(ldt);
		
		VersionServices.changeVersion(v);
		
		request.getRequestDispatcher("versionSearch.jsp").forward(request, response);
		
		
	}
}
