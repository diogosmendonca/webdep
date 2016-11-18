package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ValidaHttpReport")
public class ValidaHttpReportCommand implements Command {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String> version = new ArrayList<>();
		ArrayList<String> errorCode = new ArrayList<>();
		ArrayList<String> okCode = new ArrayList<>();
		
		System.out.println(request.getParameter("errorList"));
		
		
	}

}
