/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

	package br.cefetrj.webdep.view.servlet;

	import java.io.IOException;
	import java.io.PrintWriter;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.regex.Matcher;
	import java.util.regex.Pattern;
	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import javax.servlet.http.HttpSession;

	import br.cefetrj.webdep.model.dao.GenericDAO;
	import br.cefetrj.webdep.model.dao.PersistenceManager;
	import br.cefetrj.webdep.model.entity.PadraoURL;
	import br.cefetrj.webdep.model.entity.RegistroLogAcesso;
	import br.cefetrj.webdep.model.entity.Servidor;
	import br.cefetrj.webdep.model.entity.Sistema;
	import br.cefetrj.webdep.model.entity.Usuario;

	/**
	 *
	 * @author Rian
	 */
	@WebServlet("/CadastroSistema")
	public class CadastroSistema extends HttpServlet {

		private static final long serialVersionUID = 1L;

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    }
	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    	
	    	PrintWriter pw = response.getWriter();
	        HttpSession session = request.getSession();  
	        Long usuario_id;
	        Usuario usuarioLogado = new Usuario();
	    	usuarioLogado.setId(0L);
	        PersistenceManager pm = PersistenceManager.getInstance(); 
	        //indicando a tabela pra sistemas no banco
	    	GenericDAO<Sistema> sistemaDAO = pm.createGenericDAO(Sistema.class);

	    	List<Sistema> usuarios = sistemaDAO.listAll();
	          
	        String nome = request.getParameter("nome");
	        String server = request.getParameter("servidor");
	        String fmtLogs = request.getParameter("formatoLogs");
	        String data = request.getParameter("data");
	        String hora = request.getParameter("hora");
	        String nova = request.getParameter("nova");
	        		
	        		String mensagem = "";
	            	try{
	            		Sistema sistem = new Sistema();
	                    /* Essa parte está incompleta. Faltam alguns atributos na classe Sistema 
	                     * e é necessário criar um cálculo para mostrar a periodicidade
	                     * */
	            		sistem.setNome(nome);
	                    //sistem.setServidor(server);
	                    //sistem.setPeriodicidadeLeitura(); // 
	                    //sistem.setNovaLeitura();
	                    
	                    sistemaDAO.insert(sistem); //insere no banco
	                    mensagem = "Sistema cadastrado com sucesso!";
	            	}catch(Exception e){
	            		mensagem = "Não foi possível cadastrar o sistema!";
	            	}finally{
	            		String json = "{\"mensagem\": \""+ mensagem +"\"]}";
	                    pw.write(json);
	            	}
	            }

	}


