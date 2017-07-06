package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.entity.EmailNotificacao;
import br.cefetrj.webdep.model.entity.RegistroLogAcesso;
import br.cefetrj.webdep.services.EmailNotificacaoServices;

public class EditarEmailNotificacaoCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		EmailNotificacao emailNotificacao = EmailNotificacaoServices.obterPorId(id);
		
/*		List<String> idFalha = new ArrayList<String>();
		List<RegistroLogAcesso> lregLogAcesso = emailNotificacao.getCodigosFalha();
		for (RegistroLogAcesso item : lregLogAcesso) {
			Integer codigo = item.getCodigo();
			
			System.out.println(codigo.toString());
			idFalha.add(new String(codigo.toString()));
		}*/
		
		List<String> idFalha = new ArrayList();
		
		System.out.println();
		Integer idUrls = new Integer(emailNotificacao.getURL().getId().toString());
		Integer idSistema = new Integer(emailNotificacao.getURL().getId().toString());
		String emails = emailNotificacao.getEmail();

		request.setAttribute("idFalha", idFalha);
		request.setAttribute("idUrls", idUrls);
		request.setAttribute("idSistema", idSistema);
		request.setAttribute("emails", emails);
		
		request.setAttribute("emailNotificacao", emailNotificacao);				
		request.getRequestDispatcher("editaEmailNotificacao.jsp").forward(request, response);	
	}

}
