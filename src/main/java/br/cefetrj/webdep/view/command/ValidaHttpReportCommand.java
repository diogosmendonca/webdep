package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.entity.PadraoURL;
import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.model.entity.Versao;
import br.cefetrj.webdep.services.PadraoURLServices;
import br.cefetrj.webdep.services.SistemaServices;
import br.cefetrj.webdep.services.VersionServices;


@WebServlet("/ValidaHttpReport")
public class ValidaHttpReportCommand implements Command {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		boolean versionValidate = true;
		boolean errorValidate = true;
		boolean okValidate = true;
		boolean systemValidate = true;
		boolean patternValidate = true;
		String urlPattern = request.getParameter("padraoURL");
		String[] versionList = request.getParameterValues("versionList");
		String[] errorList = request.getParameterValues("codigosHttpErro");
		String[] okList = request.getParameterValues("codigosHttpOk");
		
		//Valida sistema
		Sistema sistema = null;
		if(request.getSession().getAttribute("idsistema") != null){
			Long idSistema = (Long)request.getSession().getAttribute("idsistema");
			sistema = SistemaServices.obterPorId(idSistema);
			if(sistema == null){
				systemValidate = false;
				request.setAttribute("systemValidate", systemValidate);
			}
		}
		
		//Valida padrão de url
		PadraoURL padrao = null;
		if(urlPattern != null && urlPattern.trim().length() > 0){
			Long padraoIdLong = null; 
			try{
				padraoIdLong = Long.parseLong(urlPattern);
				padrao = PadraoURLServices.obterPorId(padraoIdLong);
				if(padrao == null){
					patternValidate = false;
					request.setAttribute("patternValidate", patternValidate);
				}
			}catch(Exception e){
				patternValidate = false;
				request.setAttribute("patternValidate", patternValidate);
			}
		}else{
			patternValidate = false;
			request.setAttribute("patternValidate", patternValidate);
		}
		
		//Valida versões
		if(versionList != null && versionList.length > 0){
			List<Versao> versaoLista = new ArrayList<Versao>();
			try{
				for(String v : versionList){
					Versao vInst = VersionServices.obterPorId(Long.parseLong(v));
					if(vInst != null){
						versaoLista.add(vInst);
					}
					else{
						versionValidate = false;
						request.setAttribute("versionValidate", versionValidate);
					}
				}
			} catch(Exception e){
				versionValidate = false;
				request.setAttribute("versionValidate", versionValidate);
			}
		} else {
			versionValidate = false;
			request.setAttribute("versionValidate", versionValidate);
		}
		
		//Valida códigos http ok
		List<Integer> codigosOk = new ArrayList<Integer>();
		if(okList != null && okList.length > 0){
			try{
				for (String c: okList) {
					codigosOk.add(Integer.parseInt(c));
				}
			}catch(Exception e){
				okValidate = false;
				request.setAttribute("okValidate", okValidate);
			}
		}else{
			okValidate = false;
			request.setAttribute("okValidate", okValidate);
		}
		
		//Valida códigos http erro
		List<Integer> codigosErro = new ArrayList<Integer>();
		if(errorList != null && errorList.length > 0){
			try{
				for (String c: errorList) {
					codigosErro.add(Integer.parseInt(c));
				}
			}catch(Exception e){
				errorValidate = false;
				request.setAttribute("errorValidate", errorValidate);
			}
		}else{
			errorValidate = false;
			request.setAttribute("errorValidate", errorValidate);
		}
		
		//Retornar para a jsp caso algum campo seja inválido
		if((versionValidate == false) || (errorValidate == false) || (okValidate = false)){
			request.getRequestDispatcher("HTTPreport.jsp").forward(request, response);
			return;
		}
		
		//Chamar o service para retornar os dados da query
	}

}