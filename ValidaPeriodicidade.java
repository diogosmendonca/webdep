package br.cefetrj.webdep.view.command;

import javax.servlet.ServletException;
import br.cefetrj.webdep.services.SistemaServices;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


public class ValidaPeriodicidade implements Command{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String periodicidade = req.getParameter("periodicidade"), msg="";
		
		boolean periodicidadeInvalida = false;
		boolean periocidicadeValido = true;
		boolean periocidicadeVazio = false;
		
		if(periodicidade == null || periodicidade.equals("")) 
		{
			periocidicadeVazio = true;
			periodicidadeInvalida = true;
			periocidicadeValido = false;
			msg += " Periodicidade não pode ser um campo vazio ";
		}
		
		if(!(soContemNumeros(periodicidade)))
		{
			msg += " Não pode conter letras no campo periodicidade ";
		}
		
		if(periodicidadeInvalida)
		{
			req.setAttribute("periodicidade", periodicidadeInvalida);
		}
		else
		{
			req.setAttribute("periodicidade", periodicidade);
		}
	}
	
	public static boolean soContemNumeros(String period) 
	{
        if(period == null)
            return false;
        for (char letra : period.toCharArray())
            if(letra < '0' || letra > '9')
                return false;
        return true;
         
    }
}
