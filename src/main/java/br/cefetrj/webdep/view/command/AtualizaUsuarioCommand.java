package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.entity.Permissao;
import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.model.entity.Usuario;
import br.cefetrj.webdep.services.SistemaServices;
import br.cefetrj.webdep.services.UsuarioServices;

/**
 * Classe responsavel por alterar dados de usuarios cadastrados no sistema.
 *  
 * @author Lawrence Fernandes
 * @author Iury
 * @author Leonardo
 * @version 0.1
 * @since   22-11-2016 
 */

public class AtualizaUsuarioCommand implements Command {
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = null;
		if(request.getParameter("id") != null){
			try{
				id = Long.valueOf(request.getParameter("id"));
			}catch (Exception e) {
				e.printStackTrace();
			}			
		}
		
		if(id != null){
			List<Sistema> sis = new SistemaServices().listarTodos();
			request.setAttribute("sistemas", sis);
			
			UsuarioServices usuSe = new UsuarioServices();			
			Usuario usu = null;
			usu = usuSe.obterPorId(id);
			List<Permissao> lp = usu.getPermissoes();
			String valor = null;
			for (Permissao permissao : lp) {
				valor = (permissao.getSistema().getId() + ";");
			}
			
			if(usu != null){
				request.setAttribute("AlterErroUsu", false);
				request.setAttribute("usuario", usu);	
				request.setAttribute("sis", valor);				
				request.getRequestDispatcher("alteraUsuario.jsp").forward(request, response);	
			}else{
				request.setAttribute("AlterErroUsu", true);
				request.getRequestDispatcher("alteraUsuario.jsp").forward(request, response);
			}
			
		}else{
			request.setAttribute("AlterErroUsu", true);
			request.getRequestDispatcher("alteraUsuario.jsp").forward(request, response);	
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = null;
		if(request.getParameter("id") != null){
			try{
				id = Integer.parseInt(request.getParameter("id"));
			}catch (Exception e) {
				e.printStackTrace();
			}			
		}
		
		final String nome;
		if(request.getParameter("nome") != null){
			nome = request.getParameter("nome");
		}else{
			nome = "";
		}

		final String login;
		if(request.getParameter("login") != null){
			login = request.getParameter("login");
		}else{
			login = "";
		}
		
		final String email;
		if(request.getParameter("email") != null){
			email = request.getParameter("email");
		}else{
			email = "";
		}

		final String senha1;
		if(request.getParameter("senha") != null){
			senha1 = request.getParameter("senha");
		}else{
			senha1 = "";
		}
		
		final String senha2;
		if(request.getParameter("senha2") != null){
			senha2 = request.getParameter("senha2");
		}else{
			senha2 = "";
		}
		
		final String perfil;
		if(request.getParameter("perfil") != null){
			perfil = request.getParameter("perfil");
		}else{
			perfil = "";
		}
		
		List<Sistema> sistema = new ArrayList<Sistema>();
		if(request.getParameterValues("sistema") != null){
			String string[] = request.getParameterValues("sistema");
			for (String var : string) {
				Sistema sis = new Sistema();
				SistemaServices sisSe = new SistemaServices();
				sis = sisSe.obterPorId(Long.valueOf(var));
				sistema.add(sis);
			}
		}else{
			Sistema sis = new Sistema();
			sistema.add(sis);
		}		
		
		Boolean nomeValido = true, loginValido = true, emailValido = true, senhaValido1 = true, senhaValido2 = true;
		
		if(nome.trim().length() == 0){
			nomeValido = false;
			request.setAttribute("nomeValido", nomeValido);
		}
		if(login.trim().length() == 0){
			loginValido = false;
			request.setAttribute("loginValido", loginValido);
		}
		if(senha2.trim().length() == 0){
			senhaValido2 = false;
			request.setAttribute("senhaValido2", senhaValido2);
		}
		if(senha1.trim().length() == 0){
			senhaValido1 = false;
			request.setAttribute("senhaValido1", senhaValido1);
		}
		if(email.trim().length() == 0){
			emailValido = false;
			request.setAttribute("emailValido", emailValido);
		}
		
		if(!senha1.equals(senha2)){
			senhaValido2 = false;
			request.setAttribute("senhaValido2", senhaValido2);
		}
		
		String regex = "[A-Za-z0-9\\._-]+@[A-Za-z]+\\.[A-Za-z]+";
		if(!email.matches(regex)){
			emailValido = false;
			request.setAttribute("emailValido", emailValido);
		}
		UsuarioServices usuSevice = new UsuarioServices();
		 
		String rLogin = null, rEmail = null, auxEmail = null , auxLogin = null ;
		Usuario urLogin = null, urEmail = null, urAux = null;
		
		urAux = usuSevice.obterPorId(Long.valueOf(id));
		urLogin = usuSevice.validarLogin(login);
		urEmail = usuSevice.validarEmail(email);
		rLogin = urLogin != null? urLogin.getLogin(): null;
		auxLogin = urAux != null? urAux.getLogin(): null;
		auxEmail = urAux != null? urAux.getEmail(): null;
		rEmail = urEmail != null? urEmail.getEmail(): null;
		
		if(rLogin != null){
			if(!auxLogin.equals(login)){
				loginValido = false;
				request.setAttribute("loginValido", loginValido);
			}
		}
		if(rEmail != null){
			if(!auxEmail.equals(email)){
				emailValido = false;
				request.setAttribute("emailValido", emailValido);
			}
		}
		
		
							
		if(nomeValido == false || emailValido == false || loginValido == false || senhaValido1 == false || senhaValido2 == false || id == null){
			request.getRequestDispatcher("alteraUsuario.jsp").forward(request, response);
		}else{
			
			Usuario usu = new Usuario();
			usu.setNome(nome);
			usu.setEmail(email);
			usu.setLogin(login);
			usu.setSenha(senha1);
			usu.setPerfil(perfil);
			usu.setAdmGeral(false);
			
			List<Permissao> permisseos = new ArrayList<Permissao>();
			for (Sistema sis : sistema) {
				Permissao permissao = new Permissao();
				permissao.setSistema(sis);
				permissao.setUsuario(usu);
				permisseos.add(permissao);
			}
			usu.setPermissoes(permisseos);
			
			UsuarioServices usuSe = new UsuarioServices();	
			usuSe.update(usu);
			request.getRequestDispatcher("home.jsp").forward(request, response);	
		}	
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final Boolean get;
		if(request.getParameter("get") != null){
			get = Boolean.valueOf(request.getParameter("get"));
		}else{
			get = false;
		}
		
		if(get == null || get == true){
			doGet(request, response);
		}else{
			doPost(request, response);
		}
	}

}
