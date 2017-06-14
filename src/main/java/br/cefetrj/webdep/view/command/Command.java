package br.cefetrj.webdep.view.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface Command. Parte da implementação do padrão de projeto Command.
 *  
 * @author diogo
 * @since 0.1
 */
public interface Command {
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException; 

}
