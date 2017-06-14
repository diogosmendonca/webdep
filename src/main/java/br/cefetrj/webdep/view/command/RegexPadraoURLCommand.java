package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.cefetrj.webdep.model.entity.RegistroLogAcesso;
import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.services.SistemaServices;

public class RegexPadraoURLCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String json = "";
        PrintWriter pw = response.getWriter();
        HttpSession session = request.getSession();
        String lang = (String)session.getAttribute("lang");
        Sistema sistemaSession = null;
        String mensagem;
        if (session != null) {
        	Long idsistema = (Long)session.getAttribute("idsistema");
        	sistemaSession = SistemaServices.obterPorId(idsistema);
        }
        try{
            Pattern p = Pattern.compile(request.getParameter("regex"));
            List<String> URLsRegex = new ArrayList<String>();
            List<RegistroLogAcesso> registrosLogAcessoPermitidos = sistemaSession.getAcessos();
            for (RegistroLogAcesso registroLogAcesso: registrosLogAcessoPermitidos){
            	String url = registroLogAcesso.getUrl();
            	Matcher m = p.matcher(url);
            	if (m.find()) URLsRegex.add(url);
        	}
            json = "{\"url\":[";
            for (String url: URLsRegex){
                json += "\"";
                json += url; //talvez tenha que add um "/" no inicio
                json += "\",";
            }
            json += "]}";
            json = json.replace(",]}","]}");
        }catch (java.util.regex.PatternSyntaxException e){
        	switch (lang){
	        	case "en_US":
	        		mensagem = "Could not complete action. Invalid Regular Expression.";
	        		
	        		break;
	        		
	        	case "pt_BR":
	        		mensagem = "Não foi possível completar a ação. Expressão Regular inválida.";
	        		break;
	        	default: 
	    			mensagem = "Não foi possível completar a ação. Expressão Regular inválida.";
	    			break;
        	}
        	json = "{\"Erro\": \"" + mensagem + "\"}";
        	e.printStackTrace();
        }catch (NullPointerException e){
        	switch (lang){
	        	case "en_US":
	        		mensagem = "Could not complete action. Select a system above";
	        		
	        		break;
	        		
	        	case "pt_BR":
	        		mensagem = "Não foi possível completar a ação. Selecione um Sistema acima.";
	        		break;
	        	default: 
	    			mensagem = "Não foi possível completar a ação. Selecione um Sistema acima.";
	    			break;
        	}
        	json = "{\"Erro\": \"" + mensagem + "\"}";
        	e.printStackTrace();
        }catch (Exception e){
        	switch (lang){
	        	case "en_US":
	        		mensagem = "Could not complete action.";
	        		break;
	        		
	        	case "pt_BR":
	        		mensagem = "Não foi possível completar a ação.";
	        		break;
	        	default: 
	    			mensagem = "Não foi possível completar a ação.";
	    			break;
        	}
        	json = "{\"Erro\": \"" + mensagem + "\"}";
        	e.printStackTrace();
        }finally{
        	pw.write(json);
        }
	}
}
