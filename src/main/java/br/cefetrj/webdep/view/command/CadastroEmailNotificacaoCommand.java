package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.entity.EmailNotificacao;
import br.cefetrj.webdep.model.entity.PadraoURL;
import br.cefetrj.webdep.model.entity.RegistroLogAcesso;
import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.services.EmailNotificacaoServices;

/**
 * Servlet implementation class CadastroEmailCommand
 */
public class CadastroEmailNotificacaoCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String codigosHttpErro = request.getParameter("codigosHttpErro");
		String padraoUrl = request.getParameter("padraoUrl");
		String sistema = request.getParameter("sistema");
		String emails = request.getParameter("emails").trim();
		
		boolean formValido = true;
		
		if( codigosHttpErro == null ){
			request.setAttribute("keyModelo", "br.cefetrj.webdep.jsp.manteremail.erro.codigoshttperro");
			request.setAttribute("codigosHttpErroStatus", "has-error");
			formValido = false;
		}
		
		if(padraoUrl == null){
			request.setAttribute("keyMarca", "br.cefetrj.webdep.jsp.manteremail.erro.padraourl");
			request.setAttribute("padraoUrlStatus", "has-error");
			formValido = false;
		}
		
		if( sistema == null ){
			request.setAttribute("keyModelo", "br.cefetrj.webdep.jsp.manteremail.erro.sistema");
			request.setAttribute("sistemaStatus", "has-error");
			formValido = false;
		}
		
		if( emails == null || emails.trim().length() > 1000 ){
			request.setAttribute("keyModelo", "br.cefetrj.webdep.jsp.manteremail.erro.emails");
			request.setAttribute("sistemaStatus", "has-error");
			formValido = false;
		}
		
		//Formulário inválido
		if(formValido == false){
			request.getRequestDispatcher("cadastroEmailNotificacao.jsp").forward(request, response);
			return;
		}
				
		EmailNotificacao rn = new EmailNotificacao();
		
		Sistema s = new Sistema();
		s.setId(new Long(sistema));
				
		PadraoURL pu = new PadraoURL();
		pu.setId(new Long(padraoUrl));
		
		List<RegistroLogAcesso> listErro = new ArrayList<RegistroLogAcesso>();
		RegistroLogAcesso regLogAcesso = new RegistroLogAcesso();
		regLogAcesso.setId(2L);
		listErro.add(regLogAcesso);
		
		
		rn.setEmail(emails);
		rn.setSistema(s);
		rn.setURL(pu);
		rn.setCodigosFalha(listErro);
		
		EmailNotificacaoServices.create(rn);
		
		request.getRequestDispatcher("cadastroEmailNotificacao.jsp").forward(request, response);
		
	}

}
