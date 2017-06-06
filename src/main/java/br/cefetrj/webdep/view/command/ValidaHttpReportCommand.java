package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.entity.Versao;
import br.cefetrj.webdep.services.VersionServices;


@WebServlet("/ValidaHttpReport")
public class ValidaHttpReportCommand implements Command {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		boolean versionValidate = true;
		boolean errorValidate = true;
		boolean okValidate = true;
		String[] versionList;
		String[] errorList;
		String[] okList;
		
		if(request.getParameterValues("versionList") != null){
			versionList = request.getParameterValues("versionlist");
			List<Versao> v = null;
			for(int i = 0; i < versionList.length; i++){
				v.add(VersionServices.findByName(versionList[i]));
			}
		} else {
			versionValidate = false;
			request.setAttribute("versionValidate", versionValidate);
		}
		
		if(request.getParameterValues("errorList") != null){
			errorList = request.getParameterValues("errorList");
		} else {
			errorValidate = false;
			request.setAttribute("errorValidate", errorValidate);
		}
		
		
		if(request.getParameterValues("okList") != null){
			okList = request.getParameterValues("okList");
		} else {
			okValidate = false;
			request.setAttribute("okValidate", okValidate);
		}
		
		if((versionValidate == false) || (errorValidate == false) || (okValidate = false)){
			request.getRequestDispatcher("HTTPreport.jsp").forward(request, response);
			return;
		} else {
			//L�gica para a busca dos filtros...
		}
		
	}

}