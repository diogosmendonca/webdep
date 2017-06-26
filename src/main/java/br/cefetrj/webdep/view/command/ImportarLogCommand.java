package br.cefetrj.webdep.view.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.dao.SistemaDAO;
import br.cefetrj.webdep.model.entity.FormatoLog;
import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.services.LogAcessoServices;
//import br.cefetrj.webdep.services.LogServices;
import br.cefetrj.webdep.services.SistemaServices;

import java.io.File;
import java.io.IOException;

/**
 * Created by renatoor on 11/25/16.
 */
public class ImportarLogCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sistema = request.getParameter("sistema");
        String servidor = request.getParameter("servidor");
        String formatoLog = request.getParameter("log");
        String logAcesso = request.getParameter("logAcesso");
        String logErro = request.getParameter("logErro");

        request.setAttribute("sistema", sistema);
        request.setAttribute("servidor", servidor);
        request.setAttribute("log", formatoLog);
        request.setAttribute("logAcesso", logAcesso);
        request.setAttribute("logErro", logErro);       
        
        Sistema s = new Sistema();
        s = SistemaServices.obterPorId(Long.parseLong(sistema));
        
        FormatoLog f = new FormatoLog(); 
        f.setId(Long.parseLong(formatoLog));
        
        s.setFomatoLog(f);
        s.setPastaLogAcesso(logAcesso);
        s.setPastaLogErro(logErro);
        
                
        File dirLogAcesso, dirLogErro;
        boolean erro = false;
             
        if(logAcesso.equals("")) {
            request.setAttribute("logAcessoVazio", "1");
            erro = true;
        }

        if(logErro.equals("")) {
            request.setAttribute("logErroVazio", "1");
            erro = true;
        }

        dirLogAcesso = new File(logAcesso);
        dirLogErro = new File(logErro);

        if(dirLogAcesso.isDirectory() && dirLogErro.isDirectory() && !(erro)) {
        	
        	System.out.println(s.getFomatoLog());	
        	System.out.println(s.getPastaLogAcesso());
        	System.out.println(s.getPastaLogErro());
        	
        	
        	LogAcessoServices.ImportarLogAcesso(s);
        	
            request.setAttribute("logAdicionado", "1");
        }
        else {
            request.setAttribute("testarAcesso", "1");
        }

        if(erro) {
            request.setAttribute("erro", "1");
           
        }
        request.getRequestDispatcher("/importarLog.jsp").forward(request, response);
    }
}