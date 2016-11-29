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
import br.cefetrj.webdep.model.entity.Usuario;
import br.cefetrj.webdep.services.RegistroLogAcessoService;
import br.cefetrj.webdep.services.SistemaServices;
import br.cefetrj.webdep.services.UsuarioServices;

public class RegexPadraoURLCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String json = "";
        PrintWriter pw = response.getWriter();
        HttpSession session = request.getSession();
        Usuario usuarioLogado = new Usuario();
        Long sistema_id = 1L;
        String mensagem;
        if (session != null) {
        	usuarioLogado = (Usuario)session.getAttribute("usuario");
        }
        try{
            Pattern p = Pattern.compile(request.getParameter("regex"));
            List<String> URLsRegex = new ArrayList<String>();
            List<RegistroLogAcesso> registrosLogAcessoPermitidos = RegistroLogAcessoService.listAllRegisters();
            for (RegistroLogAcesso registroLogAcesso: registrosLogAcessoPermitidos){
            	String url = registroLogAcesso.getUrl();
            	Matcher m = p.matcher(url);
            	if (m.matches()) URLsRegex.add(url);
        	}
            //Transformar as URLs filtradas em JSON pra mandar de volta 
            //pra página
            json = "{\"url\": [";
            for (String url: URLsRegex){
                json += "\"";
                json += url; //talvez tenha que add um "/" no inicio
                json += "\", ";
            }
            json += "]}";
            json = json.replace(",]}", "]}");
            pw.write(json);
        }catch (Exception e){
        	mensagem = "Não foi possível completar a ação";
        	json = "{\"Erro\": \"" + mensagem + "\"}";
        	e.printStackTrace();
        }finally{
        	pw.write(json);
        }
	}
}
