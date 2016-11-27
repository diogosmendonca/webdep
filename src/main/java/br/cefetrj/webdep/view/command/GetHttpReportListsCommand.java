package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.entity.RegistroLogAcesso;
import br.cefetrj.webdep.model.entity.Versao;
import br.cefetrj.webdep.services.RegistroLogAcessoService;
import br.cefetrj.webdep.services.VersionServices;

public class GetHttpReportListsCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Versao> vers = new VersionServices().listAllVersions();
		request.setAttribute("versionList", vers);
		
		List<RegistroLogAcesso> codeOk = new ArrayList<RegistroLogAcesso>();
		List<RegistroLogAcesso> codeError = new ArrayList<RegistroLogAcesso>();
		List<RegistroLogAcesso> regLog = new RegistroLogAcessoService().listAllRegisters();
		
		for(int i=0; i < regLog.size(); i++) {
			if(regLog.get(i).getCodigo() >= 400){
				codeError.add(regLog.get(i));
			} else {
				codeOk.add(regLog.get(i));
			}
		}
		
		request.setAttribute("errorList", codeError);
		request.setAttribute("okList", codeOk);
		
		request.getRequestDispatcher("HTTPreport.jsp").forward(request, response);
	}
	
}
