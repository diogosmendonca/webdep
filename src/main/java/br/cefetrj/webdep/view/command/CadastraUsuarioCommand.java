package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.cefetrj.webdep.model.entity.Permissao;
import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.model.entity.Usuario;
import br.cefetrj.webdep.services.SistemaServices;
import br.cefetrj.webdep.services.UsuarioServices;

/**
 * Classe responsavel por cadastrar usuarios no sistema.
 * 
 * @author Lawrence Fernandes
 * @author Iury
 * @author Leonardo
 * @version 0.1
 * @since   22-11-2016 
 */

public class CadastraUsuarioCommand implements Command {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		new SistemaServices();
		List<Sistema> sis = SistemaServices.listarTodos();
		int cont = 0;
		if(sis == null && cont == 0){
			sis = SistemaServices.listarTodos();
			cont++;
		}
		request.setAttribute("cadsistemas", sis);
		request.getRequestDispatcher("cadastraUsuario.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

		String senha1;
		if(request.getParameter("senha") != null){
			senha1 =  request.getParameter("senha");
		}else{
			senha1 = "";
		}
		
		String senha2;
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
				sis = SistemaServices.obterPorId(Long.valueOf(var));
				sistema.add(sis);
			}
		}else{
			Sistema sis = new Sistema();
			sistema.add(sis);
		}
		
		Boolean nomeValido = true, loginValido = true, emailValido = true, senhaValido1 = true, senhaValido2 = true;
		
		if(nome.trim().length() == 0 || nome.trim().length() > 100){
			nomeValido = false;
			request.setAttribute("nomeValido", nomeValido);
		}
		if(login.trim().length() == 0 || login.trim().length() > 50){
			loginValido = false;
			request.setAttribute("loginValido", loginValido);
		}
		if(senha2.trim().length() == 0 || senha2.trim().length() > 64){
			senhaValido2 = false;
			request.setAttribute("senhaValido2", senhaValido2);
		}
		if(senha1.trim().length() == 0 || senha1.trim().length() > 64){
			senhaValido1 = false;
			request.setAttribute("senhaValido1", senhaValido1);
		}
		if(email.trim().length() == 0 || email.trim().length() > 100){
			emailValido = false;
			request.setAttribute("emailValido", emailValido);
		}
		
		senha1 = AutenticaUsuarioCommand.sha512(senha1);
		senha2 = AutenticaUsuarioCommand.sha512(senha2);
		
		if(!senha1.equals(senha2)){
			senhaValido2 = false;
			request.setAttribute("senhaValido2", senhaValido2);
		}
		
		String regex = "[A-Za-z0-9\\._-]+@[A-Za-z]+\\.[A-Za-z]+";
		if(!email.matches(regex)){
			emailValido = false;
			request.setAttribute("emailValido", emailValido);
		}
		
		String rLogin = null, rEmail = null;
		Usuario urLogin = null, urEmail = null;
		
		urLogin = UsuarioServices.validarLogin(login);
		urEmail = UsuarioServices.validarEmail(email);
		rLogin = urLogin != null? urLogin.getLogin(): null;
		rEmail = urEmail != null? urEmail.getEmail(): null;
		
		if(rLogin != null){
			loginValido = false;
			request.setAttribute("loginValido2", loginValido);
		}
		if(rEmail != null){
			emailValido = false;
			request.setAttribute("emailValido2", emailValido);
		}
		
		if(nomeValido == false || emailValido == false || loginValido == false || senhaValido1 == false || senhaValido2 == false){
			request.getRequestDispatcher("cadastraUsuario.jsp").forward(request, response);
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
				//permissao.setPerfil(usu.getPerfil());
				permisseos.add(permissao);
			}
			usu.setPermissoes(permisseos);
			UsuarioServices.salvar(usu);
			
			for (Permissao permissao : permisseos) {
				UsuarioServices.salvarPermissao(permissao);
			}
			
			//Usuario getusu = new UsuarioServices().validarEmail(usu.getEmail());
			/*for (Sistema sis : sistema) {
				UsuarioServices use = new UsuarioServices();
				Permissao p = use.getLastPermission();
				UsuarioServices uservice = new UsuarioServices();
				uservice.ForceSavePermission((p.getId() + 1), getusu, sis);
			}*/			
			
			request.getRequestDispatcher("FrontControllerServlet?action=listaUsuario&get=true").forward(request, response);	
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
