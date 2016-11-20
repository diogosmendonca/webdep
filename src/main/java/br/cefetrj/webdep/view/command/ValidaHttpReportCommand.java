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
		boolean versionValidate = true;
		boolean errorValidate = true;
		boolean okValidate = true;

		if(request.getParameterValues("versionList") != null){
			String versionList[] = request.getParameterValues("versionlist");
		} else {
			versionValidate = false;
			request.setAttribute("versionValidate", versionValidate);
		}
		
		if(request.getParameterValues("errorList") != null){
			String versionList[] = request.getParameterValues("errorList");
		} else {
			errorValidate = false;
			request.setAttribute("erorValidate", errorValidate);
		}
		
		
		if(request.getParameterValues("okList") != null){
			String versionList[] = request.getParameterValues("okList");
		} else {
			okValidate = false;
			request.setAttribute("okValidate", okValidate);
		}
		
		if((versionValidate == false) || (errorValidate == false) || (okValidate = false)){
			request.getRequestDispatcher("HTTPreport.jsp").forward(request, response);
			return;
		} else {
			//Lógica para a busca dos filtros...
		}
		
	}

}
