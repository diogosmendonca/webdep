package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.entity.Versao;
import br.cefetrj.webdep.services.VersionServices;

public class ChangeVersionCommand implements Command {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		/*
		 *Validação dos campos e preenchimento dos campos com os dados atuais
		 *da versão a ser alterada 
		 *Inclusão da combo de sistemas feita pelo professor 
		 * 
		 */
		
		ArrayList <Versao> l = (ArrayList) request.getSession().getAttribute("list");
		int index = Integer.parseInt(request.getParameter("index"));
		
		Versao v = l.get(index);
		
		LocalDate ld = LocalDate.parse(request.getParameter("date"));
		LocalTime lt = LocalTime.parse(request.getParameter("time"));
		LocalDateTime ldt = LocalDateTime.of(ld, lt);
		
		String nome = request.getParameter("nome");
		if(nome.trim().length()>0 && nome.trim().length()<101)
			v.setNome(nome);
		
		//v.setSistema();
		v.setTimestampLiberacao(ldt);
		
		VersionServices.changeVersion(v);
		
		request.getRequestDispatcher("versionSearch.jsp").forward(request, response);
		
		
	}
}
