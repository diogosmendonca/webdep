package br.cefetrj.webdep.view.command;

import br.cefetrj.webdep.model.entity.PadraoURL;
import br.cefetrj.webdep.model.entity.RegistroLogAcesso;
import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.services.LogAcessoServices;
import br.cefetrj.webdep.services.PadraoURLServices;
import br.cefetrj.webdep.services.RegistroLogAcessoService;
import br.cefetrj.webdep.services.SistemaServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenCodeSummaryCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        boolean erro = false;
           
        LocalDate dataI, dataF;
        LocalTime horaI, horaF;
        LocalDateTime ldtInicial, ldtFinal;

        String padraoUrl 			= request.getParameter("padraoUrl");
        String dataInicial 			= request.getParameter("dataInicial");
        String dataFinal 			= request.getParameter("dataFinal");
        String horaInicial			= "00:00:00";
        String horaFinal			= "00:00:00";   
         			
        request.setAttribute("dataInicial", dataInicial);
        request.setAttribute("dataFinal", dataFinal);
        request.setAttribute("horaInicial", horaInicial);
        request.setAttribute("horaFinal", horaFinal);

        //Valida formato de Data Incial e Final
        try {
            dataI = LocalDate.parse(dataInicial);
            dataF = LocalDate.parse(dataFinal);
        } catch (Exception e) {
            dataI = null;
            dataF = null;
            request.setAttribute("dataInvalida", "1");
            erro = true;
        }

        try{
            horaI = LocalTime.parse(horaInicial);
            horaF = LocalTime.parse(horaFinal);
        } catch (Exception e) {
            horaI = null;
            horaF = null;
            request.setAttribute("horaInvalida", "1");
            erro = true;
        }

        //Valida o id do sistema na sessão
  		Sistema sistema = null;
  		Long idSistema = null;
  		if(request.getSession().getAttribute("idsistema") != null){
  			idSistema = (Long)request.getSession().getAttribute("idsistema");
  			sistema = SistemaServices.obterPorId(idSistema);
  			if(sistema == null){
  				erro = true;
  				request.setAttribute("idSistemaEMsg", "1");
  			}
  		}  else {
  			erro = true;
  			request.setAttribute("idSistemaEMsg", "1");
  		}
        
  		//Valida padrão de URL
		PadraoURL padrao = null;
		if(padraoUrl != null && padraoUrl.trim().length() > 0){
			Long padraoIdLong = null; 
			try{
				padraoIdLong = Long.parseLong(padraoUrl);
				padrao = PadraoURLServices.obterPorId(padraoIdLong);
				if(padrao == null){
					erro = true;
					request.setAttribute("padraoUrlInvalido", "1");
				}
			}catch(Exception e){
				erro = true;
				request.setAttribute("padraoUrlInvalido", "1");
			}
		}else{
			erro = true;
			request.setAttribute("padraoUrlInvalido", "1");
		}
  		
        if(erro) {
            request.getRequestDispatcher("/summaryCode.jsp").forward(request, response);
        }

        ldtInicial = LocalDateTime.of(dataI, horaI);
        ldtFinal = LocalDateTime.of(dataF, horaF);

        
        /*
         * For debugging purposes
                
        System.out.println(sistema.getNome());
        System.out.println(padrao.getExpressaoRegular());
        */
        
        List<RegistroLogAcesso> bdLog = LogAcessoServices.buscaLogAcessoOrdenada(ldtInicial, ldtFinal, idSistema);       
        
        //bdLog = RegistroLogAcessoService.filterByPadraoURL(bdLog, padrao);
        
        /*
         *  Agrupa os registros resultantes da lista bdLog por codigo e conta as ocorrencias de cada um
         */
        Map<Integer, Integer> groupedLog = new HashMap();
	    Integer Total = bdLog.size();
	    Integer lastCode = 0;
	    Integer counter = 0;
	    
	    for (int i = 0; i < bdLog.size(); i++) {
	    	RegistroLogAcesso rla = (RegistroLogAcesso) bdLog.get(i);
	    	Integer currCode = rla.getCodigo();
	    	
	    	if(i == 0)
	    		lastCode = currCode;
	    	
	    	if(!(currCode.equals(lastCode))) {
	    		groupedLog.put(lastCode, counter);
	    		counter = 0;
	    	}	  
	    	
	    	counter++;
	    	
	    	if(i == bdLog.size()-1)
	    		groupedLog.put(currCode, counter);
	    	
	    	lastCode = currCode;	    	  	 
	    }
	    /*
	     * Fim agrupa       
	     */
	    
        if (bdLog.isEmpty()) {
            request.setAttribute("naoEncontrado", "1");
            request.getRequestDispatcher("/summaryCode.jsp").forward(request, response);
        }
        else {
            request.setAttribute("mapCodeAccess", groupedLog);
            request.setAttribute("accessTotals", Total);
            request.getRequestDispatcher("/summaryCode.jsp").forward(request, response);
        }
    }
}