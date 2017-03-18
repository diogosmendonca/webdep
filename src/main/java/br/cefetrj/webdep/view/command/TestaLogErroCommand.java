package br.cefetrj.webdep.view.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class TestaLogErroCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resposta = "";
        HttpSession session = request.getSession();
        String lang = (String)session.getAttribute("lang");
        String ptLogs = request.getParameter("ptLogs2");
        String pxLogs = request.getParameter("pxLogs2");
        String pathLog = ptLogs+pxLogs;

        if(!(pathLog.equals(""))) { //sempre ser� cheio pois a valida��o est� em JS
            File file = new File(pathLog);
            String mensagem = pathLog.replace("\\", "\\\\");
            if(file.isFile())
            	switch (lang){
		        	case "en_US":
		        		resposta = "{\"mensagem\":\"The path '" + mensagem + "' exists!\" }";
		        		break;
		        		
		        	case "pt_BR":
		        		resposta = "{\"mensagem\":\"O caminho '" + mensagem + "' existe!\" }";
		        		break;
		        	default: 
		        		resposta = "{\"mensagem\":\"O caminho '" + mensagem + "' existe!\" }";
		    			break;
	        	}
            else
            	switch (lang){
		        	case "en_US":
		        		resposta = "{\"mensagem\":\"The path '" + mensagem + "' does not exists!\" }";
		        		break;
		        		
		        	case "pt_BR":
		        		resposta = "{\"mensagem\":\"O caminho '" + mensagem + "' não existe!\" }";
		        		break;
		        	default: 
		        		resposta = "{\"mensagem\":\"O caminho '" + mensagem + "' não existe!\" }";
		    			break;
	        	}
        }

        PrintWriter pw = response.getWriter();
        response.setCharacterEncoding("UTF-8");
        pw.write(resposta);
    }
}
